package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:460 ms,击败了16.67% 的Java用户
 * 	内存消耗:153 MB,击败了58.33% 的Java用户
 */
class TaskManager3408 {
    Map<Integer,List<Integer>> taskMap;
    PriorityQueue<List<Integer>> taskQueue;
    public TaskManager3408(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        taskQueue = new PriorityQueue<>((o1,o2)->{
            if(Objects.equals(o1.get(2), o2.get(2))){
                return o2.get(1) - o1.get(1);
            }
            return o2.get(2) - o1.get(2);
        });
        for (List<Integer> task : tasks) {
            taskQueue.add(task);
            taskMap.put(task.get(1),task);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        List<Integer> task = new ArrayList<>(Arrays.asList(userId,taskId,priority));
        taskQueue.add(task);
        taskMap.put(taskId,task);
    }
    
    public void edit(int taskId, int newPriority) {
        List<Integer> task = taskMap.get(taskId);
        task.add(-1);
        List<Integer> newTask = new ArrayList<>(Arrays.asList(task.get(0),taskId,newPriority));
        taskQueue.add(newTask);
        taskMap.put(taskId,newTask);
    }
    
    public void rmv(int taskId) {
        taskMap.get(taskId).add(-1);
        taskMap.remove(taskId);
    }
    
    public int execTop() {
        while (!taskQueue.isEmpty() && taskQueue.peek().size() == 4){
            taskQueue.poll();
        }

        return taskQueue.isEmpty()?-1:taskQueue.poll().get(0);
    }
}

/**
 * 解答成功:
 * 	执行耗时:337 ms,击败了66.67% 的Java用户
 * 	内存消耗:138.7 MB,击败了93.33% 的Java用户
 */
class TaskManager {
    private final Map<Integer, int[]> mp = new HashMap<>();
    private final PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        mp.put(taskId, new int[]{priority, userId});
        pq.offer(new int[]{priority, taskId, userId});
    }

    public void edit(int taskId, int newPriority) {
        // 懒修改
        int userId = mp.get(taskId)[1];
        add(userId, taskId, newPriority);
    }

    public void rmv(int taskId) {
        // 懒删除
        mp.get(taskId)[0] = -1;
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int priority = top[0], taskId = top[1], userId = top[2];
            int[] p = mp.get(taskId);
            if (p[0] == priority && p[1] == userId) {
                rmv(taskId);
                return userId;
            }
            // else 货不对板，堆顶和 mp 中记录的不一样，说明堆顶数据已被修改或删除，不做处理
        }
        return -1;
    }
}

