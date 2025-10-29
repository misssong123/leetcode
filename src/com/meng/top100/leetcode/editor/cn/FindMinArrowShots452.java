package com.meng.top100.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class FindMinArrowShots452 {
    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了33.13% 的Java用户
     * 	内存消耗:67.4 MB,击败了82.07% 的Java用户
     * @param points
     * @return
     */
    public int findMinArrowShots452(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int res = 1,xIndex = points[0][1];
        for(int i = 1 ; i < points.length ; i++){
            if(xIndex < points[i][0]){
                res++;
                xIndex = points[i][1];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:76 ms,击败了8.54% 的Java用户
     * 	内存消耗:67.4 MB,击败了83.28% 的Java用户
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[1])); // 按照右端点从小到大排序
        int ans = 0;
        long pre = Long.MIN_VALUE;
        for (int[] p : points) {
            if (p[0] > pre) { // 上一个放的点在区间左边
                ans++;
                pre = p[1]; // 在区间的最右边放一个点
            }
        }
        return ans;
    }
}
