package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class GenerateParenthesis22 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了78.95% 的Java用户
     * @param n
     * @return
     */
    public List<String> generateParenthesis22(int n) {
        List<String> res = new ArrayList<>();
        char[] chars = new char[n * 2];
        dfs(res, chars, 0,0,0, n);
        return res;
    }

    private void dfs(List<String> res, char[] chars, int index, int l, int r, int n) {
        if(l == n && r == n){
            res.add(String.valueOf(chars));
            return;
        }
        //放置左括号
        if(l < n){
            chars[index] = '(';
            dfs(res, chars, index + 1, l + 1, r, n);
        }
        //放置右括号
        if(r < l){
            chars[index] = ')';
            dfs(res, chars, index + 1, l, r + 1, n);
        }
    }
}
