package com.meng.top100.leetcode.editor.cn;

class FindPeakElement_162 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了5.21% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElement162(int[] nums) {
        for(int i = 0 ; i < nums.length - 1 ; i++){
            if (nums[i] > nums[i+1]){
                return i;
            }
        }
        return nums.length - 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了14.68% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = -1;
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
