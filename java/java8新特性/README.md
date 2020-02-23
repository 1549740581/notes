# Java8

## 1. Java8初体验

### 1.1 匿名内部类到lambda

```java
// 匿名内部类
TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(o2, o1);
    }
});

// lambda表达式
Comparator<Integer> comp = 
    (o1, o2) -> Integer.compare(o2, o1);
```

### 1.2 策略设计模式&lambda

定义一个query接口，根据用户自定义operate()方法进行返回：

```java
@FunctionalInterface
public interface Query<T> {
    /**
     * 根据operate的返回值进行查询
     */
    boolean operate(T t);
}
```

用户可以使用匿名内部类方式自定义的query()方法进行查询，也可以使用lambda表达式进行替换：

```java
// 面向接口编程，入参为Query，运行时会使用具体策略过滤Employee
public List<Employee> filterEmployee(List<Employee> employees, Query<Employee> query) {
    List<Employee> result = new ArrayList<>();
    for (Employee employee : employees) {
        if (query.operate(employee)) {
            result.add(employee);
        }
    }
    return result;
}

// 匿名内部类：查询salary > 10000.0的Employee
List<Employee> result = filterEmployee(employees, new Query<Employee>() {
    @Override
    public boolean operate(Employee employee) {
        return employee.getSalary() > 10000.0;
    }
});

// lambda表达式：查询age < 30的Employee
List<Employee> result = filterEmployee(employees, employee -> employee.getAge() < 30);
```

### 1.3 使用Stream流

```java
List<Employee> lists = new ArrayList<>();
// 向lists中添加员工...

lists.stream()
    .filter(e -> e.getSalary() > 10000.0)
    .limit(2)
    .map(Employee::getName)
    .forEach(System.out::pritnln);

```

## 2. lambda表达式语法

Java8中引入了一个新的操作符`->`，该操作符被称为箭头操作符或者Lambda操作符，操作符将表达式拆分为两个部分：

-   左侧：参数列表
-   右侧：表达式中所需要执行的功能，即lambda体

### 2.1 基本语法

-   语法格式1：无参数，无返回值： `Runnable r = () -> System.out.println("hello lambda");`
-   语法格式2：有一个参数，但是无返回值：参数列表的括号可以省略

    -    `Consumer<String> consumer = (x) -> System.out.println(x);`
-   `Consumer<String> consumer = x -> System.out.println(x);`
-   语法格式3：有两个以上参数，有返回值，lambda体内部多条语句，这种情况是最完整的lambda表达式，此时()、{}、return三个符号都不能省略：

```java
(x, y) -> {
    int sum = x + y;
    System.out.println(sum);
    return sum;
}
```

-   语法格式4：lambda体只有一条语句，有返回值：{}、return可以省略：

`Comparator<Integer> comp = (x, y) -> Integer.compare(y, x);`

-   语法格式5：lambda表达式参数列表中参数类型可以省略，但是如果要写就要将所有参数类型写全。参数类型省略得益于Java8中的类型推断，其它类型推断示例：

```java
// 不需要写成：new ArrayList<String>
List<String> list = new ArrayList<>();

String[] strs = {"sherman", "tl", "wqy"}; // right
// 不可以分开写，因为推断不出类型
String[] strs;                            // wrong
strs = {"sherman", "tl", "wqy"};          // wrong
```

### 2.2 函数式接口

lambda表达式需要**函数式接口**的支持：接口中只有一个抽象方法的接口，被称为函数式接口，该接口可以使用Java8提供的`@FunctionalInterface`注解。例如之前的Query接口就是一个函数式接口。

## 3. 四大函数式接口

在之前的示例中，使用lambda表达式结合策略设计模式去代替匿名内部类方式，但是当时使用lambda表达式还是需要自定义函数接口Query。Java8中提供了四大核心函数式接口，其它函数式接口都是以这些为基础进行拓展的。

### 3.1 Consumer\<T\>：消费型接口

Consumer\<T\>消费型接口，需要重写`void accept(T t)`方法，接受一个参数，无返回：

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}

