package Algorithm.Tree;

import java.util.HashMap;

public class BuildTreeFromPreOrderAndInOrderArray {
    private int[] preorder;
    private HashMap<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            this.inorderMap.put(inorder[i], i);
        }
        return helper(0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode helper(int preLeft, int preRight, int inLeft, int inRight){
        if(preLeft > preRight){
            return null;
        }
        int rootValue = preorder[preLeft];
        TreeNode root = new TreeNode(rootValue);
        int inorderRootValueIndex = inorderMap.get(rootValue);
        int leftSize = inorderRootValueIndex - inLeft;
        root.left = helper(preLeft+1, preLeft+leftSize, inLeft, inorderRootValueIndex-1);
        root.right = helper(preLeft+leftSize+1, preRight, inorderRootValueIndex+1, inRight);
        return root;
    }
}
