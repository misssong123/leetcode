package com.meng.oneday.leetcode.editor.cn;

class FindSmallestInteger2598 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了93.02% 的Java用户
     * 	内存消耗:57.9 MB,击败了41.86% 的Java用户
     * @param nums
     * @param value
     * @return
     */
    public int findSmallestInteger2598(int[] nums, int value) {
        int[] cache = new int[value];
        for(int num : nums){
            if (num >= 0){
                cache[num % value]++;
            }else{
                cache[(value + num % value) % value]++;
            }
        }
        int res = 0;
        while (cache[res % value] > 0){
            cache[res % value]--;
            res++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了81.40% 的Java用户
     * 	内存消耗:57.7 MB,击败了44.19% 的Java用户
     * @param nums
     * @param m
     * @return
     */
    public int findSmallestIntegerOther(int[] nums, int m) {
        int[] cnt = new int[m];
        for (int x : nums) {
            cnt[(x % m + m) % m]++; // 保证取模结果在 [0, m) 中
        }
        int mex = 0;
        while (cnt[mex % m]-- > 0) {
            mex++;
        }
        return mex;
    }
    public int findSmallestIntegerOfficial(int[] nums, int value) {
        int[] mp = new int[value];
        for (int x : nums) {
            int v = ((x % value) + value) % value;
            mp[v]++;
        }
        int mex = 0;
        while (mp[mex % value] > 0) {
            mp[mex % value]--;
            mex++;
        }
        return mex;
    }

}
