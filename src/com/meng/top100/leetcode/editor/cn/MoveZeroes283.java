package com.meng.top100.leetcode.editor.cn;

import java.util.Arrays;

class MoveZeroes283 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.2 MB,击败了66.29% 的Java用户
     * @param nums
     */
    public void moveZeroes283(int[] nums) {
        int index = 0 , l = 0 , len = nums.length;
        while (l < len){
            if(nums[l] != 0){
                nums[index++] = nums[l];
            }
            l++;
        }
        while(index < len){
            nums[index++] = 0;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了50.66% 的Java用户
     * 	内存消耗:45.1 MB,击败了79.35% 的Java用户
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int stackSize = 0;
        for (int x : nums) {
            if (x != 0) {
                nums[stackSize++] = x; // 把 x 入栈
            }
        }
        Arrays.fill(nums, stackSize, nums.length, 0);
    }

}
