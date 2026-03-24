package Algorithm.Tree;

public class TreeFlattenToLinkedList {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null){
            if(curr.left != null){
                TreeNode predecessor = curr.left;
                while(predecessor.right != null){
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
