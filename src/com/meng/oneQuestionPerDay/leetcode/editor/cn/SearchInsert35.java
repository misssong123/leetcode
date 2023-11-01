package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class SearchInsert35 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.3 MB,击败了21.32% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0 ,right = nums.length-1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid+1;
            }else {
                right = mid - 1;
            }
        }
        return right+1;
    }

    /**
     * 时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 40.92MB
     * 击败 89.65%使用 Java 的用户
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
