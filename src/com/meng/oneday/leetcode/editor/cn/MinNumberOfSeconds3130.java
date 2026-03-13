package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class MinNumberOfSeconds3130 {
    /**
     * 解答成功:
     * 	执行耗时:294 ms,击败了5.35% 的Java用户
     * 	内存消耗:47.2 MB,击败了11.11% 的Java用户
     * @param mountainHeight
     * @param workerTimes
     * @return
     */
    public long minNumberOfSeconds3130(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;
        long[] costs = new long[n];
        long[] step = new long[n];
        //初始化
        for (int i = 0; i < n; i++) {
            step[i] += workerTimes[i];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->{
            long first = step[a] + costs[a];
            long second = step[b] + costs[b];
            if (first == second){
                return step[a] < step[b] ? -1 : 1;
            }
            return first < second ? -1 : 1;
        });
        for(int i = 0 ; i < workerTimes.length; i++){
            queue.offer(i);
        }
        while (mountainHeight > 0 && !queue.isEmpty()){
            int index = queue.poll();
            costs[index] += step[index];
            step[index] += workerTimes[index];
            queue.add(index);
            mountainHeight--;
        }
        return Arrays.stream(costs).max().getAsLong();
    }

    /**
     * 解答成功:
     * 	执行耗时:269 ms,击败了5.35% 的Java用户
     * 	内存消耗:46.9 MB,击败了47.33% 的Java用户
     * @param mountainHeight
     * @param workerTimes
     * @return
     */
    public long minNumberOfSecondsOther(int mountainHeight, int[] workerTimes) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        for (int t : workerTimes) {
            pq.offer(new long[]{t, t, t});
        }

        long ans = 0;
        while (mountainHeight-- > 0) {
            // 工作后总用时，当前工作（山高度降低 1）用时，workerTimes[i]
            long[] top = pq.poll();
            long total = top[0], cur = top[1], base = top[2];
            ans = total; // 最后一个出堆的 total 即为答案
            pq.offer(new long[]{total + cur + base, cur + base, base});
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了98.77% 的Java用户
     * 	内存消耗:46.9 MB,击败了53.91% 的Java用户
     * @param mountainHeight
     * @param workerTimes
     * @return
     */
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int maxT = 0;
        for (int t : workerTimes) {
            maxT = Math.max(maxT, t);
        }
        int h = (mountainHeight - 1) / workerTimes.length + 1;
        long left = 0;
        long right = (long) maxT * h * (h + 1) / 2;
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (check(mid, mountainHeight, workerTimes)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(long m, int leftH, int[] workerTimes) {
        for (int t : workerTimes) {
            leftH -= ((int) Math.sqrt(m / t * 8 + 1) - 1) / 2;
            if (leftH <= 0) {
                return true;
            }
        }
        return false;
    }

}
