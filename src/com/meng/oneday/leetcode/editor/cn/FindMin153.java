package com.meng.oneday.leetcode.editor.cn;

class FindMin153 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了26.97% 的Java用户
     * @param nums
     * @return
     */
    public int findMin153(int[] nums) {
        int l = 0, r = nums.length - 1;
        int res = nums[l];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= nums[r]) {
                r = mid - 1;
                res = Math.min(nums[mid],res);
            }else if (nums[mid] >= nums[l]) {
                l = mid + 1;
            }else{
                l = mid + 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.1 MB,击败了56.29% 的Java用户
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = -1;
        int right = n - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            if (nums[mid] < nums[n - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right];
    }
}
