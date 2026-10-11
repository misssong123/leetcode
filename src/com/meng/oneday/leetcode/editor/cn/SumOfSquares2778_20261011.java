package com.meng.oneday.leetcode.editor.cn;

class SumOfSquares2778_20261011 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了43.33% 的Java用户
     * @param nums
     * @return
     */
    public int sumOfSquares2778(int[] nums) {
        int res = 0,n = nums.length;
        for (int i = 1 ; i <= n  ; i++){
            if (n%i == 0){
                res += nums[i-1] * nums[i-1];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.8 MB,击败了10.00% 的Java用户
     * @param nums
     * @return
     */
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                ans += nums[i - 1] * nums[i - 1]; // 注意数组的下标还是从 0 开始的
                if (i * i < n) { // 避免重复统计
                    ans += nums[n / i - 1] * nums[n / i - 1];
                }
            }
        }
        return ans;
    }

}
