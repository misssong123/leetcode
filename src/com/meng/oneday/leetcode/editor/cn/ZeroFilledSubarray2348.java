package com.meng.oneday.leetcode.editor.cn;

class ZeroFilledSubarray2348 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了93.75% 的Java用户
     * 	内存消耗:60.4 MB,击败了71.88% 的Java用户
     * @param nums
     * @return
     */
    public long zeroFilledSubarray2348(int[] nums) {
        long ans = 0 ;
        int count = 0;
        for(int num : nums){
            if (num == 0){
                count++;
                ans += count;
            }else {
                count = 0;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了93.75% 的Java用户
     * 	内存消耗:60.6 MB,击败了43.75% 的Java用户
     * @param nums
     * @return
     */
    public long zeroFilledSubarrayOther(int[] nums) {
        long ans = 0;
        int last = -1;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x != 0) {
                last = i; // 记录上一个非 0 元素的位置
            } else {
                ans += i - last;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了93.75% 的Java用户
     * 	内存消耗:61.8 MB,击败了6.25% 的Java用户
     * @param nums
     * @return
     */
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int cnt0 = 0;
        for (int x : nums) {
            if (x != 0) {
                cnt0 = 0;
            } else {
                cnt0++; // 右端点为 i 的全 0 子数组比右端点为 i-1 的全 0 子数组多一个
                ans += cnt0;
            }
        }
        return ans;
    }

}
