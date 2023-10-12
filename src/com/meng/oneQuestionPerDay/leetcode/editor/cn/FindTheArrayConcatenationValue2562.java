package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionFindTheArrayConcVal2562 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了30.50% 的Java用户
     * 	内存消耗:42.3 MB,击败了22.00% 的Java用户
     * @param nums
     * @return
     */
    public long findTheArrayConcVal1(int[] nums) {
        int left = 0 , right = nums.length-1;
        long res = 0;
        while (left <= right){
            if (left==right){
                res += nums[left];
            }else {
                res += (Integer.parseInt(nums[left] +"" + nums[right]));
            }
            left++;
            right--;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了62.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了34.50% 的Java用户
     * @param nums
     * @return
     */
    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {
            if (i != j) {
                ans += Integer.parseInt(Integer.toString(nums[i]) + Integer.toString(nums[j]));
            } else {
                ans += nums[i];
            }
        }
        return ans;
    }

}

