package com.meng.level2.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间(中等)
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class Merge {
    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 66.58%
     * 的用户
     * 内存消耗：
     * 46.3 MB
     * , 在所有 Java 提交中击败了
     * 46.85%
     * 的用户
     * 通过测试用例：
     * 169 / 169
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len == 1){
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        List<int[]> list = new ArrayList<>();
        for(int i = 0 ; i < len ; i++){
            int left = intervals[i][0] , right = intervals[i][1];
            if (i == 0 || left > list.get(list.size()-1)[1]){
                list.add(intervals[i]);
            }else {
                int num = list.get(list.size()-1)[1];
                list.get(list.size()-1)[1] = Math.max(right,num);
            }
        }
        int[][] res = new int[list.size()][2];
        for(int i = 0 ; i < list.size() ; i++){
            res[i] = list.get(i);
        }
        return res;
    }
    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 66.69%
     * 的用户
     * 内存消耗：
     * 46.6 MB
     * , 在所有 Java 提交中击败了
     * 11.76%
     * 的用户
     * 通过测试用例：
     * 169 / 169
     * @param intervals
     * @return
     */
    public int[][] merge1(int[][] intervals) {
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
