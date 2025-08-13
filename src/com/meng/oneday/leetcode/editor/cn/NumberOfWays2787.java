package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class NumberOfWays2787 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了52.61% 的Java用户
     * 	内存消耗:42.2 MB,击败了50.22% 的Java用户
     * @param n
     * @param x
     * @return
     */
    public int numberOfWays2787(int n, int x) {
        //计算所有的可能数字
        List<Integer> list = new ArrayList<>();
        for(int i = 1 ; i <= n ; i++){
            int val = (int)Math.pow(i,x);
            if (val > n){
                break;
            }
            list.add(val);
        }
        int[] dp =  new int[n+1];
        dp[0] = 1;
        for(int num : list){
            for(int i = n ; i >= num ; i--){
                dp[i] =( dp[i] + dp[i-num])% 1000000007;
            }
        }
        return dp[n] % 1000000007;
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了58.52% 的Java用户
     * 	内存消耗:42.2 MB,击败了41.48% 的Java用户
     * @param n
     * @param x
     * @return
     */
    int numberOfWaysOther(int n, int x) {
        long[] f = new long[n + 1];
        f[0] = 1;
        // 本题数据范围小，Math.pow 的计算结果一定准确
        for (int i = 1; Math.pow(i, x) <= n; i++) {
            int v = (int) Math.pow(i, x);
            for (int s = n; s >= v; s--) {
                f[s] += f[s - v];
            }
        }
        return (int) (f[n] % 1_000_000_007);
    }

}
