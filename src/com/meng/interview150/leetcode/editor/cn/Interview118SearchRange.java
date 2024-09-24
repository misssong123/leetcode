package com.meng.interview150.leetcode.editor.cn;

class Interview118SearchRange {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.9 MB,击败了51.00% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeMy(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        while(left <= right){
            int mid = left +(right - left)/2;
            if(nums[mid] == target){
                int[] res = {mid,mid};
                while(res[0] > 0 && nums[res[0]-1] == target){
                    res[0]--;
                }
                while(res[1] < nums.length -1 && nums[res[1]+1] == target){
                    res[1]++;
                }
                return res;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return new int[]{-1,-1};
    }
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
