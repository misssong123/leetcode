package com.meng.oneday.leetcode.editor.cn;

class BestClosingTime2483 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了88.89% 的Java用户
     * 	内存消耗:46.3 MB,击败了22.22% 的Java用户
     * @param customers
     * @return
     */
    public int bestClosingTime2483(String customers) {
        int len = customers.length();
        int[] dp = new int[len+1];
        for(int i = len - 1 ; i >=0 ; i--){
            dp[i] = dp[i+1] + (customers.charAt(i) == 'Y' ? 1 : 0);
        }
        int cost = len - dp[0];
        int res = len;
        for(int i = len - 1 ; i >= 0 ; i--){
            //计算当前关门的代价
            int newCost = i - (dp[0] - dp[i])+dp[i];
            if (newCost <= cost){
                res = i;
                cost = newCost;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了88.89% 的Java用户
     * 	内存消耗:46.4 MB,击败了13.33% 的Java用户
     * @param customers
     * @return
     */
    public int bestClosingTime(String customers) {
        int penalty = 0;
        int minPenalty = 0;
        int ans = 0;
        for (int i = 0; i < customers.length(); i++) {
            penalty += customers.charAt(i) == 'N' ? 1 : -1;
            if (penalty < minPenalty) {
                minPenalty = penalty;
                ans = i + 1;
            }
        }
        return ans;
    }

}
