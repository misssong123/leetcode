package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaxCoins1561 {
    /**
     * 解答成功:
     * 	执行耗时:33 ms,击败了91.11% 的Java用户
     * 	内存消耗:67.9 MB,击败了53.33% 的Java用户
     * @param piles
     * @return
     */
    public int maxCoins1561(int[] piles) {
        Arrays.sort(piles);
        int res = 0;
        int n = piles.length;
        int cnt = n / 3;
        for (int i = n - 2 , j = 0; j < cnt; i -= 2, j++) {
            res += piles[i];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:34 ms,击败了55.56% 的Java用户
     * 	内存消耗:67.8 MB,击败了77.78% 的Java用户
     * @param piles
     * @return
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length;
        int ans = 0;
        for (int i = n / 3; i < n; i += 2) {
            ans += piles[i];
        }
        return ans;
    }
}
