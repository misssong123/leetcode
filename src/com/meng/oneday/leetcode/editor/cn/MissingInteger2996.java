package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class MissingInteger2996 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了85.71% 的Java用户
     * 	内存消耗:43.6 MB,击败了42.86% 的Java用户
     * @param nums
     * @return
     */
    public int missingInteger2996(int[] nums) {
        int sum = nums[0];
        boolean flag = true;
        Set<Integer> set = new HashSet<>();
        set.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            set.add(nums[i]);
            if (flag&&nums[i] == nums[i-1] + 1) {
                sum+=nums[i];
            }else {
                flag = false;
            }
        }
        for (int i = sum; ; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了52.38% 的Java用户
     * @param nums
     * @return
     */
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        for (int i = 1; i < nums.length && nums[i] == nums[i - 1] + 1; i++) {
            sum += nums[i];
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (set.contains(sum)) { // 至多循环 n 次，例如 1324567
            sum++;
        }
        return sum;
    }
}
