package nwpu.sherman.lambda;

/**
 * 演示函数式接口
 *
 * @author sherman
 */
@FunctionalInterface
public interface MyFuncInterface<T> {
    /**
     * 对num进行操作后返回
     *
     * @param num
     * @return
     */
    T getValue(T num);
}
