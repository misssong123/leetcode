package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class MostBooked2402 {
    /**
     * 解答成功:
     * 	执行耗时:77 ms,击败了51.79% 的Java用户
     * 	内存消耗:134.5 MB,击败了5.36% 的Java用户
     * @param n
     * @param meetings
     * @return
     */
    public int mostBooked2402(int n, int[][] meetings) {
        int[] cnt = new int[n];
        PriorityQueue<int[]> usedQueue = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        PriorityQueue<Integer> unUsedQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int i = 0 ; i < n ; i++){
            unUsedQueue.offer(i);
        }
        //按照开始时间排序
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        for (int[] meeting : meetings) {
            int time = meeting[0];
           while (!usedQueue.isEmpty() && usedQueue.peek()[1] <= time){
               unUsedQueue.offer(usedQueue.poll()[0]);
           }
           //当前时间会议室都被占用
           if (unUsedQueue.isEmpty()){
               int[] arrs = usedQueue.poll();
               cnt[arrs[0]]++;
               usedQueue.offer(new int[]{arrs[0],arrs[1] + meeting[1] - meeting[0]});
           }else{//存在未占用的会议室
               Integer index = unUsedQueue.poll();
               cnt[index]++;
               usedQueue.offer(new int[]{index,meeting[1]});
           }
        }
        int max = 0 ,index = 0;
        for(int i = 0 ; i < n ; i++){
            if (cnt[i] > max){
                max = cnt[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * 解答成功:
     * 	执行耗时:75 ms,击败了66.07% 的Java用户
     * 	内存消耗:125.5 MB,击败了42.86% 的Java用户
     * @param n
     * @param meetings
     * @return
     */
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> idle = new PriorityQueue<>(); // 会议室编号
        for (int i = 0; i < n; i++) {
            idle.offer(i);
        }
        PriorityQueue<long[]> using = new PriorityQueue<>(
                (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1])
        ); // (结束时间，会议室编号)
        int[] cnt = new int[n]; // 会议室的开会次数

        for (int[] m : meetings) {
            long start = m[0];
            long end = m[1];

            // 在 start 时刻空出来的会议室
            while (!using.isEmpty() && using.peek()[0] <= start) {
                idle.offer((int) using.poll()[1]);
            }

            int i;
            if (!idle.isEmpty()) { // 有空闲的会议室
                i = idle.poll();
            } else { // 没有空闲的会议室
                long[] p = using.poll(); // 弹出一个最早结束的会议室（若有多个同时结束，弹出编号最小的会议室）
                end += p[0] - start; // 更新当前会议的结束时间
                i = (int) p[1];
            }

            using.offer(new long[]{end, i}); // 使用一个会议室
            cnt[i]++;
        }

        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (cnt[i] > cnt[ans]) {
                ans = i;
            }
        }
        return ans;
    }
}
