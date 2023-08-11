package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class FindMaxAverage643 {
    /**
     * 详情
     * 2ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 51.61mb
     * 击败 84.21%使用 Java 的用户
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int temp  = 0;
        //计算初始值
        for(int i = 0 ; i < k ; i++){
            temp += nums[i];
        }
        max = Math.max(temp,max);
        //滑动窗口，计算最大值
        for(int i = k ; i < nums.length ; i++){
            temp = temp + nums[i] - nums[i-k];
            max = Math.max(temp,max);
        }
        return max * 1.0 / k;
    }
    /**
     *详情
     * 2ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 51.77mb
     * 击败 60.71%使用 Java 的用户
     * 官方题解
     */
    public double findMaxAverage1(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }


}

