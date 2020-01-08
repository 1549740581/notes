/**
 * 剪绳子
 * 一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），求每段绳子乘积的最大值是多少？
 *
 * @author sherman
 */
public class Q67CutRope {
    public int cutRope(int target) {
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        if (target == 4) {
            return 4;
        }
        int times = target / 3;
        if (target % 3 == 0) {
            return (int) (Math.pow(3, times));
        } else if (target % 3 == 1) {
            return (int) (Math.pow(3, times - 1)) * 4;
        } else {
            return (int) (Math.pow(3, times)) * 2;
        }
    }
}
