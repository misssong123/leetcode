package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindMissingRanges163 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了53.64% 的Java用户
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<List<Integer>> findMissingRanges163(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0){
            res.add(Arrays.asList(lower, upper));
            return res;
        }
        //头部
        if (nums[0] > lower){
            res.add(Arrays.asList(lower, nums[0] - 1));
        }
        //中间
        for(int i = 0 ; i < nums.length - 1 ; i++){
            if (nums[i+1] - nums[i] > 1){
                res.add(Arrays.asList(nums[i] + 1, nums[i+1] - 1));
            }
        }
        //尾部
        if (nums[nums.length-1] < upper){
            res.add(Arrays.asList(nums[nums.length-1] + 1, upper));
        }
        return res;
    }
}
