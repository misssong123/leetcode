package com.meng.oneday.leetcode.editor.cn;

class LengthOfLIS300 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.87% 的Java用户
     * 	内存消耗:44.7 MB,击败了44.33% 的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLIS300(int[] nums) {
        int size = 0 ;
        for (int num : nums){
            //寻找第一个大于等于num的位置
            int index = search(num,size,nums);
            nums[index] = num;
            if (index == size){
                size++;
            }
        }
        return size;
    }

    private int search(int target, int end, int[] nums) {
        int l = -1 , r = end;
        while (l+1 < r){
            int mid = (l+r)>>1;
            if(nums[mid] >= target){
                r = mid;
            }else{
                l = mid;
            }
        }
        return r;
    }
}
