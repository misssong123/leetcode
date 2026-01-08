package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaxDotProduct1458 {
    /**
     * 递归
     * 解答成功:
     * 	执行耗时:51 ms,击败了5.71% 的Java用户
     * 	内存消耗:50.5 MB,击败了5.24% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    int[][][] dp = null;
    public int maxDotProduct1458(int[] nums1, int[] nums2) {
        int x = nums1.length;
        int y = nums2.length;
        dp = new int[x][y][2];
        for (int[][] arr : dp){
            for(int[] arr1 : arr){
                Arrays.fill(arr1,Integer.MIN_VALUE);
            }

        }
        return dfs(nums1,x-1,nums2,y-1,false);
    }
    private int dfs(int[] nums1, int x, int[] nums2, int y,boolean isChoose){
        if (x < 0 || y < 0){
            return 0;
        }
        if (dp[x][y][isChoose?0:1] != Integer.MIN_VALUE){
            return dp[x][y][isChoose?0:1];
        }
        //选择x和y
        int max = nums1[x] * nums2[y] + dfs(nums1,x-1,nums2,y-1,true);
        if ( (x > 0 && y > 0) || isChoose){
            //选择x，不选择y
            max = Math.max(max,dfs(nums1,x-1,nums2,y,isChoose));
            //不选择x，选择y
            max = Math.max(max,dfs(nums1,x,nums2,y-1,isChoose));
            //不选择x和y
            max = Math.max(max,dfs(nums1,x-1,nums2,y-1,isChoose));
        }
        return dp[x][y][isChoose?0:1] = max;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了53.33% 的Java用户
     * 	内存消耗:45.7 MB,击败了31.43% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDotProductOther1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] f = new int[n + 1][m + 1];
        for (int[] row : f) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[i + 1][j + 1] = Math.max(
                        Math.max(f[i][j], 0) + nums1[i] * nums2[j],
                        Math.max(f[i][j + 1], f[i + 1][j])
                );
            }
        }
        return f[n][m];
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了90.48% 的Java用户
     * 	内存消耗:42.8 MB,击败了79.05% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDotProductOther2(int[] nums1, int[] nums2) {
        int m = nums2.length;
        int[] f = new int[m + 1];
        Arrays.fill(f, Integer.MIN_VALUE);
        for (int x : nums1) {
            int pre = f[0];
            for (int j = 0; j < m; j++) {
                int tmp = f[j + 1];
                f[j + 1] = Math.max(Math.max(pre, 0) + x * nums2[j], Math.max(f[j + 1], f[j]));
                pre = tmp;
            }
        }
        return f[m];
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了78.57% 的Java用户
     * 	内存消耗:45.9 MB,击败了8.57% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int x = nums1.length , y = nums2.length;
        int[][] dp = new int[x+1][y+1];
        for (int[] arr : dp){
            Arrays.fill(arr, Integer.MIN_VALUE);
        }
        for( int i = 0 ; i < x ; i ++ ){
            for (int j = 0 ; j < y ; j++ ){
                dp[i+1][j+1] = Math.max(Math.max(dp[i][j],0) + nums1[i] * nums2[j],
                        Math.max(dp[i+1][j],dp[i][j+1]));
            }
        }
        return dp[x][y];
    }
}