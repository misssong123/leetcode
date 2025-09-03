package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinimumTime2050 {
    /**
     * 解答成功:
     * 	执行耗时:2492 ms,击败了5.03% 的Java用户
     * 	内存消耗:70.5 MB,击败了26.81% 的Java用户
     * @param n
     * @param relations
     * @param time
     * @return
     */
    public int minimumTimeMy(int n, int[][] relations, int[] time) {
        int[] outDegree = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        int[] costTime = new int[n];
        for(int [] relation : relations){
            outDegree[relation[1]-1]++;
            if (graph[relation[0]-1] == null){
                graph[relation[0]-1] = new ArrayList<>();
            }
            graph[relation[0]-1].add(relation[1]-1);
        }
        int cnt = 0;
        while (cnt < n){
            for(int i = 0 ; i < n ; i++){
                //当前课程可以被处理
                if (outDegree[i] == 0){
                    if (graph[i] != null){
                        int curTime = costTime[i] + time[i];
                        for (int j : graph[i]){
                            outDegree[j]--;
                            costTime[j] = Math.max(costTime[j], curTime);
                        }
                    }
                    //当前课程出度为0，出度标记为-1
                    outDegree[i] = -1;
                    cnt++;
                }
            }
        }
        int max = 0;
        for(int i = 0 ; i < n ; i++){
            max = Math.max(max, costTime[i] + time[i]);
        }
        return max;

    }
    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了40.78% 的Java用户
     * 	内存消耗:61.9 MB,击败了55.86% 的Java用户
     * @param n
     * @param relations
     * @param time
     * @return
     */
    public int minimumTime2050(int n, int[][] relations, int[] time) {
        int[] outDegree = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        int[] costTime = new int[n];
        for(int [] relation : relations){
            outDegree[relation[1]-1]++;
            if (graph[relation[0]-1] == null){
                graph[relation[0]-1] = new ArrayList<>();
            }
            graph[relation[0]-1].add(relation[1]-1);
        }
        List<Integer> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            if (outDegree[i] == 0){
                list.add(i);
            }
        }
        while (!list.isEmpty()){
            for(int i : list){
                if (graph[i] != null){
                    int curTime = costTime[i] + time[i];
                    for (int j : graph[i]){
                        outDegree[j]--;
                        if (outDegree[j] == 0){
                            temp.add(j);
                        }
                        costTime[j] = Math.max(costTime[j], curTime);
                    }
                }
            }
            list.clear();
            list.addAll(temp);
            temp.clear();
        }
        int max = 0;
        for(int i = 0 ; i < n ; i++){
            max = Math.max(max, costTime[i] + time[i]);
        }
        return max;
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了40.78% 的Java用户
     * 	内存消耗:58.8 MB,击败了100.00% 的Java用户
     * @param n
     * @param relations
     * @param time
     * @return
     */
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        int[] deg = new int[n];
        for (int[] r : relations) {
            int x = r[0] - 1, y = r[1] - 1;
            g[x].add(y);
            deg[y]++;
        }
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++)
            if (deg[i] == 0) // 没有先修课
                q.add(i);
        int[] f = new int[n];
        int ans = 0;
        while (!q.isEmpty()) {
            int x = q.poll(); // x 出队，意味着 x 的所有先修课都上完了
            f[x] += time[x]; // 加上当前课程的时间，就得到了最终的 f[x]
            ans = Math.max(ans, f[x]);
            for (int y : g[x]) { // 遍历 x 的邻居 y
                f[y] = Math.max(f[y], f[x]); // 更新 f[y] 的所有先修课程耗时的最大值
                if (--deg[y] == 0) // y 的先修课已上完
                    q.add(y);
            }
        }
        return ans;
    }

}
