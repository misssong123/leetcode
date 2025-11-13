package com.meng.hot100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LetterCombinations17 {
    private final static String[][] KEY = {
            {"a","b","c"},
            {"d","e","f"},
            {"g","h","i"},
            {"j","k","l"},
            {"m","n","o"},
            {"p","q","r","s"},
            {"t","u","v"},
            {"w","x","y","z"}
    };
    List<String> res ;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.8 MB,击败了5.02% 的Java用户
     * @param digits
     * @return
     */
    public List<String> letterCombinations17(String digits) {
        StringBuffer sb = new StringBuffer();
        res = new ArrayList<>();
        dfs(digits,0,sb);
        return res;
    }

    private void dfs(String digits, int index, StringBuffer sb) {
        if(index == digits.length()){
            res.add(sb.toString());
            return;
        }
        for (String c : KEY[digits.charAt(index) - '2']) {
            sb.append(c);
            dfs(digits,index+1,sb);
            sb.deleteCharAt(index);
        }
    }
    private static final String[] MAPPING = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.8 MB,击败了5.02% 的Java用户
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        char[] path = new char[n]; // 注意 path 长度一开始就是 n，不是空数组
        dfs(0, ans, path, digits.toCharArray());
        return ans;
    }
    private void dfs(int i, List<String> ans, char[] path, char[] digits) {
        if (i == digits.length) {
            ans.add(new String(path));
            return;
        }
        String letters = MAPPING[digits[i] - '0'];
        for (char c : letters.toCharArray()) {
            path[i] = c; // 直接覆盖
            dfs(i + 1, ans, path, digits);
        }
    }
}
