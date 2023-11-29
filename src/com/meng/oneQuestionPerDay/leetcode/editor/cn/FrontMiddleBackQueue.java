package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:6 ms,击败了80.50% 的Java用户
 * 	内存消耗:43.6 MB,击败了5.03% 的Java用户
 */
class FrontMiddleBackQueue1 {
    List<Integer> cache ;
    public FrontMiddleBackQueue1() {
        cache = new ArrayList<>();
    }
    
    public void pushFront(int val) {
        cache.add(0,val);
    }
    
    public void pushMiddle(int val) {
        cache.add((cache.size())/2,val);
    }
    
    public void pushBack(int val) {
        cache.add(cache.size(),val);
    }
    
    public int popFront() {
        if (cache.size() == 0){
            return -1;
        }
        return cache.remove(0);
    }
    
    public int popMiddle() {
        if (cache.size() == 0){
            return -1;
        }
        return cache.remove((cache.size()-1)/2);
    }
    
    public int popBack() {
        if (cache.size() == 0){
            return -1;
        }
        return cache.remove(cache.size()-1);
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue1 demo = new FrontMiddleBackQueue1();
        demo.pushFront(1);
        demo.pushBack(2);
        demo.pushMiddle(3);
        demo.pushMiddle(4);
        System.out.println(demo.cache);
        System.out.println(demo.popFront());
        System.out.println(demo.popMiddle());
        System.out.println(demo.cache);
        System.out.println(demo.popMiddle());
        System.out.println(demo.popBack());
        System.out.println(demo.popFront());
    }
}

/**
 * 解答成功:
 * 	执行耗时:7 ms,击败了47.68% 的Java用户
 * 	内存消耗:43.6 MB,击败了5.63% 的Java用户
 */
class FrontMiddleBackQueue {
    Deque<Integer> left;
    Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<Integer>();
        right = new ArrayDeque<Integer>();
    }

    public void pushFront(int val) {
        left.offerFirst(val);
        if (left.size() == right.size() + 2) {
            right.offerFirst(left.pollLast());
        }
    }

    public void pushMiddle(int val) {
        if (left.size() == right.size() + 1) {
            right.offerFirst(left.pollLast());
        }
        left.offerLast(val);
    }

    public void pushBack(int val) {
        right.offerLast(val);
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
    }

    public int popFront() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = left.pollFirst();
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
        return val;
    }

    public int popMiddle() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = left.pollLast();
        if (left.size() + 1 == right.size()) {
            left.offerLast(right.pollFirst());
        }
        return val;
    }

    public int popBack() {
        if (left.isEmpty()) {
            return -1;
        }
        int val = 0;
        if (right.isEmpty()) {
            val = left.pollLast();
        } else {
            val = right.pollLast();
            if (left.size() == right.size() + 2) {
                right.offerFirst(left.pollLast());
            }
        }
        return val;
    }
}

