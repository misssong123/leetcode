package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview004RemoveDuplicates {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了75.70% 的Java用户
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int cur = 0,last = 2;
        while (last < nums.length){
            if(nums[last] != nums[cur]){
                nums[cur+2] = nums[last];
                cur++;
            }
            last++;
        }
        return cur+2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
