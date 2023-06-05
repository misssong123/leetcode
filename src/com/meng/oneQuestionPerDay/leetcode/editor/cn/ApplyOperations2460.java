package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class ApplyOperations2460 {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.5 MB
     * , 在所有 Java 提交中击败了
     * 26.92%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     * @param nums
     * @return
     */
    public int[] applyOperations(int[] nums) {
        //当前数组长度
        int len = nums.length;
        if(len == 0){
            return nums;
        }
        //如果 nums[i] == nums[i + 1] ，则 nums[i] 的值变成原来的 2 倍，nums[i + 1] 的值变成 0
        for(int i = 0 ; i < len - 1 ; i++){
            if (nums[i] == nums[i + 1]){
                nums[i] = nums[i] * 2;
                nums[i + 1] = 0;
            }
        }
        //将数组中所有的0 移到数组的末尾
        int index = 0;
        for(int i = 0 ; i < len ; i++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for(int i = index ; i < len ; i++){
            nums[i] = 0;
        }
        return nums;
    }
}

