package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class TwoSum1 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 98.13%
     * 的用户
     * 内存消耗：
     * 42.8 MB
     * , 在所有 Java 提交中击败了
     * 11.38%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            int num = nums[i];
            if (cache.get(target-num)!=null){
                return new int[]{cache.get(target-num),i};
            }
            cache.put(num,i);
        }
        return null;
    }
}

