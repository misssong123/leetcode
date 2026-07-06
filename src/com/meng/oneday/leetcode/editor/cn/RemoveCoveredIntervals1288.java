package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class RemoveCoveredIntervals1288 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了97.62% 的Java用户
     * 	内存消耗:45.9 MB,击败了23.81% 的Java用户
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals1288(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int index = 0;
        int delete = 0;
        for (int i = 1; i < intervals.length; i++) {
           if (intervals[i][1] <= intervals[index][1]){
               delete++;
               continue;
           }
           index = i;
        }
        return intervals.length - delete;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了97.62% 的Java用户
     * 	内存消耗:45.9 MB,击败了19.84% 的Java用户
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        // 按区间左端点从小到大排序
        // 区间左端点相同时，按区间右端点从大到小排序，这样会先遍历大区间，再遍历被大区间覆盖的小区间
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

        int ans = 0;
        int maxRight = 0; // 已遍历区间中的最大右端点
        for (int[] p : intervals) {
            // 由于区间左端点是从小到大排序的，已遍历区间的左端点都 <= 当前区间的左端点
            // 如果当前区间右端点 <= maxRight，说明当前区间被另一个区间覆盖，否则没被覆盖
            if (p[1] > maxRight) {
                maxRight = p[1];
                ans++; // 当前区间没被覆盖
            }
        }
        return ans;
    }

}
