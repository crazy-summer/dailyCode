package Algorithm.dp;

public class MaxProduct {
    public int maxProductV1(int[] nums) {
        // 1. 初始化：第一个元素既是当前的最大积，也是当前的最小积
        int currentMax = nums[0];
        int currentMin = nums[0];
        int globalMax = nums[0];

        // 2. 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // --- 第一步：算出所有可能的“候选人” ---
            // 如果当前数是负数，它会让最大的变最小，最小的变最大
            // 所以我们要把“前一个最大×当前数”和“前一个最小×当前数”都算出来备着
            int candidate1 = currentMax * num;
            int candidate2 = currentMin * num;

            // 还有一个候选人：干脆不要前面的了，从当前数自己重新开始
            int candidate3 = num;

            // --- 第二步：在候选人中选出新的“王”和“寇” ---
            // 新的最大值 = 三个候选人里最大的那个
            int nextMax = Math.max(candidate1, Math.max(candidate2, candidate3));

            // 新的最小值 = 三个候选人里最小的那个
            int nextMin = Math.min(candidate1, Math.min(candidate2, candidate3));

            // --- 第三步：正式更新状态 ---
            // 因为上面已经算好了 nextMax 和 nextMin，这里直接赋值，不用担心覆盖问题
            currentMax = nextMax;
            currentMin = nextMin;

            // --- 第四步：更新全局记录 ---
            if (currentMax > globalMax) {
                globalMax = currentMax;
            }
        }
        return globalMax;
    }

    public int maxProductV2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // dpMax[i]: 以 nums[i] 结尾的连续子数组的最大乘积
        // dpMin[i]: 以 nums[i] 结尾的连续子数组的最小乘积
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];

        // 初始化
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int result = nums[0];

        for (int i = 1; i < n; i++) {
            int num = nums[i];

            // 逻辑完全一样，只是写成了数组形式，不用担心覆盖问题
            int cand1 = num;
            int cand2 = dpMax[i-1] * num;
            int cand3 = dpMin[i-1] * num;

            dpMax[i] = Math.max(cand1, Math.max(cand2, cand3));
            dpMin[i] = Math.min(cand1, Math.min(cand2, cand3));

            result = Math.max(result, dpMax[i]);
        }

        return result;
    }
}
