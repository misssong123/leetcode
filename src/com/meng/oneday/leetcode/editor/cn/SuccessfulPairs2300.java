package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class SuccessfulPairs2300 {
    /**
     * 解答成功:
     * 	执行耗时:42 ms,击败了72.37% 的Java用户
     * 	内存消耗:65.8 MB,击败了43.39% 的Java用户
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs2300(int[] spells, int[] potions, long success) {
        int n = spells.length,m = potions.length;
        int[] res = new int[n];
        Arrays.sort(potions);
        for(int i = 0 ; i < n ; i++){
            long target = success / spells[i] + (success % spells[i] == 0 ? 0 : 1);
            int index = binarySearch(potions,target);
            res[i] = m - index;
        }
        return res;
    }

    private int binarySearch(int[] potions, long target) {
        int l = 0 , r = potions.length - 1;
        int res = potions.length;
        while(l <= r){
            int mid = l + r >> 1;
            if(potions[mid] >= target){
                r = mid -1;
                res = mid;
            }else {
                l = mid + 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:40 ms,击败了73.19% 的Java用户
     * 	内存消耗:62.2 MB,击败了88.25% 的Java用户
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairsOther(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            spells[i] = potions.length - lowerBound(potions, (double) success / spells[i]);
        }
        return spells;
    }

    // 返回 nums 中的第一个大于等于 target 的元素下标
    private int lowerBound(int[] nums, double target) {
        int left = -1, right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] <= target
            // nums[right] > target
            int mid = (left + right) >>> 1; // 比 left+(right-left)/2 更快的写法
            if (nums[mid] >= target) {
                right = mid; // 二分范围缩小到 (left, mid)
            } else {
                left = mid; // 二分范围缩小到 (mid, right)
            }
        }
        return right;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了99.92% 的Java用户
     * 	内存消耗:63.4 MB,击败了74.28% 的Java用户
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int mx = 0;
        for (int y : potions) {
            mx = Math.max(mx, y);
        }

        int[] cnt = new int[mx + 1];
        for (int y : potions) {
            cnt[y]++; // 统计每种药水的出现次数
        }

        // 计算 cnt 的后缀和
        for (int i = mx - 1; i >= 0; i--) {
            cnt[i] += cnt[i + 1];
        }
        // 计算完毕后，cnt[i] 就是 potions 值 >= i 的药水个数

        for (int i = 0; i < spells.length; i++) {
            long low = (success - 1) / spells[i] + 1;
            spells[i] = low <= mx ? cnt[(int) low] : 0;
        }
        return spells;
    }

}
