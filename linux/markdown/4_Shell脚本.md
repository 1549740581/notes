## Shell脚本

### 1.1 hello world
- 创建一个helloworld.sh文件：
```shell
!/bin/bash
echo "hello world"
```
- 执行该文件两种方式：
    - 给该文件执行权限：chmod a+x helloworld.sh，然后./helloworld.sh
    - 使用sh命令：sh helloworld.sh
    
### 1.2 变量
**变量分类**：

Shell中变量分为两类：系统变量和用户自定义变量：
- 系统变量：例如HOME，PWD，USER等等，直接使用echo $XXX即可获取
- 用户自定义变量：
    - 定义变量：变量名=值
    - 撤销变量：unset 变量名
    - 声明静态变量：readonly 变量，注意静态变量不能使用unset撤销
- 变量的获取使用：$符号，例如$PWD，$variable_name    
- 把一个变量提升为环境变量：使用**export**，例如export NAME="sherman":$PATH
```shell
#!/bin/bash
a=10
echo "a=$a"
unset a
readonly A=100
echo "A=$A"
# wrong!
unset A
```

**定义变量的规则**：
- 变量名称由字母、数字、下划线组成，但是不能以数字开头
- 等号两边**不能**有空格
- 变量名称一般习惯大写

**将命令的返回值赋值给变量**：
- 使用反引号将命令结果包括在内：A=\`ll /home/sherman`
- 使用$()将命令结果包括在内：A=$(date)
```shell
#!/bin/bash
MY_DATE1=`date`
MY_DATE2=$(date)
echo "MY_DATE1=$MY_DATE1"
echo "MY_DATE2=$MY_DATE2"
```

**环境变量**：
- 在/etc/profile或者在普通用户/home/sherman/.bashrc中编写环境变量
```shell
export TOMCAT_HOME=/opt/open_source/apache-tomcat-8.5.49
```
- 激活对应文件或者注销、重启：source /etc/profile
- 在terminal、或者shell脚本中使用环境变量：echo $TOMCAT_HOME

**位置参数变量**：
当执行一个Shell脚本时，希望获取命令行参数信息，就可以使用到位置参数变量，例如：
```shell
./helloworld.sh 100 200
```
其中脚本文件名./helloworld以及100、200就是命令行的位置参数，现在需要获取这些参数。

基本语法：

- $n：n表示数字，$0表示命令本身，$1表示第一个参数（100），$2表示第二个参数（200），注意如果n>=10，需要使用大括号，例如${10}
- $*：命令行中所有参数，注意它是把这些参数看成一个整体，在使用""的for循环中有体现
- $@：命令行中所有参数，注意它十把每个参数区分对待，在使用""的for循环中有体现
- $#：命令行中参数的个数，不包括命令本身
```shell
#!/bin/bash
:<<!
多行注释1
多行注释2
...
多行注释n
!
echo $0 $1 $2
echo $*
echo $@
echo $#

./script.sh 100 200 300
# 输出
./script.sh 100 200
100 200 300
100 200 300
3
```

**预定义变量**：
Shell设计者已经定义好的变量，直接就可以在shell脚本中使用：
- $$：当前进程的pid
- $!：后台运行最后一个进程的pid
- $?：最后一次执行命令的返回状态，0表示正常返回，非0表示错误

### 1.3 运算符
- 使用$((运算式))或者$\[运算式]，注意$((运算式))形式最外层要多一层()，推荐使用$\[运算式]形式
- expr m 运算符 n：注意这里运算符两边需要空格
```shell
#!/bin/bash
var1=$1
var2=$2
var3=$3
# 多一层()
result1=$((($var1+$var2)*($var3)))
# 推荐方式
result2=$[($var1+$var2)*($var3)]
# expr结果使用``包围
tmp=`expr $var1 + $var2`
# 注意下面的*需要转义
result3=`expr $tmp \* $var3`
echo $result1
echo $result2
echo $result3
```

### 1.4 流程控制
**条件判断**：
基本语法：\[ condition ]：注意condition前后有空格，返回结果可以使用 **&?** 判断，返回0表示true，否则false。

- =：字符串比较
- 两个整数之间比较：
    - -lt：小于
    - -le：小于等于
    - -eq：等于
    - -gt：大于
    - -ge：大于等于
    - -ne：不等于
- 按照文件权限判断：
    - -r：可读文件
    - -w：可写文件
    - -x：可执行文件
- 按照文件类型判断：
    - -f：文件存在并且是一个常规文件
    - -e：文件存在
    - -d：文件存在并且是一个目录

