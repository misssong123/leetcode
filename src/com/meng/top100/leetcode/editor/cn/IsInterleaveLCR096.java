package com.meng.top100.leetcode.editor.cn;

class IsInterleaveLCR096 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了75.91% 的Java用户
     * 	内存消耗:42.5 MB,击败了7.30% 的Java用户
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveLCR096(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        if (s3.isEmpty()){
            return true;
        }
        int m =  s1.length(),n = s2.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        char[] chars = s3.toCharArray();
        //初始化
        for (int i = 1 ; i<= m ; i++){
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == chars[i-1];
        }
        for (int i = 1 ; i <= n ; i++){
            dp[0][i] = dp[0][i-1] && s2.charAt(i-1) == chars[i-1];
        }
        //状态转移
        for(int i = 1 ; i <= m ; i++){
            for(int j = 1 ; j <= n ; j++){
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == chars[i+j-1])
                        || (dp[i][j-1] && s2.charAt(j-1) == chars[i+j-1]);
            }
        }
        return dp[m][n];
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了92.70% 的Java用户
     * 	内存消耗:42.4 MB,击败了10.95% 的Java用户
     * @param S1
     * @param S2
     * @param S3
     * @return
     */
    public boolean isInterleave(String S1, String S2, String S3) {
        int n = S1.length();
        int m = S2.length();
        if (n + m != S3.length()) {
            return false;
        }

        char[] s1 = S1.toCharArray();
        char[] s2 = S2.toCharArray();
        char[] s3 = S3.toCharArray();

        boolean[] f = new boolean[m + 1];
        f[0] = true;
        for (int j = 0; j < m; j++) {
            f[j + 1] = f[j] && s2[j] == s3[j];
        }
        for (int i = 0; i < n; i++) {
            f[0] = f[0] && s1[i] == s3[i];
            for (int j = 0; j < m; j++) {
                f[j + 1] = f[j + 1] && s1[i] == s3[i + j + 1] ||
                        f[j] && s2[j] == s3[i + j + 1];
            }
        }
        return f[m];
    }
}
