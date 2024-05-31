package com.meng.thinking.leetcode.editor.cn;

class SingleNumber260 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了86.49% 的Java用户
     * 	内存消耗:44.6 MB,击败了50.17% 的Java用户
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        int lowBit = xorAll & (-xorAll);
        int[] ans = new int[2];
        for(int num : nums){
            ans[(num & lowBit) == 0 ? 0 : 1] ^= num;
        }
        ans[0] = ans[0] ^ xorAll;
        ans[1] = ans[1] ^ xorAll;
        return  ans;
    }
}
