package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解答成功:
 * 	执行耗时:27 ms,击败了91.67% 的Java用户
 * 	内存消耗:50.2 MB,击败了80.56% 的Java用户
 */
class WordDistance244 {
    Map<String, List<Integer>> cache = new HashMap<>();
    public WordDistance244(String[] wordsDict) {
        for(int i = 0 ; i < wordsDict.length ; i++){
            cache.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> list1 = cache.get(word1);
        List<Integer> list2 = cache.get(word2);
        int l = 0 , r = 0;
        int min = Integer.MAX_VALUE;
        while (l < list1.size() && r < list2.size()){
            int index1 = list1.get(l);
            int index2 = list2.get(r) ;
            min = Math.min(min,Math.abs(index2-index1));
            if (index1 < index2){
                l++;
            }else {
                r++;
            }
            if (min == 1){
                break;
            }
        }
        return min;
    }
}

