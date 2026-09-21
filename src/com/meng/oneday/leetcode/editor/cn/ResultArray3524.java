package com.meng.oneday.leetcode.editor.cn;

class ResultArray3524 {
    /**
     * 超时
     * @param nums
     * @param k
     * @return
     */
    public long[] resultArrayTimeOut(int[] nums, int k) {
        long [] res = new long[k];
        int len  = nums.length;
        for (int i = 0 ; i < len ; i++){
            int pre = nums[i] % k;
            res[pre] += 1;
            if (pre == 0){
                res[pre] += len - i - 1;
                continue;
            }
            for (int j = i + 1 ; j < len ; j++){
                pre = (int) ((long)pre * nums[j] % k);
                res[pre] += 1;
                if (pre == 0){
                    res[pre] += len - j - 1;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了75.00% 的Java用户
     * 	内存消耗:91.6 MB,击败了83.33% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long[] resultArray3524(int[] nums, int k) {
        long [] res = new long[k];
        long[] temp = new long[k];
        for(int num : nums){
            long[] newTemp = new long[k];
            newTemp[num%k]++;
            for(int i = 0; i < k; i++){
                int pre = (int)((long)i * num % k);
                newTemp[pre] += temp[i];
            }
            for(int i = 0; i < k; i++){
                res[i] += newTemp[i];
            }
            temp = newTemp;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了66.67% 的Java用户
     * 	内存消耗:91.8 MB,击败了66.67% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long[] resultArrayAi(int[] nums, int k) {
        long[] ans = new long[k];
        // dp[r] 表示以当前元素结尾的子数组中，乘积 % k == r 的子数组数量
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];
            int numMod = num % k;

            // 1. 只有当前元素 num 自己构成一个全新的子数组
            newDp[numMod] = 1;

            // 2. 将当前元素追加到之前以 num[i-1] 结尾的子数组后面
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newMod = (int) ((1L * r * numMod) % k);
                    newDp[newMod] += dp[r];
                }
            }

            // 3. 将当前位置的所有可能性累加到最终结果 ans 中
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp; // 滚动更新 DP 数组
        }

        return ans;
    }
}
