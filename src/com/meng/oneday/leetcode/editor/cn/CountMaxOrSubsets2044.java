package com.meng.oneday.leetcode.editor.cn;

class CountMaxOrSubsets2044 {
    int res;

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了95.77% 的Java用户
     * 	内存消耗:40.1 MB,击败了97.18% 的Java用户
     * @param nums
     * @return
     */
    public int countMaxOrSubsets2044(int[] nums) {
        int target = 0;
        for (int num : nums) {
            target |= num;
        }
        res = 0;
        dfs(nums,0,0,target);
        return res;
    }

    private void dfs(int[] nums,int index, int val, int target) {
        if (index >= nums.length){
            return;
        }
        //不选取当前元素
        dfs(nums,index+1,val,target);
        //选取当前元素
        if ((val | nums[index]) == target) {
            res+=1<<(nums.length -1- index);
            return;
        }
        dfs(nums,index+1,val|nums[index],target);
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了92.96% 的Java用户
     * 	内存消耗:40.3 MB,击败了56.34% 的Java用户
     * @param nums
     * @return
     */
    public int countMaxOrSubsetsOther(int[] nums) {
        int totalOr = 0;
        for (int x : nums) {
            totalOr |= x;
        }

        dfs(0, 0, nums, totalOr);
        return ans;
    }

    private int ans = 0;

    private void dfs(int i, int subsetOr, int[] nums, int totalOr) {
        if (subsetOr == totalOr) {
            ans += 1 << (nums.length - i);
            return;
        }
        if (i == nums.length) {
            return;
        }
        dfs(i + 1, subsetOr, nums, totalOr); // 不选 nums[i]
        dfs(i + 1, subsetOr | nums[i], nums, totalOr); // 选 nums[i]
    }
}
