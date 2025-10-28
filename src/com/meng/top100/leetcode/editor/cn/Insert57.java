package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Insert57 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.51% 的Java用户
     * 	内存消耗:43.9 MB,击败了79.33% 的Java用户
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert57(int[][] intervals, int[] newInterval) {
        boolean merge = true;
        List<int[]> list = new ArrayList<>();
        for(int[] interval : intervals){
            if(merge){
                if (interval[0] > newInterval[1]){
                    list.add(newInterval);
                    list.add(interval);
                    merge = false;
                }else if (interval[1] < newInterval[0]){
                    list.add(interval);
                }else{
                    newInterval[0] = Math.min(newInterval[0],interval[0]);
                    newInterval[1] = Math.max(newInterval[1],interval[1]);
                    list.add(newInterval);
                    merge = false;
                }
            }else{
                int[] pre = list.get(list.size()-1);
                if( pre[1] < interval[0]){
                    list.add(interval);
                }else{
                    pre[1] = Math.max(pre[1],interval[1]);
                }
            }
        }
        if (list.isEmpty() || merge){
            list.add(newInterval);
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.51% 的Java用户
     * 	内存消耗:44.2 MB,击败了12.09% 的Java用户
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
