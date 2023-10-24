package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionNumRollsToTarget1155 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了89.80% 的Java用户
     * 	内存消耗:38.2 MB,击败了84.31% 的Java用户
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget(int n, int k, int target) {
        if (n*k < target){
            return 0;
        }
        int mod = 1000000007;
        int[][] cache = new int[n+1][target+1];
        for(int i = 1 ; i <= Math.min(k,target) ; i++){
            cache[1][i] = 1;
        }
        for(int i = 2 ; i <= n ; i++){
            int max = Math.min(target,i*k);
            for(int j = i ; j <= max ; j++){
                int num = Math.min(j,k);
                for(int m = 1 ; m <= num ; m++){
                    cache[i][j] = (cache[i][j] + cache[i-1][j-m])%mod;
                }
            }
        }
        return cache[n][target];
    }

    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了34.51% 的Java用户
     * 	内存消耗:39.7 MB,击败了43.13% 的Java用户
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget1(int n, int k, int target) {
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= target; ++j) {
                for (int x = 1; x <= k; ++x) {
                    if (j - x >= 0) {
                        f[i][j] = (f[i][j] + f[i - 1][j - x]) % MOD;
                    }
                }
            }
        }
        return f[n][target];
    }
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println( Integer.toBinaryString(170));
    }
    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了24.71% 的Java用户
     * 	内存消耗:39.7 MB,击败了38.04% 的Java用户
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget2(int n, int k, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int[] g = new int[target + 1];
            for (int j = 0; j <= target; ++j) {
                for (int x = 1; x <= k; ++x) {
                    if (j - x >= 0) {
                        g[j] = (g[j] + f[j - x]) % MOD;
                    }
                }
            }
            f = g;
        }
        return f[target];
    }

    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了19.61% 的Java用户
     * 	内存消耗:38.1 MB,击败了90.98% 的Java用户
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget3(int n, int k, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = target; j >= 0; --j) {
                f[j] = 0;
                for (int x = 1; x <= k; ++x) {
                    if (j - x >= 0) {
                        f[j] = (f[j] + f[j - x]) % MOD;
                    }
                }
            }
        }
        return f[target];
    }

}
