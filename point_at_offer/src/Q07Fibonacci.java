/**
 * 斐波那契数列的第n项，第0项为0，第一项为1
 *
 * @author sherman
 */
public class Q07Fibonacci {
    public int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int fo = 0;
        int f1 = 1;
        int fn = 0;
        while (n-- > 1) {
            fn = fo + f1;
            fo = f1;
            f1 = fn;
        }
        return fn;
    }
}
