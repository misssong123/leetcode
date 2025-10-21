package com.meng.top100.leetcode.editor.cn;

class RemoveDuplicates80 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.7 MB,击败了73.49% 的Java用户
     * @param nums
     * @return
     */
    public int removeDuplicates80(int[] nums) {
        int index = 2;
        for(int i = 2 ; i < nums.length ; i++){
            if (nums[i] != nums[index-2]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
