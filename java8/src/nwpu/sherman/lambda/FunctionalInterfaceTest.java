package nwpu.sherman.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8内置的四大核心函数式接口
 *      * Consumer<T>：消费型接口
 *          void accept(T t);
 *
 *      * Supplier<T>：供给型接口
 *          T get();
 *
 *      * Function<T, R>：函数式接口
 *          R apply(T t);
 *
 *      * Predicate<T>：断言型接口
 *          boolean test(T t);
 *
 * 其它接口：
 *      * BiFunction<T, U, R>：函数式接口子接口，两个参数类型
 *          R apply(T t, U u);
 *
 *      * UnaryOperator<T>：函数式接口子接口，参数和返回值相同类型
 *          T apply(T t);
 *
 *      * BinaryOperator<T>：函数式接口子接口，两个参数类型和返回值结果都相同
 *          T apply(T t1, T t2);
 *
 *      * BiConsumer<T, U>：消费型接口子接口，两个参数类型
 *          void accept(T t, U u);
 *
 *      // 三个自动拆箱的函数式接口
 *      * ToIntFunction<T>：     int applyAsInt(T t);
 *      * ToLongFunction<T>：    long applyAsLong(T t);
 *      * ToDoubleFunction<T>：  double applyAsDouble(T t);
 *
 *      // 三个自动装箱的函数式接口
 *      * IntFunction<R>：       R apply(int t);
 *      * LongFunction<R>：      R apply(long t);
 *      * DoubleFunction<R>：    R apply(double t);
 *
 * @author sherman
 */
public class FunctionalInterfaceTest {
    /**
     * 函数式接口一：消费型接口
     */
    @Test
    public void consumerInterfaceTest() {
        consume(1000.0, x -> System.out.println("消费了：" + x + "元!"));
    }

    /**
     * 消费型接口使用
     */
    private void consume(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * 函数式接口二：供给型接口
     */
    @Test
    public void supplyInterfaceTest() {
        int num = 10;
        int bound = 100;
        generateNRandomNum(num, () -> new Random().nextInt(bound)).forEach(System.out::println);
    }

    /**
     * 供给型接口使用：产生n个随机数放入list中并返回
     */
    private List<Integer> generateNRandomNum(int n, Supplier<Integer> supplier) {
        List<Integer> lists = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            lists.add(supplier.get());
        }
        return lists;
    }

    /**
     * 函数式接口三：函数型接口
     */
    @Test
    public void functionInterfaceTest() {
        System.out.println(strHandler("hello sherman", str -> str.toUpperCase()));
    }

    /**
     * 函数型接口使用，接受一个字符串处理后并返回
     */
    private String strHandler(String str, Function<String, String> handler) {
        return handler.apply(str);
    }

    /**
     * 函数式接口四：断言型接口
     */
    @Test
    public void predicateInterfaceTest() {
        List<String> strs = new ArrayList<>(5);
        strs.add("sherman");
        strs.add("tl");
        strs.add("tanglei");
        strs.add("wqy");
        strs.add("zhangsan");
        filterString(strs, str -> str.length() >= 5).forEach(System.out::println);
    }

    /**
     * 断言型字符串使用，给定字符串集合，返回满足特定条件的字符串集合
     */
    private List<String> filterString(List<String> strs, Predicate<String> predicate) {
        List<String> res = new ArrayList<>();
        for (String str : strs) {
            if (predicate.test(str)) {
                res.add(str);
            }
        }
        return res;
    }
}
