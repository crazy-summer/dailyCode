package Algorithm.slideWindow;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++){
            while(!dq.isEmpty() && nums[i] > nums[dq.peekLast()]){
                dq.pollLast();
            }
            dq.offerLast(i);
            // 移除过期下标(如果队头下标不在[i-k+1, i]之内)
            if(dq.peekFirst() < i-k+1){
                dq.pollFirst();
            }
            // 当窗口已经形成
            if(i >= k - 1){
                res[i-k+1] = (nums[dq.peekFirst()]);
            }
        }
        return res;
    }
}
