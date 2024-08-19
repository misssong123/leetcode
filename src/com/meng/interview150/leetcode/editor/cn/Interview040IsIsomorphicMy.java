package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class Interview040IsIsomorphicMy {
    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了48.16% 的Java用户
     * 	内存消耗:41.5 MB,击败了45.09% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphicMy(String s, String t) {
        Map<Character,Character> sCache = new HashMap<>();
        Map<Character,Character> tCache = new HashMap<>();

        int len = s.length();
        for(int i = 0 ; i < len ; i++){
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sCache.getOrDefault(sChar,tChar) != tChar||tCache.getOrDefault(tChar,sChar) != sChar){
                return false;
            }else {
                tCache.put(tChar,sChar);
                sCache.put(sChar,tChar);
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了41.07% 的Java用户
     * 	内存消耗:41.4 MB,击败了63.62% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
