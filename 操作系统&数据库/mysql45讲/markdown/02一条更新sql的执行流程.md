和查询语句类似，一条update语句也会进行：连接器 -> 分析器 -> 优化器 -> 执行器操作，但是不会存在查询缓存，因为任何update操作都会使得查询缓存失效，这也是不推荐使用查询缓存的主要原因。另外update操作还涉及到两个重要的日志模块：redo log（重做日志）和binlog（归档日志）。

redo log和binlog最主要有三点不同：

- redo log是InnoDB引擎特有的日志模块，以插件方式集成进MySQL；binlog是MySQL Server层实现的，所有存储引擎都能够使用
- redo log是物理日志，纪录例如“在某个数据页上做了什么修改”；binlog是逻辑日志记录的是这个语句的原始逻辑，例如：“给ID=2的这行数据中字段count+1”
- redo log是循环写，主要涉及到 `checkpoint` 和 `write pos`两个指针的移动；binlog是追加写，binlog文件写到一定大小之后会切换到另一个，并不会覆盖之前的日志



## Redo log

Redo log主要涉及的技术是：WAL（Write Ahead Logging），它的关键点在于：先写日志，然后写磁盘。这样可以有效避免直接写磁盘时过多的磁盘IO问题。具体来说：当有一条update操作时，InnoDB引擎会把记录写到redo log中，并更新内存，这个时候就算更新完成。在合适的时候（例如磁盘空闲时），会将redo log中的记录全部更新到磁盘中。

InnoDB的redo log大小是固定的，但是可以自定义配置，例如配置为一组4个文件，每个文件大小为1GB，则redo log大小为4GB。从头开始写，写到redo log末尾就重新回到开头循环写：

- write pos：当前记录的位置，一边写一遍后移，写到id_logfile_3末尾时就回到开头位置
- checkout：当前需要移除的位置，也是向后推移并且循环的，移除数据之前需要把记录更新到数据文件中

<img src="./pics/redolog循环写.png" align=center style="zoom:50%"/>

InnoDB的redo log能够保证数据库的crash-safe能力，即即使数据重启，数据也不会丢失。



## Binlog

Binlog是MySQL Server层提供的日志模块，因此所有的存储引擎都能使用，binlog没有crash-safe能力。



## 两阶段提交

InnoDB存储引擎在执行简单的update操作时，会使用redo log和binlog完成两阶段提交工作：

- 执行器选取ID=2的这一行记录：如果这一行记录在内存中，直接返回给执行器，否则先从磁盘中读入到内存中，再返还给执行器
- 执行器拿到这条数据，将对应字段加1后得到新的一行数据，并引擎接口写入这条新数据
- 引擎将新数据更新到内存中，并记录到redo log中，此时redo log处于 `prepare` 阶段，然后告知执行器，可以准备提交了
- 执行器生成这个操作的binlog，并把binlog写入磁盘
- 执行器调用引擎提交事务接口，把刚刚redo log的状态由 `prepare` 改成 `commit` 状态

<img src="./pics/两阶段提交.png" align=center style="zoom:50%"/>

**为什么要进行两阶段提交？**

两阶段提交是为了让两份日志之间的逻辑一致，逻辑一致才能完成类似于：如何让数据库恢复到半个月之前的任意一秒状态？具体流程：

- 冷备份：找出最近的一次全量备份，从这个最近的全量备份开始恢复到临时库
- 热备份：从临时库的时间点开始，依次取出binlog的日志纪录，恢复到事故发生的那一时刻

如果不采用两阶段提交：假设update语句完成对字段cnt的加1操作，原来cnt值为0：

- 先执行redo log，再执行binlog：执行完redo log后，服务器立刻重启，导致binlog未写入。在进行恢复时，如果使用redo log恢复的cnt为1，如果使用binlog恢复的cnt为0，两个日志逻辑不一致
- 先执行binlog，再执行redo log：执行完binlog后，服务器立刻重启，导致redo log未写入。在进行恢复时，如果使用redo log恢复的cnt为0，如果使用binllog恢复的cnt为1，两个日志逻辑不一致

注意在实际环境中，虽然服务器正好在两阶段提交之间发生宕机的可能性较小，但是当需要使用扩容时，即增加服务器的的能力时，一般也是使用 **全量备份+binlog** 方法实现，这个也就必须使用两阶段提交，否则会出现主从数据库不一致现象。

## 总结

MySQL里面有两个重要的日志系统：redo log和binlog，redo log能够保证crash-safe能力，是InnoDB存储引擎提供的，参数 `innodb_flush_log_at_trx_commit` 建议设置为1，表示每次事物的redo log都直接持久化到磁盘中，这样可以保证MySQL宕机后数据不丢失。参数 `sync_binlog` 建议设置为1，表示每次事物的binlog都会持久化到磁盘中，这样可以保证MySQL宕机后binlog不丢失。

如果事物在提交阶段，服务器发生宕机，即redo log处于prepare阶段，binlog也已经接受该日志，此时发生宕机，恢复过程会认可这个事物。

redo log记录的是这个页面“做了什么改动”，binlog有了两种模式：`statement` 格式记录的是sql语句，`row` 格式记录行的内容，记录两条，更新前和更新后都会记录。

