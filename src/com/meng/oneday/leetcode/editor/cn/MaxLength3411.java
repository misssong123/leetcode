package com.meng.oneday.leetcode.editor.cn;

class MaxLength3411 {
    /**
     * 思路错误
     * @param nums
     * @return
     */
    public int maxLength3411(int[] nums) {
        int left = 0, right = 0, res = 0;
        while (right < nums.length) {
            if(nums[right] != 1) {
                for (int i = left ; i < right; i++){
                    if (nums[i] != 1 && ( nums[i] % nums[right] == 0
                        || nums[right] % nums[i] == 0 )) {
                        left = i + 1;
                    }
                }
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了65.91% 的Java用户
     * @param nums
     * @return
     */
    public int maxLength(int[] nums) {
        int ans = 2;
        int mul = 1;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (gcd(mul, nums[right]) > 1) {
                mul /= nums[left];
                left++;
            }
            mul *= nums[right];
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
