package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MaxAlternatingSum1911 {
    /**
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 48.11%
     * 的用户
     * 内存消耗：
     * 55.5 MB
     * , 在所有 Java 提交中击败了
     * 92.45%
     * 的用户
     * 通过测试用例：
     * 65 / 65
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        long even = nums[0] ,odd = 0,res = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            even = Math.max(even,odd + nums[i]);
            odd = Math.max(odd,even - nums[i]);
            res = Math.max(res,Math.max(even,odd));
        }
        return res;
    }

    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 63.21%
     * 的用户
     * 内存消耗：
     * 56.1 MB
     * , 在所有 Java 提交中击败了
     * 53.77%
     * 的用户
     * 通过测试用例：
     * 65 / 65
     * @param nums
     * @return
     */
    public long maxAlternatingSum1(int[] nums) {
        long even = nums[0], odd = 0;
        for (int i = 1; i < nums.length; i++) {
            even = Math.max(even, odd + nums[i]);
            odd = Math.max(odd, even - nums[i]);
        }
        return even;
    }

}