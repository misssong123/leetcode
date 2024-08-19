package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class Interview044TwoSum {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.64% 的Java用户
     * 	内存消耗:43.9 MB,击败了53.40% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < nums.length; i++){
            if (cache.containsKey(target - nums[i])){
                return new int[]{cache.get(target - nums[i]),i};
            }else {
                cache.put(nums[i],i);
            }
        }
        return null;
    }
}
