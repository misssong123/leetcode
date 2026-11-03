package com.meng.oneday.leetcode.editor.cn;

class EarliestTime3683 {
    public int earliestTime3683(int[][] tasks) {
        int res = Integer.MAX_VALUE;
        for (int[] task : tasks) {
            res = Math.min(res, task[0] + task[1]);
        }
        return res;
    }
}
