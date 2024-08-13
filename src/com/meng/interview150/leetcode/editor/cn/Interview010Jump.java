package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview010Jump {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.33% 的Java用户
     * 	内存消耗:44 MB,击败了56.05% 的Java用户
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length==1){
            return 0;
        }
        int maxIndex = nums[0];
        int max = nums[0];
        int count = 0;
        for(int i = 1; i < nums.length-1;i++){
            max = Math.max(max,i+nums[i]);
            if (i==maxIndex){
                maxIndex = max;
                count++;
            }
        }
        return count+1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
