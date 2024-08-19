package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class Interview041WordPattern {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.29% 的Java用户
     * 	内存消耗:40.5 MB,击败了36.96% 的Java用户
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPatternMy(String pattern, String s) {
        String[] strs = s.split(" ");
        if(pattern.length() != strs.length){
            return false;
        }
        Map<Character,String> sCache = new HashMap<>();
        Map<String,Character> tCache = new HashMap<>();
        for(int i = 0 ; i < pattern.length() ; i++){
            char sChar = pattern.charAt(i);
            String str = strs[i];
            if (!sCache.getOrDefault(sChar,str).equals(str)||tCache.getOrDefault(str,sChar) != sChar){
                return false;
            }else {
                tCache.put(str,sChar);
                sCache.put(sChar,str);
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.29% 的Java用户
     * 	内存消耗:40.6 MB,击败了5.42% 的Java用户
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }
}
