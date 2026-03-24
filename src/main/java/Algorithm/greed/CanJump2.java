package Algorithm.greed;

public class CanJump2 {
    // 题目保证一定能从索引0跳到索引n-1，求最少的跳跃次数
    public int jump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        int jump = 0;
        int currentEnd = 0;
        for (int i = 0; i < n-1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jump++;
                currentEnd = farthest;
            }
            if(currentEnd >= n-1){
                break;
            }
        }
        return jump;
    }
}
