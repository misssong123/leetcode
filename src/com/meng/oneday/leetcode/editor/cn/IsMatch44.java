package com.meng.oneday.leetcode.editor.cn;

class IsMatch44 {
    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了67.25% 的Java用户
     * 	内存消耗:44.2 MB,击败了30.84% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch44(String s, String p) {
        int len1 = s.length(),len2 = p.length();
        boolean[][]dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        //避免s为0的情景
        for(int j = 1 ; j <= len2 ; j++){
            if (p.charAt(j-1) == '*'){
                dp[0][j] = true;
                continue;
            }
            break;
        }
        //遍历所有的情况
        for(int i = 1 ; i <= len1 ; i++){
            for(int j = 1 ; j <= len2 ; j++){
                char c1 = s.charAt(i-1),c2 = p.charAt(j-1);
                if (c2 == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else if (c1 == c2 || c2 == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 超时
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchMy(String s, String p) {
        if (p.isEmpty() && !s.isEmpty()){
            return false;
        }
        if (s.isEmpty()){
            if (p.isEmpty()){
                return true;
            }
            for(char c : p.toCharArray()){
                if (c != '*'){
                    return false;
                }
            }
            return true;
        }
        if(!p.contains("*")&&p.length() != s.length()){
            return false;
        }
        //首字符校验
        char first = p.charAt(0);
        if (first != '?' && first != '*'&&first!=s.charAt(0)){
            return false;
        }
        //尾字符校验
        char end = p.charAt(p.length()-1);
        if (end != '?' && end != '*'&&end!=s.charAt(s.length()-1)){
            return false;
        }
        return dfs(s,p,0,0);
    }

    private boolean dfs(String s, String p, int i, int j) {
        //符合条件
        if (i == s.length()) {
            if (j == p.length()) {
                return true;
            }
            for(int k = j ; k < p.length() ; k++){
                if (p.charAt(k) != '*'){
                    return false;
                }
            }
            return true;
        }
        //不符合条件
        if (i >= s.length() || j >= p.length()) {
            return false;
        }
        if (p.charAt(j) == '*') {
            return dfs(s, p, i+1, j + 1) || dfs(s, p, i + 1, j)|| dfs(s, p, i, j+1);
        }else if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
            return dfs(s, p, i + 1, j + 1);
        }else {
            return false;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了84.58% 的Java用户
     * 	内存消耗:45.1 MB,击败了25.00% 的Java用户
     * @param ss
     * @param pp
     * @return
     */
    public boolean isMatchOther(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        // 技巧：往原字符头部插入空格，这样得到 char 数组是从 1 开始，而且可以使得 f[0][0] = true，可以将 true 这个结果滚动下去
        ss = " " + ss;
        pp = " " + pp;
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();
        // f(i,j) 代表考虑 s 中的 1~i 字符和 p 中的 1~j 字符 是否匹配
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p[j] == '*') {
                    f[i][j] = f[i][j - 1] || (i - 1 >= 0 && f[i - 1][j]);
                } else {
                    f[i][j] = i - 1 >= 0 && f[i - 1][j - 1] && (s[i] == p[j] || p[j] == '?');
                }
            }
        }
        return f[n][m];
    }

    /**
     * 解答成功:
     * 	执行耗时:26 ms,击败了27.53% 的Java用户
     * 	内存消耗:43.8 MB,击败了87.54% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchOfficial(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了96.17% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchOfficial2(String s, String p) {
        int sRight = s.length(), pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }

        if (pRight == 0) {
            return sRight == 0;
        }

        int sIndex = 0, pIndex = 0;
        int sRecord = -1, pRecord = -1;

        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }

        return allStars(p, pIndex, pRight);
    }

    public boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    public boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }


}
