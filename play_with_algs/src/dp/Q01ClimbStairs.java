package dp;

/**
 * 70 climb stairs
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次可以爬 1 或 2 个台阶。你有多少种不同的方法爬到楼顶
 *
 * @author sherman
 */

public class Q01ClimbStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int fn = 0;
        for (int i = 2; i < n; ++i) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }
        return fn;
    }
}
