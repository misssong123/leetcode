package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class FindLHS594_6_30 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了60.73% 的Java用户
     * 	内存消耗:44.5 MB,击败了85.61% 的Java用户
     * @param nums
     * @return
     */
    public int findLHS594(int[] nums) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            cache.put(nums[i],cache.getOrDefault(nums[i],0)+1);
        }
        int ans = 0;
        for(Map.Entry<Integer,Integer> entry : cache.entrySet()){
            if (cache.containsKey(entry.getKey()+1)){
                ans = Math.max(ans,entry.getValue() + cache.get(entry.getKey()+1));
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了98.78% 的Java用户
     * 	内存消耗:44.8 MB,击败了48.78% 的Java用户
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
