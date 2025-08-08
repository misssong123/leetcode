package com.meng.oneday.leetcode.editor.cn;

class SoupServings808 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了90.00% 的Java用户
     * @param n
     * @return
     */
    public double soupServings808(int n) {
        if (n >= 4451) {
            return 1;
        }
        //最多执行的次数
        n = (n + 24)/25;
        double[][] cache = new double[n+1][n+1];
        return dfs808(n, n, cache);
    }

    private double dfs808(int x, int y, double[][] cache) {
        //x,y同时消耗空
        if(x <=0 && y <=0){
            return 0.5;
        }
        //x消耗空
        if(x <= 0){
            return 1;
        }
        //y消耗空
        if(y <= 0){
            return 0;
        }
        if(cache[x][y] == 0){
            cache[x][y] = (dfs808(x-4,y,cache) + dfs808(x-3,y-1,cache) + dfs808(x-2,y-2,cache) + dfs808(x-1,y-3,cache))/4;
        }
        return cache[x][y];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了56.67% 的Java用户
     * @param n
     * @return
     */
    public double soupServingsOther(int n) {
        if (n >= 4451) {
            return 1;
        }

        n = (n + 24) / 25;
        double[][] memo = new double[n + 1][n + 1];
        return dfs(n, n, memo);
    }

    private double dfs(int a, int b, double[][] memo) {
        if (a <= 0 && b <= 0) {
            return 0.5;
        }
        if (a <= 0) {
            return 1.0;
        }
        if (b <= 0) {
            return 0.0;
        }
        if (memo[a][b] == 0) { // 没有计算过
            memo[a][b] = (dfs(a - 4, b, memo) + dfs(a - 3, b - 1, memo) + dfs(a - 2, b - 2, memo) + dfs(a - 1, b - 3, memo)) / 4;
        }
        return memo[a][b];
    }
}
