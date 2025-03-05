package com.meng.oneday.leetcode.editor.cn;

class CheckPartitioning1745 {
    /**
     * 解答成功:
     * 	执行耗时:159 ms,击败了15.36% 的Java用户
     * 	内存消耗:45.2 MB,击败了61.75% 的Java用户
     * @param s
     * @return
     */
    public boolean checkPartitioning(String s) {
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
        for(int i = 0 ; i < len-2 ; i++){
            if(dp[i][0]){
                for(int j = i + 1 ; j < len -1;j++){
                    if (dp[j][i+1]&&dp[len-1][j+1]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkPartitioningOthers(String s) {
        return palindromePartition(s, 3) == 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:147 ms,击败了18.18% 的Java用户
     * 	内存消耗:68.4 MB,击败了5.45% 的Java用户
     * @param S
     * @param k
     * @return
     */
    private int palindromePartition(String S, int k) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[][] minChange = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                minChange[i][j] = minChange[i + 1][j - 1] + (s[i] != s[j] ? 1 : 0);
            }
        }

        int[] f = minChange[0];
        for (int i = 1; i < k; i++) {
            for (int r = n - k + i; r >= i; r--) {
                f[r] = Integer.MAX_VALUE;
                for (int l = i; l <= r; l++) {
                    f[r] = Math.min(f[r], f[l - 1] + minChange[l][r]);
                }
            }
        }
        return f[n - 1];
    }
}