// 示例
private void consume(double money, Consumer<Double> consumer) {
    consumer.accept(money);
}
public void consumerInterfaceTest() {
	consume(1000.0, x -> System.out.println("消费了：" + x + "元!"));    
}
```

### 3.2 Supplier\<T\>：供给型接口

Supplier\<T\>供给型接口，需要重写`T get()`方法，无参数，有返回值：

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

// 示例
private List<Integer> generateNRandomNum(int n, Supplier<Integer> supplier) {
    List<Integer> lists = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
        lists.add(supplier.get());
    }
    return lists;
}

public void supplyInterfaceTest() {
    int num = 10;
    int bound = 100;
    generateNRandomNum(num, 
                       () -> new Random().nextInt(bound)).forEach(System.out::println);
}
```

### 3.3 Function\<T, R\>：函数式接口

Function\<T, R\>函数式接口，需要重写`R apply(T t)`方法，接受一个参数，返回一个参数：

```java
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}

// 示例
private String strHandler(String str, Function<String, String> handler) {
    return handler.apply(str);
}

public void functionInterfaceTest() {
    System.out.println(strHandler("hello sherman", str -> str.toUpperCase()));
}
```

### 3.4 Predicate\<T\>：断言型接口

Predicate\<T\>断言型接口，需要实现`boolean test(T t)`方法，接受一个参数，返回值类型为Boolean：

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

// 示例
private List<String> filterString(List<String> strs, Predicate<String> predicate) {
    List<String> res = new ArrayList<>();
    for (String str : strs) {
        if (predicate.test(str)) {
            res.add(str);
        }
    }
    return res;
}

public void predicateInterfaceTest() {
    List<String> strs = new ArrayList<>(5);
    strs.add("sherman");
    strs.add("tl");
    strs.add("tanglei");
    strs.add("wqy");
    strs.add("zhangsan");
    filterString(strs, str -> str.length() >= 5).forEach(System.out::println);
}
```

### 3.5 其它拓展接口

-   BiFunction<T, U, R>：函数式接口子接口，两个参数类型：`R apply(T t, U u);`
-   UnaryOperator\<T\>：函数式接口子接口，参数和返回值类型相同：`T apply(T t);`
-   BinaryOperator\<T\>：函数式接口子接口，两个参数类型和返回值结果类型相同：`T apply(T t1, T t2)`
-   BiConsumer<T, U>：消费型接口子接口，两个参数类型：`void accept(T t, U u);`
-   三个自动拆箱的函数式接口：
    -   ToIntFunction\<T\>：`int applyAsInt(T t);`
    -   ToLongFunction\<T\>：`long applyAsLong(T t);`
    -   ToDoubleFunction\<T\>：`double applyAsDouble(T t);`
-   三个自动装箱的函数式接口：
    -   IntFunction\<R\>：`R apply(int t);`
    -   LongFunction\<R\>：`R apply(long t);`
    -   DoubleFunction\<R\>：`R apply(double t);`

## 4. 方法引用

若lambda体中内容已经有方法实现，Java8中提供方法引用：通过方法的名字来指向方法（类似于C中的函数指针），可以使语言更加紧凑。

### 4.1 方法引用

方法引用主要有三种方式：

-   对象::实例方法名

```java
Consumer<String> consumer1 = x -> System.out.println(x);
consumer1.accept("sherman");
// 使用方法引用
Consumer<String> consumer2 = System.out::println;
consumer2.accept("ping");

Employee employee = new Employee("sherman", 23, 8.8);
Supplier<String> supplier1 = () -> employee.getName();
System.out.println(supplier1.get());

// 使用方法应用
Supplier<String> supplier2 = employee::getName;
System.out.println(supplier2.get());
```

-   类名::静态方法名

```java
Comparator<Integer> comp2 = (x, y) -> Integer.compare(x, y);
// 使用方法引用
Comparator<Integer> comp1 = Integer::compare;
```

-   类名::实例方法名，但是有限制
    -   lambda体中调用方法的参数列表与返回值类型，要和函数式接口中抽象方法的函数列表和返回值类型保持一致
    -   lambda表达式参数列表中的第一个参数是实例方法的调用者，第二个参数是实例方法的参数列表

```java
BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
System.out.println(bp1.test("sherman", "tl"));

