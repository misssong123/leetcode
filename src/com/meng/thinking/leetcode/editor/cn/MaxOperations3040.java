package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

class MaxOperations3040 {
    int[] nums;
    int[][] memo;
    /**
     * 解答成功:
     * 	执行耗时:133 ms,击败了73.01% 的Java用户
     * 	内存消耗:75.6 MB,击败了48.47% 的Java用户
     * @param nums
     * @return
     */
    public int maxOperations(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        this.memo = new int[n][n];
        int res = 0;
        res = Math.max(res, helper(0, n - 1, nums[0] + nums[n - 1]));
        res = Math.max(res, helper(0, n - 1, nums[0] + nums[1]));
        res = Math.max(res, helper(0, n - 1, nums[n - 2] + nums[n - 1]));
        return res;
    }

    public int helper(int i, int j, int target) {
        for (int k = 0; k < nums.length; k++) {
            Arrays.fill(memo[k], -1);
        }
        return dfs(i, j, target);
    }

    public int dfs(int i, int j, int target) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int ans = 0;
        if (nums[i] + nums[i + 1] == target) {
            ans = Math.max(ans, dfs(i + 2, j, target) + 1);
        }
        if (nums[j - 1] + nums[j] == target) {
            ans = Math.max(ans, dfs(i, j - 2, target) + 1);
        }
        if (nums[i] + nums[j] == target) {
            ans = Math.max(ans, dfs(i + 1, j - 1, target) + 1);
        }
        memo[i][j] = ans;
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
