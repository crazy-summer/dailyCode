package algorithmTemplate.dpSolveFrame;

public class Knapsack {

    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // dp[j] 表示：容量为 j 的背包能装下的最大价值
        int[] dp = new int[capacity + 1];

        // 初始化：默认全为 0


        // 外层遍历物品
        for (int i = 0; i < n; i++) {
            // 内层遍历容量 (注意：0/1背包必须【倒序】遍历！)
            // 倒序是为了保证每个物品只被使用一次
            for (int j = capacity; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }

    // 关键点：如果是完全背包（物品无限个），内层循环改为正序遍历 (j = weights[i] to capacity)。
}
