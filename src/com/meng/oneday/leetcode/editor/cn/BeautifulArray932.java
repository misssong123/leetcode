package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class BeautifulArray932 {
    /**
     * 分治
     * 解答成功:
     * 	执行耗时:1 ms,击败了44.34% 的Java用户
     * 	内存消耗:43 MB,击败了39.62% 的Java用户
     * @param n
     * @return
     */
    public int[] beautifulArray(int n) {
        Map<Integer,int[]> memo = new HashMap<>();
        return divideAndConquer(n,memo);
    }
    private int[] divideAndConquer(int n, Map<Integer,int[]> memo) {
        if (memo.containsKey(n)){
            return memo.get(n);
        }
        int[] res = new int[n];
        if (n == 1){
            res[0] = 1;
        }else{
            int index = 0;
            //左侧基数
            int[] left = divideAndConquer((n+1)/2,memo);
            for (int num : left){
                res[index++] = 2 * num - 1;
            }
            //右侧基数
            int[] right = divideAndConquer(n/2,memo);
            for (int num : right){
                res[index++] = 2 * num;
            }
        }
        return res;
    }
}
