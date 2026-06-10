package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class FindLongestChain646 {
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了58.46% 的Java用户
     * 	内存消耗:46.2 MB,击败了60.61% 的Java用户
     * @param pairs
     * @return
     */
    public int findLongestChain646(int[][] pairs) {
        Arrays.sort(pairs,(a,b)->b[0]-a[0]);
        int res = 1;
        int pre = pairs[0][0];
        for (int i = 1 ; i < pairs.length ; i++){
            if (pairs[i][1] < pre){
                res++;
                pre = pairs[i][0];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了70.77% 的Java用户
     * 	内存消耗:46.1 MB,击败了76.61% 的Java用户
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int ans = 0;
        int preR = Integer.MIN_VALUE;
        for (int[] p : pairs) {
            if (p[0] > preR) {
                ans++;
                preR = p[1];
            }
        }
        return ans;
    }
}
