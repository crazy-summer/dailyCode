package dp;
// question:有几张扑克牌从左至右排列，小明和小绿先后从其中从左边拿一张或者是从右边拿一张，最后谁的点数和大谁赢，两人都是聪明人

public class PukeGameOptimized {

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};

        // 方法1：递归（数据量大时会超时，仅用于理解逻辑）
        // int p1 = f(0, arr.length - 1, arr);
        // int p2 = totalSum(arr) - p1;

        // 方法2：动态规划（推荐）
        int firstHandScore = dp(arr);
        int secondHandScore = totalSum(arr) - firstHandScore;

        System.out.println("先手(小明)最大得分: " + firstHandScore);
        System.out.println("后手(小绿)最大得分: " + secondHandScore);
        System.out.println("赢家: " + (firstHandScore >= secondHandScore ? "小明" : "小绿"));
    }

    /**
     * 动态规划解法
     * @param arr 扑克牌数组
     * @return 先手能获得的最大分数
     */
    public static int dp(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;

        // fmap[i][j] 表示在区间 [i, j] 上，先手能获得的最大分数
        int[][] fmap = new int[n][n];
        // gmap[i][j] 表示在区间 [i, j] 上，后手能获得的最大分数
        int[][] gmap = new int[n][n];

        // 初始化：区间长度为1的情况
        for (int i = 0; i < n; i++) {
            fmap[i][i] = arr[i]; // 只剩一张，先手直接拿走
            gmap[i][i] = 0;      // 只剩一张，先手拿走了，后手得0
        }

        // len 表示区间长度，从2开始一直到n
        for (int len = 2; len <= n; len++) {
            // i 是左边界，j 是右边界
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // 计算先手 f[i][j]
                // 选项1：拿左边 arr[i]，剩下 [i+1, j]，此时自己变后手，得分为 gmap[i+1][j]
                // 选项2：拿右边 arr[j]，剩下 [i, j-1]，此时自己变后手，得分为 gmap[i][j-1]
                int pickLeft = arr[i] + gmap[i + 1][j];
                int pickRight = arr[j] + gmap[i][j - 1];
                fmap[i][j] = Math.max(pickLeft, pickRight);

                // 计算后手 g[i][j]
                // 对手是聪明人，对手拿完后，留给我的一定是让我得分最小的情况
                // 如果对手拿左边，留给我 [i+1, j]，我得分为 fmap[i+1][j]
                // 如果对手拿右边，留给我 [i, j-1]，我得分为 fmap[i][j-1]
                gmap[i][j] = Math.min(fmap[i + 1][j], fmap[i][j - 1]);
            }
        }

        // 整个游戏 [0, n-1] 的先手得分
        return fmap[0][n - 1];
    }

    // 辅助函数：计算总分
    private static int totalSum(int[] arr) {
        int sum = 0;
        for (int x : arr) sum += x;
        return sum;
    }

    // --- 以下是原始递归逻辑的修正版，仅供学习参考 ---

    public static int f(int L, int R, int[] arr) {
        if (L > R) return 0; // 修正：空区间得分为0
        if (L == R) return arr[L];

        int p1 = arr[L] + g(L + 1, R, arr);
        int p2 = arr[R] + g(L, R - 1, arr);
        return Math.max(p1, p2);
    }

    public static int g(int L, int R, int[] arr) {
        if (L > R) return 0; // 修正
        if (L == R) return 0; // 对手拿走了最后一张

        // 对手会让我得分最小
        int p1 = f(L + 1, R, arr);
        int p2 = f(L, R - 1, arr);
        return Math.min(p1, p2);
    }
}