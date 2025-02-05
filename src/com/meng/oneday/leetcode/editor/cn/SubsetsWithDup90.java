package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SubsetsWithDup90 {
    List<List<Integer>> res = null;
    List<Integer> temp = null;

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.77% 的Java用户
     * 	内存消耗:42.5 MB,击败了77.72% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDupMy(int[] nums) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,0,true,nums.length);
        return res;
    }

    private void dfs(int[] nums, int index,boolean containPre, int length) {
        if (index == length){
            res.add(new ArrayList<>(temp));
            return;
        }
        //不存在当前元素
        dfs(nums,index+1,false,length);
        //存放当前元素
        //当前元素与前一个元素相同时，只有前一个元素被选中时，才会被选中
        if(!containPre && index > 0 && nums[index] == nums[index-1]){
            return;
        }
        temp.add(nums[index]);
        dfs(nums,index+1,true,length);
        temp.remove(temp.size()-1);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.77% 的Java用户
     * 	内存消耗:42.8 MB,击败了24.19% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDupOther1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs1(0, nums, ans, path);
        return ans;
    }


    private void dfs1(int i, int[] nums, List<List<Integer>> ans, List<Integer> path) {
        int n = nums.length;
        if (i == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 选 x
        int x = nums[i];
        path.add(x);
        dfs1(i + 1, nums, ans, path);
        path.remove(path.size() - 1); // 恢复现场

        // 不选 x，跳过所有等于 x 的数
        // 如果不跳过这些数，会导致「选 x 不选 x'」和「不选 x 选 x'」这两种情况都会加到 ans 中，这就重复了
        i++;
        while (i < n && nums[i] == x) {
            i++;
        }
        dfs1(i, nums, ans, path);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.77% 的Java用户
     * 	内存消耗:42.6 MB,击败了72.92% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, nums, ans, path);
        return ans;
    }

    private void dfs(int i, int[] nums, List<List<Integer>> ans, List<Integer> path) {
        ans.add(new ArrayList<>(path));

        // 在 [i,n-1] 中选一个 nums[j]
        // 注意选 nums[j] 意味着 [i,j-1] 中的数都没有选
        for (int j = i; j < nums.length; j++) {
            // 如果 j>i，说明 nums[j-1] 没有选
            // 同方法一，所有等于 nums[j-1] 的数都不选
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            path.add(nums[j]);
            dfs(j + 1, nums, ans, path);
            path.remove(path.size() - 1); // 恢复现场
        }
    }


}
