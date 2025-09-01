package com.meng.oneday.leetcode.editor.cn;

import java.util.PriorityQueue;

class MaxAverageRatio1792 {
    /**
     * 解答成功:
     * 	执行耗时:687 ms,击败了5.00% 的Java用户
     * 	内存消耗:77.7 MB,击败了73.75% 的Java用户
     * @param classes
     * @param extraStudents
     * @return
     */
    public double maxAverageRatio1792(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2)->{
            double a1 = (double) (o1[0] + 1) / (o1[1] + 1) - (double) o1[0] / o1[1];
            double b1 = (double) (o2[0] + 1) / (o2[1] + 1) - (double) o2[0] / o2[1];
            return Double.compare(b1, a1);
        });
        for(int[] c : classes){
            queue.offer(c);
        }
        while(extraStudents > 0&&!queue.isEmpty()){
            int[] poll = queue.poll();
            queue.offer(new int[]{poll[0] + 1, poll[1] + 1});
            extraStudents--;
        }
        double res = 0;
        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            res += (double) poll[0] / poll[1];
        }
        return res / classes.length;
    }

    /**
     * 解答成功:
     * 	执行耗时:436 ms,击败了48.75% 的Java用户
     * 	内存消耗:73.7 MB,击败了95.00% 的Java用户
     * @param classes
     * @param extraStudents
     * @return
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                Long.compare(1L * (b[1] - b[0]) * a[1] * (a[1] + 1), 1L * (a[1] - a[0]) * b[1] * (b[1] + 1))
        );
        for (int[] c : classes) {
            pq.add(c);
        }
        while (extraStudents-- > 0) {
            int[] top = pq.poll();
            top[0]++;
            top[1]++;
            pq.add(top);
        }
        double sum = 0;
        int n = pq.size();
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            sum += 1.0 * top[0] / top[1];
        }
        return sum / n;
    }
}
