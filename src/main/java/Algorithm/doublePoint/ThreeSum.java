package Algorithm.doublePoint;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            // 跳过重复的固定数
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    res.add(List.of(nums[i],nums[left],nums[right]));
                    // 跳过左右两边的重复数
                    while(left < right && nums[left] == nums[left+1])left++;
                    while(left < right && nums[right] == nums[right-1])right--;
                    left++;
                    right--;
                }else if(sum < 0){
                    left++; // 和太小，左指针右移
                }else{
                    right--; // 和太大，右指针左移
                }
            }
        }
        return res;
    }
}
