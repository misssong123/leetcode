package com.meng.leetcode75;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 时间
 * 详情
 * 21ms
 * 击败 37.91%使用 Java 的用户
 * 内存
 * 详情
 * 49.89MB
 * 击败 61.56%使用 Java 的用户
 */
public class RecentCounter933 {
    LinkedList<Integer> cache;
    public RecentCounter933() {
        cache = new LinkedList<>();
    }

    public int ping(int t) {
        int min = t - 3000;
        while (!cache.isEmpty()&&cache.peek() <min){
            cache.pop();
        }
        cache.addLast(t);
        return cache.size();
    }
}

/**
 * 时间
 * 详情
 * 23ms
 * 击败 30.09%使用 Java 的用户
 * 内存
 * 详情
 * 54.38MB
 * 击败 5.21%使用 Java 的用户
 */
class RecentCounter {
    Queue<Integer> queue;

    public RecentCounter() {
        queue = new ArrayDeque<Integer>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}
