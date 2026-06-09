package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MaxTotalValue3689 {
    /**
     * 思路错误，存在重复的数据
     * @param nums
     * @param k
     * @return
     */
    public long maxTotalValue3689(int[] nums, int k) {
        //统计每个数字出现的位置
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], v -> new ArrayList<>()).add(i);
        }
        //原数组排序
        Arrays.sort(nums);
        int l = 0 , r = nums.length - 1;
        long res = 0;
        while (l < r) {
            break;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:60.9 MB,击败了66.67% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maxTotalValue(int[] nums, int k) {
        int max = nums[0];
        int mix = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
            mix = Math.min(mix, num);
        }
        return (long) (max - mix) * k;
    }
}
