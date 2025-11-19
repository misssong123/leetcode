package com.meng.top100.leetcode.editor.cn;

class MaxSubArraySumCircular918 {
    /**
     * 超时
     * @param nums
     * @return
     */
    public int maxSubarraySumCircularTimeOut(int[] nums) {
        int len = nums.length;
        int res = Integer.MIN_VALUE;
        for(int i = 0 ; i < len ; i++){
            if (i > 0 &&nums[i] < 0){
                continue;
            }
            int pre = 0;
            for(int j = i ; j < i + len ; j++){
                pre = Math.max(0,pre) + nums[j % len];
                res = Math.max(res,pre);
            }
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了26.19% 的Java用户
     * 	内存消耗:50 MB,击败了15.20% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubarraySumCircularOther(int[] nums) {
        int maxF = 0; // 计算最大子数组和的 DP 数组（空间优化成一个变量）
        int maxS = Integer.MIN_VALUE; // 最大子数组和，不能为空
        int minF = 0; // 计算最小子数组和的 DP 数组（空间优化成一个变量）
        int minS = 0; // 最小子数组和，可以为空（元素和为 0）
        int sum = 0; // nums 的元素和

        for (int x : nums) {
            // 53. 最大子数组和（空间优化写法）
            maxF = Math.max(maxF, 0) + x;
            maxS = Math.max(maxS, maxF);
            minF = Math.min(minF, 0) + x;
            minS = Math.min(minS, minF);
            sum += x;
        }

        return maxS < 0 ? maxS : Math.max(maxS, sum - minS);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了41.29% 的Java用户
     * 	内存消耗:50.1 MB,击败了12.45% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubarraySumCircularMyImProve(int[] nums) {
        int res = Integer.MIN_VALUE;
        int suf = 0;
        int[] sufMax =  new int[nums.length+1];
        sufMax[nums.length] = Integer.MIN_VALUE;
        //求出后缀最大值
        for(int i = nums.length -1 ; i >= 0 ; i--){
            suf+=nums[i];
            sufMax[i] = Math.max(suf,sufMax[i+1]);
            res = Math.max(res,sufMax[i]);
        }
        int pre = 0,preMax =Integer.MIN_VALUE;
        int max = 0;
        for(int i = 0 ; i < nums.length ; i++){
            pre += nums[i];
            preMax = Math.max(pre,preMax);
            max = Math.max(max,0) + nums[i];
            res = Math.max(max,Math.max(res,preMax + Math.max(0,sufMax[i+1])));
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了41.29% 的Java用户
     * 	内存消耗:50.1 MB,击败了12.06% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubArraySumCircular918(int[] nums) {
        int maxF = 0;
        int maxS = Integer.MIN_VALUE;
        int minF = 0;
        int minS = Integer.MAX_VALUE;
        int sum = 0;
        for(int num : nums){
            sum += num;
            maxF = Math.max(maxF ,0) + num;
            maxS = Math.max(maxS , maxF);
            minF = Math.min(minF,0) + num;
            minS = Math.min(minS , minF);
        }
        return maxS < 0 ? maxS : Math.max(maxS,sum - minS);
    }

}
