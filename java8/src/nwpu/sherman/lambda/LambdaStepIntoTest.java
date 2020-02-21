package nwpu.sherman.lambda;

import org.junit.Test;

import java.util.*;

/**
 * 初识lambda表达式
 *
 * @author sherman
 */
public class LambdaStepIntoTest {

    /**
     * 原来的匿名内部类
     */
    @Test
    public void anonymousClassTest() {
//        Comparator<Integer> comp = new Comparator<Integer>() {
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return Integer.compare(o2, o1);
//            }
//        };
//        TreeSet<Integer> ts = new TreeSet<>(comp);

        TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        ts.add(10);
        ts.add(5);
        ts.add(7);
        for (Integer elem : ts) {
            System.out.println(elem);
        }
    }

    /**
     * 将匿名内部类改成lambda表达式
     */
    @Test
    public void lambda01Test() {
        Comparator<Integer> comp = (o1, o2) -> Integer.compare(o2, o1);
        TreeSet<Integer> ts = new TreeSet<>(comp);
        ts.add(11);
        ts.add(5);
        ts.add(7);
        for (Integer elem : ts) {
            System.out.println(elem);
        }
    }

    /**
     * 需求1：查询年龄大于30的员工
     * 需求2：查询工资大于10000的员工
     * ...
     * 需求n...
     * 采用策略设计模式
     */
    public List<Employee> filterEmployee(List<Employee> employees, Query<Employee> query) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (query.operate(employee)) {
                result.add(employee);
            }
        }
        return result;
    }

    /**
     * 策略设计模式 & 匿名内部类：根据年龄大于30查询
     */
    @Test
    public void queryByAgeTest() {
        List<Employee> employees = init();
        List<Employee> results = filterEmployee(employees, new Query<Employee>() {
            @Override
            public boolean operate(Employee employee) {
                return employee.getAge() > 30;
            }
        });
        for (Employee result : results) {
            System.out.println(result);
        }
    }

    /**
     * 策略设计模式 & 匿名内部类：根据工资大于10000.0查询
     */
    @Test
    public void queryBySalaryTest() {
        List<Employee> employees = init();
        List<Employee> result = filterEmployee(employees, new Query<Employee>() {
            @Override
            public boolean operate(Employee employee) {
                return employee.getSalary() > 10000.0;
            }
        });
        for (Employee employee : result) {
            System.out.println(employee);
        }
    }

    /**
     * 策略设计模式 & lambda表达式：根据年龄小于30查询
     */
    @Test
    public void queryByAgeLambdaTest() {
        List<Employee> employees = init();
        filterEmployee(employees, employee -> employee.getAge() < 30).forEach(System.out::println);
    }

    /**
     * 策略设计模式 & lambda表达式：根据工资小于10000.0查询
     */
    @Test
    public void queryBySalaryLambdaTest() {
        filterEmployee(init(), employee -> employee.getSalary() < 10000.0).forEach(System.out::println);
    }

    /**
     * 初始化员工集合，类似于@Before
     */
    private List<Employee> init() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("sherman", 23, 18888.0));
        employees.add(new Employee("tl", 22, 28888.0));
        employees.add(new Employee("zs", 32, 8888.0));
        employees.add(new Employee("ls", 23, 1888.0));
        return employees;
    }

    /**
     * 使用Stream流
     * 查询：工资大于10000.0，前两个员工的姓名
     */
    @Test
    public void queryBySalaryStreamTest() {
        init().stream()
                .filter(employee -> employee.getSalary() > 10000.0)
                .limit(2)
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
