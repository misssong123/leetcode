package com.meng.oneday.leetcode.editor.cn;

class CountSubMatrices3070 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了85.71% 的Java用户
     * 	内存消耗:157.7 MB,击败了86.39% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int countSubmatrices3070(int[][] grid, int k) {
        int m = grid.length , n = grid[0].length ;
        int res = 0;
        for (int i = 0; i < m ; i++){
            for (int j = 0 ;j < n ; j++){
                int left = 0 , top = 0,leftTop = 0;
                //左侧
                if(j > 0){
                    left = grid[i][j-1];
                }
                //上侧
                if (i > 0){
                    top = grid[i-1][j];
                }
                //左上
                if (i > 0 && j > 0){
                    leftTop = grid[i-1][j-1];
                }
                int sum = grid[i][j] + left + top -leftTop;
                if (sum > k){
                    n = j;
                    if(j == 0){
                        m = i;
                    }
                    break;
                }
                grid[i][j] = sum;
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:157.6 MB,击败了94.56% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid[0].length;
        int[] colSum = new int[n];
        int ans = 0;
        for (int[] row : grid) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                colSum[j] += row[j];
                s += colSum[j];
                if (s > k) {
                    break;
                }
                ans++;
            }
        }
        return ans;
    }

}
