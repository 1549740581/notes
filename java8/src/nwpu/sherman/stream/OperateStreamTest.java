package nwpu.sherman.stream;

import nwpu.sherman.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流的各种中间操作：
 *      * 特点：
 *          多个中间操作可以连接起来形成一个流水线，除非流水线上触发了终止操作，否则中间操作不会执行任何处理，
 *          而是在终止操作时一次性全部处理，称为“惰性求值”
 *
 *      * 筛选和切片：
 *          - filter:                    过滤
 *          - limit：                    截断
 *          - skip：                     跳过
 *          - distinct：                 去重，注意它也是根据hashCode()和equals()方法进行去重
 *
 *      * 映射：
 *          - map：                      映射，类似于Python的map函数
 *          - flatmap                    展平stream嵌套stream，类似于add()和addAll()的区别
 *
 *      * 排序：
 *          - sort:                     自然排序
 *          - sorted(Comparator com):   定制排序
 *
 *      * 终止：
 *          - allMatch                  是否所有元素都匹配
 *          - anyMath                   是否只要一个元素匹配
 *          - noneMatch                 是否所有元素都不匹配
 *          - findFirst                 返回第一个预算
 *          - findAny                   返回流中任意元素
 *          - count                     流中元素的总个数
 *          - max                       流中的最大值
 *          - min                       流中的最小值
 *
 *      * 规约：将流中元素反复结合起来，得到一个值
 *          - reduce                    reduce(T identity, BinaryOperator)
 *
 *      * 收集：将流转换成其它形式
 *          - collect                   按照什么方式进行收集
 *
 *      * 分组：
 *          - groupingBy                可以实现一级分组和多级分组
 *
 *      * 分区：
 *          - partitioningBy            满足条件一个区，不满足条件一个区
 *
 * @author sherman
 */
public class OperateStreamTest {
    List<Employee> employees = Arrays.asList(
            new Employee("sherman", 23, 18888.0),
            new Employee("tl", 32, 28888.0),
            new Employee("zs", 45, 1000.0),
            new Employee("ls", 18, 8000),
            new Employee("wqy", 23, 25711.0),
            new Employee("wqy", 23, 25711.0),
            new Employee("wqy", 23, 25711.0)
    );

    @Test
    public void filterStreamTest() {
        /**
         * filter、limit
         * 注意下面的短路情况，因为限制了limit(3)，因此只要找到3个满足条件的employee就会终止
         * 所以对于最后一个employee并不会在进行操作，而是直接输出，发生短路现象
         */
        employees.stream()
                .filter(e -> {
                    System.out.println("短路！");
                    return e.getSalary() >= 8000.0;
                })
                .limit(3)
                .forEach(System.out::println);
        System.out.println("===============================================");
        /**
         * skip(n)：跳过前n个，和limit相反
         */
        employees.stream()
                .filter(e -> e.getAge() > 20)
                .skip(2)
                .forEach(System.out::println);

        System.out.println("===============================================");
        /**
         * distinct：去重
         */
        employees.stream()
                .filter(e -> e.getSalary() >= 8000.0)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * map & flatmap
     */
    @Test
    public void mapStreamTest(){
        /**
         * 将所有employee的姓名改成大写，并输出修改之后的employee集合
         */
        employees.stream()
                .map(e -> new Employee(e.getName().toUpperCase(), e.getAge(), e.getSalary()))
                .forEach(System.out::println);
        System.out.println("===============================================");

        /**
         * 将employee的姓名改成大写， 并输出所修修改的姓名
         */
        employees.stream()
                .map(e -> e.getName().toUpperCase())
                .forEach(System.out::println);
        System.out.println("===============================================");

        /**
         * 流中嵌套流，因此输出的时候也需要两重输出
         */
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        Stream<Stream<Character>> stream = list.stream().map(this::filterString);
        stream.forEach(sm -> sm.forEach(System.out::println));
        System.out.println("===============================================");

        /**
         * 使用flatmap解决
         */
        list.stream()
                .flatMap(this::filterString)
                .forEach(System.out::println);
    }

    /**
     * 将str转为Stream<Character>的流
     */
    private Stream<Character> filterString(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 排序sort & sorted
     */
    @Test
    public void sortStreamTest() {
        Map<String, Integer> maps = new HashMap<String, Integer>(16) {
            {
                put("sherman", 23);
                put("tl", 13);
                put("zs", 33);
                put("wqy", 24);
                put("ls", 29);
                put("zzz", 19);
            }
        };

        /**
         * 根据key排序
         */
        maps.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
        System.out.println("===============================================");

        /**
         * 根据value排序
         */
        maps.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
        System.out.println("===============================================");

        /**
         * 将value排序后的结果保存
         */
        Map<String, Integer> result = maps.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));
        System.out.println(result);
    }

