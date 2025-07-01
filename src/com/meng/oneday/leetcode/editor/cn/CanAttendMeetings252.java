package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class CanAttendMeetings252 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了18.02% 的Java用户
     * 	内存消耗:45.3 MB,击败了22.09% 的Java用户
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings252(int[][] intervals) {
        if (intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for(int i = 1 ; i < intervals.length ; i++){
            if (intervals[i][0] < intervals[i-1][1]){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了95.93% 的Java用户
     * 	内存消耗:45.1 MB,击败了60.46% 的Java用户
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

}
