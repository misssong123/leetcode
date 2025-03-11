package com.meng.oneday.leetcode.editor.cn;

class SumOfBeauties2012 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了39.02% 的Java用户
     * 	内存消耗:60.7 MB,击败了12.20% 的Java用户
     * @param nums
     * @return
     */
    public int sumOfBeauties2012(int[] nums) {
        int len = nums.length;
        int[] min = new int[len];
        //后缀求最小值
        min[len-1] = nums[len-1];
        for(int i = len -2 ; i >= 0 ; i--){
            min[i] = Math.min(min[i+1],nums[i+1]);
        }
        int res = 0;
        int max = nums[0];
        for(int i = 1 ; i < len -1 ; i++){
            if(max < nums[i] && nums[i] < min[i]){
                res += 2;
            }else if(nums[i-1] < nums[i] && nums[i] < nums[i+1]){
                res += 1;
            }
            max = Math.max(max,nums[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了60.98% 的Java用户
     * 	内存消耗:59.3 MB,击败了43.90% 的Java用户
     * @param nums
     * @return
     */
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] sufMin = new int[n]; // 后缀最小值
        sufMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i > 1; i--) {
            sufMin[i] = Math.min(sufMin[i + 1], nums[i]);
        }

        int ans = 0;
        int preMax = nums[0]; // 前缀最大值
        for (int i = 1; i < n - 1; i++) {
            int x = nums[i];
            // 此时 preMax 表示 [0, i-1] 中的最大值
            if (preMax < x && x < sufMin[i + 1]) {
                ans += 2;
            } else if (nums[i - 1] < x && x < nums[i + 1]) {
                ans++;
            }
            // 更新后 preMax 表示 [0, i] 中的最大值
            preMax = Math.max(preMax, x);
        }
        return ans;
    }

}
