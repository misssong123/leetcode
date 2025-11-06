package com.meng.top100.leetcode.editor.cn;

import java.util.*;

class ProcessQueries3607 {
    /**
     * 解答成功:
     * 	执行耗时:119 ms,击败了39.24% 的Java用户
     * 	内存消耗:253.2 MB,击败了16.46% 的Java用户
     * @param c
     * @param connections
     * @param queries
     * @return
     */
    public int[] processQueries3607(int c, int[][] connections, int[][] queries) {
        int[] graph = new int[c +1];
        for (int i = 1 ; i <= c ; i++){
            graph[i] = i;
        }
        //构建连通图
        for(int [] conn : connections){
            int f0 =getF(graph,conn[0]);
            int f1 = getF(graph,conn[1]);
            graph[f0] = f1;
        }
        //构建分组
        Map<Integer, PriorityQueue<Integer>> cache = new HashMap<>();
        for(int i = 1 ; i <= c ; i++){
            int f = getF(graph, i);
            graph[i] = f;
            cache.computeIfAbsent(f, k -> new PriorityQueue<>()).add(i);
        }
        //记录失效节点
        Set<Integer> invalid = new HashSet<>();
        //记录结果
        List<Integer> ans = new ArrayList<>();
        for (int[] query : queries){
            int num = query[1];
            if (query[0] == 1){
                if (invalid.contains(num)){
                    int f = graph[num];
                    PriorityQueue<Integer> queue = cache.get(f);
                    while (!cache.isEmpty()&&invalid.contains(queue.peek())){
                        queue.poll();
                    }
                    num = queue.isEmpty()?-1:queue.peek();
                }
                ans.add(num);
            }else {
                invalid.add(num);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    private int getF(int[] graph, int i) {
        while (graph[i] != i){
            i = graph[i];
            graph[i] = graph[graph[i]];
        }
        return i;
    }

    /**
     * 解答成功:
     * 	执行耗时:107 ms,击败了45.57% 的Java用户
     * 	内存消耗:268.6 MB,击败了8.86% 的Java用户
     * @param c
     * @param connections
     * @param queries
     * @return
     */
    public int[] processQueriesOther(int c, int[][] connections, int[][] queries) {
        List<Integer>[] g = new ArrayList[c + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : connections) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        int[] belong = new int[c + 1];
        Arrays.fill(belong, -1);
        List<PriorityQueue<Integer>> heaps = new ArrayList<>();
        PriorityQueue<Integer> pq;
        for (int i = 1; i <= c; i++) {
            if (belong[i] >= 0) {
                continue;
            }
            pq = new PriorityQueue<>();
            dfs(i, g, belong, heaps.size(), pq);
            heaps.add(pq);
        }

        int ansSize = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                ansSize++;
            }
        }

        int[] ans = new int[ansSize];
        int idx = 0;
        boolean[] offline = new boolean[c + 1];
        for (int[] q : queries) {
            int x = q[1];
            if (q[0] == 2) {
                offline[x] = true;
                continue;
            }
            if (!offline[x]) {
                ans[idx++] = x;
                continue;
            }
            pq = heaps.get(belong[x]);
            // 懒删除：取堆顶的时候，如果离线，才删除
            while (!pq.isEmpty() && offline[pq.peek()]) {
                pq.poll();
            }
            ans[idx++] = pq.isEmpty() ? -1 : pq.peek();
        }
        return ans;
    }

    private void dfs(int x, List<Integer>[] g, int[] belong, int compId, PriorityQueue<Integer> pq) {
        belong[x] = compId; // 记录节点 x 在哪个堆
        pq.offer(x);
        for (int y : g[x]) {
            if (belong[y] < 0) {
                dfs(y, g, belong, compId, pq);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:65 ms,击败了87.34% 的Java用户
     * 	内存消耗:260.1 MB,击败了16.46% 的Java用户
     * @param c
     * @param connections
     * @param queries
     * @return
     */
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        List<Integer>[] g = new ArrayList[c + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : connections) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        int[] belong = new int[c + 1];
        Arrays.fill(belong, -1);
        int cc = 0; // 连通块编号
        for (int i = 1; i <= c; i++) {
            if (belong[i] < 0) {
                dfs(i, g, belong, cc);
                cc++;
            }
        }

        int[] offlineTime = new int[c + 1];
        Arrays.fill(offlineTime, Integer.MAX_VALUE);
        int q1 = 0;
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];
            if (q[0] == 2) {
                offlineTime[q[1]] = i; // 记录最早离线时间
            } else {
                q1++;
            }
        }

        // 维护每个连通块的在线电站的最小编号
        int[] mn = new int[cc];
        Arrays.fill(mn, Integer.MAX_VALUE);
        for (int i = 1; i <= c; i++) {
            if (offlineTime[i] == Integer.MAX_VALUE) { // 最终仍然在线
                int j = belong[i];
                mn[j] = Math.min(mn[j], i);
            }
        }

        int[] ans = new int[q1];
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];
            int x = q[1];
            int j = belong[x];
            if (q[0] == 2) {
                if (offlineTime[x] == i) { // 变回在线
                    mn[j] = Math.min(mn[j], x);
                }
            } else {
                q1--;
                if (i < offlineTime[x]) { // 已经在线（写 < 或者 <= 都可以）
                    ans[q1] = x;
                } else if (mn[j] != Integer.MAX_VALUE) {
                    ans[q1] = mn[j];
                } else {
                    ans[q1] = -1;
                }
            }
        }
        return ans;
    }

    private void dfs(int x, List<Integer>[] g, int[] belong, int compId) {
        belong[x] = compId;
        for (int y : g[x]) {
            if (belong[y] < 0) {
                dfs(y, g, belong, compId);
            }
        }
    }
}
