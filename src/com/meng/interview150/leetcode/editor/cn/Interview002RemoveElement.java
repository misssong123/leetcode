package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview002RemoveElement {
    /**
     * 前后指针，依次将不符合的数据进行覆盖
     * @param nums
     * @param val
     * @return
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了77.85% 的Java用户
     */
    public int removeElement(int[] nums, int val) {
        int cur = 0,last = 0;
        while (last < nums.length){
            if(nums[last] != val){
                nums[cur++] = nums[last];
            }
            last++;
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
