package com.meng.interview150.leetcode.editor.cn;

class Interview117Search {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了57.06% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int searchMy(int[] nums, int target) {
        int left = 0,right = nums.length -1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > nums[left]){//左侧升序
                if (target >= nums[left]&& target < nums[mid]){
                    right = mid -1;
                } else {
                    left = mid +1;
                }
            }else if (nums[mid] < nums[right]){//右侧升序
                if (target>nums[mid]&&target <= nums[right]){
                    left = mid +1;
                } else {
                    right = mid -1;
                }
            }else {//整体降序
                if (target>nums[mid]&&target <= nums[left]){
                    right = mid -1;
                } else {
                    left = mid +1;
                }
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了73.92% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}
