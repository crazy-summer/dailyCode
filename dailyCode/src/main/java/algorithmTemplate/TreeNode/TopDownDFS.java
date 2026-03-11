package algorithmTemplate.TreeNode;

public class TopDownDFS {
    int globalResult = 0; // 如果需要全局结果

    public int solve(TreeNode root) {
        // 初始调用，传入初始参数（如 depth=1, currentSum=0）
        dfs(root, 1, 0);
        return globalResult;
    }
    // extraParam 是根据题目变化的额外参数（如当前深度、当前路径和等）
    private void dfs(TreeNode node, int depth, int currentSum) {
        if (node == null) {
            return;
        }
        // 1. 更新当前状态（前序逻辑）
        currentSum += node.val;
        // 2. 判断是否达到叶子节点或满足特定条件
        if (node.left == null && node.right == null) {
            // 到达叶子，处理结果
            globalResult = Math.max(globalResult, currentSum);
            return;
        }
        // 3. 将更新后的状态传递给子节点
        dfs(node.left, depth + 1, currentSum);
        dfs(node.right, depth + 1, currentSum);

        // 注意：如果是求所有路径（回溯），这里需要撤销 currentSum 的操作
        // 但如果是简单的最大值/存在性判断，通常不需要显式撤销，因为Java参数是值传递
    }
}
