package com.meng.interview150.leetcode.editor.cn;

import java.util.*;


/**
 * 超时
 */
class Interview136MedianFinder {
    List<Integer> list;
    boolean modify;
    public Interview136MedianFinder() {
        list = new ArrayList<>(1024);
        modify = false;
    }

    public void addNum(int num) {
        list.add(num);
        modify = true;
    }

    public double findMedian() {
        if (modify){
            modify = false;
            Collections.sort(list);
        }
        if (list.size() % 2 == 0){
            return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2.0;
        }else {
            return list.get(list.size() / 2);
        }
    }
}

/**
 * 解答成功:
 * 	执行耗时:147 ms,击败了13.89% 的Java用户
 * 	内存消耗:62.1 MB,击败了76.29% 的Java用户
 */
class MedianFinderPro {
   PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public MedianFinderPro() {
       minHeap = new PriorityQueue<>((a,b)->b-a);
       maxHeap = new PriorityQueue<>(Integer::compareTo);
    }

    /**
     * 保持maxHeap和minHeap最大相差为1
     * @param num
     */
    public void addNum(int num) {
       if (minHeap.isEmpty() || num <= minHeap.peek()){
           minHeap.add(num);
           if (maxHeap.size() + 1 < minHeap.size()){
               maxHeap.add(minHeap.poll());
           }
       }else {
           maxHeap.add(num);
           if (maxHeap.size() > minHeap.size()) {
               minHeap.add(maxHeap.poll());
           }
       }
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()){
            return minHeap.peek();
        }else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}

/**
 * 解答成功:
 * 	执行耗时:177 ms,击败了5.92% 的Java用户
 * 	内存消耗:67.6 MB,击败了5.38% 的Java用户
 */
class MedianFinder {
    TreeMap<Integer, Integer> nums;
    int n;
    int[] left;
    int[] right;

    public MedianFinder() {
        nums = new TreeMap<Integer, Integer>();
        n = 0;
        left = new int[2];
        right = new int[2];
    }

    public void addNum(int num) {
        nums.put(num, nums.getOrDefault(num, 0) + 1);
        if (n == 0) {
            left[0] = right[0] = num;
            left[1] = right[1] = 1;
        } else if ((n & 1) != 0) {
            if (num < left[0]) {
                decrease(left);
            } else {
                increase(right);
            }
        } else {
            if (num > left[0] && num < right[0]) {
                increase(left);
                decrease(right);
            } else if (num >= right[0]) {
                increase(left);
            } else {
                decrease(right);
                System.arraycopy(right, 0, left, 0, 2);
            }
        }
        n++;
    }

    public double findMedian() {
        return (left[0] + right[0]) / 2.0;
    }

    private void increase(int[] iterator) {
        iterator[1]++;
        if (iterator[1] > nums.get(iterator[0])) {
            //返回大于或等于给定键的最小键。
            iterator[0] = nums.ceilingKey(iterator[0] + 1);
            iterator[1] = 1;
        }
    }

    private void decrease(int[] iterator) {
        iterator[1]--;
        if (iterator[1] == 0) {
            //返回小于或等于给定键的最大键
            iterator[0] = nums.floorKey(iterator[0] - 1);
            iterator[1] = nums.get(iterator[0]);
        }
    }
}


