package com.meng.oneday.leetcode.editor.cn;

class SubsetXORSum1863 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了34.24% 的Java用户
     * @param nums
     * @return
     */
    int sum = 0;
    public int subsetXORSum1863(int[] nums) {
        dfs(0,0,nums);
        return sum;
    }

    private void dfs(int index, int pre, int[] nums) {
        if(index == nums.length){
            sum += pre;
            return;
        }
        //选择当前数
        dfs(index+1,pre^nums[index],nums);
        //不选择当前数
        dfs(index+1,pre,nums);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.9 MB,击败了96.79% 的Java用户
     * @param nums
     * @return
     */
    public int subsetXORSum(int[] nums) {
        int or = 0;
        for (int x : nums) {
            or |= x;
        }
        return or << (nums.length - 1);
    }

}
