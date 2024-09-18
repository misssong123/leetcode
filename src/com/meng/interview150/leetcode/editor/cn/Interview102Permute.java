package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Interview102Permute {
    boolean[] visited;
    List<List<Integer>> res;

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.49% 的Java用户
     * 	内存消耗:43.3 MB,击败了96.76% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteMy(int[] nums) {
        visited = new boolean[nums.length];
        res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(nums,temp);
        return res;
    }

    private void dfs(int[] nums, List<Integer> temp) {
        if (temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]){
                visited[i] = true;
                temp.add(nums[i]);
                dfs(nums,temp);
                temp.remove(temp.size()-1);
                visited[i] = false;
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了96.49% 的Java用户
     * 	内存消耗:43.4 MB,击败了73.10% 的Java用户
     * @param nums
     * @return
     */
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
