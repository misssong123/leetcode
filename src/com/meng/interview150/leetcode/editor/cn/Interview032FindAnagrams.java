package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Interview032FindAnagrams {
    /**
     * 解答成功:
     * 	执行耗时:39 ms,击败了25.65% 的Java用户
     * 	内存消耗:44.6 MB,击败了21.00% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsMy(String s, String p) {
        int n = p.length(), m = s.length();
        List<Integer> ans = new ArrayList<>();
        if (n > m){
            return ans;
        }
        //初始化
        Map<Character,Integer> cache = new HashMap<>();
        for(char c : p.toCharArray()){
            cache.put(c,cache.getOrDefault(c,0)-1);
        }
        for(int i = 0 ; i < n ; i++){
            char c = s.charAt(i);
            cache.put(c,cache.getOrDefault(c,0)+1);
            if (cache.get(c) == 0){
                cache.remove(c);
            }
        }
        if (cache.isEmpty()){
            ans.add(0);
        }
        //滑动窗口
        for(int i = n ; i < m ; i++){
            char c = s.charAt(i);
            char preC = s.charAt(i-n);
            cache.put(preC,cache.getOrDefault(preC,0)-1);
            if (cache.get(preC) == 0){
                cache.remove(preC);
            }
            cache.put(c,cache.getOrDefault(c,0)+1);
            if (cache.get(c) == 0){
                cache.remove(c);
            }
            if (cache.isEmpty()){
                ans.add(i-n+1);
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了56.05% 的Java用户
     * 	内存消耗:43.6 MB,击败了87.03% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] count = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }

        int differ = 0;
        for (int j = 0; j < 26; ++j) {
            if (count[j] != 0) {
                ++differ;
            }
        }

        if (differ == 0) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            if (count[s.charAt(i) - 'a'] == 1) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从不同变得相同
                --differ;
            } else if (count[s.charAt(i) - 'a'] == 0) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }
            --count[s.charAt(i) - 'a'];

            if (count[s.charAt(i + pLen) - 'a'] == -1) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从不同变得相同
                --differ;
            } else if (count[s.charAt(i + pLen) - 'a'] == 0) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }
            ++count[s.charAt(i + pLen) - 'a'];

            if (differ == 0) {
                ans.add(i + 1);
            }
        }

        return ans;
    }


}
