package Algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int[] newHeights = new int[heights.length+2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < newHeights.length; i++){
            while(!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]){
                // 当前要处理的柱子，当前柱子扩展的正方形的最大面积是左右两边第一根长度小于当前柱子的索引差-1乘以当前柱子高度
                int cur = newHeights[stack.pop()];
                int left = stack.peek();
                maxArea = Math.max(maxArea, (i - left - 1) * cur);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
