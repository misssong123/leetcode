package com.meng.oneday.leetcode.editor.cn;

class WordPuzzleLCR129 {
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了78.47% 的Java用户
     * 	内存消耗:44.9 MB,击败了55.47% 的Java用户
     * @param grid
     * @param target
     * @return
     */
    public boolean wordPuzzleLCR129(char[][] grid, String target) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j++){
                if (grid[i][j] == target.charAt(0)){
                    visited[i][j] = true;
                    if (dfs(grid, visited, target, i, j, 1)){
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visited, String target, int i, int j, int index) {
        //判断是否满足要求
        if (index == target.length()){
            return true;
        }
        int m = grid.length, n = grid[0].length;
        //递归
        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == target.charAt(index)){
                visited[x][y] = true;
                if (dfs(grid, visited, target, x, y, index+1)){
                    return true;
                }
                visited[x][y] = false;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了23.72% 的Java用户
     * 	内存消耗:44.6 MB,击败了85.40% 的Java用户
     * @param grid
     * @param word
     * @return
     */
    public boolean wordPuzzle(char[][] grid, String word) {
        int h = grid.length, w = grid[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = exist(grid, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] grid, boolean[][] visited, int i, int j, String s, int k) {
        if (grid[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < grid.length && newj >= 0 && newj < grid[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = exist(grid, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

}
