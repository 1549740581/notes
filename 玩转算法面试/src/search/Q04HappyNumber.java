package search;

import java.util.HashMap;
import java.util.Map;

/**
 * 202 happy number
 * 判断一个数是否是【快乐数】
 * 快乐数定义：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为1，也可能是无限循环但始终变
 * 不到1。如果可以变为1，那么这个数就是快乐数。
 *
 * @author sherman
 */
public class Q04HappyNumber {
    public boolean isHappy(int n) {
        Map<Integer, Boolean> maps = new HashMap<>();
        while (n != 1) {
            maps.put(n, true);
            n = calcSumSquare(n);
            if (maps.containsKey(n)) {
                return false;
            }
        }
        return true;
    }

    private int calcSumSquare(int n) {
        int sum = 0;
        while (n != 0) {
            int remainder = n % 10;
            sum += remainder * remainder;
            n /= 10;
        }
        return sum;
    }
}
