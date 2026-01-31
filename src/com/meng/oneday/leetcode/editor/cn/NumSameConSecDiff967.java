package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class NumSameConSecDiff967 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了47.62% 的Java用户
     * 	内存消耗:42.8 MB,击败了62.34% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int[] numSameConSecDiff967(int n, int k) {
        List<Integer> cache = new ArrayList<>();
        dfs(n,k,0,0,cache);
        return cache.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(int n, int k, int index, int val, List<Integer> cache) {
        if(index == n ){
            cache.add(val);
            return;
        }
        //index == 0
        if (index == 0){
            for (int i = 1 ; i <=9 ; i++){
                dfs(n,k,index+1,val*10+i,cache);
            }
        }else{
            int pre = val % 10;
            //处理+k
            if (pre + k <= 9){
                dfs(n,k,index+1,val*10+pre+k,cache);
            }
            //处理-k
            if(k != 0 &&pre - k >= 0){
                dfs(n,k,index+1,val*10+pre-k,cache);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了22.51% 的Java用户
     * 	内存消耗:43.7 MB,击败了15.15% 的Java用户
     * @param N
     * @param K
     * @return
     */
    public int[] numsSameConsecDiff(int N, int K) {
        Set<Integer> cur = new HashSet();
        for (int i = 1; i <= 9; ++i)
            cur.add(i);

        for (int steps = 1; steps <= N-1; ++steps) {
            Set<Integer> cur2 = new HashSet();
            for (int x: cur) {
                int d = x % 10;
                if (d-K >= 0)
                    cur2.add(10*x + (d-K));
                if (d+K <= 9)
                    cur2.add(10*x + (d+K));
            }

            cur = cur2;
        }

        if (N == 1)
            cur.add(0);

        int[] ans = new int[cur.size()];
        int t = 0;
        for (int x: cur)
            ans[t++] = x;
        return ans;
    }

}
