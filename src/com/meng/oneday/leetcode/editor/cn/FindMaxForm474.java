package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class FindMaxForm474 {
    int[] cnts;
    int[][][] dp;
    /**
     *解答成功:
     * 	执行耗时:57 ms,击败了15.95% 的Java用户
     * 	内存消耗:84.27 MB,击败了5.00%的Java用户
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm474(String[] strs, int m, int n) {
        cnts = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            cnts[i] = (int) strs[i].chars().filter(ch -> ch == '0').count();
        }
        dp = new int[strs.length][m+1][n+1];
        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt,-1);
            }
        }
        return dfs(strs.length -1,strs,m,n);
    }
    private int dfs(int index ,String[] strs, int m,int n) {
        if (index < 0) {
            return 0;
        }
        if(dp[index][m][n] != -1){
            return dp[index][m][n];
        }
        //不选择当前元素
        int cnt = dfs(index - 1 ,strs,m,n);
        //选择当前元素
        int cnt0 = cnts[index];
        int cnt1 = strs[index].length() - cnt0;
        if(cnt0 <= m && cnt1 <= n){
            cnt = Math.max(cnt,dfs(index - 1,strs,m-cnt0,n-cnt1)+1);
        }
        return dp[index][m][n] = cnt;
    }
    /**
     *解答成功:
     * 	执行耗时:56 ms,击败了17.50% 的Java用户
     * 	内存消耗:84.54 MB,击败了5.00% 的Java用户
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxFormOther(String[] strs, int m, int n) {
        int k = strs.length;
        int[] cnt0 = new int[k];
        for (int i = 0; i < k; i++) {
            cnt0[i] = (int) strs[i].chars().filter(ch -> ch == '0').count();
        }

        int[][][] memo = new int[strs.length][m + 1][n + 1];
        for (int[][] mat : memo) {
            for (int[] arr : mat) {
                Arrays.fill(arr, -1); // -1 表示没有计算过
            }
        }
        return dfs(k - 1, m, n, strs, cnt0, memo);
    }

    private int dfs(int i, int j, int k, String[] strs, int[] cnt0, int[][][] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i][j][k] != -1) { // 之前计算过
            return memo[i][j][k];
        }
        // 不选 strs[i]
        int res = dfs(i - 1, j, k, strs, cnt0, memo);
        int cnt1 = strs[i].length() - cnt0[i];
        if (j >= cnt0[i] && k >= cnt1) {
            // 选 strs[i]
            res = Math.max(res, dfs(i - 1, j - cnt0[i], k - cnt1, strs, cnt0, memo) + 1);
        }
        return memo[i][j][k] = res; // 记忆化
    }
    /**
     *解答成功:
     * 	执行耗时:62 ms,击败了11.91% 的Java用户
     * 	内存消耗:82.91 MB,击败了5.00% 的Java用户
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxFormOther2(String[] strs, int m, int n) {
        int[][][] f = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int cnt0 = (int) strs[i].chars().filter(ch -> ch == '0').count();
            int cnt1 = strs[i].length() - cnt0;
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= cnt0 && k >= cnt1) {
                        f[i + 1][j][k] = Math.max(f[i][j][k], f[i][j - cnt0][k - cnt1] + 1);
                    } else {
                        f[i + 1][j][k] = f[i][j][k];
                    }
                }
            }
        }
        return f[strs.length][m][n];
    }
    /**
     *解答成功:
     * 	执行耗时:24 ms,击败了47.76% 的Java用户
     * 	内存消耗:43.98 MB,击败了28.64% 的Java用户
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxFormOther3(String[] strs, int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        for (String s : strs) {
            int cnt0 = (int) s.chars().filter(ch -> ch == '0').count();
            int cnt1 = s.length() - cnt0;
            for (int j = m; j >= cnt0; j--) {
                for (int k = n; k >= cnt1; k--) {
                    f[j][k] = Math.max(f[j][k], f[j - cnt0][k - cnt1] + 1);
                }
            }
        }
        return f[m][n];
    }

    /**
     *解答成功:
     * 	执行耗时:34 ms,击败了32.39% 的Java用户
     * 	内存消耗:44.5 MB,击败了28.41% 的Java用户
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxFormOther4(String[] strs, int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        int sum0 = 0;
        int sum1 = 0;
        for (String s : strs) {
            int cnt0 = (int) s.chars().filter(ch -> ch == '0').count();
            int cnt1 = s.length() - cnt0;
            sum0 = Math.min(sum0 + cnt0, m);
            sum1 = Math.min(sum1 + cnt1, n);
            for (int j = sum0; j >= cnt0; j--) {
                for (int k = sum1; k >= cnt1; k--) {
                    f[j][k] = Math.max(f[j][k], f[j - cnt0][k - cnt1] + 1);
                }
            }
        }
        int ans = 0;
        for (int[] row : f) {
            for (int v : row) {
                ans = Math.max(ans, v);
            }
        }
        return ans;
    }
}
