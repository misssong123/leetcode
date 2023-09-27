package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;
class FindMinHeightTrees310 {//
    /**
     * 选取入度最大的点，以及和选中相连的最大点
     * @param n
     * @param edges
     * @return
     * 超时
     */
    Map<Integer,List<Integer>> cache;
    List<Integer> res ;
    int minHeight;
    int[] edgeNum;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        res = new ArrayList<>();
        if (n < 3){
            for(int i = 0 ; i < n ; i++){
                res.add(i);
            }
            return res;
        }
        minHeight = n;
        cache = new HashMap<>();
        edgeNum = new int[n];
        for(int i = 0 ; i < n ; i++){
            cache.put(i,new ArrayList<>());
        }
        //初始化
        for(int[] edge : edges){
            int start = edge[0];
            int end = edge[1];
            cache.get(start).add(end);
            cache.get(end).add(start);
            edgeNum[start]++;
            edgeNum[end]++;
        }
        for(int i = 0 ; i < n ; i++){
            if (edgeNum[i]==1){
                continue;
            }
            int height = getHeight(i, n);
            if (height < minHeight){
                res.clear();
                minHeight = height;
                res.add(i);
            }else if (height == minHeight){
                res.add(i);
            }
        }
        return res;
    }

    public int getHeight(int point,int n){
        int len = 0;
        boolean[] visited = new boolean[n];
        visited[point] =true;
        List<Integer> form = new ArrayList<>(cache.get(point));
        List<Integer> to = new ArrayList<>();
        while (true){
            for(int num : form){
                if (visited[num]){
                    continue;
                }
                visited[num] =true;
                for(int t : cache.get(num)){
                    if (visited[t]){
                        continue;
                    }
                    to.add(t);
                }
            }
            if (to.size()==0){
                break;
            }
            form.clear();
            form.addAll(to);
            to.clear();
            len++;
        }
        return len;
    }

    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了21.79% 的Java用户
     * 	内存消耗:53.2 MB,击败了87.49% 的Java用户
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
        int x = findLongestNode1(0, parent, adj);
        /* 找到与节点 x 最远的节点 y */
        int y = findLongestNode1(x, parent, adj);
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

    public int findLongestNode1(int u, int[] parent, List<Integer>[] adj) {
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
     * 	执行耗时:15 ms,击败了77.24% 的Java用户
     * 	内存消耗:55.4 MB,击败了14.02% 的Java用户
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
     * 时间
     * 详情
     * 13ms
     * 击败 89.54%使用 Java 的用户
     * 内存
     * 详情
     * 52.29MB
     * 击败 59.33%使用 Java 的用户
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees3(int n, int[][] edges) {
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
