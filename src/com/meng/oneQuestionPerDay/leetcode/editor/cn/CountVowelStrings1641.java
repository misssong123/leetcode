package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class CountVowelStrings1641 {
    int sum =0;
    public int countVowelStrings(int n) {
        dfs(0,n,-1);
        return sum;
    }
    private void dfs(int i, int n,int pre) {
        if (i == n){
            sum++;
            return;
        }
        for (int j = 0 ; j < 5 ; j++){
            if (i==0)
                dfs(i+1,n,j);
            if (i>0 && j >=pre){
                dfs(i+1,n,j);
            }
        }
    }

    /**
     * 方法一：动态规划
     * @param n
     * @return
     */
    public int countVowelStrings1(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 5; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return Arrays.stream(dp).sum();
    }

    /**
     * 方法二：组合数学
     * @param n
     * @return
     */
    public int countVowelStrings2(int n) {
        return (n + 1) * (n + 2) * (n + 3) * (n + 4) / 24;
    }


}

