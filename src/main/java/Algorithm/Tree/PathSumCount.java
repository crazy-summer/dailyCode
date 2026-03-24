package Algorithm.Tree;

import java.util.HashMap;

public class PathSumCount {
    public int pathSum(TreeNode root, int targetSum) {
        // k是sum，v是次数
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return recurse(root, targetSum, 0, map, 0);
    }

    private int recurse(TreeNode node, int targetSum, long path, HashMap<Long, Integer> map, int count){
        if(node == null) return count;
        path += node.val;
        if(map.containsKey(path - targetSum)){
            count = count + map.get(path - targetSum);
        }
        map.put(path, map.getOrDefault(path, 0) + 1);
        count = recurse(node.left, targetSum, path, map, count);
        count = recurse(node.right, targetSum, path, map, count);
        map.put(path, map.getOrDefault(path, 0) - 1);
        return count;
    }
}
