package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaxAlternatingSum3727 {
    /**
     * 解答成功:
     * 	执行耗时:36 ms,击败了70.00% 的Java用户
     * 	内存消耗:129.3 MB,击败了100.00% 的Java用户
     * @param nums
     * @return
     */
    public long maxAlternatingSum3727(int[] nums) {
        //取绝对值
        for (int i = 0 ; i < nums.length ; i++){
            nums[i] = Math.abs(nums[i]);
        }
        //排序
        Arrays.sort(nums);
        //计算最大值
        long res = 0L;
        int addNum = (nums.length + 1) /2;
        for (int i = nums.length - 1,j=1 ; i >= 0 ; i--,j++){
            if (j <= addNum){
                res += (long) nums[i] *nums[i];
            }else {
                res -= (long) nums[i] * nums[i];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:36 ms,击败了70.00% 的Java用户
     * 	内存消耗:129.6 MB,击败了100.00% 的Java用户
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);

        int m = n / 2;
        // 交替和：减去小的，加上大的
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans -= nums[i];
        }
        for (int i = m; i < n; i++) {
            ans += nums[i];
        }
        return ans;
    }

}
