package algorithmTemplate;

public class DfsGridTemplate {
    // 方向数组：上、下、左、右 (可选，为了代码整洁)
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public void solve(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    dfs(board, i, j);
                }
            }
        }
    }

    private void dfs(int[][] board, int x, int y) {
        int m = board.length;
        int n = board[0].length;
        // 终止条件，越界或者已经不可达
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] == 0) {
            return;
        }

        // 3. 处理当前节点（标记为已访问）
        // 这里的逻辑取决于题目：可能是标记为 '0'，也可能是放入 visited 集合
        board[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            dfs(board, nx, ny);
        }
    }
}
