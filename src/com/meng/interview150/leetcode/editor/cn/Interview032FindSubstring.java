package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Interview032FindSubstring {
    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了33.02% 的Java用户
     * 	内存消耗:44.9 MB,击败了23.01% 的Java用户
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        int len = s.length(),n=  words[0].length(),m = words.length;
        List<Integer> res = new ArrayList<>();
        //以不同位置开始作为开头(0-n),遍历以此为开头的滑动窗口
        for(int i = 0 ; i < n ; i++){
            if (i + n * m > len){
                break;
            }
            Map<String,Integer> cache = new HashMap<>();
            //记录当前需要的数据
            for(String word : words){
                cache.put(word,cache.getOrDefault(word,0)-1);
            }
            //初始化
            for(int j = i ;j < i + m * n ; j+=n){
                String word = s.substring(j,j+n);
                cache.put(word,cache.getOrDefault(word,0)+1);
                if (cache.get(word) == 0){
                    cache.remove(word);
                }
            }
            if (cache.isEmpty()){
                res.add(i);
            }
            //滑动窗口
            for(int j = i + n ; j < len -m * n+1 ; j+=n){
                String word = s.substring(j+(m-1)*n,j+m * n);
                String pre = s.substring(j-n,j);
                cache.put(word,cache.getOrDefault(word,0)+1);
                if (cache.get(word) == 0){
                    cache.remove(word);
                }
                cache.put(pre,cache.getOrDefault(pre,0)-1);
                if (cache.get(pre) == 0){
                    cache.remove(pre);
                }
                if (cache.isEmpty()){
                    res.add(j);
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了57.65% 的Java用户
     * 	内存消耗:44.9 MB,击败了22.67% 的Java用户
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstringOfficial(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<String, Integer>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

}
