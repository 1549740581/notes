/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法
 * f(1) = 1;
 * f(n) = f(n-1) + f(n-2) + ... + f(1) + 1, n >= 2
 * 递推可得：f(n) = 2f(n-1), n >= 2
 *
 * @author sherman2571
 */
public class Q09跳台阶II {
    public int jumpFloorII(int target) {
        return 1 << (target - 1);
    }
}
