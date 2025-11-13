package com.meng.hot100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class CombineLCR080 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了91.58% 的Java用户
     * 	内存消耗:46.7 MB,击败了6.31% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combineLCR080(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(res,temp,n,k,1);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> temp, int n, int k, int num) {
        if (temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        if (n - num >= k - temp.size()){
            //不选择当前数字
            dfs(res,temp,n,k,num+1);
        }
        //选择当前数字
        temp.add(num);
        dfs(res,temp,n,k,num+1);
        temp.remove(temp.size()-1);

    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了91.58% 的Java用户
     * 	内存消耗:46.7 MB,击败了7.37% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(n, k, path, ans); // 从 i=n 开始倒着枚举，这样 dfs 中就不需要 n 了，少传一个参数
        return ans;
    }

    private void dfs(int i, int k, List<Integer> path, List<List<Integer>> ans) {
        int d = k - path.size(); // 还要选 d 个数
        if (d == 0) { // 选好了
            ans.add(new ArrayList<>(path));
            return;
        }

        // 不选 i
        if (i > d) {
            dfs(i - 1, k, path, ans);
        }

        // 选 i
        path.add(i);
        dfs(i - 1, k, path, ans);
        path.remove(path.size() - 1); // path.remove(path.size() - 1);
    }
}
