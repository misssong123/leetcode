package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinCost3650 {
    private static final int INF = Integer.MAX_VALUE / 2;
    public int minCostMemoryLimit(int n, int[][] edges) {
        //初始化
        int[] dis = new int[n];
        Arrays.fill(dis,INF);
        dis[0] = 0;
        int[][] weight = new int[n][n];
        for (int[] w : weight) {
            Arrays.fill(w,INF);
        }
        for (int[] edge : edges) {
            weight[edge[0]][edge[1]] = Math.min(edge[2], weight[edge[0]][edge[1]]);
            weight[edge[1]][edge[0]] = Math.min(edge[2] * 2, weight[edge[1]][edge[0]]);
        }
        //初始化
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (poll == null){
                    continue;
                }
                for(int j = 0 ; j < n ; j++){
                    if (weight[poll][j] > 0 && dis[j] > dis[poll] + weight[poll][j]){
                        dis[j] = dis[poll] + weight[poll][j];
                        queue.add(j);
                    }
                }
            }
        }
        return dis[n-1] == INF ? -1 : dis[n-1] ;
    }
    public int minCostTimeOut(int n, int[][] edges) {
        //初始化
        int[] dis = new int[n];
        Arrays.fill(dis,INF);
        dis[0] = 0;
        Map<Integer,Integer>[] weight = new HashMap[n];
        for (int i = 0; i < n ; i++) {
           weight[i] = new HashMap<>(n);
        }
        for (int[] edge : edges) {
            weight[edge[0]].put(edge[1],Math.min(edge[2], weight[edge[0]].getOrDefault(edge[1],edge[2])));
            weight[edge[1]].put(edge[0],Math.min(2*edge[2], weight[edge[1]].getOrDefault(edge[0],2*edge[2])));
        }
        //初始化
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (poll == null){
                    continue;
                }
                for(int j = 0 ; j < n ; j++){
                    if (weight[poll].containsKey(j) && dis[j] > dis[poll] + weight[poll].get(j)){
                        dis[j] = dis[poll] + weight[poll].get(j);
                        queue.add(j);
                    }
                }
            }
        }
        return dis[n-1] == INF ? -1 : dis[n-1] ;
    }

    /**
     * 解答成功:
     * 	执行耗时:74 ms,击败了89.29% 的Java用户
     * 	内存消耗:270.3 MB,击败了12.50% 的Java用户
     * @param n
     * @param edges
     * @return
     */
    public int minCostOther(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n]; // 邻接表
        Arrays.setAll(g, item -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int wt = e[2];
            g[x].add(new int[]{y, wt});
            g[y].add(new int[]{x, wt * 2});
        }

        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        // 堆中保存 (起点到节点 x 的最短路长度，节点 x)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dis[0] = 0; // 起点到自己的距离是 0
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int disX = p[0];
            int x = p[1];
            if (disX > dis[x]) { // x 之前出堆过
                continue;
            }
            if (x == n - 1) { // 到达终点
                return disX;
            }
            for (int[] e : g[x]) {
                int y = e[0];
                int wt = e[1];
                int newDisY = disX + wt;
                if (newDisY < dis[y]) {
                    dis[y] = newDisY; // 更新 x 的邻居的最短路
                    // 懒更新堆：只插入数据，不更新堆中数据
                    // 相同节点可能有多个不同的 newDisY，除了最小的 newDisY，其余值都会触发上面的 continue
                    pq.offer(new int[]{newDisY, y});
                }
            }
        }

        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:94 ms,击败了55.36% 的Java用户
     * 	内存消耗:272.8 MB,击败了5.36% 的Java用户
     * @param n
     * @param edges
     * @return
     */
    public int minCost3650(int n, int[][] edges) {
        //构建
        List<int[]>[] g = new List[n];
        int[] dis = new int[n];
        PriorityQueue<int[] > qp = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        //初始化
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        Arrays.setAll(g,item -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0],y = edge[1],w = edge[2];
            g[x].add(new int[]{y,w});
            g[y].add(new int[]{x,2*w});
        }
        qp.add(new int[]{0,0});
        while (!qp.isEmpty()){
            int[] poll = qp.poll();
            int y = poll[0];
            int w = poll[1];
            //0到y的距离已经被更新
            if (w > dis[y]){
                continue;
            }
            if (y == n - 1){
                break;
            }
            for (int[] edge : g[y]){
                int newW = w + edge[1];
                if(newW < dis[edge[0]]){
                    dis[edge[0]] = newW;
                    qp.add(new int[]{edge[0],newW});
                }
            }
        }
        return dis[n - 1] == Integer.MAX_VALUE ? -1 : dis[n - 1];
    }

}
