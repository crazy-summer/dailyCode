package Algorithm;

public class SlidingWindowTemplate {
    public static void main(String[] args) {}


    // 和大于等于target的最短子数组
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        boolean found = false;

        // 1. 右指针不断向右移动，扩大窗口
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                // 更新最小长度
                minLen = Math.min(minLen, right - left + 1);

                // 移除左边的数，缩小窗口
                sum -= nums[left];
                left++;
                found = true;
            }
        }

        return found ? minLen : 0;
    }
}
