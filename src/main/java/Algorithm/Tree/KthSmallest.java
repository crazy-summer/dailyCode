package Algorithm.Tree;

public class KthSmallest {
    private int res = 0;
    public int kthSmallest(TreeNode root, int k) {
        helper(root, 0, k);
        return res;
    }

    private int helper(TreeNode node, int count, int k){
        if(node == null){
            return count;
        }
        count = helper(node.left, count, k);
        count++;
        if(count == k){
            res = node.val;
        }
        count = helper(node.right, count, k);
        return count;
    }
}
