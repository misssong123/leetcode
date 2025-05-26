package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class FindWordsContaining2942 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了32.70% 的Java用户
     * 	内存消耗:44.4 MB,击败了5.28% 的Java用户
     * @param words
     * @param x
     * @return
     */
    public List<Integer> findWordsContaining2942(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < words.length; i++){
            if (words[i].contains(String.valueOf(x))){
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了5.28% 的Java用户
     * @param words
     * @param x
     * @return
     */
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            if (words[i].indexOf(x) != -1) {
                res.add(i);
            }
        }
        return res;
    }

}
