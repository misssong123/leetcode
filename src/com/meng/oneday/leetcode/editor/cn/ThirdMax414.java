package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class ThirdMax414 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了75.64% 的Java用户
     * 	内存消耗:41.9 MB,击败了95.45% 的Java用户
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int index = nums.length - 1;
        int count = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            if (nums[i] != nums[index]){
                count++;
                index = i;
            }
            if (count == 3){
                break;
            }
        }
        return count == 3 ? nums[index] : nums[nums.length - 1];
    }
}
