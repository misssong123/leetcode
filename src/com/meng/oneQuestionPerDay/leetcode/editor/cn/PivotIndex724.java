package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class PivotIndex724 {
    /**
     * 详情
     * 3ms
     * 击败 32.78%使用 Java 的用户
     * 内存
     * 详情
     * 41.82mb
     * 击败 88.40%使用 Java 的用户
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int leftSum = 0,rightSum = sum;
        int index = 0;
        for(int num : nums){
            rightSum -= num;
            if(leftSum == rightSum){
                return index;
            }
            leftSum += num;
            index++;
        }
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex724 demo = new PivotIndex724();
        int[] nums = new int[]{1,7,3,6,5,6};
        System.out.println(demo.pivotIndex(nums));
    }

    /**
     * 详情
     * 3ms
     * 击败 32.78%使用 Java 的用户
     * 内存
     * 详情
     * 41.61mb
     * 击败 97.31%使用 Java 的用户
     * @param nums
     * @return
     */
    public int pivotIndex1(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}

