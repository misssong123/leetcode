package com.meng.hot100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PermuteLCR083 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了92.61% 的Java用户
     * 	内存消耗:44.5 MB,击败了10.50% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteLCR083(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums,res,temp,used);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0 ; i < nums.length; i++){
            if(!used[i]){
                used[i] = true;
                temp.add(nums[i]);
                dfs(nums,res,temp,used);
                used[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
