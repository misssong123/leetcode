package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.Arrays;

class DieSimulator1223 {
    /**
     * 递归
     * @param n
     * @param rollMax
     * @return
     * 结果不对
     */
    static final int N = 1000000007;
    int[][][] records ;
    public int dieSimulator(int n, int[] rollMax) {
        records = new int[n][6][15];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 6; ++j)
                Arrays.fill(records[i][j], -1); // -1 表示没有访问过
        long ans = 0;
        for (int j = 0; j < 6; ++j)
            ans += dfs1(j,n - 1, rollMax, rollMax[j] - 1);
        return (int) (ans % MOD);
    }

    private int dfs(int num, int n, int[] rollMax, int left) {
        if (n==0){
            return 1;
        }
        if (records[n][num][left] >= 0){
            return records[n][num][left];
        }
        long res = 0;
        for (int j = 0; j < 6; ++j){
            if (j != num) res += dfs(j, n-1,rollMax, rollMax[j] - 1);
            else if (left > 0) res += dfs(j,n - 1, rollMax, left - 1);
        }
        return records[n][num][left] = (int)(res % N);
    }
    private int dfs1(int num, int n, int[] rollMax, int left) {
        if (n == 0) return 1;
        if (records[n][num][left] >= 0) return records[n][num][left];
        long res = 0;
        for (int j = 0; j < 6; ++j){
            if (j != num) res += dfs1(j, n-1,rollMax, rollMax[j] - 1);
            else if (left > 0) res += dfs1(j,n - 1, rollMax, left - 1);
        }
        return records[n][num][left] = (int) (res % MOD);
    }
    private static final long MOD = (long) 1e9 + 7;
    private int[] rollMax;
    private int[][][] cache;

    /**
     * 执行用时：
     * 37 ms
     * , 在所有 Java 提交中击败了
     * 42.50%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 47.50%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     * @param n
     * @param rollMax
     * @return
     */
    public int dieSimulator4(int n, int[] rollMax) {
        this.rollMax = rollMax;
        int m = rollMax.length;
        cache = new int[n][m][15];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; ++j)
                Arrays.fill(cache[i][j], -1); // -1 表示没有访问过
        long ans = 0;
        for (int j = 0; j < m; ++j)
            ans += dfs(n - 1, j, rollMax[j] - 1);
        return (int) (ans % MOD);
    }

    private int dfs(int i, int last, int left) {
        if (i == 0) return 1;
        if (cache[i][last][left] >= 0) return cache[i][last][left];
        long res = 0;
        for (int j = 0; j < rollMax.length; ++j)
            if (j != last) res += dfs(i - 1, j, rollMax[j] - 1);
            else if (left > 0) res += dfs(i - 1, j, left - 1);
        return cache[i][last][left] = (int) (res % MOD);
    }

    public static void main(String[] args) {
        int n = 16;
        int[] rollMax = {8,5,10,8,7,2};
        DieSimulator1223 demo = new DieSimulator1223();
        System.out.println(demo.dieSimulator(n,rollMax));
        System.out.println(demo.dieSimulator4(n,rollMax));
    }



//    public int dieSimulator1(int n, int[] rollMax) {
//        int[][][] d = new int[n + 1][6][16];
//        for (int j = 0; j < 6; j++) {
//            d[1][j][1] = 1;
//        }
//        for (int i = 2; i <= n; i++) {
//            for (int j = 0; j < 6; j++) {
//                for (int k = 1; k <= rollMax[j]; k++) {
//                    for (int p = 0; p < 6; p++) {
//                        if (p != j) {
//                            d[i][p][1] = (d[i][p][1] + d[i - 1][j][k]) % MOD;
//                        } else if (k + 1 <= rollMax[j]) {
//                            d[i][p][k + 1] = (d[i][p][k + 1] + d[i - 1][j][k]) % MOD;
//                        }
//                    }
//                }
//            }
//        }
//
//        int res = 0;
//        for (int j = 0; j < 6; j++) {
//            for (int k = 1; k <= rollMax[j]; k++) {
//                res = (res + d[n][j][k]) % MOD;
//            }
//        }
//        return res;
//    }
//
//    static final int MOD = 1000000007;
//
//    public int dieSimulator2(int n, int[] rollMax) {
//        int[][][] d = new int[2][6][16];
//        for (int j = 0; j < 6; j++) {
//            d[1][j][1] = 1;
//        }
//        for (int i = 2; i <= n; i++) {
//            int t = i & 1;
//            for (int j = 0; j < 6; j++) {
//                Arrays.fill(d[t][j], 0);
//            }
//            for (int j = 0; j < 6; j++) {
//                for (int k = 1; k <= rollMax[j]; k++) {
//                    for (int p = 0; p < 6; p++) {
//                        if (p != j) {
//                            d[t][p][1] = (d[t][p][1] + d[t ^ 1][j][k]) % MOD;
//                        } else if (k + 1 <= rollMax[j]) {
//                            d[t][p][k + 1] = (d[t][p][k + 1] + d[t ^ 1][j][k]) % MOD;
//                        }
//                    }
//                }
//            }
//        }
//
//        int res = 0;
//        for (int j = 0; j < 6; j++) {
//            for (int k = 1; k <= rollMax[j]; k++) {
//                res = (res + d[n & 1][j][k]) % MOD;
//            }
//        }
//        return res;
//    }


    static final int NUM = 1000000007;

    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 92.50%
     * 的用户
     * 内存消耗：
     * 40.5 MB
     * , 在所有 Java 提交中击败了
     * 82.50%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     * @param n
     * @param rollMax
     * @return
     */
    public int dieSimulator3(int n, int[] rollMax) {
        int[][] d = new int[n + 1][6];
        int[] sum = new int[n + 1];
        sum[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                int pos = Math.max(i - rollMax[j] - 1, 0);
                int sub = ((sum[pos] - d[pos][j]) % NUM + NUM) % NUM;
                d[i][j] = ((sum[i - 1] - sub) % NUM + NUM) % NUM;
                if (i <= rollMax[j]) {
                    d[i][j] = (d[i][j] + 1) % NUM;
                }
                sum[i] = (sum[i] + d[i][j]) % NUM;
            }
        }
        return sum[n];
    }

}



