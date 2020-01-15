package dp;

/**
 * 309 best time to buy and sell stock with cooldown
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 ​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）
 * - 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 * - 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
 *
 * @author sherman
 */
public class Q12BestTimeForStock {
    public int maxProfit(int[] prices) {
        /**
         * sold[i] = hold[i-1] + price[i]
         * hold[i] = max{hold[i-1], rest[i-1]-price[i]}
         * rest[i] = max{rest[i-1], sold[i-1]}
         */
        int sold = 0, rest = 0, hold = Integer.MIN_VALUE;
        for (int p : prices) {
            int tmp = sold;
            sold = hold + p;
            hold = Math.max(hold, rest - p);
            rest = Math.max(rest, tmp);
        }
        return Math.max(sold, rest);
    }
}
