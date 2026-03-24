package Algorithm.dp;

public class CanPartition {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 1. 如果总和是奇数，直接返回 false
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int n = nums.length;

        // 2. 创建二维数组
        // dp[i][j]: 使用前 i 个数字，能否凑出和 j
        boolean[][] dp = new boolean[n + 1][target + 1];

        // 3. 初始化
        // 凑出和为 0，永远为 true (什么都不选)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        // 其他位置默认为 false (Java boolean 数组默认就是 false)

        // 4. 填表 (01 背包逻辑)
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1]; // 当前数字

            for (int j = 1; j <= target; j++) {
                // 选项 A: 不选当前数字 -> 继承上一行
                boolean notPick = dp[i - 1][j];

                // 选项 B: 选当前数字 -> 前提是容量够，且剩余部分能凑出
                boolean pick = false;
                if (j >= num) {
                    // 【关键点】：依赖 dp[i-1] (上一行)，因为是 01 背包
                    pick = dp[i - 1][j - num];
                }

                // 只要有一种方案可行，即为 true
                dp[i][j] = notPick || pick;

                // 【小优化】：如果已经发现能凑出 target，可以提前结束（可选）
                if (j == target && dp[i][j]) {
                    return true;
                }
            }
        }

        // 5. 返回结果
        return dp[n][target];
    }
}
