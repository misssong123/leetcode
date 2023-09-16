package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionRob198 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.5 MB,击败了92.18% 的Java用户
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        int l = nums[0] , r = Math.max(l,nums[1]);
        for(int i = 2 ; i < len ; i++){
            int temp = l;
            l = r;
            r = Math.max(temp + nums[i],r);
        }
        return Math.max(l,r);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.5 MB,击败了93.04% 的Java用户
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
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
//leetcode submit region end(Prohibit modification and deletion)
