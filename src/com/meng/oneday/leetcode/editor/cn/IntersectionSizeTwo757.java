package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IntersectionSizeTwo757 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了86.49% 的Java用户
     * 	内存消耗:44 MB,击败了100.00% 的Java用户
     * @param intervals
     * @return
     */
    public int intersectionSizeTwo757(int[][] intervals) {
        //按照结束值降序，开始值升序
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        int first = -1;
        int second = -1;
        int res = 0;
        for(int[] interval : intervals) {
            if(first < interval[0]) {
                first = interval[1];
                second = interval[1]-1;
                res+=2;
            }else if (second < interval[0]) {
                second = first;
                first = interval[1];
                res+=1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了13.51% 的Java用户
     * 	内存消耗:45.3 MB,击败了5.41% 的Java用户
     * @param intervals
     * @return
     */
    public int intersectionSizeTwoOfficial(int[][] intervals) {
        int n = intervals.length;
        int res = 0;
        int m = 2;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        List<Integer>[] temp = new List[n];
        for (int i = 0; i < n; i++) {
            temp[i] = new ArrayList<Integer>();
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = intervals[i][0], k = temp[i].size(); k < m; j++, k++) {
                res++;
                help(intervals, temp, i - 1, j);
            }
        }
        return res;
    }

    public void help(int[][] intervals, List<Integer>[] temp, int pos, int num) {
        for (int i = pos; i >= 0; i--) {
            if (intervals[i][1] < num) {
                break;
            }
            temp[i].add(num);
        }
    }
}
