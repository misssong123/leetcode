package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MaxVowels1456 {
    /**
     *
     * @param s
     * @param k
     * @return
     * 时间
     * 详情
     * 22ms
     * 击败 27.41%使用 Java 的用户
     * 内存
     * 详情
     * 41.39mb
     * 击败 92.20%使用 Java 的用户
     */
    static Set<Character> cache = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    public int maxVowels(String s, int k) {
        int res = 0,len = s.length();
        //控制初始答案
        for(int i = 0 ; i < k ; i++){
            if(cache.contains(s.charAt(i))){
                res++;
            }
        }
        int temp = res;
        for(int i = k ; i < len ; i++){
            if (res == k){
                return k;
            }
            if(cache.contains(s.charAt(i))){
                temp+=1;
            }
            if(cache.contains(s.charAt(i - k))){
                temp-=1;
            }
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 时间
     * 详情
     * 11ms
     * 击败 84.08%使用 Java 的用户
     * 内存
     * 详情
     * 41.44mb
     * 击败 88.47%使用 Java 的用户
     * @param s
     * @param k
     * @return
     */
    public int maxVowels1(String s, int k) {
        int n = s.length();
        int vowel_count = 0;
        for (int i = 0; i < k; ++i) {
            vowel_count += isVowel(s.charAt(i));
        }
        int ans = vowel_count;
        for (int i = k; i < n; ++i) {
            vowel_count += isVowel(s.charAt(i)) - isVowel(s.charAt(i - k));
            ans = Math.max(ans, vowel_count);
        }
        return ans;
    }

    public int isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }
}

