package com.meng.oneday.leetcode.editor.cn;

class MinTimeToVisitAllPoints1266 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.80% 的Java用户
     * 	内存消耗:44.1 MB,击败了22.95% 的Java用户
     * @param points
     * @return
     */
    public int minTimeToVisitAllPoints1266(int[][] points) {
        int res = 0 ;
        for (int i = 1 ; i < points.length ; i++){
            res += Math.max(Math.abs(points[i][0] - points[i-1][0]),Math.abs(points[i][1] - points[i-1][1]));
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.80% 的Java用户
     * 	内存消耗:44.1 MB,击败了22.95% 的Java用户
     * @param points
     * @return
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            int[] p = points[i - 1];
            int[] q = points[i];
            ans += Math.max(Math.abs(p[0] - q[0]), Math.abs(p[1] - q[1]));
        }
        return ans;
    }
}
