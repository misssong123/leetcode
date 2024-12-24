package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:9 ms,击败了100.00% 的Java用户
 * 	内存消耗:45.6 MB,击败了94.48% 的Java用户
 */
class ExamRoom855 {
    PriorityQueue<int[]> queue;
    int n;
    boolean start = false;
    boolean end = false;
    public ExamRoom855(int n) {
        queue = new PriorityQueue<>((a,b)->{
            if (a[2] == b[2]) {
                return a[0] - b[0];
            }
            return b[2] - a[2];
        });
        queue.add(new int[]{0,n-1,n});
        this.n = n;
    }

    public int seat() {
        int[] maxSite = queue.poll();
        //起始下标为0，并且未被占
        if (maxSite[0] == 0 && !start){
            start = true;
            //更新队列
            if (maxSite[1] == n-1 && !end) {
                queue.add(new int[]{0,n-1,n-1});
            }else {
                queue.add(new int[]{0,maxSite[1],maxSite[1]/2});
            }
            return 0;
        }else if (maxSite[1] == n-1 && !end) {
            end = true;
            queue.add(new int[]{maxSite[0],maxSite[1],(maxSite[1] - maxSite[0])/2});
            return n-1;
        }else {
            int mid = maxSite[0] + maxSite[2];
            queue.add(new int[]{maxSite[0],mid,(mid - maxSite[0])/2});
            queue.add(new int[]{mid,maxSite[1],(maxSite[1] - mid)/2});
            return mid;
        }
    }

    public void leave(int p) {
        if (p == 0){
            start = false;
        }else if (p == n-1){
            end = false;
        }
        List<int[]> removeList = new ArrayList<>();
        Iterator<int[]> iterator = queue.iterator();
        while (iterator.hasNext()){
            int[] next = iterator.next();
            if (next[0] == p || next[1] == p){
                removeList.add(next);
                iterator.remove();
            }
        }
        int min = n;
        int max = 0;
        for (int[] arr : removeList){
            min = Math.min(min,arr[0]);
            max = Math.max(max,arr[1]);
        }
        int len = ((min == 0&&!start) || (max == n-1&&!end))?max-min:(max-min)/2;
        queue.add(new int[]{min,max,len});
    }
}

/**
 * 解答成功:
 * 	执行耗时:17 ms,击败了97.90% 的Java用户
 * 	内存消耗:45.7 MB,击败了84.53% 的Java用户
 */
class ExamRoom {
    int n;
    TreeSet<Integer> seats;
    PriorityQueue<int[]> pq;

    public ExamRoom(int n) {
        this.n = n;
        this.seats = new TreeSet<Integer>();
        this.pq = new PriorityQueue<int[]>((a, b) -> {
            int d1 = a[1] - a[0], d2 = b[1] - b[0];
            return d1 / 2 < d2 / 2 || (d1 / 2 == d2 / 2 && a[0] > b[0]) ? 1 : -1;
        });
    }

    public int seat() {
        if (seats.isEmpty()) {
            seats.add(0);
            return 0;
        }
        int left = seats.first(), right = n - 1 - seats.last();
        while (seats.size() >= 2) {
            int[] p = pq.peek();
            if (seats.contains(p[0]) && seats.contains(p[1]) && seats.higher(p[0]) == p[1]) { // 不属于延迟删除的区间
                int d = p[1] - p[0];
                if (d / 2 < right || d / 2 <= left) { // 最左或最右的座位更优
                    break;
                }
                pq.poll();
                pq.offer(new int[]{p[0], p[0] + d / 2});
                pq.offer(new int[]{p[0] + d / 2, p[1]});
                seats.add(p[0] + d / 2);
                return p[0] + d / 2;
            }
            pq.poll(); // leave 函数中延迟删除的区间在此时删除
        }
        if (right > left) { // 最右的位置更优
            pq.offer(new int[]{seats.last(), n - 1});
            seats.add(n - 1);
            return n - 1;
        } else {
            pq.offer(new int[]{0, seats.first()});
            seats.add(0);
            return 0;
        }
    }

    public void leave(int p) {
        if (p != seats.first() && p != seats.last()) {
            int prev = seats.lower(p), next = seats.higher(p);
            pq.offer(new int[]{prev, next});
        }
        seats.remove(p);
    }
}



