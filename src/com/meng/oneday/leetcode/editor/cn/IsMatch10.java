package com.meng.oneday.leetcode.editor.cn;

class IsMatch10 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了18.61% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch10(String s, String p) {
        int m = s.length() , n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //初始化为*的情况
        for(int i = 1 ; i < n ; i+=2){
            if(p.charAt(i) == '*'){
                dp[0][i+1] = true;
            }else {
                break;
            }
        }
        //字符匹配
        for(int i = 1 ; i <= m ; i++){
            for (int j = 1 ; j <= n ; j++){
                char pChar = p.charAt(j-1);
                char sChar = s.charAt(i-1);
                if (pChar == sChar || pChar == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (pChar == '*'){
                    dp[i][j] = dp[i][j-1] ||dp[i][j-2] || (dp[i-1][j] && (sChar == p.charAt(j-2) || p.charAt(j-2) == '.'));
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了74.05% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchOfficial(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
