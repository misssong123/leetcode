package com.meng.thinking.leetcode.editor.cn;

class MaxScore2786 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了87.79% 的Java用户
     * 	内存消耗:58.5 MB,击败了55.72% 的Java用户
     * @param nums
     * @param x
     * @return
     */
    public long maxScoreMy(int[] nums, int x) {
        long[] cache = {-x,-x};
        cache[nums[0]%2] = nums[0];
        for (int i = 1 ; i < nums.length ; i++){
            int index =  nums[i] % 2;
            cache[index] = Math.max(cache[index],cache[1-index]-x)+nums[i];
        }
        return Math.max(cache[0],cache[1]);
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了54.20% 的Java用户
     * 	内存消耗:58.7 MB,击败了31.30% 的Java用户
     * @param nums
     * @param x
     * @return
     */
    public long maxScore(int[] nums, int x) {
        long res = nums[0];
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0] % 2] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int parity = (int) (nums[i] % 2);
            long cur = Math.max(dp[parity] + nums[i], dp[1 - parity] - x + nums[i]);
            res = Math.max(res, cur);
            dp[parity] = Math.max(dp[parity], cur);
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
