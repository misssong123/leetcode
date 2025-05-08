package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class MinTimeToReach3342 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    /**
     * 解答成功:
     * 	执行耗时:309 ms,击败了22.86% 的Java用户
     * 	内存消耗:106.6 MB,击败了29.52% 的Java用户
     * @param moveTime
     * @return
     */
    public int minTimeToReach3342(int[][] moveTime) {
        int n = moveTime.length,m = moveTime[0].length;
        int[][]dis = new int[n][m];
        //填充最大值
        for(int[] d : dis){
            Arrays.fill(d,Integer.MAX_VALUE);
        }
        //初始化起点
        dis[0][0] = 0;
        //记录可出发的节点
        PriorityQueue<int[]> cache = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        cache.add(new int[]{0,0,0});
        while (!cache.isEmpty()){
            int[] poll = cache.poll();
            int x = poll[0],y = poll[1],d = poll[2];
            //到达终点
            if(x == n - 1 && y == m -1){
                return d;
            }
            //当前节点已经计算过，无需处理
            if (d > dis[x][y]){
                continue;
            }
            int time = (x +y) % 2 +1;
            //遍历新的起点
            for(int[] dir : dirs){
                int newX = x + dir[0],newY = y + dir[1];
                if (newX >= 0 && newX < n && newY>=0 && newY <m){
                    int newD = Math.max(dis[x][y],moveTime[newX][newY]) + time;
                    if (newD < dis[newX][newY]) {
                        dis[newX][newY] = newD;
                        cache.add(new int[]{newX,newY,newD});
                    }
                }
            }
        }
        return dis[n-1][m-1];
    }
    private final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 解答成功:
     * 	执行耗时:212 ms,击败了59.05% 的Java用户
     * 	内存消耗:106.2 MB,击败了72.38% 的Java用户
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
            int time = (i + j) % 2 + 1;
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
