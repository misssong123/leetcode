package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GenerateParenthesisLCR085 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了17.42% 的Java用户
     * @param n
     * @return
     */
    public List<String> generateParenthesisLCR085(int n) {
        List<String> res = new ArrayList<>();
        char[] temp = new char[n*2];
        dfs(res,temp,n,n,0);
        return res;
    }

    private void dfs(List<String> res, char[] temp, int l, int r,int index) {
        if (l == 0 && r == 0){
            res.add(new String(temp));
            return;
        }
        //只能添加左括号
        if(l == r){
            temp[index] = '(';
            dfs(res,temp,l-1,r,index+1);
        }else{
            //可以添加左括号
            if (l > 0){
                temp[index] = '(';
                dfs(res,temp,l-1,r,index+1);
            }
            //可以添加右括号
            if (r > 0){
                temp[index] = ')';
                dfs(res,temp,l,r-1,index+1);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了70.83% 的Java用户
     * 	内存消耗:43.6 MB,击败了16.29% 的Java用户
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, 0, n, path, ans);
        return ans;
    }

    // 目前填了 i 个括号
    // 这 i 个括号中的左括号个数 - 右括号个数 = balance
    private void dfs(int i, int balance, int n, List<Integer> path, List<String> ans) {
        if (path.size() == n) {
            char[] s = new char[n * 2];
            Arrays.fill(s, ')');
            for (int j : path) {
                s[j] = '(';
            }
            ans.add(new String(s));
            return;
        }
        // 枚举填 right=0,1,2,...,balance 个右括号
        for (int right = 0; right <= balance; right++) {
            // 先填 right 个右括号，然后填 1 个左括号，记录左括号的下标 i+right
            path.add(i + right);
            dfs(i + right + 1, balance - right + 1, n, path, ans);
            path.remove(path.size() - 1); // path.remove(path.size() - 1);
        }
    }
}
