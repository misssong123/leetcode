import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximalPathQuality2065 {
    int max;
    /**
     * 解答成功:
     * 	执行耗时:122 ms,击败了40.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了20.00% 的Java用户
     * @param values
     * @param edges
     * @param maxTime
     * @return
     */
    public int maximalPathQualityMy(int[] values, int[][] edges, int maxTime) {
        max = maxTime;
        List<List<int[]>> cache = new ArrayList<>();
        int n = values.length;
        for (int i = 0; i < n; i++){
            cache.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int x = edge[0], y = edge[1],time = edge[2];
            cache.get(x).add(new int[]{y,time,values[y]});
            cache.get(y).add(new int[]{x,time,values[x]});
        }
        boolean[] flag = new boolean[n];
        flag[0]=true;
        int res = dfs(0,0,values[0],flag,cache);
        return res;
    }

    private int dfs(int index, int sumTime,int sumValue, boolean[] flag, List<List<int[]>> cache) {
        //当前节点为0，记录总的长度
        int res = index == 0 ?sumValue:0;
        for (int[] next : cache.get(index)){
            //大于时间返回
            if (sumTime+next[1] > max){
                continue;
            }
            //使用过
            if (flag[next[0]]){
                res = Math.max(res,dfs(next[0],sumTime+next[1],sumValue,flag,cache));
            }else {
                flag[next[0]] = true;
                res = Math.max(res,dfs(next[0],sumTime+next[1],sumValue+next[2],flag,cache));
                flag[next[0]] = false;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了95.00% 的Java用户
     * 	内存消耗:44.1 MB,击败了35.00% 的Java用户
     * @param values
     * @param edges
     * @param maxTime
     * @return
     */
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int t = e[2];
            g[x].add(new int[]{y, t});
            g[y].add(new int[]{x, t});
        }

        // Dijkstra 算法
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dx = p[0];
            int x = p[1];
            if (dx > dis[x]) { // x 之前出堆过
                continue;
            }
            for (int[] e : g[x]) {
                int y = e[0];
                int newDis = dx + e[1];
                if (newDis < dis[y]) {
                    dis[y] = newDis; // 更新 x 的邻居的最短路
                    pq.offer(new int[]{newDis, y});
                }
            }
        }

        boolean[] vis = new boolean[n];
        vis[0] = true;
        return dfs(0, 0, values[0], vis, g, values, maxTime, dis);
    }

    private int dfs(int x, int sumTime, int sumValue, boolean[] vis, List<int[]>[] g, int[] values, int maxTime, int[] dis) {
        int res = x == 0 ? sumValue : 0;
        for (int[] e : g[x]) {
            int y = e[0];
            int t = e[1];
            // 相比方法一，这里多了 dis[y]
            if (sumTime + t + dis[y] > maxTime) {
                continue;
            }
            if (vis[y]) {
                res = Math.max(res, dfs(y, sumTime + t, sumValue, vis, g, values, maxTime, dis));
            } else {
                vis[y] = true;
                // 每个节点的价值至多算入价值总和中一次
                res = Math.max(res, dfs(y, sumTime + t, sumValue + values[y], vis, g, values, maxTime, dis));
                vis[y] = false; // 恢复现场
            }
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
