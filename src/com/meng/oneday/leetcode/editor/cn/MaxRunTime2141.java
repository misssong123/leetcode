package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class MaxRunTime2141 {
    /**
     * 思路有误
     * @param n
     * @param batteries
     * @return
     */
    public long maxRunTimeError(int n, int[] batteries) {
        if (batteries.length < n){
            return 0;
        }
        PriorityQueue<Integer> batteryQueue = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<int[]> used = new PriorityQueue<>((a, b) -> a[0] - b[0] + (a[1] - b[1]));
        //初始化
        for(int battery : batteries){
            batteryQueue.offer(battery);
        }
        for (int i = 0 ; i < n ; i++){
            used.offer(new int[]{batteryQueue.poll(),0});
        }
        int time = 1;
        while(used.size() == n){
            //清理不满足的数据
            while(!used.isEmpty() && used.peek()[0] + used.peek()[1] <= time){
                used.poll();
            }
            //填充数据
            int size = used.size();
            while (size++ < n && !batteryQueue.isEmpty()){
                used.offer(new int[]{batteryQueue.poll(),time});
            }
            if (used.size() == n){
                int[] first = used.peek();
                //计算首个是否需要替换
                if(!batteryQueue.isEmpty() && first[0] + first[1] < batteryQueue.peek() + time){
                    //移除
                    used.poll();
                    //替换
                    used.offer(new int[]{batteryQueue.poll(),time});
                    if (first[0] + first[1] > time){
                        batteryQueue.offer(first[0] + first[1] - time);
                    }
                }
                time++;
            }
        }
        return time;
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了50.63% 的Java用户
     * 	内存消耗:72.6 MB,击败了14.65% 的Java用户
     * @param n
     * @param batteries
     * @return
     */
    public long maxRunTime2141(int n, int[] batteries) {
        long total = 0;
        for (int bat : batteries) {
            total += bat;
        }
        long left = 0, right = total / n;
        while (left < right) {
            long mid = (left + right + 1) / 2;
            long sum = 0;
            for (int bat : batteries) {
                sum += Math.min(bat, mid);
            }
            if (sum >= n * mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了20.50% 的Java用户
     * 	内存消耗:71 MB,击败了27.20% 的Java用户
     * @param n
     * @param batteries
     * @return
     */
    public long maxRunTime(int n, int[] batteries) {
        Arrays.sort(batteries);
        long sum = 0;
        for (int b : batteries) {
            sum += b;
        }
        for (int i = batteries.length - 1; ; i--) {
            if (batteries[i] <= sum / n) {
                return sum / n;
            }
            sum -= batteries[i];
            n--;
        }
    }
}
