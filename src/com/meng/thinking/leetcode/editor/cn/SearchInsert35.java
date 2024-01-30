package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class SearchInsert35 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了63.06% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertMy(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right + left)/2;
            if (nums[mid] == target){
                return mid;
            }else if(nums[mid]< target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
