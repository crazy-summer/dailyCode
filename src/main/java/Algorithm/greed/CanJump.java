package Algorithm.greed;

public class CanJump {
    // 能否从0跳到n-1
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxReach = 0;
        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]); // 考虑<= i 位置能跳到的最远位置
            if (maxReach >= n-1) {
                return true;
            }
        }
        return false;
    }
}
