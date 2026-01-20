package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class GetOrder1834 {
    /**
     * 解答成功:
     * 	执行耗时:204 ms,击败了5.56% 的Java用户
     * 	内存消耗:146.6 MB,击败了19.45% 的Java用户
     * @param tasks
     * @return
     */
    public int[] getOrder1834(int[][] tasks) {
        //待执行的任务
        PriorityQueue<int[]> waitQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0 ; i < tasks.length ; i++){
            waitQueue.add(new int[]{tasks[i][0],tasks[i][1],i});
        }
        //可执行的任务,长短,下标
        PriorityQueue<int[]> runQueue = new PriorityQueue<>((a,b)->{
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int[] res = new int[tasks.length];
        int index = 0;
        long time = 0;
        while (!waitQueue.isEmpty() || !runQueue.isEmpty()){
            //将当前时间点之前可以执行的任务加入可执行队列
            if (runQueue.isEmpty()){
                time = Math.max(time,waitQueue.peek()[0]);
            }
            //可新加入的任务
            while (!waitQueue.isEmpty() && waitQueue.peek()[0] <= time){
                int[] poll = waitQueue.poll();
                runQueue.add(new int[]{poll[1],poll[2]});
            }
            //执行任务
            if (!runQueue.isEmpty()){
                int[] poll = runQueue.poll();
                res[index++] = poll[1];
                time += poll[0];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:173 ms,击败了10.42% 的Java用户
     * 	内存消耗:146.7 MB,击败了19.45% 的Java用户
     * @param tasks
     * @return
     */
    public int[] getOrderDeepSeek(int[][] tasks) {
        int n = tasks.length;

        // 未到执行时间的任务：enqueueTime 排序
        PriorityQueue<int[]> waitQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int i = 0; i < n; i++) {
            waitQueue.offer(new int[]{tasks[i][0], tasks[i][1], i});
        }

        // 可执行任务：processingTime -> index
        PriorityQueue<int[]> runQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int[] res = new int[n];
        int idx = 0;
        long time = 0;

        while (!waitQueue.isEmpty() || !runQueue.isEmpty()) {

            // CPU 空闲，跳到下一个任务开始时间
            if (runQueue.isEmpty()) {
                time = Math.max(time, waitQueue.peek()[0]);
            }

            // 将当前时间能执行的任务加入 runQueue
            while (!waitQueue.isEmpty() && waitQueue.peek()[0] <= time) {
                int[] task = waitQueue.poll();
                runQueue.offer(new int[]{task[1], task[2]});
            }

            // 执行任务
            int[] cur = runQueue.poll();
            res[idx++] = cur[1];
            time += cur[0];
        }

        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:148 ms,击败了17.36% 的Java用户
     * 	内存消耗:135.8 MB,击败了82.64% 的Java用户
     * @param tasks
     * @return
     */
    public int[] getOrderGemini(int[][] tasks) {
        int n = tasks.length;
        int[] res = new int[n];

        // 1. 创建索引数组并按任务入队时间排序
        // 这样避免了构建一个完整的 PriorityQueue 来存储初始任务
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        Arrays.sort(indices, (i, j) -> Integer.compare(tasks[i][0], tasks[j][0]));

        // 2. 准备就绪队列：按 [执行时间] 升序，若相同则按 [原始索引] 升序
        PriorityQueue<Integer> readyQueue = new PriorityQueue<>((i, j) -> {
            if (tasks[i][1] != tasks[j][1]) {
                return Integer.compare(tasks[i][1], tasks[j][1]);
            }
            return Integer.compare(i, j);
        });

        long currentTime = 0; // 使用 long 防止溢出
        int taskIdx = 0;      // 遍历已排序索引数组的指针
        int resIdx = 0;       // 结果数组的指针

        while (resIdx < n) {
            // 如果当前没有可执行任务，直接跳跃时间到下一个任务的入队时间
            if (readyQueue.isEmpty() && currentTime < tasks[indices[taskIdx]][0]) {
                currentTime = tasks[indices[taskIdx]][0];
            }

            // 将所有入队时间 <= 当前时间的任务加入就绪队列
            while (taskIdx < n && tasks[indices[taskIdx]][0] <= currentTime) {
                readyQueue.offer(indices[taskIdx]);
                taskIdx++;
            }

            // 执行就绪队列中优先级最高的任务
            if (!readyQueue.isEmpty()) {
                int currentTaskIndex = readyQueue.poll();
                res[resIdx++] = currentTaskIndex;
                currentTime += tasks[currentTaskIndex][1];
            }
        }

        return res;
    }

}
