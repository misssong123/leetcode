package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.Arrays;

class PredictTheWinner486 {
    //思路出错，未想通如何保证双方每次都拿最有解的
    public boolean PredictTheWinner(int[] nums) {
        int num = (Arrays.stream(nums).sum() + 1) /2;

        return true;
    }

    /**
     * 方法一：递归
     * @param nums
     * @return
     * 执行用时：
     * 69 ms
     * , 在所有 Java 提交中击败了
     * 10.32%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 30.96%
     * 的用户
     * 通过测试用例：
     * 62 / 62
     */
    public boolean PredictTheWinner1(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 62.46%
     * 的用户
     * 通过测试用例：
     * 62 / 62
     */
    public boolean PredictTheWinner2(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

}

