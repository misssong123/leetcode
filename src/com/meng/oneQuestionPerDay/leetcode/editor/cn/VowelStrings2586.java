package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class VowelStrings2586 {
    static Set<Character> cache = new HashSet<>(Arrays.asList('a','e','i','o','u'));

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了76.39% 的Java用户
     * @param words
     * @param left
     * @param right
     * @return
     */
    public int vowelStringsMy(String[] words, int left, int right) {
        int res = 0;
        for(int i = left ; i <= right ; i++){
            if (cache.contains(words[i].charAt(0))&&cache.contains(words[i].charAt(words[i].length()-1))){
                res++;
            }
        }
        return res;
    }

    /**
     *解答成功:
     * 	执行耗时:2 ms,击败了31.42% 的Java用户
     * 	内存消耗:41.9 MB,击败了78.03% 的Java用户
     * @param words
     * @param left
     * @param right
     * @return
     */
    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> vowels = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
        }};
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            String word = words[i];
            if (vowels.contains(word.charAt(0)) && vowels.contains(word.charAt(word.length() - 1))) {
                ++ans;
            }
        }
        return ans;
    }

}
