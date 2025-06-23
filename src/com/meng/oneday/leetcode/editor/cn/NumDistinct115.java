package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class NumDistinct115 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了63.56% 的Java用户
     * 	内存消耗:44.2 MB,击败了91.16% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public int numDistinct115(String s, String t) {
        Map<String,Integer> cache = new HashMap<>();
        return dfs(0,0,s,t,cache);
    }
    private int dfs(int sIndex, int tIndex, String s, String t,Map<String,Integer> cache) {
        //找到了符合条件的数据
        if(tIndex == t.length()){
            return 1;
        }
        //是否遍历过当前元素
        if (cache.containsKey(sIndex + "-" + tIndex)) {
            return cache.get(sIndex + "-" + tIndex);
        }
        int temp = 0;
        for(int i = sIndex; i < s.length(); i++){
            //无法满足长度进行剪枝
            if(s.length() - i < t.length() - tIndex){
                break;
            }
            if(s.charAt(i) == t.charAt(tIndex)){
                temp += dfs(i+1,tIndex+1,s,t,cache);
            }
        }
        cache.put(sIndex + "-" + tIndex,temp);
        return temp;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.75% 的Java用户
     * 	内存消耗:40.7 MB,击败了96.29% 的Java用户
     * @param S
     * @param T
     * @return
     */
    public int numDistinct3(String S, String T) {
        int n = S.length();
        int m = T.length();
        if (n < m) {
            return 0;
        }

        char[] s = S.toCharArray();
        char[] t = T.toCharArray();

        int[] f = new int[m + 1];
        f[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(i, m - 1); j >= Math.max(m - n + i, 0); j--) {
                if (s[i] == t[j]) {
                    f[j + 1] += f[j];
                }
            }
        }
        return f[m];
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了85.37% 的Java用户
     * 	内存消耗:50.5 MB,击败了7.19% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public int numDistinct1(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1; // -1 表示没有计算过
            }
        }
        return dfs(n - 1, m - 1, s.toCharArray(), t.toCharArray(), memo);
    }

    private int dfs(int i, int j, char[] s, char[] t, int[][] memo) {
        if (i < j) {
            return 0;
        }
        if (j < 0) {
            return 1;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int res = dfs(i - 1, j, s, t, memo);
        if (s[i] == t[j]) {
            res += dfs(i - 1, j - 1, s, t, memo);
        }
        return memo[i][j] = res; // 记忆化
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了98.79% 的Java用户
     * 	内存消耗:40.6 MB,击败了98.23% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen < tLen) {
            return 0;
        }
        int[] dp = new int[tLen + 1];
        dp[0] = 1;
        for(int i = 0 ; i < sLen ; i++){
            /*for(int j = Math.min(i,t.length()-1) ; j < Math.min(i+1,tLen) ; j++){
                dp[j+1] += (s.charAt(i) == t.charAt(j) ? dp[j] : 0);
            }*/
            for (int j = Math.min(i, tLen - 1); j >= Math.max(tLen - sLen + i, 0); j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j + 1] += dp[j];
                }
            }
        }
        return dp[tLen];
    }
}
