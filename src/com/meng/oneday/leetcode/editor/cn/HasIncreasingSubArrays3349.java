package com.meng.oneday.leetcode.editor.cn;

import java.util.List;
class HasIncreasingSubArrays3349 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了48.84% 的Java用户
     * 	内存消耗:43.4 MB,击败了97.67% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean hasIncreasingSubArrays3349(List<Integer> nums, int k) {
        int pre = 0 , cnt = 1;
        for(int i = 1 ; i < nums.size() ; i++){
            if (nums.get(i) > nums.get(i-1)){
                cnt++;
            }else {
                pre = cnt;
                cnt = 1;
            }
            if(pre >= 2 * k || cnt >= 2 *k || (pre >=k && cnt >= k)){
                return true;
            }
        }
        return pre >= 2 * k || cnt >= 2 *k || (pre >=k && cnt >= k);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了48.84% 的Java用户
     * 	内存消耗:43.5 MB,击败了60.46% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean hasIncreasingSubArrays(List<Integer> nums, int k) {
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
        return ans >= k;
    }

}
