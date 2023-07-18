package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class MinInterval1851 {
    /**
     * 超时
     * @param intervals
     * @param queries
     * @return
     */
    public int[] minInterval(int[][] intervals, int[] queries) {
        //排序，将闭区间按照左端点从小到大排序
        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));
        System.out.println(Arrays.deepToString(intervals));
        //定义结果集
        int[] res = new int[queries.length];
        int index = 0;
        for(int query : queries){
            int temp = Integer.MAX_VALUE;
            for(int [] interval : intervals){
                //如果当前区间的左端点大于query，那么后面的区间就不用看了，因为后面的区间的左端点都比当前区间的左端点大
                if (interval[0] > query){
                    break;
                }
                //是否存在区间内
                if (interval[1]>=query){
                    temp = Math.min(temp,interval[1]-interval[0]+1);
                }
            }
            res[index++] = temp == Integer.MAX_VALUE ? -1 : temp;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] intervals =  {{1,4},{2,4},{3,6},{4,4}};
        int [] queries = {2,3,4,5};
        MinInterval1851 demo = new MinInterval1851();
        int[] res = demo.minInterval(intervals,queries);
        for (int i : res) {
            System.out.println(i);
        }
    }

    /**
     * 执行用时：
     * 119 ms
     * , 在所有 Java 提交中击败了
     * 71.01%
     * 的用户
     * 内存消耗：
     * 93.2 MB
     * , 在所有 Java 提交中击败了
     * 59.42%
     * 的用户
     * 通过测试用例：
     * 42 / 42
     * @param intervals
     * @param queries
     * @return
     */
    public int[] minInterval1(int[][] intervals, int[] queries) {
        Integer[] qindex = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            qindex[i] = i;
        }
        Arrays.sort(qindex, (i, j) -> queries[i] - queries[j]);
        Arrays.sort(intervals, (i, j) -> i[0] - j[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        int i = 0;
        for (int qi : qindex) {
            while (i < intervals.length && intervals[i][0] <= queries[qi]) {
                pq.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][0], intervals[i][1]});
                i++;
            }
            while (!pq.isEmpty() && pq.peek()[2] < queries[qi]) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                res[qi] = pq.peek()[0];
            }
        }
        return res;
    }

}
