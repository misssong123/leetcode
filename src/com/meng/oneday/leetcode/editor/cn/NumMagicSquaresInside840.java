package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class NumMagicSquaresInside840 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了30.56% 的Java用户
     * 	内存消耗:42.4 MB,击败了50.00% 的Java用户
     * @param grid
     * @return
     */
    public int numMagicSquaresInside840(int[][] grid) {
        int val = 15;
        int row = grid.length,col = grid[0].length;
        Set<int[]> disabled = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] > 9 || grid[i][j] < 1){
                    disabled.add(new int[]{i,j});
                }
            }
        }
        int res  = 0;
        for (int i = 0 ; i < row -2 ; i++){
            for (int j = 0 ; j < col -2 ; j++){
                //中心是否为5
                if (grid[i+1][j+1] != 5){
                    continue;
                }
                boolean flag = false;
                for (int [] d : disabled){
                    if (d[0] >= i && d[0] <= i+2 && d[1] >= j && d[1] <= j+2){
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    continue;
                }
                if (judge(grid,i,j,val)){
                    res++;
                }
            }
        }
        return res;
    }

    private boolean judge(int[][] grid, int i, int j, int val) {
        int[] arr = new int[9];
        for (int k = 0 ; k < 3 ; k++){
            for (int l = 0 ; l < 3 ; l++){
                int num = grid[i+k][j+l];
                if (num < 1 || num > 9){
                    return false;
                }
                arr[num-1]++;
                if (arr[num-1] > 1){
                    return false;
                }
            }
        }
        return grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2] == val &&
                grid[i+2][j] + grid[i+1][j+1] + grid[i][j+2] == val &&
                grid[i][j] + grid[i][j+1] + grid[i][j+2] == val &&
                grid[i+1][j] + grid[i+1][j+1] + grid[i+1][j+2] == val &&
                grid[i][j] + grid[i+1][j] + grid[i+2][j] == val &&
                grid[i][j+1] + grid[i+1][j+1] + grid[i+2][j+1] == val ;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了16.67% 的Java用户
     * @param grid
     * @return
     */
    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (grid[i + 1][j + 1] != 5) {
                    continue;
                }

                int mask = 0;
                int[] rSum = new int[3];
                int[] cSum = new int[3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        int x = grid[i + r][j + c];
                        mask |= 1 << x;
                        rSum[r] += x;
                        cSum[c] += x;
                    }
                }

                if (mask == (1 << 10) - 2 &&
                        rSum[0] == 15 && rSum[1] == 15 &&
                        cSum[0] == 15 && cSum[1] == 15) {
                    ans++;
                }
            }
        }

        return ans;
    }

}
