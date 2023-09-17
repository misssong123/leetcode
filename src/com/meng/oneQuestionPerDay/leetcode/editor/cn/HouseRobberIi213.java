package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionRob213 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.7 MB,击败了78.33% 的Java用户
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        if (len == 2){
            return Math.max(nums[0],nums[1]);
        }
       return Math.max(rob(0,len-1,nums),rob(1,len,nums));
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.7 MB,击败了79.36% 的Java用户
     * @param start
     * @param end
     * @param nums
     * @return
     */
    public int rob(int start ,int end,int[] nums) {
        int l = nums[start] , r = Math.max(l,nums[start+1]);
        for(int i = start + 2 ; i < end ; i++){
            int temp = l;
            l = r;
            r = Math.max(temp + nums[i],r);
        }
        return Math.max(l,r);
    }
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

}
