package com.meng.oneday.leetcode.editor.cn;

import java.util.List;

class MaxIncreasingSubArrays3350 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了61.85% 的Java用户
     * 	内存消耗:69.5 MB,击败了87.28% 的Java用户
     * @param nums
     * @return
     */
    public int maxIncreasingSubArrays3350(List<Integer> nums) {
        int ans = 1,pre = 1,cnt = 1;
        for(int i = 1 ; i < nums.size() ; i++){
            if(nums.get(i) > nums.get(i-1)){
                cnt++;
            }else{
                ans = Math.max(ans,Math.max(Math.min(pre,cnt),cnt/2));
                pre = cnt;
                cnt = 1;
            }
        }
        return Math.max(ans,Math.max(Math.min(pre,cnt),cnt/2));
    }

    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了36.99% 的Java用户
     * 	内存消耗:69.7 MB,击败了59.54% 的Java用户
     * @param nums
     * @return
     */
    public int maxIncreasingSubArraysOther(List<Integer> nums) {
        int ans = 0;
        int preCnt = 0;
        int cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            cnt++;
            // i 是严格递增段的末尾
            if (i == nums.size() - 1 || nums.get(i) >= nums.get(i + 1)) {
                ans = Math.max(ans, Math.max(cnt / 2, Math.min(preCnt, cnt)));
                preCnt = cnt;
                cnt = 0;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了57.80% 的Java用户
     * 	内存消耗:69.6 MB,击败了76.88% 的Java用户
     * @param nums
     * @return
     */
    public int maxIncreasingSubArrays(List<Integer> nums) {
        int n = nums.size();
        int cnt = 1, precnt = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            if (nums.get(i) > nums.get( i- 1)) {
                ++cnt;
            }else {
                precnt = cnt;
                cnt = 1;
            }
            ans = Math.max(ans, Math.min(precnt, cnt));
            ans = Math.max(ans, cnt / 2);
        }
        return ans;
    }

}
