package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class DivideArray2966 {
    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了84.38% 的Java用户
     * 	内存消耗:57.2 MB,击败了75.00% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int[][] divideArray2966(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] res = new int[n/3][3];
        for(int i = 0 ; i < n / 3 ; i++){
            int j = i * 3;
            res[i][0] = nums[j];
            res[i][1] = nums[j+1];
            res[i][2] = nums[j+2];
            if(nums[j+2] - nums[j] > k){
                return new int[0][0];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了100.00% 的Java用户
     * 	内存消耗:57.1 MB,击败了90.63% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] ans = new int[n / 3][];
        for (int i = 2; i < n; i += 3) {
            if (nums[i] - nums[i - 2] > k) {
                return new int[][]{};
            }
            ans[i / 3] = new int[]{nums[i - 2], nums[i - 1], nums[i]};
        }
        return ans;
    }

}
