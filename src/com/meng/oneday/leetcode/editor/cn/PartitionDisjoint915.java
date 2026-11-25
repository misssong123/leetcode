package com.meng.oneday.leetcode.editor.cn;

class PartitionDisjoint915 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了44.23% 的Java用户
     * 	内存消耗:91.2 MB,击败了28.85% 的Java用户
     * @param nums
     * @return
     */
    public int partitionDisjoint915(int[] nums) {
        //计算后缀最小值
        int[] min = new int[nums.length];
        min[nums.length-1] = nums[nums.length-1];
        for (int i = nums.length-2; i >= 0 ; i--) {
            min[i] = Math.min(min[i+1],nums[i]);
        }
        //计算
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length-1; i++) {
            max = Math.max(max,nums[i]);
            if(max <= min[i+1]){
                return i+1;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了44.23% 的Java用户
     * 	内存消耗:91.7 MB,击败了5.77% 的Java用户
     * @param nums
     * @return
     */
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] min = new int[n + 10];
        min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) min[i] = Math.min(min[i + 1], nums[i]);
        for (int i = 0, max = 0; i < n - 1; i++) {
            max = Math.max(max, nums[i]);
            if (max <= min[i + 1]) return i + 1;
        }
        return -1; // never
    }

}
