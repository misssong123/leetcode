package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class UniqueOccurrences1207 {
    /**
     * 详情
     * 1ms
     * 击败 99.13%使用 Java 的用户
     * 内存
     * 详情
     * 38.74MB
     * 击败 21.46%使用 Java 的用户
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] cnt = new int[2001];
        for(int num : arr){
            cnt[num + 1000]++;
        }
        boolean[] vis = new boolean[1001];
        for(int num : cnt){
            if(num != 0){
                if(vis[num]){
                    return false;
                }
                vis[num] = true;
            }
        }
        return true;
    }

    /**
     * 时间
     * 详情
     * 2ms
     * 击败 83.29%使用 Java 的用户
     * 内存
     * 详情
     * 38.56MB
     * 击败 55.06%使用 Java 的用户
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences1(int[] arr) {
        Map<Integer, Integer> occur = new HashMap<Integer, Integer>();
        for (int x : arr) {
            occur.put(x, occur.getOrDefault(x, 0) + 1);
        }
        Set<Integer> times = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> x : occur.entrySet()) {
            times.add(x.getValue());
        }
        return times.size() == occur.size();
    }

}

