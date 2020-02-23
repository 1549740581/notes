package nwpu.sherman.servlet;

/**
 * 用于演示ServletContainerInitializer接口的使用：
 * 在ServletContainerInitializer接口的实现类上加上@HandlesTypes注解
 * 并将FooInterface.class传递进去，则在ServletContainerInitializer接口
 * 对应的实现方法onStartup(Set<Class<?>> set, ServletContext sc)中：
 * 第一个参数【Set<Class<?>> set】就是@HandlesTypes指定类型的所有子类：
 *      - FooAbstract：子抽象类
 *      - FooSubInterface：子接口
 *      - FooInterfaceImpl：子实现类
 *
 * @author sherman
 */
public interface FooInterface {
}
