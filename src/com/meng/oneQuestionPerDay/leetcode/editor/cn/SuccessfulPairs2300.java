package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;


class SuccessfulPairs2300 {
    /**
     * 解答成功:
     * 	执行耗时:35 ms,击败了82.37% 的Java用户
     * 	内存消耗:55.2 MB,击败了83.41% 的Java用户
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairsMy(int[] spells, int[] potions, long success) {
        int n = spells.length,m=potions.length;
        int[] res = new int[n];
        Arrays.sort(potions);
        for(int i = 0 ; i < n ; i++){
            long target = (success + spells[i] - 1) / spells[i];
            if (potions[0]>=target){
                res[i] = m;
                continue;
            }
            if (potions[m-1]<target){
                res[i] = 0;
                continue;
            }
            int left = 0 , right = m - 1;
            while (left <= right){
                int mid = left + (right - left)/2;
                if (potions[mid] == target){
                    left = mid;
                    break;
                }else if (potions[mid] < target){
                    left = mid+1;
                }else {
                    right = mid - 1;
                }
            }
            //存在等于的情况
            if (left<=m-1) {
                if (potions[left] == target) {
                    while (--left >= 0 && potions[left] == target) ;
                    res[i] = m - left - 1;
                } else if (right >= -1) {
                    res[i] = m - right -1;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:49 ms,击败了37.44% 的Java用户
     * 	内存消耗:55.2 MB,击败了76.68% 的Java用户
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs1(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            long t = (success + spells[i] - 1) / spells[i] - 1;
            res[i] = m - binarySearch(potions, 0, m - 1, t);
        }
        return res;
    }

    public int binarySearch(int[] arr, int lo, int hi, long target) {
        int res = hi + 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > target) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:84 ms,击败了5.12% 的Java用户
     * 	内存消耗:56.9 MB,击败了12.42% 的Java用户
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        int[][] idx = new int[n][2];
        for (int i = 0; i < n; ++i) {
            idx[i][0] = spells[i];
            idx[i][1] = i;
        }
        Arrays.sort(potions);
        for (int i = 0, j = m - 1; i < j; ++i, --j) {
            int temp = potions[i];
            potions[i] = potions[j];
            potions[j] = temp;
        }
        Arrays.sort(idx, (a, b) -> a[0] - b[0]);
        for (int i = 0, j = 0; i < n; ++i) {
            int p = idx[i][1];
            int v = idx[i][0];
            while (j < m && (long) potions[j] * v >= success) {
                ++j;
            }
            res[p] = j;
        }
        return res;
    }

}

