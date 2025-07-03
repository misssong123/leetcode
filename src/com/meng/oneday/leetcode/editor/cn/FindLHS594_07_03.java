package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class FindLHS594_07_03 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了98.91% 的Java用户
     * 	内存消耗:44.6 MB,击败了81.69% 的Java用户
     * @param nums
     * @return
     */
    public int findLHS594(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] == nums[nums.length - 1]){
            return 0;
        }
        int res = 0;
        int l = 0;
        for(int i = 0 ; i < nums.length ; i++){
            while (nums[i] - nums[l] > 1){
                l++;
            }
            if (nums[i] - nums[l] == 1){
                res = Math.max(res,i - l + 1);
            }

        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了98.91% 的Java用户
     * 	内存消耗:44.8 MB,击败了58.52% 的Java用户
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>(nums.length);
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            int x = e.getKey();
            Integer c2 = cnt.get(x + 1);
            if (c2 != null) { // x+1 在 cnt 中
                ans = Math.max(ans, e.getValue() + c2);
            }
        }
        return ans;
    }

}
