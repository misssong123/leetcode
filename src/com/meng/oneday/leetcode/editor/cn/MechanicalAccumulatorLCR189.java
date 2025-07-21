package com.meng.oneday.leetcode.editor.cn;

class MechanicalAccumulatorLCR189 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了84.27% 的Java用户
     * @param target
     * @return
     */
    public int mechanicalAccumulatorLCR189(int target) {
        return (1+target)*target/2;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了99.30% 的Java用户
     * @param target
     * @return
     */
    public int mechanicalAccumulatorRecursion(int target) {
        return target == 1 ? 1 : target + mechanicalAccumulatorRecursion(target - 1);
    }
}
