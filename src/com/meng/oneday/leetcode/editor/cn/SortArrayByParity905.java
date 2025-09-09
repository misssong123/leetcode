package com.meng.oneday.leetcode.editor.cn;

class SortArrayByParity905 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44 MB,击败了89.37% 的Java用户
     * @param nums
     * @return
     */
    public int[] sortArrayByParity905(int[] nums) {
        int evenIndex = 0 ;
        for(int i = 0 ; i < nums.length ; i++){
            if (nums[i] % 2 == 0){
                if (evenIndex != i){
                    int temp = nums[i];
                    nums[i] = nums[evenIndex];
                    nums[evenIndex] = temp;
                }
                evenIndex++;
            }
        }
        return nums;
    }
}
