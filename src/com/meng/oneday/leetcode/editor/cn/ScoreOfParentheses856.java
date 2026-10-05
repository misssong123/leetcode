package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class ScoreOfParentheses856 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了47.58% 的Java用户
     * 	内存消耗:42 MB,击败了78.23% 的Java用户
     * @param s
     * @return
     */
    public int scoreOfParentheses856(String s) {
        Map<Integer,Integer> map = new HashMap<>();
        int level = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                level++;
            }else{
                if (map.containsKey(level + 1)){
                    map.put(level,map.getOrDefault(level,0) + 2 * map.get(level + 1));
                    map.remove(level + 1);
                }else{
                    map.put(level,map.getOrDefault(level,0) + 1);
                }
                level--;
                if (level == 0){
                    res += map.get(1);
                    map.remove(1);
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了29.03% 的Java用户
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
        char[] s = S.toCharArray();
        int depth = 0;
        int ans = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '(') {
                depth++;
            } else {
                depth--;
                if (s[i - 1] == '(') { // 发现一对 "()"
                    ans += 1 << depth;
                }
            }
        }
        return ans;
    }

}
