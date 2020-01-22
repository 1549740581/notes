package greedy;

import java.util.Arrays;

/**
 * 455 assign cookies
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子i，都有一个胃口值gi，
 * 这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干j，都有一个尺寸sj。如果sj>=gi，我们可以将这个饼干j分配给孩子i，
 * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * - 你可以假设胃口值为正。
 * - 一个小朋友最多只能拥有一块饼干。
 *
 * @author sherman
 */
public class Q01AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int idx = 0;
        for (int cookie : s) {
            if (idx == g.length) {
                break;
            }

            /**
             * 当前饼干要么满足当前最小需求，被使用掉，且只能使用一次
             * 要么不满足当前最小需求，直接被丢弃
             */
            if (cookie >= g[idx]) {
                ++idx;
            }
        }
        return idx;
    }
}
