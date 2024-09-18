package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview103Combine {
    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了47.29% 的Java用户
     * 	内存消耗:92.6 MB,击败了13.67% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combineMy(int n, int k) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(1,temp,n,k,res);
        return res;
    }

    private void dfs(int index, List<Integer> temp,int n, int k, List<List<Integer>> res) {
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index ; i <= n ; i++){
            temp.add(i);
            dfs(i+1,temp,n,k,res);
            temp.remove(temp.size()-1);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了79.60% 的Java用户
     * 	内存消耗:92.4 MB,击败了42.72% 的Java用户
     * @param n
     * @param k
     * @return
     */
    List<Integer> temp1 = new ArrayList<Integer>();
    List<List<Integer>> ans1 = new ArrayList<List<Integer>>();
    public List<List<Integer>> combine1(int n, int k) {
        dfs(1, n, k);
        return ans1;
    }

    public void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp1.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp1.size() == k) {
            ans1.add(new ArrayList<Integer>(temp1));
            return;
        }
        // 考虑选择当前位置
        temp1.add(cur);
        dfs(cur + 1, n, k);
        temp1.remove(temp1.size() - 1);
        // 考虑不选择当前位置
        dfs(cur + 1, n, k);
    }

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了13.85% 的Java用户
     * 	内存消耗:89.8 MB,击败了77.63% 的Java用户
     * @param n
     * @param k
     * @return
     */
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        // 初始化
        // 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
        // 末尾加一位 n + 1 作为哨兵
        for (int i = 1; i <= k; ++i) {
            temp.add(i);
        }
        temp.add(n + 1);

        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<>(temp.subList(0, k)));
            j = 0;
            // 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
            // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
            while (j < k && temp.get(j) + 1 == temp.get(j + 1)) {
                temp.set(j, j + 1);
                ++j;
            }
            // j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
            temp.set(j, temp.get(j) + 1);
        }
        return ans;
    }
}
