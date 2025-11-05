package com.meng.top100.leetcode.editor.cn;

import java.util.Arrays;

class SingleNumberLCR004 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了6.41% 的Java用户
     * 	内存消耗:44.8 MB,击败了9.94% 的Java用户
     * @param nums
     * @return
     */
    public int singleNumberLCR004(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length -1 ; i+= 3){
            if (nums[i] != nums[i+1]){
                return nums[i];
            }
        }
        return nums[nums.length -1];
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了64.74% 的Java用户
     * 	内存消耗:44.5 MB,击败了69.87% 的Java用户
     * @param nums
     * @return
     */
    public int singleNumberOther1(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt1 = 0;
            for (int x : nums) {
                cnt1 += x >> i & 1;
            }
            ans |= cnt1 % 3 << i;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.91% 的Java用户
     * 	内存消耗:44.5 MB,击败了65.71% 的Java用户
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            b = (b ^ x) & ~a;
            a = (a ^ x) & ~b;
        }
        return b;
    }

}
