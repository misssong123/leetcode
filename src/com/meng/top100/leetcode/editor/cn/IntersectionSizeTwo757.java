package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
class IntersectionSizeTwo757 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了90.57% 的Java用户
     * 	内存消耗:46.6 MB,击败了7.55% 的Java用户
     * @param intervals
     * @return
     */
    public int intersectionSizeTwo757(int[][] intervals) {
        PriorityQueue<int[]> qp = new PriorityQueue<>((a,b)->{
            if (a[1] != b[1]){
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        for (int[] interval : intervals) {
            qp.offer(interval);
        }
        int res = 0,first = -1 ,second = -1;
        while(!qp.isEmpty()){
            int[] cur = qp.poll();
            //没有交集
            if (cur[0] > second){
                res+=2;
                first = cur[1] - 1;
                second = cur[1];
            }else if (cur[0] <= first ){//包含
                continue;
            }else {//有交集
                res+=1;
                first = second;
                second = cur[1];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了11.32% 的Java用户
     * 	内存消耗:46.2 MB,击败了15.09% 的Java用户
     * @param intervals
     * @return
     */
    public int intersectionSizeTwo(int[][] intervals) {
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
