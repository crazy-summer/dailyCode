package odQuestions;

import java.util.*;

class Result {
    int inX;
    int inY;
    int area;
    public Result(int inX, int inY, int area) {
        this.inX = inX;
        this.inY = inY;
        this.area = area;
    }
}

public class FindSingleInArea {
    // up down left right
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    int dfs(char[][] grid,boolean[][] visited, List<int[]> inPoints, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        // 出界返回0
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] == 'X') {
            return 0;
        }
        // 表格边界，添加坐标到inPoints
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            inPoints.add(new int[]{x, y});
        }
        visited[x][y] = true;
        int area = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            area = area + dfs(grid, visited, inPoints, nx, ny);
        }
        return area;
    }

    public void solve(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<Result> results = new ArrayList<>();
        // 遍历表格，寻找入口
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是O并且没有被访问过，那么就开始dfs
                if (grid[i][j] == 'O' && !visited[i][j]) {
                    // 存放这个O区域in的坐标
                    List<int[]> inPoints = new ArrayList<>();
                    int area = dfs(grid, visited, inPoints, i, j);
                    // 如果只有一个in，也就是单入口区域，那么就放到results里面
                    if (inPoints.size() == 1) {
                        int[] point = inPoints.get(0);
                        results.add(new Result(point[0], point[1], area));
                    }
                }
            }
        }
        // 将单入口区域按照面积大小降序排序
        results.sort((r1, r2) -> Integer.compare(r2.area, r1.area));

        // 如果有多个单入口区域，再如果多个最大区域面积相同，就只输出面积，否则输出坐标和面积
        if (results.size() > 1) {
            Result first = results.get(0);
            Result second = results.get(1);
            if (first.area == second.area) {
                System.out.println(first.area);
            }else {
                System.out.printf("%d %d %d%n", first.inX, first.inY, first.area);
            }
        }else if (results.isEmpty()) {
            // 没有单入口区域，输出NULL
            System.out.println("NULL");
        }else { // 如果只有一个单入口区域，输出坐标和面积
            Result first = results.get(0);
            System.out.printf("%d %d %d%n", first.inX, first.inY, first.area);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }
        FindSingleInArea obj = new FindSingleInArea();
        obj.solve(grid);
    }
}
