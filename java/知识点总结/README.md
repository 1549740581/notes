## 类的初始化

类加载详细过程参考8.1小节：https://github.com/tanglei302wqy/notes/tree/master/java/jvm 

示例一：

```java
// output:
// Base01 ctor
// Base01 static
// Base01 ctor
// Test01 ctor
class Base01 {
    private static Base01 base01 = new Base01();

    static {
        System.out.println("Base01 static");
    }

    public Base01() {
        System.out.println("Base01 ctor");
    }
}

public class Test01 extends Base01 {
    public Test01() {
        System.out.println("Test01 ctor");
    }

    public static void main(String[] args) {
        Test01 mainTest = new Test01();
    }
}
```

示例二：

```java
// output:
// Base02 block
// Base02 static
// Base02 block
// Test02 ctor
class Base02 {
    // 第一次初始化类时，先执行该static赋值，会new一个Base01()产生实例变量
    // new实例变量时，只执行代码块和构造器初始化，不会进行static成员变量和static代码块初始化
    // 因为这两者在第一次类初始化已经完成了
    private static Base02 base02 = new Base02();

    static {
        System.out.println("Base02 static");
    }

    {
        System.out.println("Base02 block");
    }
}

public class Test02 extends Base02 {
    public Test02() {
        System.out.println("Test02 ctor");
    }

    public static void main(String[] args) {
        Test02 test = new Test02();
    }
}
```

实例三：

```java
// output:
// Base03 block
// Base03 ctor
// Base03 static block
// ----------
// Base03 block
// Base03 ctor
// Test03 ctor
class Base03 {
    public Base03() {
        System.out.println("Base03 ctor");
    }

    private static Base03 base03 = new Base03();

    static {
        System.out.println("Base03 static block");
    }

    {
        System.out.println("Base03 block");
    }
}

public class Test03 extends Base03 {
    public Test03() {
        System.out.println("Test03 ctor");
    }

    public static void main(String[] args) {
        System.out.println("----------");
        new Test03();
    }
}
```

