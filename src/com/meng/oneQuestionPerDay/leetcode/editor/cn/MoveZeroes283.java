package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MoveZeroes283 {
    /**
     * 详情
     * 1ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 43.02mb
     * 击败 40.20%使用 Java 的用户
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if (nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for(int i = index ; i < nums.length ; i++){
            nums[i] = 0;
        }
    }

    /**
     * 双指针
     * @param nums
     * 详情
     * 2ms
     * 击败 32.20%使用 Java 的用户
     * 内存
     * 详情
     * 42.93mb
     * 击败 52.37%使用 Java 的用户
     */
    public void moveZeroes2(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}

