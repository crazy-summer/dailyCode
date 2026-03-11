package algorithmTemplate.dpSolveFrame;

//⚡ 空间优化技巧 (滚动数组)
//很多 DP 问题中，dp[i] 只依赖于 dp[i-1] 或 dp[i-2]
//此时可以将O(N)或者O(N^2)的空间优化为O(1)或O(N)
public class OneDimensionalOptimize {
    // 只需要保存前两个状态，不需要整个数组
    public int climbStairsOptimized(int n) {
        if (n <= 2) return n;
        int prev2 = 1; // dp[i-2]
        int prev1 = 2; // dp[i-1]
        int curr = 0;

        for (int i = 3; i <= n; i++) {
            curr = prev1 + prev2;
            // 滚动更新
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
