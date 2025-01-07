package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:34 ms,击败了62.42% 的Java用户
 * 	内存消耗:44.9 MB,击败了17.98% 的Java用户
 */
class MyCalendar729 {
    Map<Integer,Integer> map;
    TreeSet<Integer> set;
    public MyCalendar729() {
        map = new HashMap<>(32);
        set = new TreeSet<>();
    }
    
    public boolean book(int startTime, int endTime) {
        if (set.contains(startTime)||set.contains(endTime-1)){
            return false;
        }
        Integer floor = set.floor(startTime);
        if(floor != null && map.containsKey(floor) && map.get(floor) > startTime){
            return false;
        }
        floor = set.floor(endTime-1);
        if(floor != null && floor >= startTime){
            return false;
        }
        map.put(startTime,endTime-1);
        set.add(startTime);
        set.add(endTime-1);
        return true;
    }
}

/**
 * 解答成功:
 * 	执行耗时:26 ms,击败了67.08% 的Java用户
 * 	内存消耗:44.6 MB,击败了71.58% 的Java用户
 */
class MyCalendarBinarySearch {
    TreeSet<int[]> booked;

    public MyCalendarBinarySearch() {
        booked = new TreeSet<int[]>((a, b) -> a[0] - b[0]);
    }

    public boolean book(int start, int end) {
        if (booked.isEmpty()) {
            booked.add(new int[]{start, end});
            return true;
        }
        int[] tmp = {end, 0};
        int[] arr = booked.ceiling(tmp);
        int[] prev = arr == null ? booked.last() : booked.lower(arr);
        if (arr == booked.first() || booked.lower(tmp)[1] <= start) {
            booked.add(new int[]{start, end});
            return true;
        }
        return false;
    }
}

/**
 * 解答成功:
 * 	执行耗时:99 ms,击败了15.86% 的Java用户
 * 	内存消耗:46.6 MB,击败了8.81% 的Java用户
 */
class MyCalendar {
    Set<Integer> tree;
    Set<Integer> lazy;

    public MyCalendar() {
        tree = new HashSet<Integer>();
        lazy = new HashSet<Integer>();
    }

    public boolean book(int start, int end) {
        if (query(start, end - 1, 0, 1000000000, 1)) {
            return false;
        }
        update(start, end - 1, 0, 1000000000, 1);
        return true;
    }

    public boolean query(int start, int end, int l, int r, int idx) {
        if (start > r || end < l) {
            return false;
        }
        /* 如果该区间已被预订，则直接返回 */
        if (lazy.contains(idx)) {
            return true;
        }
        if (start <= l && r <= end) {
            return tree.contains(idx);
        } else {
            int mid = (l + r) >> 1;
            if (end <= mid) {
                return query(start, end, l, mid, 2 * idx);
            } else if (start > mid) {
                return query(start, end, mid + 1, r, 2 * idx + 1);
            } else {
                return query(start, end, l, mid, 2 * idx) | query(start, end, mid + 1, r, 2 * idx + 1);
            }
        }
    }

    public void update(int start, int end, int l, int r, int idx) {
        if (r < start || end < l) {
            return;
        }
        if (start <= l && r <= end) {
            tree.add(idx);
            lazy.add(idx);
        } else {
            int mid = (l + r) >> 1;
            update(start, end, l, mid, 2 * idx);
            update(start, end, mid + 1, r, 2 * idx + 1);
            tree.add(idx);
        }
    }
}


