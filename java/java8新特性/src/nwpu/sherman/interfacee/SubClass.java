package nwpu.sherman.interfacee;

/**
 * SubClass继承BaseClass，实现NewInterface接口，演示“类优先”原则
 *
 * @author sherman
 */
public class SubClass extends BaseClass implements NewInterface {

    /**
     * 输出：BaseClass
     */
    public static void main(String[] args) {
        System.out.println(new SubClass().getName());
    }
}
