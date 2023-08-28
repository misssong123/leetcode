package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Insert57 {
    /**
     * 时间
     * 详情
     * 1ms
     * 击败 97.56%使用 Java 的用户
     * 内存
     * 详情
     * 41.85MB
     * 击败 43.00%使用 Java 的用户
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> cache = new ArrayList<>();
        boolean addFlag = false;
        boolean operateFlag = false;
        int len = intervals.length;
        for(int i = 0 ; i < len ; i++){
            if (!addFlag){
                int[] interval = intervals[i];
                if(!operateFlag){
                    //完全不包含
                    if (interval[1] < newInterval[0]){
                        cache.add(interval);
                    }else if (interval[0] <= newInterval[0] && interval[1]>= newInterval[1]){//完全包含
                        cache.add(interval);
                        addFlag = true;
                        operateFlag = true;
                    }else if (interval[0] > newInterval[1]){
                        cache.add(newInterval);
                        cache.add(interval);
                        addFlag = true;
                        operateFlag = true;
                    }else {
                        operateFlag = true;
                        int[] temp = new int[2];
                        temp[0] = Math.min(interval[0],newInterval[0]);
                        temp[1] = Math.max(interval[1],newInterval[1]);
                        if (interval[1] >=newInterval[1]){
                            addFlag = true;
                        }
                        cache.add(temp);
                    }
                }else {
                    //不包含
                    if (interval[0] > newInterval[1]) {
                        cache.add(interval);
                        addFlag = true;
                    }else{//包含
                        if (newInterval[1] <= interval[1]){
                            cache.get(cache.size()-1)[1] = interval[1];
                            addFlag = true;
                        }
                    }
                }
            }else {
                cache.add(intervals[i]);
            }
        }
        if (!operateFlag){
            cache.add(newInterval);
        }
        int[][] res = new int[cache.size()][2];
        int index = 0;
        for(int[] num : cache){
            res[index++] = num;
        }
        return res;
    }

    public static void main(String[] args) {
        Insert57 demo = new Insert57();
        int[][] intervals = new int[][]{{1,3},{6,9}};
        int[] newInterval = new int[]{2,5};
        int[][] res = demo.insert(intervals,newInterval);
    }

    /**
     * 时间
     * 详情
     * 1ms
     * 击败 97.56%使用 Java 的用户
     * 内存
     * 详情
     * 41.94MB
     * 击败 27.44%使用 Java 的用户
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
