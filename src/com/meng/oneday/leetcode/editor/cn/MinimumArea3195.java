package com.meng.oneday.leetcode.editor.cn;

class MinimumArea3195 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了98.08% 的Java用户
     * 	内存消耗:192.8 MB,击败了53.85% 的Java用户
     * @param grid
     * @return
     */
    public int minimumArea3195(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int l = n , r = 0 , t = m , b = 0;
        for(int i = 0 ; i < m ; i ++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 1){
                    l = Math.min(l,j);
                    r = Math.max(r,j);
                    t = Math.min(t,i);
                    b = Math.max(b,i);
                }
            }
        }
        return (r - l + 1) * (b - t + 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了98.08% 的Java用户
     * 	内存消耗:193 MB,击败了23.08% 的Java用户
     * @param grid
     * @return
     */
    public int minimumArea(int[][] grid) {
        int left = Integer.MAX_VALUE;
        int right = 0;
        int top = Integer.MAX_VALUE;
        int bottom = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    top = Math.min(top, i);
                    bottom = i;
                }
            }
        }
        return (right - left + 1) * (bottom - top + 1);
    }
}
