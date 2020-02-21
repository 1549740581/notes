/**
 * 二进制中1的个数，负数用补码表示
 *
 * @author sherman
 */
public class Q11二进制数字1的个数 {
	public int numberOf1(int n) {
		// return Integer.bitCount(n);
		int count = 0;
		while (n != 0) {
			++count;
			n &= (n - 1);
		}
		return count;
	}
}
