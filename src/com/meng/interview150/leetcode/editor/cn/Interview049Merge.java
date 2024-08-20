package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Interview049Merge {
    /**
     * 排序+合并
     * 解答成功:
     * 	执行耗时:9 ms,击败了14.44% 的Java用户
     * 	内存消耗:45.3 MB,击败了89.18% 的Java用户
     * @param intervals
     * @return
     */
    public int[][] mergeMy(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> temp = new ArrayList<>();
        temp.add(intervals[0]);
        for(int i = 1 ; i < intervals.length ; i++){
            if (intervals[i][0] > temp.get(temp.size()-1)[1]){
                temp.add(intervals[i]);
            }else {
                temp.get(temp.size()-1)[1] = Math.max(temp.get(temp.size()-1)[1],intervals[i][1]);
            }
        }
        int[][] res = new int[temp.size()][2];
        for (int i = 0 ; i < temp.size() ; i++){
            res[i] = temp.get(i);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了84.92% 的Java用户
     * 	内存消耗:45.6 MB,击败了42.27% 的Java用户
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
