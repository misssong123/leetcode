package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * 解答成功:
 * 	执行耗时:20 ms,击败了46.38% 的Java用户
 * 	内存消耗:43.4 MB,击败了42.82% 的Java用户
 */
class SmallestInfiniteSet2336 {
    Set<Integer> cache ;
    PriorityQueue<Integer> queue;
    public SmallestInfiniteSet2336() {
        cache = new HashSet<>();
        queue = new PriorityQueue<>();
        for(int i = 1 ; i <= 1000 ; i++){
            queue.add(i);
        }
    }
    
    public int popSmallest() {
        Integer num = queue.poll();
        cache.add(num);
        return num ;
    }
    
    public void addBack(int num) {
        if (cache.contains(num)){
            cache.remove(num);
            queue.add(num);
        }
    }
}

/**
 * 解答成功:
 * 	执行耗时:9 ms,击败了81.96% 的Java用户
 * 	内存消耗:42.9 MB,击败了98.40% 的Java用户
 */
class SmallestInfiniteSet {
    private int thres;
    private TreeSet<Integer> set;

    public SmallestInfiniteSet() {
        thres = 1;
        set = new TreeSet<Integer>();
    }

    public int popSmallest() {
        if (set.isEmpty()) {
            int ans = thres;
            ++thres;
            return ans;
        }
        int ans = set.pollFirst();
        return ans;
    }

    public void addBack(int num) {
        if (num < thres) {
            set.add(num);
        }
    }
}

