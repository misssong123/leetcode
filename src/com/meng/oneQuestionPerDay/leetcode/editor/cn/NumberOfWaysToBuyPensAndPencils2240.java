package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionWaysToBuyPensPencils2240 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了85.28% 的Java用户
     * 	内存消耗:38.4 MB,击败了14.72% 的Java用户
     * @param total
     * @param cost1
     * @param cost2
     * @return
     */
    public long waysToBuyPensPencils1(int total, int cost1, int cost2) {
        long res = 0L;
        while (0 <= total){
            res += total / cost2 +1;
            total -= cost1;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了46.01% 的Java用户
     * 	内存消耗:38.2 MB,击败了49.69% 的Java用户
     * @param total
     * @param cost1
     * @param cost2
     * @return
     */
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        if (cost1 < cost2) {
            return waysToBuyPensPencils(total, cost2, cost1);
        }
        long res = 0, cnt = 0;
        while (cnt * cost1 <= total) {
            res += (total - cnt * cost1) / cost2 + 1;
            cnt++;
        }
        return res;
    }
}

