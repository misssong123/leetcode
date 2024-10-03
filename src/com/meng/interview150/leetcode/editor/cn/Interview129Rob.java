package com.meng.interview150.leetcode.editor.cn;

class Interview129Rob {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.9 MB,击败了98.04% 的Java用户
     * @param nums
     * @return
     */

    public int robMy(int[] nums) {
        int n = nums.length;
        int no = 0, yes = nums[0];
        for(int i = 1 ; i < n ; i++){
            int temp = yes;
            yes = Math.max(no+nums[i],yes);
            no = Math.max(no,temp);
        }
        return Math.max(no,yes);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了41.42% 的Java用户
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

}
