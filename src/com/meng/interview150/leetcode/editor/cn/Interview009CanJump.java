package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview009CanJump {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了95.91% 的Java用户
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length ;i++){
            if (i > max){
                return false;
            }
            max = Math.max(max,i+nums[i]);
            if (max >= nums.length-1){
                return true;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
