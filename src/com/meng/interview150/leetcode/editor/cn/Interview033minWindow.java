package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class Interview033minWindow {
    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了42.75% 的Java用户
     * 	内存消耗:44.6 MB,击败了13.23% 的Java用户
     * 1.统计所有的字符
     * 2.滑动窗口
     * @param s
     * @param t
     * @return
     */
    public String minWindowMy(String s, String t) {
        if (s.length() < t.length()){
            return "";
        }
        Map<Character,Integer> cache = new HashMap<>();
        for(char c : t.toCharArray()){
            cache.put(c,cache.getOrDefault(c,0)-1);
        }
        int diff = cache.size();
        int left = 0;
        String res = "";
        for(int i = 0 ; i < s.length();i++){
            char c = s.charAt(i);
            if (cache.containsKey(c)){
                cache.put(c,cache.get(c)+1);
                //如果某个字符的数量为0，则diff--
                if (cache.get(c) == 0){
                    diff--;
                }
                //字符相同
                if (diff == 0){
                    while(left < i){
                        char leftC = s.charAt(left);
                        if (!cache.containsKey(leftC)){
                            left++;
                        }else if (cache.get(leftC) > 0){
                            cache.put(leftC,cache.get(leftC)-1);
                            left++;
                        }else {
                            break;
                        }
                    }
                    String temp = s.substring(left,i+1);
                    if (temp.length() < res.length() || res.isEmpty()){
                        res = temp;
                    }
                    char leftC = s.charAt(left);
                    cache.put(leftC,cache.get(leftC)-1);
                    diff++;
                    left++;
                }
            }
            if (res.length() == t.length()){
                break;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了82.07% 的Java用户
     * @param S
     * @param t
     * @return
     */
    public String minWindow(String S, String t) {
        char[] s = S.toCharArray();
        int m = s.length;
        int ansLeft = -1;
        int ansRight = m;
        int left = 0;
        int less = 0;
        int[] cntS = new int[128]; // s 子串字母的出现次数
        int[] cntT = new int[128]; // t 中字母的出现次数
        for (char c : t.toCharArray()) {
            if (cntT[c]++ == 0) {
                less++; // 有 less 种字母的出现次数 < t 中的字母出现次数
            }
        }
        for (int right = 0; right < m; right++) { // 移动子串右端点
            char c = s[right]; // 右端点字母（移入子串）
            if (++cntS[c] == cntT[c]) {
                less--; // c 的出现次数从 < 变成 >=
            }
            while (less == 0) { // 涵盖：所有字母的出现次数都是 >=
                if (right - left < ansRight - ansLeft) { // 找到更短的子串
                    ansLeft = left; // 记录此时的左右端点
                    ansRight = right;
                }
                char x = s[left++]; // 左端点字母（移出子串）
                if (cntS[x]-- == cntT[x]) {
                    less++; // x 的出现次数从 >= 变成 <
                }
            }
        }
        return ansLeft < 0 ? "" : S.substring(ansLeft, ansRight + 1);
    }
}
