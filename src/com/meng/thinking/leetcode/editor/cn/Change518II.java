package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Change518II {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.9 MB,击败了97.89% 的Java用户
     * @param amount
     * @param coins
     * @return
     */
    public int changeMy(int amount, int[] coins) {
        if (amount == 0){
            return 1;
        }
        int[] dp = new int[amount + 1];
        //遍历硬币组合
        for(int coin : coins){
            if (coin > amount){
                continue;
            }
            dp[coin]++;
            for(int i = coin +1 ; i<= amount; i++){
                dp[i] = dp[i] + dp[i-coin];
            }
        }
        return  dp[amount];
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:40 MB,击败了83.36% 的Java用户
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }


}
//leetcode submit region end(Prohibit modification and deletion)
