package dp;

/**
 * 343 integer break
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * @author sherman
 */
public class Q04IntegerBreak {
    public int integerBreak(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }
        int[] sub = new int[n + 1];
        sub[1] = 1;
        sub[2] = 2;
        sub[3] = 3;
        sub[4] = 4;
        for (int i = 5; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2 + 1; ++j) {
                max = Math.max(max, sub[j] * sub[i - j]);
            }
            sub[i] = max;
        }
        return sub[n];
    }

    /**
     * 3(n - 3) > 2(n - 2)，n > 5，尽可能多的拆分出3
     *
     * @param n
     * @return
     */
    public int integerBreak1(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }
        int threeCnt = n / 3;
        int remainder = n % 3;
        int res = (int) Math.pow(3, threeCnt);
        if (remainder == 0) {
            return res;
        } else if (remainder == 1) {
            return res / 3 * 4;
        } else {
            return res * 2;
        }
    }
}
