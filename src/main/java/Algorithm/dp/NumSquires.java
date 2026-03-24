package Algorithm.dp;

import java.util.Arrays;

public class NumSquires {
    public int numSquaresV1(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n+1);
        dp[0] = 0;
        for(int k = 1; k * k <= n; k++){
            int t = k * k;
            for (int i = t; i <= n; i++){
                dp[i] = Math.min(dp[i], dp[i-t] + 1);
            }
        }
        return dp[n];
    }

    // 二维dp数组
    public int numSquaresV2(int n) {
        // 1. 计算有多少种“物品”（平方数）
        // 比如 n=12, 平方数有 1, 4, 9 (3种), 所以 m=3
        int m = (int) Math.sqrt(n);

        // 2. 创建二维数组
        // dp[i][j]: 使用前 i 种平方数，凑成数字 j 的最少个数
        // 行标 0..m, 列标 0..n
        int[][] dp = new int[m + 1][n + 1];

        // 3. 初始化
        // 用一个较大的数代表“不可能”，但不用 Integer.MAX_VALUE 避免溢出烦恼
        // 这里用 n + 1 即可，因为最坏情况也就是全用 1^2，个数是 n，所以 n+1 肯定代表不可达
        int INF = n + 1;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = INF;
            }
        }

        // 【基准情况】：凑成数字 0，需要 0 个平方数
        // 无论用前几种物品，凑 0 都是 0 个
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        // 4. 开始填表 (外层物品，内层容量)
        for (int i = 1; i <= m; i++) {
            int square = i * i; // 当前物品的重量

            for (int j = 1; j <= n; j++) {
                // 选项 A: 不使用当前这个平方数 (直接照抄上一行的结果)
                int notUse = dp[i-1][j];

                // 选项 B: 使用当前这个平方数
                int use = INF;
                if (j >= square) {
                    // 关键点：完全背包，所以看的是同一行 (dp[i]) 的前面位置
                    // 如果前面的位置能凑出来，那当前就能凑出来 (个数+1)
                    if (dp[i][j - square] != INF) {
                        use = dp[i][j - square] + 1;
                    }
                }

                // 取两者的最小值
                dp[i][j] = Math.min(notUse, use);
            }
        }

        return dp[m][n];
    }
}
