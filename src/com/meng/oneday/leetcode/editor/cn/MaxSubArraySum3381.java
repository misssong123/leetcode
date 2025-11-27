package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaxSubArraySum3381 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了40.91% 的Java用户
     * 	内存消耗:145.8 MB,击败了18.18% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maxSubArraySum3381(int[] nums, int k) {
        long[] max = new long[k];
        int len = nums.length;
        long sum = 0;

        int index = 0;
        long res = Long.MIN_VALUE;
        for (int i = 0 ; i < len;i++){
            sum += nums[i];
            if (i >= k -1 ){
                index = index % k;
                max[index] = Math.max(max[index],0) + sum;
                res = Math.max(res,max[index]);
                index++;
                sum -= nums[i - k + 1];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了100.00% 的Java用户
     * 	内存消耗:145.8 MB,击败了18.18% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maxSubarraySumOther(int[] nums, int k) {
        long[] minS = new long[k];
        Arrays.fill(minS, 0, k - 1, Long.MAX_VALUE / 2); // 防止下面减法溢出
        long ans = Long.MIN_VALUE;
        long s = 0;
        for (int j = 0; j < nums.length; j++) {
            s += nums[j];
            int i = j % k;
            ans = Math.max(ans, s - minS[i]);
            minS[i] = Math.min(minS[i], s);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:146 MB,击败了10.60% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maxSubarraySum(int[] nums, int k) {
        long[] min = new long[k];
        long sum = 0;
        //初始化
        for(int i = 0 ; i < k -1 ; i++){
            sum += nums[i];
            min[i+1] = sum;
        }
        //计算
        long res = Long.MIN_VALUE;
        for (int i = k - 1 ; i < nums.length ; i++){
            sum += nums[i];
            int index = (i + 1) % k;
            res = Math.max(res,sum - min[index]);
            min[index] = Math.min(min[index],sum);
        }
        return res;
    }
}
