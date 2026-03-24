package algorithmTemplate.dpSolveFrame;
import java.util.Arrays;

public class CompleteKnapsack {
    /**
     * @param n 背包总容量
     * @param weights 物品重量数组
     * @param values 物品价值数组
     * @return 背包能装下的最大价值
     */
    public int completeKnapsack(int n, int[] weights, int[] values) {
        // dp[j] 表示容量为 j 的背包能装下的最大价值
        int[] dp = new int[n + 1];

        // 求最大值时，初始化为 0 即可（因为价值非负）
        // 如果求最小值，需要初始化为无穷大，且 dp[0]=0

        // 1. 外层遍历物品
        for (int i = 0; i < weights.length; i++) {
            int w = weights[i];
            int v = values[i];

            // 2. 内层遍历容量：【关键】必须从小到大 (正向遍历)
            // 只有从 w 开始，才能保证 dp[j-w] 是已经考虑过当前物品 i 的状态
            // 从而实现“多次选取”
            for (int j = w; j <= n; j++) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        CompleteKnapsack solution = new CompleteKnapsack();
        int n = 5; // 容量
        int[] weights = {1, 2, 3}; // 物品重量
        int[] values = {1, 4, 5};  // 物品价值

        // 解释：
        // 选 1个重量1 (价值1) + 2个重量2 (价值4*2=8) = 总重5, 总价值9
        // 或者 选 2个重量1 + 1个重量3 = 1*2 + 5 = 7
        // 最优解应该是 9 (1个重量1 + 2个重量2)
        System.out.println(solution.completeKnapsack(n, weights, values));
    }
}