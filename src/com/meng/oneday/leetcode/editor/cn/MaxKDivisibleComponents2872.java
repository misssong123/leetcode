package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MaxKDivisibleComponents2872 {
    //无思路
    public int maxKDivisibleComponents2872NoWay(int n, int[][] edges, int[] values, int k) {
        return -1;
    }

    /**
     * 看完解析后的思路
     * 解答成功:
     * 	执行耗时:27 ms,击败了38.89% 的Java用户
     * 	内存消耗:87.8 MB,击败了16.67% 的Java用户
     * @param n
     * @param edges
     * @param values
     * @param k
     * @return
     */
    int step;
    public int maxKDivisibleComponents2872(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] graph = new List[n];
        for(int i = 0 ; i < n ; i++){
            graph[i] = new ArrayList<>();
        }
        //初始化边
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        step = 0;
        dfs(graph,values,visited,0,k);
        return step;
    }

    private int dfs(List<Integer>[] graph, int[] values, boolean[] visited, int point,int target) {
        visited[point] = true;
        int sum = values[point];
        for(int next : graph[point]){
            if(!visited[next]){
                sum += dfs(graph,values,visited,next,target);
            }
        }
        sum = sum % target;
        if(sum == 0){
            step++;
        }
        return sum;
    }
    private int ans;

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了33.33% 的Java用户
     * 	内存消耗:88.3 MB,击败了11.11% 的Java用户
     * @param n
     * @param edges
     * @param values
     * @param k
     * @return
     */
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, G -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        dfs(0, -1, g, values, k);
        return ans;
    }

    // 返回子树 x 的点权和
    private long dfs(int x, int fa, List<Integer>[] g, int[] values, int k) {
        long sum = values[x];
        for (int y : g[x]) {
            if (y != fa) { // 避免访问父节点
                // 加上子树 y 的点权和，得到子树 x 的点权和
                sum += dfs(y, x, g, values, k);
            }
        }
        ans += sum % k == 0 ? 1 : 0;
        return sum;
    }
}
