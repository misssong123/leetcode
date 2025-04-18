package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CountBadPairs2364 {
    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了82.61% 的Java用户
     * 	内存消耗:59.4 MB,击败了5.80% 的Java用户
     * @param nums
     * @return
     */
    public long countBadPairs2364(int[] nums) {
        int n = nums.length;
        long res = 0L;
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < n  ; i++){
            int count = cache.getOrDefault( nums[i] - i,0);
            res += (i-count);
            cache.put(nums[i]-i,count+1);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了82.61% 的Java用户
     * 	内存消耗:58.8 MB,击败了13.77% 的Java用户
     * @param nums
     * @return
     */
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long ans = (long) n * (n - 1) / 2;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i] - i;
            int c = cnt.getOrDefault(x, 0);
            ans -= c;
            cnt.put(x, c + 1);
        }
        return ans;
    }
}
