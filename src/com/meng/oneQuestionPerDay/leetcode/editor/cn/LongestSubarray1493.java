package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class LongestSubarray1493 {
    /**
     * 时间
     * 详情
     * 2ms
     * 击败 86.03%使用 Java 的用户
     * 内存
     * 详情
     * 53.28mb
     * 击败 22.63%使用 Java 的用户
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        int res = 0 , k =1,left = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if (nums[i] == 0){
                k--;
            }
            while (k < 0){
                if (nums[left] == 0){
                    k++;
                }
                left++;
            }
            res = Math.max(res,i-left);
        }
        return res == nums.length ? res-1 : res;
    }

    /**
     *时间
     * 详情
     * 2ms
     * 击败 86.03%使用 Java 的用户
     * 内存
     * 详情
     * 52.89mb
     * 击败 43.58%使用 Java 的用户
     * @param nums
     * @return
     */
    public int longestSubarray1(int[] nums) {
        int ans = 0;
        int p0 = 0, p1 = 0;
        for (int num : nums) {
            if (num == 0) {
                p1 = p0;
                p0 = 0;
            } else {
                ++p0;
                ++p1;
            }
            ans = Math.max(ans, p1);
        }
        if (ans == nums.length) {
            --ans;
        }
        return ans;
    }

}

