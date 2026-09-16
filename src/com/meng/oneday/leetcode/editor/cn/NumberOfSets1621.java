package com.meng.oneday.leetcode.editor.cn;

class NumberOfSets1621 {
    /**
     * 解答成功:
     * 	执行耗时:1256 ms,击败了11.11% 的Java用户
     * 	内存消耗:61.3 MB,击败了11.11% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int numberOfSets1621(int n, int k) {
        long[][] dp = new long[n+1][k+1];
        return (int)(dfs(n,k,0,1,dp) % MOD);
    }

    private long dfs(int n, int k, int pre,int index,long[][] dp) {
        if(index == k){
            int cnt =  n - pre - 1;
            return (long) (cnt + 1) * cnt / 2;
        }
        if(dp[pre][index] != 0){
            return dp[pre][index];
        }
        long res = 0;
        for(int i = pre + 1 ; i < n - (k-index) ; i++){
            res += (i - pre) * dfs(n ,k ,i,index + 1,dp);
            res %= MOD;
        }
        return dp[pre][index] = res;
    }

    /**
     * 解答成功:
     * 	执行耗时:52 ms,击败了22.22% 的Java用户
     * 	内存消耗:41.7 MB,击败了66.67% 的Java用户
     * @param n
     * @param k
     * @return
     */
    private static final int MOD = 1000000007;
    public int numberOfSetsOfficial(int n, int k) {
        int[] dp = new int[n];
        int[] prefixSums = new int[n + 1];
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
        }
        for (int i = 1; i <= k; i++) {
            dp[0] = 0;
            for (int j = 1; j < n; j++) {
                dp[j] = (dp[j - 1] + prefixSums[j]) % MOD;
            }
            for (int j = 0; j < n; j++) {
                prefixSums[j + 1] = (prefixSums[j] + dp[j]) % MOD;
            }
        }
        return dp[n - 1];
    }

    /**
     * 超时
     * @param n
     * @param k
     * @return
     */
    public int numberOfSetsAI1(int n, int k) {
        // dp[i][j] 表示前 i 个点（索引 0 ~ i-1），构成 j 条线段的方案数
        long[][] dp = new long[n][k + 1];

        // 基础状态：0 条线段时，任何位置停靠的方案数均为 1
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // 外层按线段数 j 递增，内层按当前点 i 递增
        for (int j = 1; j <= k; j++) {
            for (int i = j; i < n; i++) {
                long sum = 0;
                // 遍历上一条线段的右端点 p
                for (int p = j - 1; p < i; p++) {
                    sum = (sum + dp[p][j - 1] * (i - p)) % MOD;
                }
                dp[i][j] = sum;
            }
        }

        return (int) dp[n - 1][k];
    }

    /**
     * 解答成功:
     * 	执行耗时:43 ms,击败了33.33% 的Java用户
     * 	内存消耗:61.4 MB,击败了11.11% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int numberOfSets(int n, int k) {
        // dp[i][j] 表示前 i 个点（索引 0 ~ i）组成 j 条线段的总方案数
        long[][] dp = new long[n][k + 1];

        // 初始化：0 条线段时，任何点数方案数都为 1
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 不选点 i 作为右端点，或者延伸/新建线段
                // dp[i-1][j] * 2: 包含了不选点 i 的方案 + 延伸原有线段的方案
                // - dp[i-2][j]: 减去重复计算的部分
                // + dp[i-1][j-1]: 以点 i 结尾，新开第 j 条线段的方案
                long val = dp[i - 1][j] * 2 % MOD;
                if (i >= 2) {
                    val = (val - dp[i - 2][j] + MOD) % MOD;
                }
                val = (val + dp[i - 1][j - 1]) % MOD;

                dp[i][j] = val;
            }
        }

        return (int) dp[n - 1][k];
    }
}
