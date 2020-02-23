package nwpu.sherman.lambda;

/**
 * 查询接口，策略实际模式实现，根据该接口的operate方法返回值
 * 实现查询成功与否的功能
 *
 * @author sherman
 */
@FunctionalInterface
public interface Query<T> {
    /**
     * 根据operate的返回值进行查询
     *
     * @param t 查询的类型
     * @return
     */
    boolean operate(T t);
}
