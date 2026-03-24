package Algorithm.dp.knapsackTemplate;

public class Knapsack01TwoD {
    public int knapsack(int[] weights, int[] values, int capacity){
        // 假设：weights[] 是重量，values[] 是价值，capacity 是背包容量
        // n 是物品个数
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // 初始化：通常 dp[0][...] = 0 (0个物品价值为0)，其他根据题意(如求最小值则初始化为INF)
        // 这里以求最大价值为例，Java int数组默认就是0，无需额外初始化

        for (int i = 1; i <= n; i++) {             // 遍历物品 (第1个到第n个)
            int w = weights[i - 1];                // 当前物品重量
            int v = values[i - 1];                 // 当前物品价值

            for (int j = 0; j <= capacity; j++) {  // 遍历容量 (0到capacity)
                // 选项1: 不选当前物品 -> 直接继承上一行的结果
                int notPick = dp[i - 1][j];

                // 选项2: 选当前物品 -> 前提是容量够
                int pick = 0;
                if (j >= w) {
                    // 【关键点】：依赖 dp[i-1] (上一行)
                    // 因为物品只有一个，选了它，剩下的容量只能由“前 i-1 个物品”来凑
                    pick = dp[i - 1][j - w] + v;
                }

                // 取最大值
                dp[i][j] = Math.max(notPick, pick);
            }
        }

        // 结果在右下角
        return dp[n][capacity];
    }
}
