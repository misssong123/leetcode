package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Interview104CombinationSum {
    /**
     * 排序加递归
     * 解答成功:
     * 	执行耗时:2 ms,击败了91.59% 的Java用户
     * 	内存消耗:43.7 MB,击败了38.38% 的Java用户
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> res;
    public List<List<Integer>> combinationSumMy(int[] candidates, int target) {
        res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, temp);
        return res;
    }

    private void dfs(int[] candidates, int target, int index, List<Integer> temp) {
        if (index >= candidates.length || target < 0){
            return;
        }
        if (target == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        //选择当前元素
        if (candidates[index] <= target){
            temp.add(candidates[index]);
            dfs(candidates, target - candidates[index], index, temp);
            temp.remove(temp.size() - 1);
        }
        //不选择当前元素
        dfs(candidates,target,index+1,temp);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了91.59% 的Java用户
     * 	内存消耗:43.6 MB,击败了60.21% 的Java用户
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
