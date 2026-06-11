package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AssignEdgeWeights3558 {
    private final static int MOD = 1000000007;

    /**
     * 解答成功:
     * 	执行耗时:96 ms,击败了73.33% 的Java用户
     * 	内存消耗:229.7 MB,击败了93.33% 的Java用户
     * @param edges
     * @return
     */
    public int assignEdgeWeights3558(int[][] edges) {
        int len = edges.length;
        //查找最大深度
        List<Integer>[] graph = new List[len + 2];
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        //初始化构建
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[len + 2];
        //计算最大深度
        List<Integer> nodes = new ArrayList<>();
        nodes.add(1);
        visited[1] = true;
        int max = -1;
        while (!nodes.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            for(Integer node : nodes){
                for(Integer next : graph[node]){
                    if(!visited[next]){
                        temp.add(next);
                        visited[next] = true;
                    }
                }
            }
            nodes = temp;
            max++;
        }

        return (int)pow(2,max -1);
    }

    /**
     * 解答成功:
     * 	执行耗时:97 ms,击败了73.33% 的Java用户
     * 	内存消耗:284.8 MB,击败了26.67% 的Java用户
     * @param edges
     * @return
     */
    public int assignEdgeWeightsOther(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        int k = dfs(1, 0, g);
        return (int) pow(2, k - 1);
    }

    private int dfs(int x, int fa, List<Integer>[] g) {
        int d = 0;
        for (int y : g[x]) {
            if (y != fa) { // 不递归到父节点
                d = Math.max(d, dfs(y, x, g) + 1);
            }
        }
        return d;
    }

    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

}
