package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class PossibleBiPartition886 {
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了89.31% 的Java用户
     * 	内存消耗:54.8 MB,击败了11.06% 的Java用户
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[n+1];
        int[] color = new int[n+1];
        //初始化邻接表
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }
        for (int i = 1 ; i <= n ; i++){
            if(color[i] != 0){
                continue;
            }
            color[i] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()){
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    if(color[next] == 0){
                        color[next] = -color[cur];
                        queue.add(next);
                    }else if (color[next] == color[cur]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * 解答成功:
     * 	执行耗时:285 ms,击败了5.08% 的Java用户
     * 	内存消耗:66.2 MB,击败了5.08% 的Java用户
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition886(int n, int[][] dislikes) {
        boolean[] visited = new boolean[n + 1];
        Set<Integer>[] cache = new HashSet[n+1];
        for (int i = 1 ; i <= n ; i++){
            cache[i] = new HashSet<>();
        }
        for (int[] dislike : dislikes) {
            cache[dislike[0]].add(dislike[1]);
            cache[dislike[1]].add(dislike[0]);
        }
        Set<Integer> group1 = new HashSet<>();
        Set<Integer> group2 = new HashSet<>();
        for(int i = 1 ; i <= n ; i++){
            if(!visited[i]){
                group1.add(i);
                visited[i] = true;
                if(!dfs(visited,cache,group1,group2,cache[i])){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs( boolean[] visited, Set<Integer>[] cache,
                        Set<Integer> group1, Set<Integer> group2,Set<Integer> add) {
        if (add.isEmpty()){
            return true;
        }
        Set<Integer> newAdd = new HashSet<>();
        //需要把add中的元素添加到group2中
        for (int num : add){
            //已经添加了
            if (visited[num]){
                continue;
            }
            //是否冲突
            for (int val : group2){
                if (cache[val].contains(num)){
                    return false;
                }
            }
            group2.add(num);
            visited[num] = true;
            for (int v : cache[num]){
                if (!visited[v]){
                    newAdd.add(v);
                }
            }
        }
        if (newAdd.isEmpty()){
            return true;
        }
        return dfs(visited,cache,group2,group1,newAdd);
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了59.37% 的Java用户
     * 	内存消耗:53.3 MB,击败了52.35% 的Java用户
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartitionOther(int n, int[][] dislikes) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, item -> new ArrayList<>());
        for (int[] e : dislikes) {
            int x = e[0] - 1; // 节点编号改成从 0 开始
            int y = e[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        return isBipartite(g);
    }

    // 785. 判断二分图
    private boolean isBipartite(List<Integer>[] graph) {
        // colors[i] = 0  表示未访问节点 i
        // colors[i] = 1  表示节点 i 为红色
        // colors[i] = -1 表示节点 i 为蓝色
        int[] colors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0 && !dfs(i, 1, graph, colors)) {
                // 从节点 i 开始递归，发现 i 所在连通块不是二分图
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int x, int c, List<Integer>[] graph, int[] colors) {
        colors[x] = c; // 节点 x 染成颜色 c
        for (int y : graph[x]) {
            // 邻居 y 的颜色与 x 的相同，说明不是二分图，返回 false
            // 或者继续递归，发现不是二分图，返回 false
            if (colors[y] == c ||
                    colors[y] == 0 && !dfs(y, -c, graph, colors)) { // 取相反数，实现交替染色
                return false;
            }
        }
        return true;
    }

}
