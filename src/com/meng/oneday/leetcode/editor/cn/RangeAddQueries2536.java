package com.meng.oneday.leetcode.editor.cn;

class RangeAddQueries2536 {
    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了18.38% 的Java用户
     * 	内存消耗:55.1 MB,击败了29.41% 的Java用户
     * @param n
     * @param queries
     * @return
     */
    public int[][] rangeAddQueries2536(int n, int[][] queries) {
        int[][] cache = new int[n][n];
        for(int[] query : queries){
            for(int i = query[0] ; i <=query[2] ; i++ ){
                cache[i][query[1]]++;
                if (query[3]+1 < n){
                    cache[i][query[3]+1]--;
                }
            }
        }
        int[][]res = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            int temp = 0;
            for(int j = 0 ; j < n ; j++){
                temp += cache[i][j];
                res[i][j] = temp + res[i][j];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了99.26% 的Java用户
     * 	内存消耗:54.6 MB,击败了64.71% 的Java用户
     * @param n
     * @param queries
     * @return
     */
    public int[][] rangeAddQueries(int n, int[][] queries) {
        // 二维差分
        int[][] diff = new int[n + 2][n + 2];
        for (int[] q : queries) {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
            diff[r1 + 1][c1 + 1]++;
            diff[r1 + 1][c2 + 2]--;
            diff[r2 + 2][c1 + 1]--;
            diff[r2 + 2][c2 + 2]++;
        }
        // 计算 diff 的二维前缀和（原地修改），然后填入 ans
        int[][] ans = new int[n][n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                diff[i][j] += diff[i][j - 1] + diff[i - 1][j] - diff[i - 1][j - 1];
                ans[i - 1][j - 1] = diff[i][j];
            }
        }
        return ans;
    }
}
