package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class TwoSum {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.68% 的Java用户
     * 	内存消耗:44.3 MB,击败了17.91% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if (cache.containsKey(target - nums[i])){
                return new int[]{cache.get(target - nums[i]),i};
            }
            cache.put(nums[i],i);
        }
        return new int[]{0,0};
    }
}
