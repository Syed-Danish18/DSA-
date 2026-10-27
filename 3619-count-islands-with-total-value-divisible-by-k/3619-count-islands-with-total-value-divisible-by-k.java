class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long sum = dfs(grid, visited, i, j, m, n);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, boolean[][] visited,
                     int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n ||
            grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;

        long sum = grid[i][j];

        sum += dfs(grid, visited, i + 1, j, m, n);
        sum += dfs(grid, visited, i - 1, j, m, n);
        sum += dfs(grid, visited, i, j + 1, m, n);
        sum += dfs(grid, visited, i, j - 1, m, n);

        return sum;
    }
}