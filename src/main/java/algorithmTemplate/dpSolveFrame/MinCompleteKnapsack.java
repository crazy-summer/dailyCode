package algorithmTemplate.dpSolveFrame;

import java.util.Arrays;// 求最小个数的完全背包模板
public class MinCompleteKnapsack{
    public int minCountKnapsack(int n, int[] items) {
        int[] dp = new int[n + 1];
        // 1. 初始化为无穷大 (表示暂时无法组成)
        Arrays.fill(dp, Integer.MAX_VALUE / 2); // 防止 +1 溢出
        dp[0] = 0; // 基础情况：容量0需要0个物品

        for (int item : items) {
            // 2. 依然保持从小到大遍历
            for (int j = item; j <= n; j++) {
                if (dp[j - item] != Integer.MAX_VALUE / 2) {
                    dp[j] = Math.min(dp[j], dp[j - item] + 1);
                }
            }
        }

        return dp[n] == Integer.MAX_VALUE / 2 ? -1 : dp[n];
    }
}