其它更全面的测试示例可以参考[代码](https://github.com/tanglei302wqy/notes/blob/master/面试/面试第一季/src/main/java/nwpu/sherman/Question03.java)。



## try-finally

正常情况下，无论try里面执行了 **return** 、 **break** 还是 **continue** ，又或者是 **抛出异常** ，finally语句块还是会继续执行。除非显示调用System.exit(int)来终止它。



如果try块中有return，那么将会：

* 先将try块中的返回值保存到局部变量中
* 执行jsr指令跳转到finally块中执行
* finally块中执行完毕后，**返回之前保存在局部变量表中的值**



如果finally块中也有return语句：那么会在try块执行return之前跳转到finally块中执行，执行完毕后直接返回，**忽略try块中的return** 。

```java
public class TryFinally {
    public int method() {
        int a = 1;
        try {
            ++a;
            return a;
        } catch (Exception e) {
          
        } finally {
            System.out.println("执行finally");
            ++a;    // a自增为3，但是最终返回的还是try block中之前保存的值（2）
        }
        return a;
    }
    public static void main(String[] args) {
        TryFinally tf = new TryFinally();
        System.out.println(tf.method());
    }
}
// output: 
执行finally
2

*********************************************************************************
public int method02() {
    int a = 1;
    try {
        ++a;
        // int j = 1 / 0;
    } catch (Exception e) {

    } finally {
        System.out.println("执行finally");
        return ++a;
    }
}

@Test
public void test01() {
    System.out.println(method02());
}
// output:
执行finally
3
```



**注意try-finally对引用类型的修改:star:**

```java
class BeanTest {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BeanTest{" +
            "id=" + id +
            '}';
    }
}

public BeanTest method03() {
    BeanTest bt = new BeanTest();
    try {
        bt.setId(10);
        return bt;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // 这里对引用类型的修改是生效的
        bt.setId(100);
    }
    System.out.println("here");
    return bt;
}

@Test
public void test05() {
    System.out.println(method03());
}
// output: BeanTest{id=100}
```



## equals和==

基本类型的equals是比较内存地址，即使用==进行比较，引用类型看是否重写了equals方法，String、Integer、Date等类型都重写了equals方法，因此比较的是内容。如果没有重写，使用Object类中默认实现，即使用==进行比较。

注意：StringBuilder和StringBuffer没有重写equals方法，它们都是使用==进行比较，如果要比较内容，转换成String类型在调用equals方法。



## happens-before原则

编译器和处理器都存在重排序可能，如果让程序员去了解底层实现和具体规则，会大大加重程序员负担，影响并发编程开发效率。因此JMM使用happends-before原则向程序员和编译器、处理器做出两点承诺：

-   对程序员承诺：如果A happends-before B，那么JMM保证：A操作的结果对B操作是可见的，且A操作的执行顺序在B操作之前
-   对编译器、处理器承诺：只要不改变程序运行的执行结果（无论是单线程还是正确同步的多线程），编译器和处理器怎么优化都行。即程序员真正关心的是执行结果，至于怎么重排序并不关心。



happends-before具体规则：

-   顺序规则：单线程中，每个操作都happends-before后续的任意操作
-   锁规则：一个锁的解锁操作，happends-before随后对该锁的加锁操作
-   volatile规则：volatile变量的写操作，happends-before随后的读操作
-   传递规则：A happends-before B，B happends-before C，则A happends-before C
-   start()规则：线程A执行线程B的start()方法，则线程A的ThreadB.start()操作happends-before线程B中任意操作
-   join()规则：线程A执行线程B的join()并成功返回，那么线程B中任意操作都happends-before于线程A从ThreadB.join()操作的成功返回
-   中断规则：线程的interrupted()方法的调用happends-before于被中断线程的代码检测到中断时间的发生
-   finalize规则：对象的构造happends-before它的finalize()方法



## synchronized关键字优化

synchronized代码块使用 **monitorenter** 和 **monitorexit** 两个指令实现，synchronized方法使用 **ACC_SYNCHRONIZED** 访问标志来标识。



JDK1.6之后对synchronized做了很多优化，之前可以说synchronized是重量级做，JDK1.6之后synchronized的性能已经优化的很好了，例如JDK1.8的ConcurrentHashMap抛弃分段锁机制，使用synchronized和CAS来实现。



JDK1.6 对锁的实现引入了大量的优化，如偏向锁、轻量级锁、自旋锁、适应性自旋锁、锁消除、锁粗化等技术来减少锁操作的开销。

锁主要存在四种状态：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，它们会随着竞争的激烈而逐渐升级。注意锁可以升级不可降级，这种策略是为了提高获得锁和释放锁的效率。

### 偏向锁

偏向锁总是偏向第一个获取锁的线程，如果该线程释放锁到准备重新获取锁的期间，该锁并没有被其他线程获取，那么该线程就可以直接获取锁，不需要同步操作。

如果在锁竞争比较激烈的环境下，并不适合使用偏向锁，偏向锁申请失败后，不会直接膨胀为重量级锁，先会升级成轻量级锁。

### 轻量级锁

偏向锁获取失败时，并不会立即升级为重量级锁，而是先升级成轻量级锁进行优化。注意轻量级锁的目的不是为了替代重量级锁，而是在没有多线程竞争的条件下，减少传统重量级锁因为需要使用操作系统的互斥量而产生的性能消耗，因为轻量级锁的使用并不需要申请互斥量。

轻量级锁能够提升同步性能的经验依据：对于绝大数锁，在整个同步期间都是不存在竞争的。如果没有竞争，可以直接使用CAS操作来避免使用互斥操作的开销。但是如果存在竞争，除了需要使用互斥量外，还会发生CAS开销。因此如果在锁竞争激烈的情况下，轻量级锁比传统重量级锁效率更低，轻量级锁也会膨胀为重量级锁。

### 自旋锁和自适应自旋锁

轻量级锁失败后，JVM为了避免线程真实地在操作系统层面挂起，还会采用自旋锁进行优化。一般来说，线程持有锁的时间不会太长，为了这一点时间去挂起、恢复线程是得不偿失的。所以JVM会使用CAS忙自旋等待一会，然后尝试去获取锁。

在JDK1.6之后，自选锁时默认开启的（-XX:+UseSpinning），同时还引入了自适应自旋锁，即：自旋等待的时间不再是固定值，而是取决于前一次锁的自旋事件以及锁拥有者的状态来动态决定。

### 锁消除

JVM会检测出不可能存在竞争关系的共享数据，对这些锁进行消除，从而避免毫无意义的请求、释放锁事件。

### 锁粗化

一言而言，编写代码时应该要求锁的粒度更细，限制的范围更小。但是如果存在一个连续的操作都对同一个对象进行反复的加锁和解锁操作，那么会带来不必要的性能消耗，这个时候可以使用锁粗化技术。

