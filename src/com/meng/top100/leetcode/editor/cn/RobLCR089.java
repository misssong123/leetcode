package com.meng.top100.leetcode.editor.cn;

class RobLCR089 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了13.85% 的Java用户
     * @param nums
     * @return
     */
    public int robLCR089(int[] nums) {
        int rob = 0,notRob = 0;
        for(int num : nums){
            int temp = notRob;
            //当前家不偷
            notRob = Math.max(rob,notRob);
            //当前家偷
            rob = temp + num;
        }
        return Math.max(rob,notRob);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了5.27% 的Java用户
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int f0 = 0;
        int f1 = 0;
        for (int x : nums) {
            int newF = Math.max(f1, f0 + x);
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }
}
