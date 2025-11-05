package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class NetworkDelayTime_743 {
    private static final int INF = Integer.MAX_VALUE;

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了79.66% 的Java用户
     * 	内存消耗:48.4 MB,击败了20.18% 的Java用户
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime743(int[][] times, int n, int k) {
        int[] dist = new int[n+1];
        int[][] route = new int[n+1][n+1];
        //初始化
        for (int[] r : route) {
            Arrays.fill(r, INF);
        }
        Arrays.fill(dist, INF);
        for(int[] time : times) {
            route[time[0]][time[1]] = time[2];
        }
        dist[k] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->a[0] - b[0]);
        queue.offer(new int[]{0,k});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int dis = cur[0],p=cur[1];
            //节点已经被更新
            if (dis > dist[p]){
                continue;
            }
            for(int i = 1 ; i <=n ; i++){
                if (route[p][i] != INF && dist[i] > dis + route[p][i]){
                    dist[i] = dis + route[p][i];
                    queue.offer(new int[]{dist[i],i});
                }
            }
        }
        int max = -1;
        for (int i = 1 ; i <= n ; i++){
            max = Math.max(max, dist[i]);
        }
        return max == INF ? -1 : max;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:48.7 MB,击败了5.55% 的Java用户
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTimeOther(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2; // 防止加法溢出
        int[][] g = new int[n][n]; // 邻接矩阵
        for (int[] row : g) {
            Arrays.fill(row, INF);
        }
        for (int[] t : times) {
            g[t[0] - 1][t[1] - 1] = t[2];
        }

        int maxDis = 0;
        int[] dis = new int[n];
        Arrays.fill(dis, INF);
        dis[k - 1] = 0;
        boolean[] done = new boolean[n];
        while (true) {
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!done[i] && (x < 0 || dis[i] < dis[x])) {
                    x = i;
                }
            }
            if (x < 0) {
                return maxDis; // 最后一次算出的最短路就是最大的
            }
            if (dis[x] == INF) { // 有节点无法到达
                return -1;
            }
            maxDis = dis[x]; // 求出的最短路会越来越大
            done[x] = true; // 最短路长度已确定（无法变得更小）
            for (int y = 0; y < n; y++) {
                // 更新 x 的邻居的最短路
                dis[y] = Math.min(dis[y], dis[x] + g[x][y]);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了48.94% 的Java用户
     * 	内存消耗:48.5 MB,击败了13.40% 的Java用户
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] g = new ArrayList[n]; // 邻接表
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] t : times) {
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
        }

        int maxDis = 0;
        int left = n; // 未确定最短路的节点个数
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.offer(new int[]{0, k - 1});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dx = p[0];
            int x = p[1];
            if (dx > dis[x]) { // x 之前出堆过
                continue;
            }
            maxDis = dx; // 求出的最短路会越来越大
            left--;
            for (int[] e : g[x]) {
                int y = e[0];
                int newDis = dx + e[1];
                if (newDis < dis[y]) {
                    dis[y] = newDis; // 更新 x 的邻居的最短路
                    pq.offer(new int[]{newDis, y});
                }
            }
        }
        return left == 0 ? maxDis : -1;
    }

}
