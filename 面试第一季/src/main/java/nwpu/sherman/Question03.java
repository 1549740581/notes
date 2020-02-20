package nwpu.sherman;

/**
 * 类初始化和实例初始化
 * 1、类初始化过程；
 * - 一个类要创建实例需要先加载并初始化该类：main方法所在的类需要先加载并初始化
 * - 一字类需要初始化需要先初始化父类
 * - 一个类的初始化就是执行<clinit>()方法
 *      - <clinit>方法由静态类变量显式赋值代码和静态代码块组成
 *      - 类变量显式赋值代码和静态代码块从上到下顺序执行
 *
 * 2、实例初始化过程：
 * - <init>()方法可能有多个，有几个构造器就有几个<init>()方法
 * - <init>()方法由非静态实例变量显式赋值代码、非静态代码块和对应构造器组成
 * - 非静态实例变量显式赋值代码、非静态代码块从上到下执行，对应的构造器最后执行
 * - 每次创建实例对象，调用对应构造器，执行的就是对应的<init>()方法
 * - <init>()方法的首行是super()或者super(实参列表)，及对应父类的<init>()方法
 *
 * 3、哪些方法不会被重写：
 * - final方法
 * - static方法
 * - private方法，不可见
 * @author sherman
 */
class Father {
    Father() {
        System.out.print("(7)");
    }

    private int i = test();
    private static int j = method();

    static {
        System.out.print("(2)");
    }

    {
        System.out.print("(6)");
    }


    public static int method() {
        System.out.print("(1)");
        return 0;
    }

    public int test() {
        System.out.print("(3)");
        return 0;
    }
}

class Son extends Father {
    static {
        System.out.print("(3)");
    }

    private int i = test();
    private static int j = method();

    Son() {
        System.out.print("(9)");
    }

    {
        System.out.print("(8)");
    }

    public static int method() {
        System.out.print("(4)");
        return 0;
    }

    public int test() {
        System.out.print("【5】");
        return 0;
    }
}

public class Question03 {
    public static void main(String[] args) {
        new Son();
        System.out.println();
        new Son();
    }
}
