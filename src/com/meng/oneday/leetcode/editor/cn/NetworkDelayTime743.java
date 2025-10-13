package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class NetworkDelayTime743 {
    private static final int INF = Integer.MAX_VALUE;

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了67.79% 的Java用户
     * 	内存消耗:48.1 MB,击败了40.49% 的Java用户
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime743(int[][] times, int n, int k) {
        //初始化边
        int[][] edge = new int[n][n];
        for(int[] e : edge){
            Arrays.fill(e,INF);
        }
        for (int[] time : times) {
            edge[time[0]-1][time[1]-1] = time[2];
        }
        //初始化距离
        int[] dist = new int[n];
        Arrays.fill(dist,INF);
        dist[k-1] = 0;
        //初始化访问数组
        boolean[] visited = new boolean[n];
        //初始化队列
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.offer(new int[]{k-1,0});
        //遍历
        while (!queue.isEmpty()){
            int[] point = queue.poll();
            int p = point[0];
            int dis = point[1];
            if (visited[p] || dis > dist[p]){
                continue;
            }
            visited[p] = true;
            //遍历当前节点关联的边
            for(int i = 0 ; i < n ; i++){
                if (i== p || edge[p][i] == INF || dist[p] == INF){
                    continue;
                }
                if (dist[i] > dist[p] + edge[p][i]){
                    dist[i] = dist[p] + edge[p][i];
                    queue.offer(new int[]{i,dist[i]});
                }
            }
        }
        //计算结果
        int res = 0;
        for(int num : dist){
            res = Math.max(res,num);
        }
        return res == INF ? -1 : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了85.49% 的Java用户
     * 	内存消耗:48.5 MB,击败了10.82% 的Java用户
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTimeOther1(int[][] times, int n, int k) {
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
     * 	执行耗时:10 ms,击败了46.15% 的Java用户
     * 	内存消耗:47.8 MB,击败了65.90% 的Java用户
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