// 入参和返回值相同，且第一个参数是实例方法调用中，第二个参数是实例方法的入参
BiPredicate<String, String> bp2 = String::equals;
System.out.println(bp2.test("sherman", "sherman"));
```

### 4.2 构造器引用

构造器引用使用时候限制较大，因为受Java提供的函数式接口限制，构造器参数最多只能有两个：

```java
//调用无参构造器创建对象
Supplier<Employee> supplier = Employee::new;
System.out.println(supplier.get());

//调用一个参数的构造器创建对象
Function<String, Employee> function = Employee::new;
System.out.println(function.apply("sherman"));

//调用两个参数的构造器创建对象
BiFunction<String, Integer, Employee> bf = Employee::new;
System.out.println(bf.apply("sherman", 23));
```

### 4.3 数组引用

数组也可以使用方法引用，但实际开发中较少使用：

```java
Function<Integer, String[]> function01 = x -> new String[x];
String[] strs = function01.apply(10);
System.out.println(strs.length);

// 使用数组引用
Function<Integer, Integer[]> function02 = Integer[]::new;
System.out.println(function02.apply(10).length);
```

## 5. Interface新特性

Java8中Interface有两个重要的变化：

-   使用default关键字，支持默认方法
-   支持static方法

### 5.1 default方法

Interface在支持default方法的同时，会带来两个新的问题：

-   类优先原则：如果一个子类在继承了父类BaseClass和实现接口NewInterface的同时，父类和接口中存在同名的方法，且该方法是default的，并且在父类中有具体实现。则子类优先实现父类中的方法。
-   Java中接口可以多实现，如果多个接口中有相同的默认方法名，则子类实现这些类接口时，需要显式指定具体实现哪一个接口的默认方法。

### 5.2 static方法

Java8中支持static方法，注意main方法也是static的，因此接口中也能存在main方法。

```java
public interface NewInterface {
    /**
     * 演示default方法
     */
    default String getName() {
        return "NewInterface";
    }

    /**
     * Java8的Interface支持static方法，main方法也是static的
     */
    static void main(String[] args) {
        System.out.println("hello");
    }
}
```

## 6. 可重复注解

Java使用`@Repeatable`注解提供可重复注解功能，具体使用：

-   使用@Repeatable指定MyAnnotation注解为可重复注解：

```java
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
public @interface MyAnnotation {
    String value() default "sherman";
}
```

-   在@Repeatable指定的注解类中指定可重复注解类数组：

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
public @interface MyAnnotations {

    MyAnnotation[] value();
}
```

-   使用可重复注解

```java
@Test
@MyAnnotation("tl")
@MyAnnotation("wqy")
public void repeatAnnotationTest() throws Exception {
    /**
     * 通过反射获取重复注解的值
     */
    Method method = RepeatAnnotationTest.class.getMethod("repeatAnnotationTest");
    MyAnnotation[] annotations = method.getAnnotationsByType(MyAnnotation.class);
    for (MyAnnotation annotation : annotations) {
        System.out.println(annotation.value());
    }
}
```

## 7. Stream流

Java8中提供Stream流重要用于高效计算。

### 7.1 Stream流产生方式

产生Stream流主要有四种方式：

-   Collection集合的stream()、parallel()方法

```java
List<Integer> list = new ArrayList<>();
Stream<Integer> stream01 = list.stream();
```

-   Arrays数组类的stream()方法

```java
Employee[] employees = new Employee[10];
Stream<Employee> stream02 = Arrays.stream(employees);
```

-   Stream类的静态方法of(T t)、of(T... t)

```java
Stream<Integer> stream03 = Stream.of(100);
Stream<Integer> stream04 = Stream.of(11, 22, 33);
```

-   无限流：Stream.iterator(T seed, UnaryOperator\<T\> f)

```java
Stream.iterate(0, x -> x + 2)
    .limit(10)
    .forEach(System.out::println);

Stream.generate(() -> new Random().nextInt(100))
    .limit(100)
    .forEach(System.out::println);
```

### 7.2 Stream流中间操作

多个中间操作可以连接起来形成一个流水线，除非流水线上触发了终止操作，否则中间操作不会执行任何处理，       而是在终止操作时一次性全部处理，称为“惰性求值”。

-   筛选和切片：
    -   filter：过滤
    -   limit：截取
    -   skip：跳过
    -   distinct：去重，注意也是使用hashCode()和equals()进行去重

