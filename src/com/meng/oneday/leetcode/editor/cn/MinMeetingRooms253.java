package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class MinMeetingRooms253 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了32.56% 的Java用户
     * 	内存消耗:45.3 MB,击败了17.79% 的Java用户
     * @param intervals
     * @return
     */
    public int minMeetingRooms253(int[][] intervals) {
        //排序,按照开始时间最小，结束时间最长排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
        for(int[] interval : intervals){
            if (queue.isEmpty() || queue.peek() > interval[0]){
                queue.offer(interval[1]);
            }else {
                queue.poll();
                queue.offer(interval[1]);
            }
        }
        return queue.size();
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了11.78% 的Java用户
     * 	内存消耗:45.2 MB,击败了35.80% 的Java用户
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        // 按照结束时间对间隔排序
        Arrays.sort(end,Comparator.comparingInt(a -> a));
        // 按照开始时间对间隔排序
        Arrays.sort(start,Comparator.comparingInt(a -> a));
        // 算法中的两个指针：e_ptr 和 s_ptr。
        int startPointer = 0, endPointer = 0;
        // 变量来跟踪使用的最大房间数。
        int usedRooms = 0;
        // 在间隔上迭代。
        while (startPointer < intervals.length) {
            // 如果有一个会议在 `start_pointer` 开始时已经结束
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }
            // 无论房间是否空闲，我们都会这样做。
            // 如果一个房间是空闲的，那么 used_rooms+=1 将不会有任何效果。 used_rooms
            // 在这种情况下会保持不变。如果没有空闲的房间，则会增加已用房间数。
            usedRooms += 1;
            startPointer += 1;

        }
        return usedRooms;
    }

}
