package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MaxOperations1679 {
    /**
     * 遍历
     * @param nums
     * @param k
     * @return
     * 详情
     * 45ms
     * 击败 7.08%使用 Java 的用户
     * 内存
     * 详情
     * 55.72mb
     * 击败 5.30%使用 Java 的用户
     */
    public int maxOperations(int[] nums, int k) {
        Map<Integer,Integer> cache = new HashMap<>();
        int ans = 0;
        for(int num : nums){
            if (cache.containsKey(k - num)&&cache.get(k-num)>0){
                ans++;
                cache.put(k - num,cache.get(k - num) - 1);
                continue;
            }
            cache.put(num,cache.getOrDefault(num,0)+1);
        }
        return ans;
    }

    /**
     * 排序+双指针
     * @param nums
     * @param k
     * @return
     * 详情
     * 18ms
     * 击败 97.34%使用 Java 的用户
     * 内存
     * 详情
     * 52.32mb
     * 击败 70.15%使用 Java 的用户
     */
    public int maxOperations1(int[] nums, int k) {
        Arrays.sort(nums);
        int result = 0;
        int i = 0, j = nums.length-1;
        int sum = 0;
        while(i<j){
            sum = nums[i] + nums[j];
            if(sum == k){
                ++result;
                ++i;
                --j;
            }
            else if(sum>k){
                --j;
            }
            else{
                ++i;
            }
        }

        return result;
    }

}

