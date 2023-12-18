package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class FindPeakElement162 {
    /**
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了29.71% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElementMy(int[] nums) {
        int len = nums.length;
        for(int i = 0 ; i < len ; i++){
            boolean flag = true;
            if (i > 0){
                flag =  nums[i] > nums[i - 1];
            }
            if (flag&&i+1<len){
                flag = nums[i] > nums[i+1];
            }
            if (flag){
                return i;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了5.71% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElement1(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public int compare(int[] nums, int idx1, int idx2) {
        int[] num1 = get(nums, idx1);
        int[] num2 = get(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }

    /**
     *解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了8.85% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }


}
