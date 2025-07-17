package com.meng.oneday.leetcode.editor.cn;

class MaximumLength3202 {
    /**
     * 解答成功:
     * 	执行耗时:46 ms,击败了31.34% 的Java用户
     * 	内存消耗:49.1 MB,击败了82.09% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximumLength3202(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k];
        int res = 0;
        for(int i = 1 ; i < n ; i++){
            for(int j = i - 1 ; j >=0; j--){
                int mod = (nums[j] + nums[i]) % k;
                dp[i][mod] = Math.max(dp[i][mod],Math.max(dp[j][mod]+1,2));
                res = Math.max(res,dp[i][mod]);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了94.03% 的Java用户
     * 	内存消耗:53 MB,击败了61.19% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximumLengthOther1(int[] nums, int k) {
        int[][] dp = new int[k][k];
        int res = 0;
        for (int num : nums) {
            num %= k;
            for (int prev = 0; prev < k; prev++) {
                dp[prev][num] = dp[num][prev] + 1;
                res = Math.max(res, dp[prev][num]);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:50 ms,击败了26.87% 的Java用户
     * 	内存消耗:44 MB,击败了95.52% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximumLength(int[] nums, int k) {
        int ans = 0;
        for (int m = 0; m < k; m++) {
            int[] f = new int[k];
            for (int x : nums) {
                x %= k;
                f[x] = f[(m - x + k) % k] + 1;
                ans = Math.max(ans, f[x]);
            }
        }
        return ans;
    }

}
