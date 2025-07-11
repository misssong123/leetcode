package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class CountDays3169 {
    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了15.25% 的Java用户
     * 	内存消耗:98.5 MB,击败了59.32% 的Java用户
     * @param days
     * @param meetings
     * @return
     */
    public int countDays3169(int days, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        int res = meetings[0][0]-1;
        int pre = meetings[0][1];
        for(int i = 1 ; i < meetings.length ; i++){
            if(meetings[i][0]<=pre){
                pre = Math.max(pre,meetings[i][1]);
            }else{
                res += meetings[i][0]-pre-1;
                pre = meetings[i][1];
            }
        }
        res += days-pre;
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了98.31% 的Java用户
     * 	内存消耗:98.6 MB,击败了45.76% 的Java用户
     * @param days
     * @param meetings
     * @return
     */
    public int countDaysOther(int days, int[][] meetings) {
        Arrays.sort(meetings, (p, q) -> p[0] - q[0]); // 按照左端点从小到大排序
        int start = 1, end = 0; // 当前合并区间的左右端点
        for (int[] p : meetings) {
            if (p[0] > end) { // 不相交
                days -= end - start + 1; // 当前合并区间的长度
                start = p[0]; // 下一个合并区间的左端点
            }
            end = Math.max(end, p[1]);
        }
        days -= end - start + 1; // 最后一个合并区间的长度
        return days;
    }

    /**
     * 解答成功:
     * 	执行耗时:44 ms,击败了25.42% 的Java用户
     * 	内存消耗:98.7 MB,击败了16.95% 的Java用户
     * @param days
     * @param meetings
     * @return
     */
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        int l = 1, r = 0;
        for (int[] m : meetings) {
            if (m[0] > r) {
                days -= (r - l + 1);
                l = m[0];
            }
            r = Math.max(r, m[1]);
        }
        days -= (r - l + 1);
        return days;
    }
}
