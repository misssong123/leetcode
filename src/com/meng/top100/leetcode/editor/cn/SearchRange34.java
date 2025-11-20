package com.meng.top100.leetcode.editor.cn;

class SearchRange34 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.2 MB,击败了11.21% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange34(int[] nums, int target) {
        int l = 0 , r = nums.length - 1,ans = -1;
        while(l <= r){
            int mid = l + r >> 1;
            if(nums[mid] >= target){
                ans = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        if(ans == -1 || nums[ans] != target){
            return new int[]{-1,-1};
        }
        int[] res = new int[]{ans,ans};
        while (ans + 1 < nums.length && nums[ans + 1] == target){
            ans++;
        }
        res[1] = ans;
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.3 MB,击败了8.76% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int start = lowerBound(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1}; // nums 中没有 target
        }
        // 如果 start 存在，那么 end 必定存在
        int end = lowerBound(nums, target + 1) - 1;
        return new int[]{start, end};
    }

    // lowerBound 返回最小的满足 nums[i] >= target 的下标 i
    // 如果数组为空，或者所有数都 < target，则返回 nums.length
    // 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) { // 区间不为空
            // 循环不变量：
            // nums[left-1] < target
            // nums[right+1] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1; // 范围缩小到 [left, mid-1]
            } else {
                left = mid + 1; // 范围缩小到 [mid+1, right]
            }
        }
        // 循环结束后 left = right+1
        // 此时 nums[left-1] < target 而 nums[left] = nums[right+1] >= target
        // 所以 left 就是第一个 >= target 的元素下标
        return left;
    }
}
