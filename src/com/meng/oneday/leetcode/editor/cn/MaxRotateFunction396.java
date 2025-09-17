package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaxRotateFunction396 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了34.20% 的Java用户
     * 	内存消耗:62.2 MB,击败了5.18% 的Java用户
     * @param nums
     * @return
     */
    public int maxRotateFunction396(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return 0;
        }
        //计算前缀和
        int[] prefix = new int[len];
        prefix[0] = nums[0];
        int res = 0;
        for(int i = 1 ; i < len ; i++){
            prefix[i] = prefix[i-1] + nums[i];
            res += i * nums[i];
        }
        int pre = res;
        for (int i = 1 ; i < len ; i ++){
            pre = pre + prefix[len - i - 1] - (len -1) * nums[len -i] + prefix[len -1] -prefix[len - i];
            res = Math.max(res,pre);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了7.25% 的Java用户
     * 	内存消耗:57.9 MB,击败了32.13% 的Java用户
     * @param nums
     * @return
     */
    public int maxRotateFunctionOfficial(int[] nums) {
        int f = 0, n = nums.length, numSum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }
        int res = f;
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了29.53% 的Java用户
     * 	内存消耗:57.6 MB,击败了77.20% 的Java用户
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n * 2 + 10];
        for (int i = 1; i <= 2 * n; i++) sum[i] = sum[i - 1] + nums[(i - 1) % n];
        int ans = 0;
        for (int i = 1; i <= n; i++) ans += nums[i - 1] * (i - 1);
        for (int i = n + 1, cur = ans; i < 2 * n; i++) {
            cur += nums[(i - 1) % n] * (n - 1);
            cur -= sum[i - 1] - sum[i - n];
            if (cur > ans) ans = cur;
        }
        return ans;
    }


}
