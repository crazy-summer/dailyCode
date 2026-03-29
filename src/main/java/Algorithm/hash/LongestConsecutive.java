package Algorithm.hash;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        int longestStreak = 0;
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        for(int num : set){
            // 只有num-1不在set中，num可能是一个潜在的序列起点
            if(!set.contains(num-1)){
                int currentNum = num;
                int currentStreak = 1;
                while(set.contains(num+1)){
                    currentStreak++;
                    num++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
