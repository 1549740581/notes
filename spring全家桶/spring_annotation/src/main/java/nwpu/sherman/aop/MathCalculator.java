package nwpu.sherman.aop;

/**
 * 数学计算器类，切面类的各个方法会切入到该类的div方法中
 *
 * @author sherman
 */
public class MathCalculator {

    public int div(int i, int j) {
        return i / j;
    }
}
