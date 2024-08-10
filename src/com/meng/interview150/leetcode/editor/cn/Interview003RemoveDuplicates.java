package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview003RemoveDuplicates{
    /**
     * 与27题类似
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.8 MB,击败了77.08% 的Java用户
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int cur = 0,last = 1;
        while (last < nums.length){
            if(nums[last] != nums[cur]){
                nums[++cur] = nums[last];
            }
            last++;
        }
        return cur+1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
