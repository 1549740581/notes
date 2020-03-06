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

