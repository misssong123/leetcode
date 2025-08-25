package com.meng.oneday.leetcode.editor.cn;

class LongestSubArray1493 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了95.47% 的Java用户
     * 	内存消耗:56 MB,击败了30.57% 的Java用户
     * @param nums
     * @return
     */
    public int longestSubArray1493(int[] nums) {
        int res = 0,n = nums.length,temp =0,pre =0;
        for(int i = 0 ; i < n ; i++){
            if(nums[i] == 1){
                temp++;
            }else {
                res = Math.max(res,temp + pre);
                pre = temp;
                temp = 0;
                if (i > 0 && nums[i - 1] == 0) {
                    pre = 0;
                }
            }
        }
        return Math.min(n-1,Math.max(res,temp + pre));
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了40.47% 的Java用户
     * 	内存消耗:55.8 MB,击败了54.06% 的Java用户
     * @param nums
     * @return
     */
    public int longestSubArray(int[] nums) {
        int ans = 0;
        int cnt0 = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 1. 入，nums[right] 进入窗口
            cnt0 += 1 - nums[right]; // 维护窗口中的 0 的个数
            while (cnt0 > 1) { // 不符合题目要求
                // 2. 出，nums[left] 离开窗口
                cnt0 -= 1 - nums[left]; // 维护窗口中的 0 的个数
                left++;
            }
            // 3. 更新答案，注意不是 right-left+1，因为我们要删掉一个数
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
