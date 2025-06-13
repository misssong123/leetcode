package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimizeMax2616 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了95.00% 的Java用户
     * 	内存消耗:54.8 MB,击败了87.00% 的Java用户
     * @param nums
     * @param p
     * @return
     */
    public int minimizeMax2616(int[] nums, int p) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0 ,right = nums[len-1]-nums[0];
        int res = right;
        while (left <= right){
            int mid = left + (right-left)/2;
            int count = 0;
            for(int i = 0 ; i < len -1 ; i++){
                if (nums[i+1] - nums[i] <= mid){
                    count++;
                    i++;
                }
            }
            if(count >= p){
                res = mid;
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了95.00% 的Java用户
     * 	内存消耗:54.9 MB,击败了77.00% 的Java用户
     * @param nums
     * @param p
     * @return
     */
    public int minimizeMaxOther(int[] nums, int p) {
        Arrays.sort(nums);
        int left = -1;
        int right = nums[nums.length - 1] - nums[0];
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(mid, nums, p)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int mx, int[] nums, int p) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] <= mx) { // 选 nums[i] 和 nums[i+1]
                cnt++;
                i++;
            }
        }
        return cnt >= p;
    }

}
