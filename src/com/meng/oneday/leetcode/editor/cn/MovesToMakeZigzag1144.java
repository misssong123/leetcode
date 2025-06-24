package com.meng.oneday.leetcode.editor.cn;

class MovesToMakeZigzag1144 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了94.51% 的Java用户
     * @param nums
     * @return
     */
    public int movesToMakeZigzag1144(int[] nums) {
        int odd = 0 ,even = 0 ;
        for(int i = 0 ; i < nums.length ; i+=2){
            //保留偶数
            if(i < nums.length-1){
                int min = Math.min(nums[i]-1,nums[i+1]);
                if (i < nums.length -2){
                    min = Math.min(min,nums[i+2]-1);
                }
                even += nums[i+1] -min;
            }
            //保留奇数
            int min = nums[i]+1;
            if (i > 0){
                min = Math.min(min,nums[i-1]);
            }
            if (i < nums.length -1){
                min = Math.min(min,nums[i+1]);
            }
            odd += nums[i] -min +1;
        }
        return Math.min(odd,even);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了27.47% 的Java用户
     * @param nums
     * @return
     */
    public int movesToMakeZigzag(int[] nums) {
        int[] s = new int[2];
        for (int i = 0, n = nums.length; i < n; ++i) {
            int left = i > 0 ? nums[i - 1] : Integer.MAX_VALUE;
            int right = i < n - 1 ? nums[i + 1] : Integer.MAX_VALUE;
            s[i % 2] += Math.max(nums[i] - Math.min(left, right) + 1, 0);
        }
        return Math.min(s[0], s[1]);
    }

}
