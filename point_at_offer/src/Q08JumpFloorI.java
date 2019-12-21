/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 *
 * @author sherman
 */
public class Q08JumpFloorI {
    public int jumpFloor(int target) {
        if (target == 1 || target == 2) {
            return target;
        }
        int f0 = 1;
        int f1 = 2;
        int fn = 0;
        while (target-- > 2) {
            fn = f0 + f1;
            f0 = f1;
            f1 = fn;
        }
        return fn;
    }
}
