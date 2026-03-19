package com.meng.oneday.leetcode.editor.cn;

class NumberOfSubMatrices3212 {
    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了36.26% 的Java用户
     * 	内存消耗:223.7 MB,击败了96.70% 的Java用户
     * @param grid
     * @return
     */
    public int numberOfSubmatrices3212(char[][] grid) {
        int m = grid.length , n = grid[0].length;
        Node[][] dp = new Node[m][n];
        int res = 0;
        for(int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j++){
                int x = 0 , y = 0;
                //左侧
                if(j > 0){
                    x += dp[i][j-1].x;
                    y += dp[i][j-1].y;
                }
                //上侧
                if(i > 0){
                    x += dp[i-1][j].x;
                    y += dp[i-1][j].y;
                }
                //左上
                if(i > 0 && j > 0){
                    x -= dp[i-1][j-1].x;
                    y -= dp[i-1][j-1].y;
                }
                x += grid[i][j] == 'X' ? 1 : 0;
                y += grid[i][j] == 'Y' ? 1 : 0;
                dp[i][j] = new Node(x,y);
                if (x > 0 && x == y){
                    res++;
                }
            }
        }
        return res;
    }
    class Node{
        int x ;
        int y;
        public Node(int x , int y){
            this.x = x;
            this.y = y;
        }
        public Node(){

        }
    }

    /**
     * 解答成功:
     * 	执行耗时:100 ms,击败了8.79% 的Java用户
     * 	内存消耗:238.9 MB,击败了68.13% 的Java用户
     * @param grid
     * @return
     */
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] sum = new int[m + 1][n + 1][2];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1][0] = sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i][j][0];
                sum[i + 1][j + 1][1] = sum[i + 1][j][1] + sum[i][j + 1][1] - sum[i][j][1];
                if (grid[i][j] != '.') {
                    sum[i + 1][j + 1][grid[i][j] & 1]++;
                }
                if (sum[i + 1][j + 1][0] > 0 && sum[i + 1][j + 1][0] == sum[i + 1][j + 1][1]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
