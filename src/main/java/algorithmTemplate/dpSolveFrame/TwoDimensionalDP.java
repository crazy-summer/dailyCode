package algorithmTemplate.dpSolveFrame;
// 2. 二维 DP 通用模板
//适用场景：背包问题、编辑距离、最长公共子序列 (LCS)、不同路径、最大正方形等。
public class TwoDimensionalDP {
    public int solve(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // 1. 定义 dp 数组
        // dp[i][j] 的含义：nums1 前 i 个 和 nums2 前 j 个 ... 的最优解
        // 通常大小设为 [m+1][n+1] 方便处理边界（索引0表示空）
        int[][] dp = new int[m + 1][n + 1];

        // 2. 初始化 (Base Case)
        // 当其中一个序列为空时，结果通常为 0
        for (int i = 0; i <= m; i++) dp[i][0] = 0;
        for (int j = 0; j <= n; j++) dp[0][j] = 0;

        // 3. 遍历顺序 (通常双重循环)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 4. 状态转移
                if (nums1[i-1] == nums2[j-1]) {
                    // 情况A：当前元素匹配/满足条件
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 情况B：不匹配，取之前的最优解
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 5. 返回结果
        return dp[m][n];
    }
}
