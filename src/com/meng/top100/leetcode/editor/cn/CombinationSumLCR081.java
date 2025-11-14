package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombinationSumLCR081 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了40.44% 的Java用户
     * 	内存消耗:45.1 MB,击败了5.15% 的Java用户
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumLCR081(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfsLCR081(candidates,target,res,temp,0);
        return res;
    }

    private void dfsLCR081(int[] candidates, int target, List<List<Integer>> res, List<Integer> temp,int index) {
        if(index > candidates.length - 1){
            return;
        }
        if(target == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        if (candidates[index] > target){
            return;
        }
        //选择当前元素
        temp.add(candidates[index]);
        dfsLCR081(candidates,target - candidates[index],res,temp,index);
        temp.remove(temp.size() - 1);
        //不选择当前元素
        dfsLCR081(candidates,target,res,temp,index + 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了68.01% 的Java用户
     * 	内存消耗:44.8 MB,击败了9.56% 的Java用户
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
