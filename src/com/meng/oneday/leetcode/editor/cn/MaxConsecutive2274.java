package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaxConsecutive2274 {
    /**
     * 解答成功:
     * 	执行耗时:33 ms,击败了94.23% 的Java用户
     * 	内存消耗:54.4 MB,击败了61.11% 的Java用户
     * @param bottom
     * @param top
     * @param special
     * @return
     */
    public int maxConsecutiveMy(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = special[0] - bottom;
        for(int i = 1 ; i < special.length ; i++){
            max = Math.max(max,special[i] - special[i-1] - 1);
        }
        max = Math.max(max,top - special[special.length-1]);
        return max;
    }

    /**
     * 解答成功:
     * 	执行耗时:36 ms,击败了15.38% 的Java用户
     * 	内存消耗:54.1 MB,击败了95.83% 的Java用户
     * @param bottom
     * @param top
     * @param special
     * @return
     */
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int n = special.length;
        int ans = Math.max(special[0] - bottom, top - special[n - 1]);
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, special[i] - special[i - 1] - 1);
        }
        return ans;
    }

}