```java
employees.stream()
    .filter(e -> {
        System.out.println("短路！");
        return e.getSalary() >= 8000.0;
    })
    .limit(3)
    .forEach(System.out::println);

// 跳过前n个
employees.stream()
    .filter(e -> e.getAge() > 20)
    .skip(2)
    .forEach(System.out::println);

employees.stream()
    .filter(e -> e.getSalary() >= 8000.0)
    .distinct()
    .forEach(System.out::println);
```

-   映射：
    -   map：映射，map/reduce
    -   flatmap：展平stream嵌套stream，类似于add()和addAll()的区别

```java
employees.stream()
    .map(e -> new Employee(e.getName().toUpperCase(), e.getAge(), e.getSalary()))
    .forEach(System.out::println);

employees.stream()
    .map(e -> e.getName().toUpperCase())
    .forEach(System.out::println);

// 流中嵌套流，因此输出的时候也需要两重输出
private Stream<Character> filterString(String str) {
    List<Character> list = new ArrayList<>();
    for (Character ch : str.toCharArray()) {
        list.add(ch);
    }
    return list.stream();
}
List<String> list = Arrays.asList("aaa", "bbb", "ccc");
Stream<Stream<Character>> stream = list.stream().map(this::filterString);
stream.forEach(sm -> sm.forEach(System.out::println));
System.out.println("===============================================");

// 使用flatmap解决
list.stream()
    .flatMap(this::filterString)
    .forEach(System.out::println);
```

-   排序：
    -   sort：自然排序
    -   sorted(Comparator comp)：定制排序

```java
// 根据key排序
maps.entrySet().stream()
    .sorted(Map.Entry.comparingByKey())
    .forEach(System.out::println);

// 根据value排序
maps.entrySet().stream()
    .sorted(Map.Entry.comparingByValue())
    .forEach(System.out::println);

// 将value排序后的结果保存
Map<String, Integer> result = maps.entrySet().stream()
    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    .collect(Collectors.toMap(
        Map.Entry::getKey,
        Map.Entry::getValue,
        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
```

-   终止
    -   allMatch：所有元素都匹配
    -   anyMatch：只要一个元素匹配
    -   nonMatch：所有元素都不匹配
    -   findFirst：返回第一个
    -   findAny：返回任意一个
    -   count：总数
    -   max/min：最大/最小

```java
// allMatch & anyMatch & nonMatch
employees.stream().allMatch(employee -> employee.getName().startsWith("s"));
employees.stream().anyMatch(employee -> employee.getName().startsWith("s"));
employees.stream().noneMatch(employee -> employee.getName().startsWith("s"));
System.out.println(res1 + ", " + res2 + ", " + res3);

// findFirst & findAny
Optional<Employee> maxSalary = employees.stream()
    		.sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
    		.findFirst();

Optional<Employee> anyEmployee = employees.stream()
    		.sorted(Comparator.comparingDouble(Employee::getSalary))
    		.findAny();

// count & min & max
int[] arr = {4, 3, 2, 1, 5, 9, 100};
long count = Arrays.stream(arr).count();
OptionalInt max = Arrays.stream(arr).max();
OptionalInt min = Arrays.stream(arr).min();

// 查找employees中工资的最小值
Optional<Double> minSalary = employees.stream()
    							.map(Employee::getSalary)
    							.min(Double::compare);
```

-   规约：reduce，将Stream流中元素反复结合起来，map/reduce，得到一个值

```java
// reduce设置了初始值0，所以一定不返回null
Integer result01 = list.stream().reduce(0, (x, y) -> x + y);

// 没有设置初始值，需要使用Optional封装
Optional<Double> result02 = employees.stream()
    							.map(Employee::getSalary)
    							.reduce(Double::sum);
```

-   收集：collect，按照指定的方式将流转换成其它方式

