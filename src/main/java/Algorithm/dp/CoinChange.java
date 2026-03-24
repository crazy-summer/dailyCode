package Algorithm.dp;

public class CoinChange {
    public int coinChange(int[] coins, int amount){
        int n = coins.length;
        // dp[i][j]表示考虑前i个硬币，凑成金额j需要的最少硬币数
        int[][] dp = new int[n+1][amount+1];

        for(int i = 0; i < n+1; i++){
            for(int j = 0;j < amount+1; j++){
                dp[i][j] = amount+1;
            }
        }
        // 凑成金额0需要0个硬币
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i < n+1; i++){
            for(int j = 1; j <= amount; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i-1]] + 1);
            }
        }
        return dp[n][amount] == amount+1 ? -1 : dp[n][amount];
    }


    // 二维dp清晰版
    public int coinChangeV1(int[] coins, int amount) {
        int n = coins.length;

        // 1. 创建二维数组
        // dp[i][j]: 使用前 i 种硬币，凑成金额 j 的最少个数
        // 行：0 ~ n (0 表示不使用任何硬币)
        // 列：0 ~ amount
        int[][] dp = new int[n + 1][amount + 1];

        // 2. 初始化
        // 定义一个“无穷大”，设为 amount + 1 即可（因为最多只需要 amount 个 1 元硬币）
        int INF = amount + 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = INF;
            }
        }

        // 【基准情况】：凑成金额 0，无论有多少种硬币，都只需要 0 个
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // 3. 填表 (外层遍历物品/硬币种类，内层遍历容量/金额)
        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1]; // 当前硬币的面值 (注意下标是 i-1)

            for (int j = 1; j <= amount; j++) {
                // 选项 A: 不使用当前硬币 (直接继承上一行的结果)
                int notUse = dp[i - 1][j];

                // 选项 B: 使用当前硬币
                int use = INF;
                if (j >= coin) {
                    // 关键点：完全背包，看的是【当前行】的左边格子 dp[i][j - coin]
                    // 如果左边格子能凑出来 (!= INF)，那当前就能凑出来
                    if (dp[i][j - coin] != INF) {
                        use = dp[i][j - coin] + 1;
                    }
                }

                // 取最小值
                dp[i][j] = Math.min(notUse, use);
            }
        }

        // 4. 返回结果
        // 如果最后结果还是 INF，说明凑不出来，返回 -1
        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(coins, 11));
    }
}
