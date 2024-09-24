package com.meng.interview150.leetcode.editor.cn;

class Interview119FindMin {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了41.41% 的Java用户
     * @param nums
     * @return
     */
    public int findMinMy(int[] nums) {
        int left = 0 , right = nums.length - 1;
        if (left == right || nums[left] < nums[right]) {//升序
            return nums[left];
        }
        int n = right;
        //倒序
        if (nums[n] < nums[n - 1]&&nums[n]<nums[0]) {
            return nums[n];
        }
        while (left <= right) {
            int mid = left + ( right - left) /2;
            if (mid == 0){
                left = mid+1;
            }else if(mid == n){
                right = mid-1;
            }else if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
                return nums[mid];
            } else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return nums[mid+1];
            }else if (nums[mid] > nums[mid - 1] && nums[mid] >nums[0]) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return nums[left];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了56.83% 的Java用户
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }
}
