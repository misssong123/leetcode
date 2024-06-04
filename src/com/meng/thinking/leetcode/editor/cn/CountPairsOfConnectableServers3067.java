package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CountPairsOfConnectableServers3067 {
    //
    List<Integer>[] cache;
    Map<String,Integer> weightCache = new HashMap<>();
    boolean[] visited ;
    /**
     * 解答成功:
     * 	执行耗时:712 ms,击败了8.53% 的Java用户
     * 	内存消耗:47.5 MB,击败了11.98% 的Java用户
     * @param edges
     * @param signalSpeed
     * @return
     */
    public int[] countPairsOfConnectableServersMy(int[][] edges, int signalSpeed) {
        //节点个数
        int n = edges.length + 1;
        int[] res = new int[n];
        //连接的节点
        cache = new List[n];
        for (int i = 0; i < n; i++) {
            cache[i] = new ArrayList<>();
        }
        //初始化连接节点
        for(int[] edge:edges){
            int x = edge[0],y = edge[1];
            cache[x].add(y);
            cache[y].add(x);
            weightCache.put(x+"-"+y,edge[2]);
            weightCache.put(y+"-"+x,edge[2]);
        }
        //构建子树
        for(int i = 0 ; i < n; i++){
            if (cache[i].size() > 1){
                visited = new boolean[n];
                res[i] = find(i,signalSpeed,0,0);
            }else {
                res[i] = 0;
            }
        }
        return  res;
    }

    /**
     * 构建以index为根节点的树
     * @param index
     * @param signalSpeed
     * @return
     */
    private int find(int index, int signalSpeed, int level,int weight) {
        visited[index] = true;
        List<Integer> list = new ArrayList<>();
        for(int x : cache[index]){
            int num = 0;
            if (!visited[x]){
                int newWeight = weight + weightCache.get(index+"-"+x);
                if (newWeight % signalSpeed == 0){
                    num++;
                }
                num += find(x,signalSpeed,level+1,newWeight);
            }
            if (num > 0){
                list.add(num);
            }
        }
        if (level >0){
            if (list.size() > 0){
                int num = 0;
                for (int x : list){
                    num += x;
                }
                return num;
            }
        }else {
            if (list.size() > 1){
                int num = 0;
                for(int i = 0 ; i < list.size() ; i++){
                    for(int j = i+1 ; j < list.size() ; j++){
                        num += list.get(i) * list.get(j);
                    }
                }
                return num;
            }
        }
        return 0;
    }

    /**
     * 执行用时分布
     * 145
     * ms
     * 击败
     * 94.47%
     * 复杂度分析
     * 消耗内存分布
     * 44.54
     * MB
     * 击败
     * 83.64%
     * @param edges
     * @param signalSpeed
     * @return
     */
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<int[]>[] graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int pre = 0;
            if (graph[i].size() > 1){
                for (int[] e : graph[i]) {
                    int cnt = dfs(e[0], i, e[1] % signalSpeed, signalSpeed, graph);
                    res[i] += pre * cnt;
                    pre += cnt;
                }
            }
        }
        return res;
    }

    private int dfs(int p, int root, int curr, int signalSpeed, List<int[]>[] graph) {
        int res = 0;
        if (curr == 0) {
            res++;
        }
        for (int[] e : graph[p]) {
            int v = e[0];
            int cost = e[1];
            if (v != root) {
                res += dfs(v, p, (curr + cost) % signalSpeed, signalSpeed, graph);
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
