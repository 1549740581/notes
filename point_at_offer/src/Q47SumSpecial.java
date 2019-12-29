/**
 * 求1+2+3+...+n
 * 要求不能使用乘除法，for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 *
 * @author sherman
 */
public class Q47SumSpecial {
    public int sum(int n) {
        int res = (int) (Math.pow(n, 2) + n);
        return res >> 1;
    }

    public int sum1(int n) {
        int sum = n;
        @SuppressWarnings("unused")
        boolean ans = (n > 0) && ((sum += sum1(n - 1)) > 0);
        return sum;
    }
}
