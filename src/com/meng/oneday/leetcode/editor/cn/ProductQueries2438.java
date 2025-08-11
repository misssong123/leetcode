package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class ProductQueries2438 {
    private static final int MOD = 1000000007;

    /**
     * 解答成功:
     * 	执行耗时:28 ms,击败了39.36% 的Java用户
     * 	内存消耗:104.2 MB,击败了5.32% 的Java用户
     * @param n
     * @param queries
     * @return
     */
    public int[] productQueries2438(int n, int[][] queries) {
        List<Integer> list = new ArrayList<>();
        while(n >0){
            int lowbit = n & -n;
            list.add(lowbit);
            n ^= lowbit;
        }
        int[] ans = new int[queries.length];
        int index = 0;
        for(int [] query : queries){
            long sum = 1;
            for(int i = query[0];i <= query[1];i++){
                sum = sum * list.get(i) % MOD;
            }
            ans[index++] = (int)(sum % MOD);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了36.17% 的Java用户
     * 	内存消耗:103.9 MB,击败了35.11% 的Java用户
     * @param n
     * @param queries
     * @return
     */
    public int[] productQueriesOther(int n, int[][] queries) {
        final int MOD = 1_000_000_007;
        // 例如二进制 1100 分解为 100 + 1000
        // 第一轮循环 lowbit(1100) = 100，然后 1100 ^ 100 = 1000
        // 第二轮循环 lowbit(1000) = 1000，然后 1000 ^ 1000 = 0，循环结束
        List<Integer> powers = new ArrayList<>();
        while (n > 0) {
            int lowbit = n & -n;
            powers.add(lowbit);
            n ^= lowbit;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            long mul = 1;
            for (int j = q[0]; j <= q[1]; j++) {
                mul = mul * powers.get(j) % MOD;
            }
            ans[i] = (int) mul;
        }
        return ans;
    }

}
