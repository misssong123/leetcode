package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Flipgame822 {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 22.78%
     * 的用户
     * 内存消耗：
     * 42.8 MB
     * , 在所有 Java 提交中击败了
     * 18.99%
     * 的用户
     * 通过测试用例：
     * 169 / 169
     * @param fronts
     * @param backs
     * @return
     */
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> cache = new HashSet<>();
        int len = fronts.length;
        for(int i = 0 ; i < len ; i++){
            if (fronts[i] == backs[i]){
                cache.add(fronts[i]);
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < len ; i ++){
            if (!cache.contains(fronts[i])){
                res.add(fronts[i]);
            }
            if (!cache.contains(backs[i])){
                res.add(backs[i]);
            }
        }
        return res.size()>0?res.stream().min(Integer::compare).get():0;
    }
}

