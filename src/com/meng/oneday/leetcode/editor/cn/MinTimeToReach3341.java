package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class MinTimeToReach3341 {
    /**
     * 解答成功:
     * 	执行耗时:271 ms,击败了5.39% 的Java用户
     * 	内存消耗:43.5 MB,击败了97.93% 的Java用户
     */
    int[][] costs = null;
    public int minTimeToReach3341(int[][] moveTime) {
        int n = moveTime.length,m = moveTime[0].length;
        costs = new int[n][m];
        for(int[] cost : costs){
            Arrays.fill(cost,Integer.MAX_VALUE);
        }
        costs[0][0] = 0;
        dfs(moveTime,0,0,n,m);
        return costs[n-1][m-1];
    }

    private void dfs(int[][] moveTime, int x, int y, int n, int m) {
        //左侧移动
        if(y-1>=0){
            int cost = Math.max(costs[x][y],moveTime[x][y-1]) + 1;
            if (cost < costs[x][y-1]){
                costs[x][y-1] = cost;
                dfs(moveTime,x,y-1,n,m);
            }
        }
        //右侧移动
        if (y+1<m){
            int cost = Math.max(costs[x][y],moveTime[x][y+1]) + 1;
            if (cost < costs[x][y+1]){
                costs[x][y+1] = cost;
                dfs(moveTime,x,y+1,n,m);
            }
        }
        //上侧移动
        if (x - 1 >=0){
            int cost = Math.max(costs[x][y],moveTime[x-1][y]) + 1;
            if (cost < costs[x-1][y]){
                costs[x-1][y] = cost;
                dfs(moveTime,x-1,y,n,m);
            }
        }
        //下侧移动
        if (x+1<n){
            int cost = Math.max(costs[x][y],moveTime[x+1][y]) + 1;
            if (cost < costs[x+1][y]){
                costs[x+1][y] = cost;
                dfs(moveTime,x+1,y,n,m);
            }
        }
    }

    private final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了85.89% 的Java用户
     * 	内存消耗:43.7 MB,击败了77.18% 的Java用户
     * @param moveTime
     * @return
     */
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;

        int[][] dis = new int[n][m];
        for (int[] row : dis) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dis[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0, 0});

        while (true) {
            int[] p = pq.poll();
            int d = p[0], i = p[1], j = p[2];
            if (i == n - 1 && j == m - 1) {
                return d;
            }
            if (d > dis[i][j]) {
                continue;
            }
            int time = 1;
            for (int[] q : DIRS) {
                int x = i + q[0], y = j + q[1];
                if (0 <= x && x < n && 0 <= y && y < m) {
                    int newDis = Math.max(d, moveTime[x][y]) + time;
                    if (newDis < dis[x][y]) {
                        dis[x][y] = newDis;
                        pq.add(new int[]{newDis, x, y});
                    }
                }
            }
        }
    }
}
