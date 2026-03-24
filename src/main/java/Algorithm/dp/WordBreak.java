package Algorithm.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        Arrays.fill(dp, false);
        dp[0] = true;
        for (int i = 1; i < n+1; i++){
            for(String word : wordDict){
                int length = word.length();
                if(i >= length){
                    if(dp[i- length] && (s.substring(i - length, i).equals(word))){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreakV2(String s, List<String> wordDict) {
        // 优化：转成 HashSet 以便 O(1) 查询单词是否存在
        Set<String> wordSet = new HashSet<>(wordDict);

        int n = s.length();

        // dp[j] 表示：s 的前 j 个字符 (s[0...j-1]) 能否被拆分
        boolean[] dp = new boolean[n + 1];

        // 【基准情况】：空字符串 (长度 0) 默认为可以拆分
        dp[0] = true;

        // 外层循环：遍历背包容量 (字符串的每个位置 j)
        // 这对应于二维中的列遍历
        for (int j = 1; j <= n; j++) {
            // 内层循环：遍历分割点 i (0 到 j-1)
            // 我们尝试把 s[0...j] 切成两段：s[0...i] 和 s[i...j]
            // 如果 s[0...i] 能拆分 (dp[i] == true)
            // 且 s[i...j] 在字典里，那么 s[0...j] 就能拆分
            for (int i = 0; i < j; i++) {
                // 剪枝：如果前缀 i 都不能拆分，那没必要检查后面的单词了
                if (!dp[i]) {
                    continue;
                }

                // 截取子串 s[i...j]
                String sub = s.substring(i, j);

                // 检查子串是否在字典中
                if (wordSet.contains(sub)) {
                    dp[j] = true;
                    break; // 只要找到一种拆分方法，就可以停止检查 j 的其他分割点了
                }
            }
        }

        return dp[n];
    }


}
