package algorithmTemplate.bfsTemplate;

import java.util.LinkedList;
import java.util.Queue;

//通用 BFS 模板 (图 / 网格 / 迷宫)
//适用场景：寻找两点间最短路径、单词接龙、打开转盘锁、岛屿最小距离等。
public class GeneralBFS {
    // 方向数组 (如果是网格问题)
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int bfs(int[][] grid, int[] start, int[] target) {
        int m = grid.length;
        int n = grid[0].length;

        // 1. 核心数据结构：队列 (存放待访问的节点)
        Queue<int[]> queue = new LinkedList<>();
        // 2. 记录已访问节点 (防止走回头路/死循环)
        // 对于网格，可以直接修改 grid 值，或者用 boolean[][] visited
        boolean[][] visited = new boolean[m][n];
        // 3. 初始化：起点入队，标记已访问
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        int steps = 0; // 记录层数/步数

        // 4. 开始循环：只要队列不空，就说明还有路可走
        while (!queue.isEmpty()) {
            int size = queue.size(); // 【关键】记录当前层的节点数量
            // 遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];

                // 5. 判断是否到达终点
                if (x == target[0] && y == target[1]) {
                    return steps;
                }

                // 6. 将当前节点的【所有未访问邻居】加入队列
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    // 剪枝：越界检查 + 障碍物检查 + 是否已访问
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n
                            && grid[nx][ny] != 1 // 假设1是障碍物
                            && !visited[nx][ny]) {

                        visited[nx][ny] = true; // 标记为已访问 (必须在入队时标记！)
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            steps++;
        }
        return -1; // 如果队列空了还没找到，说明不可达
    }
}
