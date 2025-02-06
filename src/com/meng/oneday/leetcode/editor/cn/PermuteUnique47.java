package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PermuteUnique47 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    boolean[] visited;
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了62.16% 的Java用户
     * 	内存消耗:44.1 MB,击败了45.72% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUniqueMy(int[] nums) {
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums);
        return res;
    }

    private void dfs(int[] nums) {
        //满足条件，跳出
        if (temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0 ; i < nums.length ; i++){
            //如果当前元素已经使用，或者当前元素和前一个元素相同且前一个元素未使用，跳过
            if(visited[i] || (i >0 && nums[i] == nums[i-1] && !visited[i-1])){
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            dfs(nums);
            temp.remove(temp.size()-1);
            visited[i] = false;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.95% 的Java用户
     * 	内存消耗:43.9 MB,击败了71.73% 的Java用户
     * @param nums
     * @return
     */
    boolean[] vis;
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

}
