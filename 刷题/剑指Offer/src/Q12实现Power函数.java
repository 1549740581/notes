/**
 * 数值的整数次方，保证base和exponent不同时为0
 *
 * @author sherman
 */
public class Q12实现Power函数 {
    public double power(double base, int exponent) {
        // return Math.pow(base, exponent);
        boolean flag = exponent < 0;
        if (flag) {
            exponent = -exponent;
        }
        double ans = powerInternal(base, exponent);
        return flag ? (1 / ans) : ans;
    }

	//递归
    private double powerInternal(double base, int exp) {
        if (exp == 0){
            return 1;
        }
        if (exp == 1){
            return base;
        }
        double ans = powerInternal(base, exp >> 1);
        ans *= ans;
        if ((exp & 1) == 1){
            ans *= base;
        }
        return ans;
    }

	// 迭代
    public double power01(double base, int exponent) {
        boolean flag = exponent < 0;
        if (flag) {
            exponent = -exponent;
        }
        double ans = 1.0;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                ans *= base;
            }
            exponent >>= 1;
            base *= base;
        }
        return flag ? (1 / ans) : ans;
    }
}
