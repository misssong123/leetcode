package com.meng.oneday.leetcode.editor.cn;

class LongestArithSeqLength1027 {
    /**
     * 解答成功:
     * 	执行耗时:2674 ms,击败了5.13% 的Java用户
     * 	内存消耗:45.4 MB,击败了99.36% 的Java用户
     * @param nums
     * @return
     */
    public int longestArithSeqLength(int[] nums) {
        int len =  nums.length;
        int res = 0;
        for (int i = 0 ; i < len -1 ; i++) {
            for (int j = i + 1; j < len; j++) {
                int diff = nums[j] - nums[i];
                int target = nums[j] + diff;
                int cnt = 2;
                for(int k = j + 1; k < len; k++) {
                    if (nums[k] == target) {
                        cnt++;
                        target += diff;
                    }
                }
                if (cnt == len) {
                    return cnt;
                }
                res = Math.max(res,cnt);
            }
        }
        return res;
    }
}
