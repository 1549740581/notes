/**
 * 覆盖矩形
 * 用2*1的小矩形横着或者竖着去覆盖2*n的大矩形，总共有多少种方法
 *
 * @author sherman
 */
public class Q10覆盖矩形 {
    public int rectCover(int target) {
        if (target == 1 || target == 2)
            return target;
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
