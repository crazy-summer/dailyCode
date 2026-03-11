package algorithmTemplate.dfsTemplate;

public class TreeDFS {
    // 主函数
    public void traverse(TreeNode root) {
        if (root == null) {
            return; // 终止条件：遇到空节点
        }
        // --- 前序位置 (Pre-order) ---
        // 在这里处理当前节点（例如：打印、累加、判断条件）
        System.out.println("访问: " + root.val);
        // 递归左子树
        traverse(root.left);
        // --- 中序位置 (In-order) ---
        // 在这里处理（例如：二叉搜索树的中序遍历是有序的）

        // 递归右子树
        traverse(root.right);

        // --- 后序位置 (Post-order) ---
        // 在这里处理（例如：计算高度、销毁节点、自底向上的统计）
    }
}
