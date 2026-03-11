package algorithmTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DfsGraphTemplate {
    // 记录节点是否被访问过
    boolean[] visited;

    public void solve(List<List<Integer>> graph) {
        int n = graph.size();
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(List<List<Integer>> graph, int node) {
        // 1. 处理当前节点
        System.out.println("访问节点: " + node);
        visited[node] = true; // 标记已访问
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor);
            }
        }
    }
}
