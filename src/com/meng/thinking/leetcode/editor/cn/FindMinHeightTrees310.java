package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class FindMinHeightTrees310 {
    /**
     * 解答成功:
     * 	执行耗时:418 ms,击败了5.16% 的Java用户
     * 	内存消耗:59.2 MB,击败了5.17% 的Java用户
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTreesMy(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }
        if (n == 2) {
            return Arrays.asList(0, 1);
        }
        int[] cache = new int[n];
        // 统计每个节点的入度
        Map<Integer,Set<Integer>> map = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            map.put(i,new HashSet<>());
        }
        for(int[] edge : edges){
            cache[edge[0]]++;
            cache[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        List<Integer> res = new ArrayList<>();
        int num = n;
        while (num > 0){
            for (int i = 0; i < n; i++){
                if (cache[i] == 1 || cache[i] == 0){
                    res.add(i);
                    num--;
                }
                if (num == 0){
                    return res;
                }
            }
            for (int index : res){
                cache[index]= -1;
                for(int j : map.get(index)){
                    cache[j]--;
                    map.get(j).remove(index);
                }
                map.remove(index);
            }
            res.clear();

        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了48.88% 的Java用户
     * 	内存消耗:54.3 MB,击败了60.59% 的Java用户
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<Integer>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        /* 找到与节点 0 最远的节点 x */
        int x = findLongestNode(0, parent, adj);
        /* 找到与节点 x 最远的节点 y */
        int y = findLongestNode(x, parent, adj);
        /* 求出节点 x 到节点 y 的路径 */
        List<Integer> path = new ArrayList<Integer>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    public int findLongestNode(int u, int[] parent, List<Integer>[] adj) {
        int n = adj.length;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        boolean[] visit = new boolean[n];
        queue.offer(u);
        visit[u] = true;
        int node = -1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            node = curr;
            for (int v : adj[curr]) {
                if (!visit[v]) {
                    visit[v] = true;
                    parent[v] = curr;
                    queue.offer(v);
                }
            }
        }
        return node;
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了79.35% 的Java用户
     * 	内存消耗:56 MB,击败了24.79% 的Java用户
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<Integer>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        /* 找到与节点 0 最远的节点 x */
        int x = findLongestNode2(0, parent, adj);
        /* 找到与节点 x 最远的节点 y */
        int y = findLongestNode2(x, parent, adj);
        /* 求出节点 x 到节点 y 的路径 */
        List<Integer> path = new ArrayList<Integer>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    public int findLongestNode2(int u, int[] parent, List<Integer>[] adj) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[u] = 0;
        dfs(u, dist, parent, adj);
        int maxdist = 0;
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxdist) {
                maxdist = dist[i];
                node = i;
            }
        }
        return node;
    }

    public void dfs(int u, int[] dist, int[] parent, List<Integer>[] adj) {
        for (int v : adj[u]) {
            if (dist[v] < 0) {
                dist[v] = dist[u] + 1;
                parent[v] = u;
                dfs(v, dist, parent, adj);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了95.35% 的Java用户
     * 	内存消耗:54.1 MB,击败了79.52% 的Java用户
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<Integer>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        int[] degree = new int[n];
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        int remainNodes = n;
        while (remainNodes > 2) {
            int sz = queue.size();
            remainNodes -= sz;
            for (int i = 0; i < sz; i++) {
                int curr = queue.poll();
                for (int v : adj[curr]) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            ans.add(queue.poll());
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
