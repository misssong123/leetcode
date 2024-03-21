package com.meng.thinking.leetcode.editor.cn;

import javafx.util.Pair;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CountPaths1976 {
    /**
     * 无思路
     * @param n
     * @param roads
     * @return
     */
    public int countPathsMy(int n, int[][] roads) {
        int[] nums = new int[n];
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了22.55% 的Java用户
     * 	内存消耗:47.8 MB,击败了73.70% 的Java用户
     * @param n
     * @param roads
     * @return
     */
    public int countPaths1(int n, int[][] roads) {
        int mod = 1000000007;
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<int[]>();
        }
        for (int[] road : roads) {
            int x = road[0], y = road[1], t = road[2];
            e[x].add(new int[]{y, t});
            e[y].add(new int[]{x, t});
        }
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        int[] ways = new int[n];

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});
        dis[0] = 0;
        ways[0] = 1;

        while (!pq.isEmpty()) {
            long[] arr = pq.poll();
            long t = arr[0];
            int u = (int) arr[1];
            if (t > dis[u]) {
                continue;
            }
            for (int[] next : e[u]) {
                int v = next[0], w = next[1];
                if (t + w < dis[v]) {
                    dis[v] = t + w;
                    ways[v] = ways[u];
                    pq.offer(new long[]{t + w, v});
                } else if (t + w == dis[v]) {
                    ways[v] = (ways[u] + ways[v]) % mod;
                }
            }
        }
        return ways[n - 1];
    }

    /**
     * 朴素 Dijkstra（适用于稠密图）
     * 解答成功:
     * 	执行耗时:3 ms,击败了99.90% 的Java用户
     * 	内存消耗:50 MB,击败了21.74% 的Java用户
     * @param n
     * @param roads
     * @return
     */
    public int countPaths2(int n, int[][] roads) {
        long[][] g = new long[n][n]; // 邻接矩阵
        for (long[] row : g) {
            Arrays.fill(row, Long.MAX_VALUE / 2); // 防止溢出
        }
        for (int[] r : roads) {
            int x = r[0];
            int y = r[1];
            int d = r[2];
            g[x][y] = d;
            g[y][x] = d;
        }

        long[] dis = new long[n];
        Arrays.fill(dis, 1, n, Long.MAX_VALUE / 2);
        int[] f = new int[n];
        f[0] = 1;
        boolean[] done = new boolean[n];
        while (true) {
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!done[i] && (x < 0 || dis[i] < dis[x])) {
                    x = i;
                }
            }
            if (x == n - 1) {
                // 不可能找到比 dis[n-1] 更短，或者一样短的最短路了（注意本题边权都是正数）
                return f[n - 1];
            }
            done[x] = true; // 最短路长度已确定（无法变得更小）
            for (int y = 0; y < n; y++) { // 尝试更新 x 的邻居的最短路
                long newDis = dis[x] + g[x][y];
                if (newDis < dis[y]) {
                    // 就目前来说，最短路必须经过 x
                    dis[y] = newDis;
                    f[y] = f[x];
                } else if (newDis == dis[y]) {
                    // 和之前求的最短路一样长
                    f[y] = (f[y] + f[x]) % 1_000_000_007;
                }
            }
        }
    }

    /**
     * 堆优化 Dijkstra（适用于稀疏图）
     * 解答成功:
     * 	执行耗时:12 ms,击败了17.95% 的Java用户
     * 	内存消耗:48.1 MB,击败了37.63% 的Java用户
     * @param n
     * @param roads
     * @return
     */
    public int countPaths(int n, int[][] roads) {
        List<int[]>[] g = new ArrayList[n]; // 邻接表
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] r : roads) {
            int x = r[0];
            int y = r[1];
            int d = r[2];
            g[x].add(new int[]{y, d});
            g[y].add(new int[]{x, d});
        }

        long[] dis = new long[n];
        Arrays.fill(dis, 1, n, Long.MAX_VALUE);
        int[] f = new int[n];
        f[0] = 1;
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>(Comparator.comparingLong(Pair::getKey));
        pq.offer(new Pair<>(0L, 0));
        while (true) {
            Pair<Long, Integer> pair = pq.poll();
            long dx = pair.getKey();
            int x = pair.getValue();
            if (x == n - 1) {
                // 不可能找到比 dis[n-1] 更短，或者一样短的最短路了（注意本题边权都是正数）
                return f[n - 1];
            }
            if (dx > dis[x]) {
                continue;
            }
            for (int[] e : g[x]) { // 尝试更新 x 的邻居的最短路
                int y = e[0];
                long newDis = dx + e[1];
                if (newDis < dis[y]) {
                    // 就目前来说，最短路必须经过 x
                    dis[y] = newDis;
                    f[y] = f[x];
                    pq.offer(new Pair<>(newDis, y));
                } else if (newDis == dis[y]) {
                    // 和之前求的最短路一样长
                    f[y] = (f[y] + f[x]) % 1_000_000_007;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
