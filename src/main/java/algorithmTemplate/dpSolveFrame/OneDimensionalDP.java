package algorithmTemplate.dpSolveFrame;

public class OneDimensionalDP {
    public int solve(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // 1. 定义 dp 数组
        // dp[i] 的含义：以第 i 个元素结尾/处理到第 i 个元素时的最优解
        int[] dp = new int[n];

        // 2. 初始化 (Base Case)
        // 根据题目逻辑填写，通常是第一个或前几个元素
        dp[0] = nums[0];
        if (n > 1) dp[1] = 0; // if (n > 1) dp[1] = ...视题目而定
        // 3. 遍历顺序 & 状态转移
        for (int i = 2; i < n; i++) {
            // 核心逻辑：尝试所有可能的“前一个状态”，取最优
            // 例如：dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);

            // 【伪代码】具体公式需根据题目推导
            dp[i] = transitionFormula(dp, i, nums);
        }
        // 4. 返回结果
        // 可能是最后一个元素，也可能是整个数组的最大值
        return dp[n - 1];
    }

    private int transitionFormula(int[] dp, int i, int[] nums) {
        // 这里填入具体的状态转移方程
        return 0;
    }
}


//经典案例：爬楼梯 (Climbing Stairs)
//含义：dp[i] = 到达第 i 阶的方法数。
//转移：dp[i] = dp[i-1] + dp[i-2] (从上一阶或上上阶跳过来)。
//java
//
//        编辑
//
//
//
//public int climbStairs(int n) {
//    if (n <= 2) return n;
//    int[] dp = new int[n + 1];
//    dp[1] = 1;
//    dp[2] = 2;
//    for (int i = 3; i <= n; i++) {
//        dp[i] = dp[i - 1] + dp[i - 2];
//    }
//    return dp[n];
//}
