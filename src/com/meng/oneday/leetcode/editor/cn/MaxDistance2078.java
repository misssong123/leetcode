package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MaxDistance2078 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了10.42% 的Java用户
     * 	内存消耗:43 MB,击败了6.25% 的Java用户
     * @param colors
     * @return
     */
    public int maxDistance2078(int[] colors) {
        int res = 0;
        Set<Integer> exist = new HashSet<>();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            for (int[] ints : list) {
                if (ints[0] != colors[i]) {
                    res = Math.max(res, i - ints[1]);
                    break;
                }
            }
            if (exist.add(colors[i])) {
                list.add(new int[]{colors[i],i});
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.5 MB,击败了89.58% 的Java用户
     * @param colors
     * @return
     */
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int c = colors[0];
        if (c != colors[n - 1]) {
            return n - 1;
        }

        // 找最右边的颜色不等于 c 的房子
        // 题目保证至少有两栋颜色不同的房子
        int r = n - 2;
        while (colors[r] == c) {
            r--;
        }

        // 找最左边的颜色不等于 c 的房子
        int l = 1;
        while (colors[l] == c) {
            l++;
        }

        return Math.max(r, n - 1 - l);
    }

}
