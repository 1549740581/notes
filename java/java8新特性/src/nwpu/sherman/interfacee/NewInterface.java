package nwpu.sherman.interfacee;

/**
 * Java8中Interface两个重要变化：
 *      * 支持默认方法：default关键字
 *
 *      * 支持static方法
 *
 * 1、支持默认方法：
 *      类优先原则：
 *          一个子类继承了父类BaseClass和接口NewInterface，BaseClass和NewInterface中有
 *          同名的方法，且该方法在BaseClass中有具体实现，在NewInterface中是default方法，
 *          则子类优先实现父类中的方法
 *
 *      接口冲突：
 *          Java中接口可以多实现，如果多个接口中有相同的默认方法名，则子类实现这些类接口时，
 *          需要显式指定具体实现哪一个接口的默认方法
 *
 * 2、支持static方法：
 *      main方法也是static方法，所以在Java8的Interface中，可以编写main方法，并且可以运行
 *
 * @author sherman
 */
public interface NewInterface {
    /**
     * 演示default方法
     */
    default String getName() {
        return "NewInterface";
    }

    /**
     * Java8的Interface支持static方法，main方法也是static的，因此也可以在Interface中添加
     * main方法
     *
     * @param args
     */
    static void main(String[] args) {
        System.out.println("hello");
    }
}
