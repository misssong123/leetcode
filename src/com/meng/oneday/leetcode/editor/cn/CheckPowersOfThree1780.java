package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class CheckPowersOfThree1780 {
    /**
     * 解答成功:
     * 	执行耗时:2124 ms,击败了4.44% 的Java用户
     * 	内存消耗:81.4 MB,击败了6.67% 的Java用户
     * @param n
     * @return
     */
    public boolean checkPowersOfThree1780(int n) {
       if (n % 3 != 0 && (n- 1) % 3 != 0){
           return false;
       }
       List<Integer> list =  new ArrayList<>();
       int i = 1;
       while (i <= n){
           list.add(i);
           i *= 3;
       }
       int[] dp = new int[n+1];
       dp[0] =1;
       for(int num : list){
           for(int j = n ; j >= num; j--){
               dp[j] = dp[j-num];
           }
           if (dp[n] > 0){
               return true;
           }
       }
       return false;
    }

    /**
     * 执行用时分布
     * 0
     * ms
     * 击败
     * 100.00%
     * 复杂度分析
     * 消耗内存分布
     * 39.61
     * MB
     * 击败
     * 82.22%
     * @param n
     * @return
     */
    public boolean checkPowersOfThree(int n) {
        while (n != 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
