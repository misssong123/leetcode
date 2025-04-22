package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IdealArrays2338 {
    int mod = 1000000007;
    Map<Integer, Map<Integer,Integer>> cache = new HashMap<>();

    /**
     * Time Limit Exceeded
     * @param n
     * @param maxValue
     * @return
     */
    public int idealArrays2338(int n, int maxValue) {
        return dfs(0,n,1,maxValue);
    }

    private int dfs(int index, int n, int pre, int maxValue) {
        //当前方案可行
        if (index == n){
            return 1;
        }
        int temp = 0;
        for(int i = pre ; i <= maxValue ; i++){
            if (i % pre != 0){
                continue;
            }
            int next = 0;
            //当前位置已经处理
            if(cache.containsKey(index)&&cache.get(index).containsKey(i)){
                next = cache.get(index).get(i);
            }else {
                next = dfs(index+1,n,i,maxValue)%mod;
                if (!cache.containsKey(index)){
                    cache.put(index,new HashMap<>());
                }
                if (!cache.get(index).containsKey(i)){
                    cache.get(index).put(i,next);
                }
            }
            temp = (temp + next)% mod;
        }
        return temp;
    }
    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 10_000;
    private static final int MAX_E = 13;

    private static final List<Integer>[] EXP = new ArrayList[MAX_N + 1];
    private static final int[][] C = new int[MAX_N + MAX_E][MAX_E + 1];

    private static boolean done = false;

    private void init() {
        // 这样写比 static block 更快
        if (done) {
            return;
        }
        done = true;

        // EXP[x] 为 x 分解质因数后，每个质因数的指数
        for (int x = 1; x < EXP.length; x++) {
            EXP[x] = new ArrayList<>();
            int t = x;
            for (int i = 2; i * i <= t; i++) {
                int e = 0;
                for (; t % i == 0; t /= i) {
                    e++;
                }
                if (e > 0) {
                    EXP[x].add(e);
                }
            }
            if (t > 1) {
                EXP[x].add(1);
            }
        }

        // 预处理组合数
        for (int i = 0; i < MAX_N + MAX_E; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= Math.min(i, MAX_E); j++) {
                C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % MOD;
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了76.19% 的Java用户
     * 	内存消耗:44.8 MB,击败了14.29% 的Java用户
     * @param n
     * @param maxValue
     * @return
     */
    public int idealArrays(int n, int maxValue) {
        init();
        long ans = 0;
        for (int x = 1; x <= maxValue; x++) {
            long mul = 1;
            for (int e : EXP[x]) {
                mul = mul * C[n + e - 1][e] % MOD;
            }
            ans += mul;
        }
        return (int) (ans % MOD);
    }

}
