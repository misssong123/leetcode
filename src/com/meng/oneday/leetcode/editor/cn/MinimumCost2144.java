package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumCost2144 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了96.43% 的Java用户
     * 	内存消耗:44.2 MB,击败了8.33% 的Java用户
     * @param cost
     * @return
     */
    public int minimumCost2144(int[] cost) {
        Arrays.sort(cost);
        int len = cost.length -1;
        int sum = 0;
        for (int i = len ; i >= 0 ; i -= 3){
            sum+= cost[i];
            if (i-1>=0){
                sum+= cost[i-1];
            }
        }
        return sum;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了96.43% 的Java用户
     * 	内存消耗:43.7 MB,击败了86.90% 的Java用户
     * @param cost
     * @return
     */
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int ans = 0;
        // 从大到小，买两个送一个
        for (int i = cost.length - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if (i > 0) {
                ans += cost[i - 1];
            }
        }
        return ans;
    }
}
