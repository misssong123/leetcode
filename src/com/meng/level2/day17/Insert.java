package com.meng.level2.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. 插入区间(中等)
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 *
 * 提示：
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 */
public class Insert {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 84.83%
     * 的用户
     * 内存消耗：
     * 43.4 MB
     * , 在所有 Java 提交中击败了
     * 82.16%
     * 的用户
     * 通过测试用例：
     * 156 / 156
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> cache = new ArrayList<>();
        if (intervals.length == 0){
            cache.add(newInterval);
        }
        int start = newInterval[0];
        int end = newInterval[1];
        int len = intervals.length;
        boolean flag = false;
        int [] t = new int[2];
        if (len != 0){
            if (intervals[0][0] > end){
                cache.add(newInterval);
            }
        }
        for(int i = 0 ; i < len ; i++){
            int[] temp = intervals[i];
            if (flag){
                if (temp[0] >end){
                    flag = false;
                    t[1] = end;
                    cache.add(t);
                    cache.add(temp);
                }else if (temp[1] >= end){
                    flag = false;
                    t[1] = temp[1];
                    cache.add(t);
                }
                if (i == len - 1 && temp[1] < end){
                    t[1] = end;
                    cache.add(t);
                }
                continue;
            }
            //被某一数组范围包围
            if (temp[0] <= start && temp[1]>=end){
                return intervals;
            }
            //前后数据，小于或大于集合
            if (i!=0 && temp[0] > end && intervals[i-1][1] < start){
                cache.add(newInterval);
                cache.add(temp);
                continue;
            }
            //不再当前数组范围内
            if (temp[0] > end || temp[1]<start){
                cache.add(temp);
                continue;
            }

            //包含一部分数据
            t[0] =start < temp[0] ? start:temp[0];
            flag = true;
            if (temp[1] >= end){
                flag = false;
                t[1] = temp[1];
                cache.add(t);
                continue;
            }
            if (i == len -1){
                t[1] = end;
                cache.add(t);
            }

        }
        if (len != 0){
            if (intervals[len-1][1] < start){
                cache.add(newInterval);
            }
        }
        int[][] res = new int[cache.size()][2];
        for(int i = 0 ; i < cache.size() ; i++){
            res[i] = cache.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] counts = {{3,5},{16,18}};
        int[] count = {9,9};
        Insert demo = new Insert();
        int[][] insert = demo.insert(counts, count);
        for (int[] n : insert){
            System.out.println(Arrays.toString(n));
        }
    }

        /**
         * 执行用时：
         * 0 ms
         * , 在所有 Java 提交中击败了
         * 100.00%
         * 的用户
         * 内存消耗：
         * 43.8 MB
         * , 在所有 Java 提交中击败了
         * 27.90%
         * 的用户
         * 通过测试用例：
         * 156 / 156
         * @param intervals
         * @param newInterval
         * @return
         */
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

}
