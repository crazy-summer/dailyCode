package algorithmTemplate;

import java.util.ArrayList;
import java.util.List;

public class BackTrackingTemplate {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        BackTrackingTemplate bt = new BackTrackingTemplate();
        bt.backtracking(arr, 0, 2, bt.path, bt.result);
        System.out.println(bt.result);
    }
    void backtracking(int[] arr, int startIndex, int n, List<Integer> path, List<List<Integer>> result) {
        // base
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < arr.length; i++) {
            path.add(arr[i]);
            backtracking(arr, i + 1, n, path, result);
            path.removeLast();
        }
    }
}
