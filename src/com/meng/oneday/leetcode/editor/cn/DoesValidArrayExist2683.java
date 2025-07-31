package com.meng.oneday.leetcode.editor.cn;

class DoesValidArrayExist2683 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:62.4 MB,击败了17.00% 的Java用户
     * @param derived
     * @return
     */
    public boolean doesValidArrayExist2683(int[] derived) {
        int target = 0;
        for(int num : derived){
            target ^= num;
        }
        return target == 0;
    }
}
