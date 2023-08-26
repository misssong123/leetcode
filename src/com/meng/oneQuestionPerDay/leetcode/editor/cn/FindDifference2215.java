package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FindDifference2215 {
    /**
     * 时间
     * 详情
     * 9ms
     * 击败 91.73%使用 Java 的用户
     * 内存
     * 详情
     * 42.36MB
     * 击败 56.42%使用 Java 的用户
     * @param nums1
     * @param nums2
     * @return
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> cache = new HashSet<>();
        Set<Integer> cache1 = new HashSet<>();
        Set<Integer> cache2 = new HashSet<>();
        for(int num : nums1){
            cache.add(num);
        }
        for(int num : nums2){
            if(cache.contains(num)){
                cache1.add(num);
            }else{
                cache2.add(num);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        cache.removeAll(cache1);
        result.add(new ArrayList<>(cache));
        result.add(new ArrayList<>(cache2));
        return result;
    }

    /**
     * 时间
     * 详情
     * 9ms
     * 击败 91.73%使用 Java 的用户
     * 内存
     * 详情
     * 42.18MB
     * 击败 88.65%使用 Java 的用户
     * @param nums1
     * @param nums2
     * @return
     */
    public List<List<Integer>> findDifference1(int[] nums1, int[] nums2) {
        Set<Integer> cache1 = new HashSet<>();
        Set<Integer> cache2 = new HashSet<>();
        for(int num : nums1){
            cache1.add(num);
        }
        for(int num : nums2){
            cache2.add(num);
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for(int num : cache1){
           if (!cache2.contains(num)) {
               list1.add(num);
           }
        }
        for(int num : cache2){
            if (!cache1.contains(num)) {
                list2.add(num);
            }
        }
        result.add(list1);
        result.add(list2);
        return result;
    }
}

