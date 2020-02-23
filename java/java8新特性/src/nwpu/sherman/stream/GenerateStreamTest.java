package nwpu.sherman.stream;

import nwpu.sherman.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 产生Stream流的四种方式
 *      * Collection系列集合的stream()、parallel方法
 *
 *      * Arrays数组类的stream()方法
 *
 *      * Stream类的静态方法of(T t)、of(T... t)
 *
 *      * 无限流：Stream.iterator(T seed, UnaryOperator<T> f)
 *
 * @author sherman
 */
public class GenerateStreamTest {
    @Test
    public void generateStreamTest() {
        /**
         * 通过Collection系列集合提供的stream()、parallelStream()方法创建
         */
        List<Integer> list = new ArrayList<>();
        Stream<Integer> stream01 = list.stream();

        /**
         * 通过Arrays中静态方法stream()获取数组流
         */
        Employee[] employees = new Employee[10];
        Stream<Employee> stream02 = Arrays.stream(employees);

        /**
         * 通过Stream类中静态方法of(T)、of(T...t)创建流
         */
        Stream<Integer> stream03 = Stream.of(100);
        Stream<Integer> stream04 = Stream.of(11, 22, 33);

        /**
         * 创建无限流
         */
        Stream.iterate(0, x -> x + 2)
                .limit(10)
                .forEach(System.out::println);

        /**
         * 生成流
         */
        Stream.generate(() -> new Random().nextInt(100))
                .limit(100)
                .forEach(System.out::println);
    }
}
