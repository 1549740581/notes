package array;

/**
 * 11 container with most water
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai).
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水.
 *
 * @author sherman
 */
public class Q12ContainerWithMostWater {
    public int maxArea(int[] height) {
        int lo = 0, hi = height.length - 1;
        int area = 0;
        while (lo < hi) {
            int hgt = 0;
            int width = hi - lo;
            if (height[lo] < height[hi]) {
                hgt = height[lo++];
            } else {
                hgt = height[hi--];
            }
            area = Math.max(area, hgt * width);
        }
        return area;
    }
}
