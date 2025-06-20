package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class CanPartitionKSubsets698 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了94.64% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets698(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum = 0;
        //计算总和
        for(int num : nums){
            sum += num;
        }
        //不能平分
        if (sum % k != 0) {
            return false;
        }
        //存在大于平均值的数
        int target = sum / k;
        if (target < nums[len - 1]) {
            return false;
        }
        int[] buckets = new int[k];
        return dfs(len-1,buckets,nums,target,k);
    }

    private boolean dfs(int index,int[] buckets,int[] nums,int target, int k) {
        if (index < 0) {
            return true;
        }
        int num = nums[index];
        for(int i = 0 ; i < k ; i++){
            //不能存放当前桶
            if(num + buckets[i] > target|| (i > 0 && buckets[i] == buckets[i - 1])){
                continue;
            }
            buckets[i] += num;
            if (dfs(index - 1,buckets,nums,target,k)){
                return true;
            }
            buckets[i] -= num;
        }
        return false;
    }
    int[] nums;
    int n, t, k;

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了88.27% 的Java用户
     * @param _nums
     * @param _k
     * @return
     */
    public boolean canPartitionKSubsetsOther(int[] _nums, int _k) {
        nums = _nums; k = _k;
        int tot = 0;
        for (int x : nums) tot += x;
        if (tot % k != 0) return false; // 可行性剪枝
        Arrays.sort(nums);
        n = nums.length; t = tot / k;
        return dfs(n - 1, 0, 0, new boolean[n]);
    }
    boolean dfs(int idx, int cur, int cnt, boolean[] vis) {
        if (cnt == k) return true;
        if (cur == t) return dfs(n - 1, 0, cnt + 1, vis);
        for (int i = idx; i >= 0; i--) {  // 顺序性剪枝
            if (vis[i] || cur + nums[i] > t) continue;  // 可行性剪枝
            vis[i] = true;
            if (dfs(i - 1, cur + nums[i], cnt, vis)) return true;
            vis[i] = false;
            if (cur == 0) return false; // 可行性剪枝
        }
        return false;
    }
}
