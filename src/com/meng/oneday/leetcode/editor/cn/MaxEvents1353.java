package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MaxEvents1353 {
    /**
     * 解答成功:
     * 	执行耗时:104 ms,击败了12.70% 的Java用户
     * 	内存消耗:73.5 MB,击败了96.30% 的Java用户
     * @param events
     * @return
     */
    public int maxEvents1353(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> qp = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int index = 0;
        int day = events[0][0];
        int res = 0;
        int cnt = 0;
        while (cnt < events.length){
            for (int i = index ; i < events.length && events[i][0] == day; i++){
                qp.offer(events[i]);
                index++;
            }
            while (!qp.isEmpty()){
                cnt++;
                if (qp.peek()[1]>=day){
                    qp.poll();
                    res++;
                    break;
                }else {
                    qp.poll();
                }
            }
            if (qp.isEmpty()&&index<events.length){
                day = Math.max(day+1,events[index][0]);
            }else {
                day++;
            }

        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:59 ms,击败了98.41% 的Java用户
     * 	内存消耗:88.4 MB,击败了10.05% 的Java用户
     * @param events
     * @return
     */
    public int maxEventsOther1(int[][] events) {
        int mx = 0;
        for (int[] e : events) {
            mx = Math.max(mx, e[1]);
        }

        // 按照开始时间分组
        List<Integer>[] groups = new ArrayList[mx + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int[] e : events) {
            groups[e[0]].add(e[1]);
        }

        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <= mx; i++) {
            // 删除过期会议
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            // 新增可以参加的会议
            for (int endDay : groups[i]) {
                pq.offer(endDay);
            }
            // 参加一个结束时间最早的会议
            if (!pq.isEmpty()) {
                ans++;
                pq.poll();
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了100.00% 的Java用户
     * 	内存消耗:72.7 MB,击败了100.00% 的Java用户
     * @param events
     * @return
     */
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        int mx = events[events.length - 1][1];
        int[] fa = new int[mx + 2];
        for (int i = 0; i < fa.length; i++) {
            fa[i] = i;
        }

        int ans = 0;
        for (int[] e : events) {
            int x = find(e[0], fa); // 查找从 startDay 开始的第一个可用天
            if (x <= e[1]) {
                ans++;
                fa[x] = x + 1; // 标记 x 已占用
            }
        }
        return ans;
    }

    private int find(int x, int[] fa) {
        if (fa[x] != x) {
            fa[x] = find(fa[x], fa);
        }
        return fa[x];
    }


}
