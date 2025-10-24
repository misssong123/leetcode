package com.meng.hot100.leetcode.editor.cn;

class MinSubArrayLen209 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.88% 的Java用户
     * 	内存消耗:53.9 MB,击败了80.56% 的Java用户
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen209(int target, int[] nums) {
        int l = 0 ,min = Integer.MAX_VALUE,sum = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            while(sum >= target){
                min = Math.min(min, i - l + 1);
                sum -= nums[l++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.88% 的Java用户
     * 	内存消耗:56.8 MB,击败了60.43% 的Java用户
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = n + 1;
        int sum = 0; // 子数组元素和
        int left = 0; // 子数组左端点
        for (int right = 0; right < n; right++) { // 枚举子数组右端点
            sum += nums[right];
            while (sum >= target) { // 满足要求
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++; // 左端点右移
            }
        }
        return ans <= n ? ans : 0;
    }
}
