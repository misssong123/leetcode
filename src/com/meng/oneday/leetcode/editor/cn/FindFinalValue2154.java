package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class FindFinalValue2154 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了30.30% 的Java用户
     * 	内存消耗:45.3 MB,击败了11.51% 的Java用户
     * @param nums
     * @param original
     * @return
     */
    public int findFinalValue2154(int[] nums, int original) {
        Set<Integer> cache = new HashSet<>();
        for (int num : nums) {
            cache.add(num);
        }
        while (cache.contains(original)) {
            original *= 2;
        }
        return original;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了65.45% 的Java用户
     * 	内存消耗:45.4 MB,击败了10.91% 的Java用户
     * @param nums
     * @param original
     * @return
     */
    public int findFinalValueOther(int[] nums, int original) {
        Set<Integer> st = new HashSet<>();
        for (int x : nums) {
            int k = x / original;
            if (x % original == 0 && (k & (k - 1)) == 0) {
                st.add(x);
            }
        }
        while (st.contains(original)) {
            original *= 2;
        }
        return original;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了86.67% 的Java用户
     * 	内存消耗:44.8 MB,击败了13.94% 的Java用户
     * @param nums
     * @param original
     * @return
     */
    public int findFinalValue(int[] nums, int original) {
        int mask = 0;
        for (int x : nums) {
            int k = x / original;
            if (x % original == 0 && (k & (k - 1)) == 0) {
                mask |= k;
            }
        }

        // 找最低的 0，等价于取反后，找最低的 1（lowbit）
        mask = ~mask;
        return original * (mask & -mask);
    }

}
