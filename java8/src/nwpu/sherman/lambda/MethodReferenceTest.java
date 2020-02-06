package nwpu.sherman.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.*;

/**
 * 一、方法引用：若Lambda体中内容有方法已经实现了，我们可以使用“方法引用”
 *      * 对象::实例对象名
 *
 *      * 类名::静态方法名
 *
 *      * 类名::实例方法名（有限制）
 *
 *      注意：
 *          * lambda体中调用方法的参数列表与返回值类型，要和函数式接口中抽象方法的函数列表和返回值类型保持一致
 *          * lambda表达式参数列表中的第一个参数是实例方法的调用者，第二个参数是实例方法的参数列表，则可以使用：
 *            类名::实例方法名形式
 *
 * 二、构造器引用：
 *      * 类名::new
 *
 *      注意：需要调用的构造器的参数列表需要和函数式接口中抽象方法的参数列表保持一致
 *
 * 三、数组引用：
 *      * Type[]::new
 *
 * @author sherman
 */
public class MethodReferenceTest {
    /**
     * 方法引用一：
     * 对象::实例方法名
     */
    @Test
    public void methodReference01Test() {
        Consumer<String> consumer1 = System.out::print;
        Consumer<Integer> consumer2 = x -> System.out.println(x);
        consumer1.accept("sherman, age: ");
        consumer2.accept(23);

        Employee employee = new Employee("sherman", 23, 18888.0);
        Supplier<String> supplier1 = employee::getName;
        Supplier<Integer> supplier2 = employee::getAge;
        System.out.println(supplier1.get());
        System.out.println(supplier2.get());
    }

    /**
     * 方法引用二：
     * 类名::静态方法名
     */
    @Test
    public void methodReference02Test() {
        Comparator<Integer> comp1 = Integer::compare;
        new TreeSet<>(comp1);

        Comparator<Integer> comp2 = (x, y) -> Integer.compare(x, y);
        new TreeSet<>(comp2);
    }

    /**
     * 方法引用三：
     * 类名::实例方法名
     */
    @Test
    public void methodReference03Test() {
        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp1.test("sherman", "tl"));
        System.out.println(bp2.test("sherman", "sherman"));
    }

    /**
     * 构造器引用
     */
    @Test
    public void constructReferenceTest(){
        /**
         * 调用无参构造器创建对象
         */
        Supplier<Employee> supplier = Employee::new;
        System.out.println(supplier.get());
        /**
         * 调用一个参数的构造器创建对象
         */
        Function<String, Employee> function = Employee::new;
        System.out.println(function.apply("sherman"));
        /**
         * 调用两个参数的构造器创建对象
         */
        BiFunction<String, Integer, Employee> bf = Employee::new;
        System.out.println(bf.apply("sherman", 23));
    }

    /**
     * 数组引用
     */
    @Test
    public void arrayReferenceTest(){
        Function<Integer, String[]> function01 = x -> new String[x];
        String[] strs = function01.apply(10);
        System.out.println(strs.length);

        Function<Integer, Integer[]> function02 = Integer[]::new;
        System.out.println(function02.apply(10).length);
    }
}