    @Test
    public void stopStreamTest() {
        /**
         * allMatch & anyMatch & nonMatch
         */
        boolean res1 = employees.stream()
                .allMatch(employee -> employee.getName().startsWith("s"));
        boolean res2 = employees.stream()
                .anyMatch(employee -> employee.getName().startsWith("s"));
        boolean res3 = employees.stream()
                .noneMatch(employee -> employee.getName().startsWith("s"));
        System.out.println(res1 + ", " + res2 + ", " + res3);
        System.out.println("===============================================");

        /**
         * findFirst & findAny
         */
        Optional<Employee> maxSalary = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .findFirst();
        Optional<Employee> anyEmployee = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findAny();
        System.out.println(maxSalary.get() + ", " + anyEmployee.get());
        System.out.println("===============================================");

        /**
         * count & min & max
         */
        int[] arr = {4, 3, 2, 1, 5, 9, 100};
        long count = Arrays.stream(arr).count();
        OptionalInt max = Arrays.stream(arr).max();
        OptionalInt min = Arrays.stream(arr).min();
        System.out.println(count + ", " + max.getAsInt() + ", " + min.getAsInt());

        /**
         * 查找employees中工资的最小值
         */
        Optional<Double> minSalary = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(minSalary.get());
    }

    @Test
    public void reduceStreamTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10);
        /**
         * 注意因为这里有初始值identity=0，所以返回结果一定不可能为null
         * 所以返回类型是Integer，而不是Optional
         */
        Integer result01 = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(result01);

        Optional<Double> result02 = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(result02.get());
    }

    /**
     * collect
     */
    @Test
    public void collectStreamTest(){
        /**
         * collect -> Collectors.toList()
         */
        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("===============================================");

        /**
         * collect -> Collectors.toSet() -> 去重
         */
        employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        System.out.println("===============================================");

        /**
         * collect -> Collectors.toCollection(TypeCollection::new)
         */
        HashSet<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect.forEach(System.out::println);
        System.out.println("===============================================");

        /**
         * collector -> 获取stream中数量、最大值、最小值、平均值和总和
         */
        System.out.println(employees.stream()
                .collect(Collectors.counting()));
        System.out.println(employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare)).get());
        System.out.println(employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))).get());
        System.out.println(employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary)));
    }

    /**
     * collect & Collectors.groupingBy
     */
    @Test
    public void groupStreamTest() {
        /**
         * 一级分组：按照年龄分组
         */
        int threshold1 = 30;
        int threshold2 = 50;
        Map<String, List<Employee>> group = employees.stream()
                .collect(Collectors.groupingBy(e -> {
                    if (e.getAge() <= threshold1) {
                        return "青年";
                    } else if (e.getAge() <= threshold2) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                }));
        System.out.println(group);
        System.out.println("===============================================");

        /**
         * 多级分组：先按照年龄分组，再按照工资分组
         */
        double salaryLow = 10000.0;
        double salaryHigh = 20000.0;
        /**
         * Collectors.groupingBy(xxx, Collectors.groupingBy(yyy))
         */
        Map<String, Map<String, List<Employee>>> group1 = employees.stream()
                .collect(Collectors.groupingBy(e -> {
                    if (e.getAge() <= threshold1) {
                        return "青年";
                    } else if (e.getAge() <= threshold2) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                }, Collectors.groupingBy(e -> {
                    if (e.getSalary() <= salaryLow) {
                        return "低收入";
                    } else if (e.getSalary() <= salaryHigh) {
                        return "中等收入";
                    } else {
                        return "高收入";
                    }
                })));
        System.out.println(group1);
    }

    /**
     * collector & partitioningBy
     */
    @Test
    public void partitionStreamTest() {
        Map<Boolean, List<Employee>> res = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() >= 15000.0));
        System.out.println(res.get(true));
        System.out.println(res.get(false));
    }

    /**
     * 其它收集方式1
     */
    @Test
    public void otherCollect01Test() {
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getSum());
    }

    /**
     * 其它收集方式2
     */
    @Test
    public void otherCollect02Test() {
        System.out.println(employees.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.joining(",", "[", "]")));
    }
}
