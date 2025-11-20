package com.meng.top100.leetcode.editor.cn;

class Search33 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了10.91% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int search33(int[] nums, int target) {
        int l = 0 ,r = nums.length -1;
        while(l <= r){
            int mid = (l + r) >> 1;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > nums[r]){
                if (target > nums[mid] || target <= nums[r]){
                    l = mid +1;
                }else{
                    r = mid -1;
                }
            }else{
                if (target > nums[mid] && target <= nums[r]){
                    l = mid +1;
                }else{
                    r = mid -1;
                }
            }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        int last = nums[nums.length - 1];
        int left = -1;
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            int x = nums[mid];
            if (target > last && x <= last) { // target 在第一段，x 在第二段
                right = mid; // 下轮循环去左边找
            } else if (x > last && target <= last) { // x 在第一段，target 在第二段
                left = mid; // 下轮循环去右边找
            } else if (x >= target) { // 否则，x 和 target 在同一段，这就和方法一的 lowerBound 一样了
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
}
