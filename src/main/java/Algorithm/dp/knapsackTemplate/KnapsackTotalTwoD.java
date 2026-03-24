package Algorithm.dp.knapsackTemplate;

public class KnapsackTotalTwoD {
    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // 初始化：
        // 如果是求最大价值：dp[...][0] = 0, 其他默认为0即可
        // 如果是求最小个数 (如零钱兑换)：dp[...][0] = 0, 其他初始化为 INF (amount+1)
        // 这里以求最大价值为例
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        // 如果求最小值，记得把非0位置初始化为一个大数

        for (int i = 1; i <= n; i++) {             // 遍历物品
            int w = weights[i - 1];
            int v = values[i - 1];

            for (int j = 0; j <= capacity; j++) {  // 遍历容量
                // 选项1: 不选当前物品 -> 继承上一行
                int notPick = dp[i - 1][j];

                // 选项2: 选当前物品
                int pick = 0; // 或者 INF，取决于求最大还是最小
                if (j >= w) {
                    // 【关键点】：依赖 dp[i] (当前行)
                    // 因为物品无限，选了它，剩下的容量依然可以由“前 i 个物品” (包括它自己) 来凑
                    // 注意这里是 dp[i][j-w]，不是 dp[i-1]

                    // 如果是求最小值，需要加判断防止 INF 溢出 (虽然二维用大数通常没事)
                    if (dp[i][j - w] != -1) { // 假设 -1 或 INF 代表不可达，具体看初始化
                        pick = dp[i][j - w] + v;
                    } else {
                        pick = 0; // 或者保持不可达状态
                    }
                    // 简化版 (求最大值且初始化为0时):
                    // pick = dp[i][j - w] + v;
                }

                // 取最值 (max 或 min)
                dp[i][j] = Math.max(notPick, pick);
                // 如果是求最小值: dp[i][j] = Math.min(notPick, pick);
            }
        }

        return dp[n][capacity];
    }
}
