package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class RemoveOuterParentheses1021 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了30.15% 的Java用户
     * 	内存消耗:45.7 MB,击败了5.15% 的Java用户
     * @param s
     * @return
     */
    public String removeOuterParentheses1021(String s) {
        List<String> list = new ArrayList<>();
        int left = 0,right = 0;
        StringBuffer sb = new StringBuffer();
        //抽取原语
        for(char c : s.toCharArray()){
            sb.append(c);
            if(c == '('){
                left++;
            }else {
                right++;
                if (left == right) {
                    list.add(sb.toString());
                    left = 0;
                    right = 0;
                    sb = new StringBuffer();
                }
            }
        }
        //删除外层括号
        StringBuffer res = new StringBuffer();
        for(String str : list){
            if (str.length() > 2) {
                res.append(str, 1, str.length() - 1);
            }
        }
        return res.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.85% 的Java用户
     * 	内存消耗:42.8 MB,击败了63.24% 的Java用户
     * @param S
     * @return
     */
    public String removeOuterParentheses(String S) {
        char[] s = S.toCharArray();
        int size = 0;
        int depth = 0;
        for (char ch : s) {
            if (ch == '(') {
                if (depth > 0) {
                    s[size++] = ch;
                }
                depth++;
            } else {
                depth--;
                if (depth > 0) {
                    s[size++] = ch;
                }
            }
        }
        return new String(s, 0, size);
    }
}
