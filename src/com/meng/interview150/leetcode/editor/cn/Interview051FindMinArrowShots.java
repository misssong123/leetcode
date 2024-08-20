package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class Interview051FindMinArrowShots {
    /**
     * 解答成功:
     * 	执行耗时:68 ms,击败了12.54% 的Java用户
     * 	内存消耗:67.2 MB,击败了73.98% 的Java用户
     * @param points
     * @return
     */
    public int findMinArrowShotsMy(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int R = points[0][1];
        int res = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > R) {
                res++;
                R = points[i][1];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:54 ms,击败了90.40% 的Java用户
     * 	内存消耗:67.3 MB,击败了30.71% 的Java用户
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }


}
