package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumCount2529 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了88.97% 的Java用户
     * @param nums
     * @return
     */
    public int maximumCountMy(int[] nums) {
        int left =  0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/ 2;
            if (nums[mid]>0){
                right = mid - 1;
            }else {
                left = mid+1;
            }
        }
        right = left;
        if (left >= nums.length){
            left = nums.length-1;
        }
        while (left >= 0 && nums[left]>=0){
            left--;
        }
        return Math.max(left+1,nums.length - right);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.8 MB,击败了70.26% 的Java用户
     * @param nums
     * @return
     */
    public int maximumCount(int[] nums) {
        int pos1 = lowerBound(nums, 0);
        int pos2 = lowerBound(nums, 1);
        return Math.max(pos1, nums.length - pos2);
    }

    public int lowerBound(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] >= val) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
