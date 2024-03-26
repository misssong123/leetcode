package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 无思路
 */
class GraphMy {

    public GraphMy(int n, int[][] edges) {

    }
    
    public void addEdge(int[] edge) {

    }
    
    public int shortestPath(int node1, int node2) {
        return 0;
    }
}

/**
 * 解答成功:
 * 	执行耗时:93 ms,击败了40.63% 的Java用户
 * 	内存消耗:54.1 MB,击败了17.19% 的Java用户
 */
class Graph1 {
    private List<int[]>[] graph;

    public Graph1(int n, int[][] edges) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<int[]>();
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int cost = edge[2];
            graph[x].add(new int[]{y, cost});
        }
    }

    public void addEdge(int[] edge) {
        int x = edge[0];
        int y = edge[1];
        int cost = edge[2];
        graph[x].add(new int[]{y, cost});
    }

    public int shortestPath(int node1, int node2) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[node1] = 0;
        pq.offer(new int[]{0, node1});
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int cost = arr[0], cur = arr[1];
            if (cur == node2) {
                return cost;
            }
            for (int[] nextArr : graph[cur]) {
                int next = nextArr[0], ncost = nextArr[1];
                if (dist[next] > cost + ncost) {
                    dist[next] = cost + ncost;
                    pq.offer(new int[]{cost + ncost, next});
                }
            }
        }
        return -1;
    }
}

/**
 * 解答成功:
 * 	执行耗时:66 ms,击败了75.00% 的Java用户
 * 	内存消耗:54 MB,击败了54.69% 的Java用户
 */
class Graph {
    private int[][] dist;

    public Graph(int n, int[][] edges) {
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    public void addEdge(int[] edge) {
        int x = edge[0], y = edge[1], cost = edge[2];
        if (cost >= dist[x][y]) {
            return;
        }
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][x] != Integer.MAX_VALUE && dist[y][j] != Integer.MAX_VALUE) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][x] + cost + dist[y][j]);
                }
            }
        }
    }

    public int shortestPath(int node1, int node2) {
        int res = dist[node1][node2];
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
