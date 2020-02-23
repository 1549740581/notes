package search;

import java.util.HashMap;
import java.util.Map;

/**
 * 447 number of boomerangs
 * 给定平面上n对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中i和j之间的距离和i和k之间的距离相等（需要考虑元组的顺序）。
 *
 * @author sherman
 */
public class Q14NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
            Map<Integer, Integer> maps = new HashMap();
            for (int j = 0; j < points.length; ++j) {
                if (j != i) {
                    int dist = (int) (Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                    maps.put(dist, maps.getOrDefault(dist, 0) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
                /**
                 * n * (n - 1)，可以重复
                 */
                res += entry.getValue() * (entry.getValue() - 1);
            }
        }
        return res;
    }
}
