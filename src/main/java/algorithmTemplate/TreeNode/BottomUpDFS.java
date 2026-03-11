package algorithmTemplate.TreeNode;

public class BottomUpDFS {
    public int solve(TreeNode root) {
        return dfs(root);
    }
    // 返回值通常是 int, boolean, 或者自定义对象
    private int dfs(TreeNode node) {
        // 1. 终止条件
        if (node == null) {
            return 0; // 根据题目返回基准值，如高度为0
        }
        // 2. 递归获取左右子树的结果
        int leftVal = dfs(node.left);
        int rightVal = dfs(node.right);

        // 3. 结合左右子树的结果，计算当前节点的结果（后序逻辑）
        // 例子：计算当前树的高度 = max(左高, 右高) + 1
        int currentVal = Math.max(leftVal, rightVal) + 1;
        // 可以在这里更新全局变量（如最大直径 = 左高 + 右高）

        return currentVal; // 返回给父节点
    }
}
