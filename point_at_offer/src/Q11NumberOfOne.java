/**
 * 二进制表示中1的个数，负数用补码表示
 *
 * @author sherman
 */
public class Q11NumberOfOne {
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