示例：
```shell
#!/bin/bash
# 判断x-sheran和sherman是否相等
if [ "x-sherman" = "sherman" ]
then 
	echo "equals"
else 
	echo "not equals"
fi
# 判断100是否大于99
if [ 100 -gt 99 ]
then
	echo "yes"
else
	echo "no"
fi
# 判断/home/sherman/tmp是否存在helloworld.sh文件
if [ -e /home/sherman/tmp/helloworld.sh ]
then
	echo "exists"
else 
	echo "not exists"
fi
```

**流程控制**：
- if：条件判断基本形式：
    - if-then-else-fi：
    ```shell
    if [ condition ]
    then 
        expression1
    else
        expression2
    fi
    ```
    - if-then-elif-then-fi:
    ```shell
    if [ condition1 ]
    then 
        expression1
    elif [ condition2 ]
    then 
        expression2
    fi
    ```
- case：基本句法：
```shell
case $variable_name in
    "value1")
        expression1
    ;;
    "value2")
        expression2
    ;;
    *)
        上面都不匹配则执行这里
    ;;
esac
```
示例：
```shell
#!/bin/bash
value=$1
case $value in
"1")
	echo "1"
;;
"2")
	echo "2"
;;
"3")	
	echo "3"
;;
*)
	echo "other"
esac
```
- for循环：两种语法：
    - 语法1：
    ```shell
    for value in v1 v2 v3...
    do
        expression
    done
    ```
    - 语法2：注意外层多一对()
    for((init_value;condition;change))
    do
        expression
    done
示例1：并结合上面 **$\*** 和 **$@**区别：
```shell
#/bin/bash
echo "没有引号情况下："
for val in $*
do
	echo $val
done
echo "============"
for val in $@
do 	
	echo $val
done

echo "有引号情况下："
for val in "$*"
do
	echo $val
done

echo "============"
for val in "$@"
do 	
	echo $val
done
```

案例2：累加1-100的值：
```shell
#!/bin/bash
sum=0
# 注意有两层()，且不能写成++i
for ((i=1;i<=100;i++))
do 
	sum=$[$sum + $i]
done
echo $sum
```

- while循环：
语法：
```shell
while [ condition ]
do
    expression
done
```
示例：
```shell
#!/bin/bash
sum=$1
i=$2
end=$3
while [ $i -le $end ]
do
	sum=$[$sum + $i]
	i=$[$i + 1]
done
echo $sum
```

### 1.5 读取控制台内容
read命令就可以完成从控制台读取内容，并且还可以限制时间和给出提示：
- -p：指定读取时的提示符
- -t：指定读取值的等待时间（秒），如果指定时间内没有输入，不再等待

示例：5秒内读取一个值给val，并输出val内容：
```shell
#!/bin/bash
read -p "请输入一个数字：" -t 5 val
echo $val
```

### 1.6 函数
Shell和其它语言一样也有函数的概念，有系统函数和自定义函数：
- 系统函数：这里只介绍啷个：basename & dirname
    - basename path \[suffix]：例如basename /home/sherman/tmp/foo.sh .sh -> foo
    - dirname path：例如dir /home/sherman/tmp/foo.sh -> /home/sherman/tmp
    - 总之：dirname path + / + basename path + suffix 拼接为 path
- 自定义函数：基本语法
```shell
[ function ] function_name[()]
{
    expression
    [return statement]
}
```
示例:
```shell
function get_sum
{
    sum=$[$val1 + $val2]
    return $sum
}

$(get_sum $val1 $val2)
# $?获取最后一次调用返回值
echo "res: " $?
```

案例：

创建一个自动备份数据库的脚本，每个一分钟对数据库进行一次备份，并且删除十分钟之前的备份数据：
- 新建一个：mysql_backup.sh
```
#!/bin/bash
BACKUP=/data/backup/db
#当前时间作为文件名
DATETIME=$(date +%Y_%m_%d_%H_%M_%S)
echo ============开始备份=============
#主机
HOST=localhost
#用户名
USER=root
#密码
PASSWORD=sherman
#备份数据库名称
DB_NAME=test_db

#创建备份路径
[ ! -d "$BACKUP/$DATETIME" ] && mkdir -p  "$BACKUP/$DATETIME"
#执行数据库备份指令，先放入到临时文件夹中
mysqldump -u${USER} -p${PASSWORD} --host=${HOST} $DB_NAME | gzip > $BACKUP/$DATETIME/DATETIME.sql.gz
cd $BACKUP
#将DATETIME整个文件夹压缩成tar.gz
tar -zcvf $DATETIME.tar.gz $DATETIME
#删除临时目录
rm -rf $BACKUP/$DATETIME
#删除10天前的备份文件
find $BACKUP -amin +10 -name "*.tar.gz" -exec rm -rf {} \;
echo ============备份完成=============
```
- 创建定时任务：1 * * * * /home/sherman/mysql_backup.sh