package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class LongestOnes1004 {
    /**
     * 详情
     * 2ms
     * 击败 99.17%使用 Java 的用户
     * 内存
     * 详情
     * 43.70mb
     * 击败 93.21%使用 Java 的用户
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int res = 0;
        int left = 0,temp = 0;
        for(int right = 0;right < nums.length;right++){
            if(nums[right] == 0){
                temp++;
            }
            while(temp > k){
                if(nums[left] == 0){
                    temp--;
                }
                left++;
            }
            res = Math.max(res,right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestOnes1004 demo = new LongestOnes1004();
        int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        System.out.println(demo.longestOnes(nums,k));
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     * 详情
     * 3ms
     * 击败 64.07%使用 Java 的用户
     * 内存
     * 详情
     * 43.58mb
     * 击败 96.49%使用 Java 的用户
     */
    public int longestOnes1(int[] nums, int k) {
        int n = nums.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            rsum += 1 - nums[right];
            while (lsum < rsum - k) {
                lsum += 1 - nums[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

}

