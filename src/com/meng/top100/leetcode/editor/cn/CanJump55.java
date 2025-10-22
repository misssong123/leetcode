package com.meng.top100.leetcode.editor.cn;

class CanJump55 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了86.21% 的Java用户
     * 	内存消耗:44.8 MB,击败了62.92% 的Java用户
     * @param nums
     * @return
     */
    public boolean canJump55(int[] nums) {
        int maxStep = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(i > maxStep){
                return false;
            }
            maxStep = Math.max(maxStep,i + nums[i]);
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了86.21% 的Java用户
     * 	内存消耗:44.9 MB,击败了16.78% 的Java用户
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int mx = 0;
        for (int i = 0; mx < nums.length - 1; i++) {
            if (i > mx) { // 无法到达 i
                return false;
            }
            mx = Math.max(mx, i + nums[i]); // 从 i 最右可以跳到 i + nums[i]
        }
        return true;
    }

}
