package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class GenerateString3474 {
    /**
     * 解答成功:
     * 	执行耗时:41 ms,击败了10.00% 的Java用户
     * 	内存消耗:46.6 MB,击败了10.00% 的Java用户
     * @param str1
     * @param str2
     * @return
     */
    public String generateString3474(String str1, String str2) {
        int n = str1.length() , m = str2.length();
        int len =  n + m - 1;
        char[] chars = new char[len];
        Arrays.fill(chars, ' ');
        //处理为T的情况
        for(int i = 0 ; i < n ; i++){
            if (str1.charAt(i) == 'T'){
                for(int j = i ; j < i + m  ; j++){
                    char c = str2.charAt(j - i);
                    if (chars[j] == ' ' || chars[j] == c){
                        chars[j] = c;
                    }else {
                        return "";
                    }
                }
            }
        }
        //保存当前情况
        char[] temp = Arrays.copyOf(chars, len);
        //填充‘ ’为 a
        for(int i = 0 ; i < len ; i++){
            if (chars[i] == ' '){
                chars[i] = 'a';
            }
        }
        //校验F的情况
        for(int i = 0 ; i < n ; i++){
            if (str1.charAt(i) == 'F'){
                if (!str2.equals(new String(chars,i,m))){
                    continue;
                }
                boolean flag = true;
                for(int j = i + m -1 ; j >= i  ; j--){
                    if (temp[j] == ' '){
                        flag = false;
                        chars[j] = 'b';
                        break;
                    }
                }
                if (flag){
                    return "";
                }
            }
        }
        return new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了10.00% 的Java用户
     * 	内存消耗:46 MB,击败了30.00% 的Java用户
     * @param S
     * @param t
     * @return
     */
    public String generateStringOther(String S, String t) {
        char[] s = S.toCharArray();
        int n = s.length;
        int m = t.length();
        char[] ans = new char[n + m - 1];
        Arrays.fill(ans, '?'); // '?' 表示待定位置

        // 处理 T
        for (int i = 0; i < n; i++) {
            if (s[i] != 'T') {
                continue;
            }
            // 子串必须等于 t
            for (int j = 0; j < m; j++) {
                char v = ans[i + j];
                if (v != '?' && v != t.charAt(j)) {
                    return "";
                }
                ans[i + j] = t.charAt(j);
            }
        }

        char[] oldAns = ans.clone();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == '?') {
                ans[i] = 'a'; // 待定位置的初始值为 'a'
            }
        }

        // 处理 F
        for (int i = 0; i < n; i++) {
            if (s[i] != 'F') {
                continue;
            }
            // 子串必须不等于 t
            if (!new String(ans, i, m).equals(t)) {
                continue;
            }
            // 找最后一个待定位置
            boolean ok = false;
            for (int j = i + m - 1; j >= i; j--) {
                if (oldAns[j] == '?') { // 之前填 'a'，现在改成 'b'
                    ans[j] = 'b';
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                return "";
            }
        }

        return new String(ans);
    }

}
