package com.meng.top100.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class TwoSum1 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.67% 的Java用户
     * 	内存消耗:44.2 MB,击败了33.64% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }
}