```java
// 转换成List
employees.stream()
	.map(Employee::getName)
    .collect(Collectors.toList())
    .forEach(System.out::println);

// 转换层Set
employees.stream()
    .map(Employee::getName)
    .collect(Collectors.toSet())
    .forEach(System.out::println);

// 转换成HashSet
employees.stream()
    .map(Employee::getName)
    .collect(Collectors.toCollection(HashSet::new))
    .forEach(System.out::println);

// collector -> 获取stream中数量、最大值、最小值、平均值和总和
System.out.println(employees.stream().collect(Collectors.counting()));
             
System.out.println(employees.stream()
                   .map(Employee::getSalary)
                   .collect(Collectors.maxBy(Double::compare)).get());

System.out.println(employees.stream().collect(Collectors.maxBy(
                 		Comparator.comparingDouble(Employee::getSalary))).get());

System.out.println(employees.stream().collect(
                       Collectors.averagingDouble(Employee::getSalary)));

System.out.println(employees.stream().collect(
    					Collectors.summingDouble(Employee::getSalary)));
```

-   分组&分区：

    -   分组：groupingBy，可以实现一级分组和多级分组
    
    ```java
    // 一级分组
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
    
    // 多级分组：先按照年龄分组，再按照工资分组
    double salaryLow = 10000.0;
    double salaryHigh = 20000.0;
    // Collectors.groupingBy(xxx, Collectors.groupingBy(yyy))
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
    ```
    
    -   分区：partitioningBy，满足条件一个分区，不满足条件另一个分区

```java
Map<Boolean, List<Employee>> res = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() >= 15000.0));
System.out.println(res.get(true));
System.out.println(res.get(false));
```

## 8. 时间日期类

### 8.1 Java8之前处理SimpleDateFormat

SimpleDateFormat是线程不安全的，在Java8之前可以使用ThreadLocal来解决：

```java
private static final ThreadLocal<DateFormat> df = ThreadLocal.withInitial(
    () -> new SimpleDateFormat("yyyy-MM-dd")
);

private static Date convert(String source) throws ParseException {
    return df.get().parse(source);
}

@Test
public void threadLocalTest() throws Exception {
    int threadNums = 10;
    Callable<Date> task = () -> convert("2019-11-25");
    ExecutorService pool = Executors.newFixedThreadPool(threadNums);
    List<Future<Date>> results = new ArrayList<>();
    for (int i = 0; i < threadNums; i++) {
        results.add(pool.submit(task));
    }
    for (Future<Date> result : results) {
        System.out.println(result.get());
    }
    pool.shutdown();
}
```

Java8中可以使用LocalDate和DateTimeFormatter来解决该问题：

```java
@Test
public void localDateTest() throws Exception {
    int threadNums = 10;
    List<Future<LocalDate>> results = new ArrayList<>();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Callable<LocalDate> task = () -> LocalDate.parse("2019-11-25", dtf);
    ExecutorService pool = Executors.newFixedThreadPool(threadNums);
    for (int i = 0; i < threadNums; i++) {
        results.add(pool.submit(task));
    }
    for (Future<LocalDate> result : results) {
        System.out.println(result.get());
    }
}
```

### 8.2 时间日期类API

[测试类参考NewTimeApiTest.java](https://github.com/tanglei302wqy/notes/blob/master/java8/src/nwpu/sherman/time/NewTimeApiTest.java)

Java8中主要提供三个不可变类，LocalDate、LocalTime、LocalDateTime分别表示ISO-8601日历系统中的日期、时间、日期和时间。

主要API：

-   now()：根据系统当前时间创建对象
-   of()：根据指定时间、日期创建对象
-   增加时间：
    -   plusDays
    -   plusWeeks
    -   plusMonths
    -   plusYears
-   减少时间：
    -   MinusDays
    -   MinusWeeks
    -   MinusMonths
    -   MinusYears
-   获取对应时间的值
    -   getMonth
    -   getMonthValue
    -   getYear
-   修改月份、年份天数：
    -   withDayOfMonth
    -   withDayOfYear
    -   withMonth
    -   withYear
-   获取月份、年份的第几天：
    -   getDayOfMonth
    -   getDayOfYear
    -   getDayOfWeek
-   比较两个LocalDate时间前后：
    -   isBefore
    -   isAfter

Instant类主要API：时间戳

-   now：获取默认UTC时区
-   atOffset：修改时区
-   toEpochMilli：获取过去的秒数
-   Instant.ofEpochSecond：在当前时间戳基础上增加分钟数

Duration.between：获取两个“时间”之间间隔

Period.between：获取两个“日期”之间间隔