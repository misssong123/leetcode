package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class AnswerQueries2389 {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] cache = new int[nums.length+1];
        for(int i = 1 ; i <= nums.length ; i++){
            cache[i] = cache[i-1] + nums[i-1];
        }
       return null;
    }
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 98.48%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 52.58%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public int[] answerQueries1(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i] + nums[i];
        }
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            answer[i] = binarySearch(f, queries[i]) - 1;
        }
        return answer;
    }

    public int binarySearch(int[] f, int target) {
        int low = 1, high = f.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (f[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

