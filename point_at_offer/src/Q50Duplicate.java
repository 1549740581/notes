import java.util.HashSet;
import java.util.Set;

/**
 * 数组中重复的数字
 * 在一个长度为n的数组中，数组中所有数字都在0 -> n-1之间，如果有重复的数字，返回true，并找到第一个重复的数字，否则返回false
 *
 * @author sherman
 */
public class Q50Duplicate {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length <= 1 || numbers.length != length) {
            return false;
        }
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < length; ++i) {
            if (sets.contains(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            } else {
                sets.add(numbers[i]);
            }
        }
        return false;
    }

    public boolean duplicate01(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length <= 1 || numbers.length != length){
            return false;
        }
        for (int i = 0; i < length; ++i) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                int tmp = numbers[i];
                /**
                 * 注意不能写成：numbers[numbers[i]] = tmp;
                 */
                numbers[i] = numbers[tmp];
                numbers[tmp] = tmp;
            }
        }
        return false;
    }
}
