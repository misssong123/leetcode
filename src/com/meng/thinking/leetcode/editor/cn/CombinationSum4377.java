package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class CombinationSum4377 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了8.76% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum41(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(target, nums, memo);
    }

    private int dfs(int i, int[] nums, int[] memo) {
        if (i == 0) { // 爬完了
            return 1;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }
        int res = 0;
        for (int x : nums) { // 枚举所有可以爬的台阶数
            if (x <= i) {
                res += dfs(i - x, nums, memo);
            }
        }
        return memo[i] = res; // 记忆化
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.38% 的Java用户
     * 	内存消耗:40 MB,击败了27.54% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int x : nums) {
                if (x <= i) {
                    f[i] += f[i - x];
                }
            }
        }
        return f[target];
    }

    /**
     * 执行耗时:1 ms,击败了96.38% 的Java用户
     * 	内存消耗:39.9 MB,击败了66.07% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4Official(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }


}
//leetcode submit region end(Prohibit modification and deletion)
