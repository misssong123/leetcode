package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CountSubarrays2488 {
    public int countSubarrays(int[] nums, int k) {
        return 0;
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 15 ms
     * , 在所有 Java 提交中击败了
     * 50.00%
     * 的用户
     * 内存消耗：
     * 51.2 MB
     * , 在所有 Java 提交中击败了
     * 58.57%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    public int countSubarrays1(int[] nums, int k) {
        int n = nums.length;
        int kIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                kIndex = i;
                break;
            }
        }
        int ans = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(0, 1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sign(nums[i] - k);
            if (i < kIndex) {
                counts.put(sum, counts.getOrDefault(sum, 0) + 1);
            } else {
                int prev0 = counts.getOrDefault(sum, 0);
                int prev1 = counts.getOrDefault(sum - 1, 0);
                ans += prev0 + prev1;
            }
        }
        return ans;
    }

    public int sign(int num) {
        if (num == 0) {
            return 0;
        }
        return num > 0 ? 1 : -1;
    }
}
