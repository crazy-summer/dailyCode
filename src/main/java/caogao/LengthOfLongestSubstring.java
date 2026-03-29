package caogao;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()){
            // 不断扩大右边界直到满足条件
            while(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
            }

            maxLen = Math.max(maxLen, right - left);
            // 不断缩小左边界
            left++;
            set.remove(s.charAt(left));
        }
        return maxLen;
    }
}
