package com.meng.oneday.leetcode.editor.cn;

class SemiOrderedPermutation2717 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了65.38% 的Java用户
     * @param nums
     * @return
     */
    public int semiOrderedPermutation2717(int[] nums) {
        int n = nums.length;
        if (nums[0] == 1 && nums[n - 1] == n) {
            return 0;
        }
        int oneIndex = 0;
        //寻找第一个1的下标
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                oneIndex = i;
                break;
            }
        }
        //寻找最后一个n的下标
        int nIndex = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == n) {
                nIndex = i;
                break;
            }
        }
        int diff = oneIndex > nIndex ? 1 :0;
        return oneIndex + (n - nIndex - 1) - diff;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.8 MB,击败了15.38% 的Java用户
     * @param nums
     * @return
     */
    public int semiOrderedPermutation(int[] nums) {
        int n = nums.length;
        int p = 0;
        int q = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                p = i;
            } else if (nums[i] == n) {
                q = i;
            }
        }
        return p + n - 1 - q - (p > q ? 1 : 0);
    }
}
