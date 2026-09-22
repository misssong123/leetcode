package com.meng.oneday.leetcode.editor.cn;

class EscapeGhosts789 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了85.71% 的Java用户
     * @param ghosts
     * @param target
     * @return
     */
    public boolean escapeGhosts789(int[][] ghosts, int[] target) {
        int min = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            if (Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]) <= min) {
                return false;
            }
        }
        return true;
    }
}
