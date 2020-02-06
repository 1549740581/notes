package nwpu.sherman.optional;

import nwpu.sherman.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional容器类：尽可能避免空指针异常：
 * API介绍：
 *      * Optional.of(T t)：         创建一个Optional实例，参数不能为null
 *      * Optional.ofNullable(T t)： 若t不为null，创建Optional实例，否则创建空实例
 *      * Optional.empty()：         创建一个空的Optional实例
 *      * isPresent()：              判断一个Optional实例是否包含值
 *      * orElse(T t)：              如果调用对象包含值，则返回该值，否则返回t
 *      * orElseGet(Supplier s)：    如果调用对象包含值，则返回该值，否则返回s获取的值
 *      * map(Function f)：          如果有值对其处理，并返回处理后的Optional对象，否则返回Optional.empty()
 *      * flatmap(Function mapper)： 与map类似，要求返回值必须是Optional
 *
 * @author sherman
 */
public class OptionalApiTest {

    /**
     * Optional.of(T t)：不能为null
     */
    @Test
    public void optionalApi01Test() {
        Optional<Employee> op = Optional.of(null);
        System.out.println(op);
    }

    /**
     * Optional.empty()是返回一个空的Optional对象
     * Optional也是对象，对象的默认值都为null，为了避免null，
     * 使用empty()方法表示空的Optional对象
     */
    @Test
    public void optionalApi02Test(){
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty.get());
    }

    /**
     * Optional.ofNullable(null)不报错，但是结果还需要使用isPresent()进行判断
     */
    @Test
    public void optionalApi03Test() {
        Optional<Employee> op = Optional.ofNullable(null);
        if (op.isPresent()) {
            System.out.println("not empty!");
        } else {
            System.out.println("empty!");
        }
    }

    /**
     * orElse & orElseMap
     */
    @Test
    public void optionalApi04Test() {
        Optional<Employee> op = Optional.ofNullable(null);
        Employee sherman = op.orElse(new Employee("sherman", 23, 18888.8));
        System.out.println(sherman);
        Employee tl = op.orElseGet(() -> {
            System.out.println("相比较orElse操作，functionalInterface可以自定义操作！");
            return new Employee("tl", 23, 28888.8);
        });
        System.out.println(tl);
    }

    /**
     * map & flatmap
     */
    @Test
    public void optionalApi05Test(){
        Optional<Employee> sherman = Optional.ofNullable(new Employee("sherman", 23, 18888.8));
        Optional<String> name = sherman.map(e -> e.getName());
        System.out.println(name);

        /**
         * flatmap相比于map，其lambda体内部又使用了一次Optional进行封装
         */
        Optional<Employee> nullEmployee = Optional.ofNullable(null);
        Optional<Double> salary = nullEmployee.flatMap(e -> Optional.ofNullable(e.getSalary()));
        // 仍然需要判断是否为present
        if(salary.isPresent()) {
            System.out.println(salary.get());
        }else{
            System.out.println("empty()");
        }
    }
}
