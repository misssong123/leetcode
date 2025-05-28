package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MaxTargetNodes3372 {
    /**
     * 解答成功:
     * 	执行耗时:583 ms,击败了8.00% 的Java用户
     * 	内存消耗:46.2 MB,击败了12.00% 的Java用户
     * @param edges1
     * @param edges2
     * @param k
     * @return
     */
    public int[] maxTargetNodes3372(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length+1;
        int m = edges2.length+1;
        if (k == 0){
            int[] res = new int[n];
            Arrays.fill(res,1);
            return res;
        }
        List<Integer>[] graph1 = new List[n];
        List<Integer>[] graph2 = new List[m];
        for (int i = 0; i < n; i++) {
            graph1[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            graph2[i] = new ArrayList<>();
        }
        for (int[] edge : edges1) {
            graph1[edge[0]].add(edge[1]);
            graph1[edge[1]].add(edge[0]);
        }
        for (int[] edge : edges2) {
            graph2[edge[0]].add(edge[1]);
            graph2[edge[1]].add(edge[0]);
        }

        //计算
        int[] nRes = dfs(graph1,k);
        int[] mRes = dfs(graph2,k-1);
        int max = Arrays.stream(mRes).max().getAsInt();
        for (int i = 0; i < n; i++) {
            nRes[i] += max;
        }
        return nRes;
    }

    private int[] dfs(List<Integer>[] graph1,int deep) {
        int len =  graph1.length;
        int[] res = new int[len];
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if(deep > 0){
                boolean[] visited = new boolean[len];
                visited[i] = true;
                temp.add(i);
                List<Integer> t = new ArrayList<>();
                int path = deep;
                while (!temp.isEmpty()&&path >0){
                    for(int index : temp){
                        for(int next : graph1[index]){
                            if (!visited[next]){
                                visited[next] = true;
                                t.add(next);
                            }
                        }
                    }
                    path--;
                    res[i] += t.size();
                    temp = new ArrayList<>(t);
                    t.clear();
                }
            }
            res[i] ++;
            temp.clear();
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:330 ms,击败了56.00% 的Java用户
     * 	内存消耗:44.9 MB,击败了92.00% 的Java用户
     * @param edges1
     * @param edges2
     * @param k
     * @return
     */
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int max2 = 0;
        if (k > 0) {
            List<Integer>[] g = buildTree(edges2);
            for (int i = 0; i < edges2.length + 1; i++) {
                max2 = Math.max(max2, dfs(i, -1, 0, g, k - 1)); // 注意这里传的是 k-1
            }
        }

        List<Integer>[] g = buildTree(edges1);
        int[] ans = new int[edges1.length + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = dfs(i, -1, 0, g, k) + max2;
        }
        return ans;
    }

    private List<Integer>[] buildTree(int[][] edges) {
        List<Integer>[] g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        return g;
    }

    private int dfs(int x, int fa, int d, List<Integer>[] g, int k) {
        if (d > k) {
            return 0;
        }
        int cnt = 1;
        for (int y : g[x]) {
            if (y != fa) {
                cnt += dfs(y, x, d + 1, g, k);
            }
        }
        return cnt;
    }

}
