package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview050Insert {
    /**
     *解答成功:
     * 	执行耗时:3 ms,击败了13.94% 的Java用户
     * 	内存消耗:43.7 MB,击败了97.81% 的Java用户
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insertMy(int[][] intervals, int[] newInterval) {
        List<int[]> temp = new ArrayList<>();
        boolean flag = false;
        for(int i = 0;i < intervals.length;i++){
            //当前数据小于指定区间
            if (intervals[i][1] < newInterval[0]){
                temp.add(intervals[i]);
            }
            //当前数据大于指定区间，后续数据无需比较
            else if (intervals[i][0] > newInterval[1]){
                if (!flag){
                    flag =true;
                    temp.add(newInterval);
                }
                temp.add(intervals[i]);
            }else {//当前数据存在交集
                if (flag){
                    int R = temp.get(temp.size()-1)[1];
                    temp.get(temp.size()-1)[1] = Math.max(R,intervals[i][1]);
                }else {
                    flag = true;
                    temp.add(new int[]{Math.min(intervals[i][0],newInterval[0]),Math.max(intervals[i][1],newInterval[1])});
                }
            }
        }
        if (intervals.length==0 || (!flag&&intervals[intervals.length-1][1] < newInterval[0])){
            temp.add(newInterval);
        }
        return temp.toArray(new int[0][]);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.45% 的Java用户
     * 	内存消耗:43.8 MB,击败了56.98% 的Java用户
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
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
