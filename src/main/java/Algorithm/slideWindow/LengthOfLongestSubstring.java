package Algorithm.slideWindow;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int left = 0;
        int maxLen = 0;
        for(int i=0; i < chars.length; i++){
            char c = chars[i];
            // 如果当前窗口包含了右边的字符，需要不断移动左边的字符，直到窗口不包含c为止
            while(set.contains(c)){
                set.remove(chars[left]);
                left++;
            }
            set.add(c);
            maxLen = Math.max(maxLen, i - left+1);
        }
        return maxLen;
    }
}
