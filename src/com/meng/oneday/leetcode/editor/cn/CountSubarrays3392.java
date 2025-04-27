package com.meng.oneday.leetcode.editor.cn;

class CountSubarrays3392 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.2 MB,击败了22.37% 的Java用户
     * @param nums
     * @return
     */
    public int countSubarrays3392(int[] nums) {
        int res = 0;
        for(int i = 1 ; i < nums.length -1 ; i++){
            if (nums[i]%2 == 0 && (nums[i-1] + nums[i+1] == nums[i]/2)){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.2 MB,击败了21.05% 的Java用户
     * @param nums
     * @return
     */
    public int countSubarrays(int[] nums) {
        int ans = 0;
        for (int i = 2; i < nums.length; i++) {
            if ((nums[i - 2] + nums[i]) * 2 == nums[i - 1]) {
                ans++;
            }
        }
        return ans;
    }

}
