/**
 * 不使用加减乘除做加法
 *
 * @author sherman
 */
public class Q48特殊加法运算 {
    public int add(int num1, int num2) {
        int sum, carry;
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        } while (num2 != 0);
        return sum;
    }
}
