package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaximumHappinessSum3075 {
    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了52.13% 的Java用户
     * 	内存消耗:107.1 MB,击败了6.38% 的Java用户
     * @param happiness
     * @param k
     * @return
     */
    public long maximumHappinessSum3075(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = 0,index = happiness.length-1;
        long res = 0;
        while (index >= 0 && k > 0 && happiness[index] > n){
            res += (happiness[index --] - n);
            k--;
            n++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了52.13% 的Java用户
     * 	内存消耗:107.1 MB,击败了6.38% 的Java用户
     * @param happiness
     * @param k
     * @return
     */
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = happiness.length;
        long ans = 0;
        for (int i = n - 1; i >= n - k && happiness[i] > n - 1 - i; i--) {
            ans += happiness[i] - (n - 1 - i);
        }
        return ans;
    }

}
