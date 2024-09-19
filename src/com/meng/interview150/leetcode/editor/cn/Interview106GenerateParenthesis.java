package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview106GenerateParenthesis {


    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了68.51% 的Java用户
     * 	内存消耗:41.8 MB,击败了65.67% 的Java用户
     * @param n
     * @return
     */
    List<String> res;
    public List<String> generateParenthesisMy(int n) {
        res =  new ArrayList<>();
        dfs(n,0,0,new StringBuffer());
        return res;
    }

    private void dfs(int n, int l, int r, StringBuffer sb) {
        //左侧括号个数小于右侧括号个数，非法数据
        if (l < r){
            return;
        }
        if (r == n && l == n){
            res.add(sb.toString());
            return;
        }
        //当前位置放左括号
        if(l < n){
            sb.append("(");
            dfs(n,l+1,r,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        //当前位置放右括号
        if (r < l){
            sb.append(")");
            dfs(n,l,r+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }



    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了6.44% 的Java用户
     * 	内存消耗:42.4 MB,击败了19.50% 的Java用户
     * @param n
     * @return
     */
    ArrayList[] cache = new ArrayList[100];
    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    public List<String> generateParenthesis1(int n) {
        return generate(n);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了68.51% 的Java用户
     * 	内存消耗:41.7 MB,击败了90.07% 的Java用户
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


}
