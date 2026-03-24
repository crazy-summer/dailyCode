package Algorithm.Tree;

public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        Long min = Long.MIN_VALUE;
        Long max = Long.MAX_VALUE;
        return helper(root, min, max);
    }
    private boolean helper(TreeNode node, Long min, Long max){
        if (node == null) return true;
        if(!(node.val > min && node.val < max)) return false;
        return helper(node.left, min, (long) node.val) && helper(node.right, (long) node.val, max);
    }
}
