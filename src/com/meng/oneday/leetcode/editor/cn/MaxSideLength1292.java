package com.meng.oneday.leetcode.editor.cn;

class MaxSideLength1292 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了88.70% 的Java用户
     * 	内存消耗:56.6 MB,击败了60.87% 的Java用户
     * @param mat
     * @param threshold
     * @return
     */
    public int maxSideLength(int[][] mat, int threshold) {
        //前缀和
        int m = mat.length , n = mat[0].length;
        int[][] preSum = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i+1][j+1] = preSum[i+1][j] +preSum[i][j+1] - preSum[i][j]+ mat[i][j];
            }
        }
        //最大边长
        int max = Math.min(m,n);
        int min = 0;
        int res = 0;
        while (min <= max){
            int mid = (min + max) >> 1;
            if (check(preSum,mid,threshold)){
                res = mid;
                min = mid + 1;
            }else{
                max = mid - 1;
            }
        }
        return res;
    }

    private boolean check(int[][] preSum, int mid, int threshold) {
        int m = preSum.length -1 , n = preSum[0].length - 1;
        for (int i = 0 ; i <= m - mid ; i++){
            for (int j = 0 ; j <= n - mid ; j++){
                int sum = preSum[i+mid][j+mid] - preSum[i][j+mid] - preSum[i+mid][j] + preSum[i][j];
                if (sum <= threshold){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 解答成功:
     * 	执行耗时:59 ms,击败了19.13% 的Java用户
     * 	内存消耗:56.5 MB,击败了76.52% 的Java用户
     * @param mat
     * @param threshold
     * @return
     */
    public int maxSideLength1292(int[][] mat, int threshold) {
        //前缀和
        int m = mat.length , n = mat[0].length;
        int[][] preSum = new int[m][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i][j+1] = preSum[i][j] + mat[i][j];
            }
        }
        //最大边长
        int max = Math.min(m,n);
        int min = 0;
        int res = 0;
        while (min <= max){
            int mid = (min + max) >> 1;
            if (check1292(preSum,mid,threshold)){
                res = mid;
                min = mid + 1;
            }else{
                max = mid - 1;
            }
        }
        return res;
    }

    private boolean check1292(int[][] preSum, int mid, int threshold) {
        int m = preSum.length , n = preSum[0].length - 1;
        for (int i = 0 ; i <= m - mid ; i++){
            for (int j = 0 ; j <= n - mid ; j++){
                int sum = 0;
                for (int k = 0 ; k < mid ; k++){
                    sum += preSum[i+k][j+mid] - preSum[i+k][j];
                }
                if (sum <= threshold){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了98.26% 的Java用户
     * 	内存消耗:56.9 MB,击败了15.65% 的Java用户
     * @param mat
     * @param threshold
     * @return
     */
    public int maxSideLengthOther(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + mat[i][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 边长为 ans+1 的正方形，左上角在 (i, j)，右下角在 (i+ans, j+ans)
                while (i + ans < m && j + ans < n && query(sum, i, j, i + ans, j + ans) <= threshold) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 返回左上角在 (r1, c1)，右下角在 (r2, c2) 的子矩阵元素和
    private int query(int[][] sum, int r1, int c1, int r2, int c2) {
        return sum[r2 + 1][c2 + 1] - sum[r2 + 1][c1] - sum[r1][c2 + 1] + sum[r1][c1];
    }

}
