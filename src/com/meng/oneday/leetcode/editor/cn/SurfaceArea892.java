package com.meng.oneday.leetcode.editor.cn;

class SurfaceArea892 {
    private static final int[][] DIRS = {{0, 1},  {1, 0}};

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了94.12% 的Java用户
     * 	内存消耗:43.4 MB,击败了35.29% 的Java用户
     * @param grid
     * @return
     */
    public int surfaceArea892(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                int num = grid[i][j];
                if (num == 0){
                    continue;
                }
                int delNum = num - 1;
                for(int[] dir : DIRS){
                    int nX = i + dir[0];
                    int nY = j + dir[1];
                    if (nX < n && nY < n){
                        delNum += Math.min(num, grid[nX][nY]);
                    }
                }
                ans += (num * 6 - delNum * 2);
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了44.71% 的Java用户
     * 	内存消耗:43.4 MB,击败了63.53% 的Java用户
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};

        int N = grid.length;
        int ans = 0;

        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                if (grid[r][c] > 0) {
                    ans += 2;
                    for (int k = 0; k < 4; ++k) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        int nv = 0;
                        if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                            nv = grid[nr][nc];
                        }

                        ans += Math.max(grid[r][c] - nv, 0);
                    }
                }
            }
        }

        return ans;
    }

}
