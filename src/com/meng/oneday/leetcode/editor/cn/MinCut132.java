package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinCut132 {
    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了64.79% 的Java用户
     * 	内存消耗:45.8 MB,击败了86.27% 的Java用户
     * @param s
     * @return
     */
    public int minCut(String s) {
        int len = s.length();
        boolean[][] dp =  new boolean[len][len];
        //构建回文串
        for(int i = 0 ; i < len ; i ++){
            for(int j = i ; j >=0 ; j--){
                if (i == j){
                    dp[i][j] = true;
                }else{
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                    if (dp[i][j] && i - j > 1){
                        dp[i][j] = dp[i][j] && dp[i-1][j+1];
                    }
                }
            }
        }
        int[] f = new int[len];
        for(int i = 0 ; i < len ; i++){
            if(dp[i][0]){
                continue;
            }
            int res = i;
            for(int j = 0 ; j <= i ; j++){
                if (dp[i][j]){
                    res = Math.min(res,f[j-1]+1);
                }
            }
            f[i] = res;
        }
        return f[len -1];
    }
    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了76.87% 的Java用户
     * 	内存消耗:45.7 MB,击败了82.05% 的Java用户
     * @param S
     * @return
     */
    public int minCutOther(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        // isPalindrome[l][r] 表示 s[l] 到 s[r] 是否为回文串
        boolean[][] isPalindrome = new boolean[n][n];
        for (boolean[] row : isPalindrome) {
            Arrays.fill(row, true);
        }
        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                isPalindrome[l][r] = s[l] == s[r] && isPalindrome[l + 1][r - 1];
            }
        }

        int[] f = new int[n];
        for (int r = 0; r < n; r++) {
            if (isPalindrome[0][r]) { // 已是回文串，无需分割
                continue;
            }
            int res = Integer.MAX_VALUE;
            for (int l = 1; l <= r; l++) { // 枚举分割位置
                if (isPalindrome[l][r]) {
                    res = Math.min(res, f[l - 1] + 1); // 在 l-1 和 l 之间切一刀
                }
            }
            f[r] = res;
        }
        return f[n - 1];
    }

}
