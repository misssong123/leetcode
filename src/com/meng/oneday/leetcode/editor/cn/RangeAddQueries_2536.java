package com.meng.oneday.leetcode.editor.cn;

class RangeAddQueries_2536 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了55.92% 的Java用户
     * 	内存消耗:75.8 MB,击败了5.26% 的Java用户
     * @param n
     * @param queries
     * @return
     */
    public int[][] rangeAddQueries2536(int n, int[][] queries) {
        int[][] diff = new int[n][n];
        //计算差分数组
        for(int[] query : queries){
            int x1 = query[0],y1 = query[1],x2 = query[2],y2 = query[3];
            diff[x1][y1]++;
            if (x2 + 1 < n){
                diff[x2+1][y1]--;
            }
            if (y2 + 1 < n){
                diff[x1][y2+1]--;
            }
            if (x2 + 1 < n && y2 + 1 < n){
                diff[x2+1][y2+1]++;
            }
        }
        //还原数组
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if (i > 0){
                    diff[i][j] += diff[i-1][j];
                }
                if (j > 0){
                    diff[i][j] += diff[i][j-1];
                }
                if (i > 0 && j > 0){
                    diff[i][j] -= diff[i-1][j-1];
                }
            }
        }
        return diff;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了55.92% 的Java用户
     * 	内存消耗:81.4 MB,击败了5.26% 的Java用户
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

        // 原地计算 diff 的二维前缀和，然后填入答案
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i + 1][j] + diff[i][j + 1] - diff[i][j];
                ans[i][j] = diff[i + 1][j + 1];
            }
        }
        return ans;
    }
}
