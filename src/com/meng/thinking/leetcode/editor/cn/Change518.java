package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Change518 {

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.92% 的Java用户
     * 	内存消耗:40.1 MB,击败了64.70% 的Java用户
     * @param amount
     * @param coins
     * @return
     */
    public int changeMy(int amount, int[] coins) {
        if (amount == 0){
            return 1;
        }
        int[] cache = new int[amount+1];
        for(int coin : coins){
            if (coin > amount){
                continue;
            }
            cache[coin]++;
            for(int i = coin ; i <=amount ; i++){
                cache[i] += cache[i-coin];
            }
        }
        return cache[amount];
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.92% 的Java用户
     * 	内存消耗:40.1 MB,击败了65.48% 的Java用户
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
