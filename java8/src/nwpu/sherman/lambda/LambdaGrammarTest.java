package nwpu.sherman.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * lambda表达式语法
 * 一、Lambda表达式基础语法：
 *      Java8中引入了一个新的操作符"->"，该操作符被称为箭头操作符或者Lambda操作符，
 *      操作符将表达式拆分为两个部分：
 *         * 左侧：参数列表
 *         * 右侧：表达式中所需要执行的功能，即lambda体
 *
 *      语法格式1：无参数，无返回值：
 *         * () -> System.out.println("hello lambda");
 *
 *      语法格式2：有一个参数，但是无返回值：参数列表的括号可以省略
 *         * (x) -> System.out.println(x);
 *         * x -> System.out.println(x);
 *
 *      语法格式3：有两个以上参数，有返回值，lambda体内部多条语句：这种情况是最完整的lambda表达式：
 *         * (x, y) -> {
 *             System.out.println(x + y);
 *             return x + y;
 *           }
 *           // 此时()、{}、return三个都不能省略
 *
 *      语法格式4：lambda体只有一条语句，有返回值：{}、return可以省略：
 *          * x -> x * x;
 *          * (x, y) -> x * y;
 *
 *      语法格式5：lambda表达式参数列表中参数类型可以省略，但是如果要写就要将所有参数类型写全，
 *                参数类型省略得益于Java8中的类型推断，其它类型推断示例：
 *          * List<String> list = new ArrayList<>();
 *          * String[] strs = {"sherman", "tl", "wqy"}; // right
 *
 *          * String[] strs;                            // wrong
 *          * strs = {"sherman", "tl", "wqy"};          // wrong
 * 二、Lambda表达式需要"函数式接口"的支持：
 *      函数式接口：接口中只有一个抽象方法的接口，被称为函数式接口
 *                可以使用@FunctionalInterface进行注解
 *
 * @author sherman
 */
public class LambdaGrammarTest {

    /**
     * 语法格式1：无参数、无返回值
     */
    @Test
    public void grammar01Test() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        r.run();

        Runnable r1 = () -> System.out.println("hello lambda!");
        r1.run();
    }

    /**
     * 语法格式2：一个参数，无返回值，()可以省略
     */
    @Test
    public void grammar02Test() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("Sherman loves Java");
    }

    /**
     * 语法格式3：多个参数，有返回值，lambda体多条语句
     */
    @Test
    public void grammar03Test() {
        Comparator<Integer> comp = (x, y) -> {
            System.out.println(x + y);
            return Integer.compare(x, y);
        };
        new TreeSet<>(comp);
    }

    /**
     * 语法格式4：只要一条语句，有返回值，{}和return都可以省略
     */
    @Test
    public void grammar04Test() {
        Comparator<Integer> comp = (x, y) -> Integer.compare(y, x);
        new TreeSet<>(comp);
    }

    /**
     * 演示函数式接口
     */
    @Test
    public void functionInterfaceTest() {
        System.out.println(operate(100, x -> x * x));
        System.out.println(operate(100, x -> x + x));
    }

    public <T> T operate(T num, MyFuncInterface<T> mfi){
        return mfi.getValue(num);
    }
}

