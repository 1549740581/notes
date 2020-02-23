import java.util.Arrays;

/**
 * 扑克牌的顺子
 * 从一副牌中抽出5张牌，判断5张牌是否能组成顺子，大小王视为任意牌，A->1，J->11，Q->12，K->13
 *
 * @author sherman
 */
public class Q45扑克牌的顺序 {
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }
        Arrays.sort(numbers);
        int zeroCnt = 0;
        for (int number : numbers) {
            if (number == 0)
                ++zeroCnt;
            else {
                break;
            }
        }
        int lo = zeroCnt;
        int hi = lo + 1;
        int gapCnt = 0;
        while (hi < numbers.length) {
            /**
             * 对子
             */
            if (numbers[lo] == numbers[hi]) {
                return false;
            }
            gapCnt += numbers[hi] - numbers[lo] - 1;
            lo = hi;
            ++hi;
        }
        return gapCnt <= zeroCnt;
    }
}
