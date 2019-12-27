/**
 * 整数中1出现的次数
 *
 * @author sherman
 */
public class Q31NumberOf1Between1AndN {
    public int numberOf1Between1AndN(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; ++i) {
            cnt += cntOfOne(i);
        }
        return cnt;
    }

    private int cntOfOne(int n) {
        int cnt = 0;
        while (n > 0) {
            int reminder = n % 10;
            if (reminder == 1)
                ++cnt;
            n /= 10;
        }
        return cnt;
    }
}
