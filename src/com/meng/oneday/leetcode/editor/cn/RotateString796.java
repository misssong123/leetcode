package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class RotateString796 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了86.82% 的Java用户
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString796(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] gChars = goal.toCharArray();
        for (int i = 0 ; i < s.length(); i++) {
            if(check(sChars, gChars, i)){
                return true;
            }
        }
        return false;
    }

    private boolean check(char[] sChars, char[] gChars, int start) {
        for(int i = start,j = 0; i < sChars.length; i++,j++){
            if(sChars[i] != gChars[j]){
                return false;
            }
        }
        for(int i = 0,j = sChars.length - start; i < start; i++,j++){
            if(sChars[i] != gChars[j]){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了67.73% 的Java用户
     * 	内存消耗:42.8 MB,击败了75.08% 的Java用户
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateStringOther1(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了67.73% 的Java用户
     * 	内存消耗:42.8 MB,击败了68.05% 的Java用户
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateStringKMP(String s, String goal) {
        return s.length() == goal.length() &&
                !kmpSearch((s + s).toCharArray(), goal.toCharArray()).isEmpty();
    }

    // 下面是 KMP 模板。对于本题来说，找到一个就可以返回了。为方便大家使用，我保留了完整的模板。
    // 在文本串 text 中查找模式串 pattern，返回所有成功匹配的位置（pattern[0] 在 text 中的下标）
    private List<Integer> kmpSearch(char[] text, char[] pattern) {
        int m = pattern.length;
        int[] pi = new int[m];
        int cnt = 0;
        for (int i = 1; i < m; i++) {
            char b = pattern[i];
            while (cnt > 0 && pattern[cnt] != b) {
                cnt = pi[cnt - 1];
            }
            if (pattern[cnt] == b) {
                cnt++;
            }
            pi[i] = cnt;
        }

        List<Integer> pos = new ArrayList<>();
        cnt = 0;
        for (int i = 0; i < text.length; i++) {
            char b = text[i];
            while (cnt > 0 && pattern[cnt] != b) {
                cnt = pi[cnt - 1];
            }
            if (pattern[cnt] == b) {
                cnt++;
            }
            if (cnt == m) {
                pos.add(i - m + 1);
                cnt = pi[cnt - 1];
            }
        }
        return pos;
    }


}
