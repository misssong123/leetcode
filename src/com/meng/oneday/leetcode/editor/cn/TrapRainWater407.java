package com.meng.oneday.leetcode.editor.cn;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class TrapRainWater407 {
    /**
     * 1.寻找无法存水的表格
     * 2.寻找可以存水的表格
     * 3.寻找存水表格外围的最大值
     * 4.计算存水量
     * 思路有误
     * @param heightMap
     * @return
     */
    private static final int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public int trapRainWater407(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] visited = new boolean[n][m];
        //上侧
        for(int i = 0 ; i < m ; i++){
            dfs(heightMap,visited,0,i);
        }
        //下侧
        for(int i = 0 ; i < m ; i++){
            dfs(heightMap,visited,n-1,i);
        }
        //左侧
        for(int i = 0 ; i < n ; i++){
            dfs(heightMap,visited,i,0);
        }
        //右侧
        for(int i = 0 ; i < n ; i++){
            dfs(heightMap,visited,i,m-1);
        }
        //寻找未被连接的点是否可以蓄水
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(!visited[i][j]){
                    //四周需要存在大于当前节点的值
                    boolean flag = false;
                    //左侧
                    int x = i -1;
                    while (x >= 0 && heightMap[x][j] <= heightMap[i][j]){
                        x--;
                    }
                    if (x <0){
                        flag = true;
                    }
                    if (!flag){
                        //右侧
                        x = i +1;
                        while (x <= n-1 && heightMap[x][j] <= heightMap[i][j]){
                            x++;
                        }
                        if (x > n-1){
                            flag = true;
                        }
                        if (!flag){
                            //上侧
                            int y = j -1;
                            while (y >= 0 && heightMap[i][y] <= heightMap[i][j]){
                                y--;
                            }
                            if (y <0){
                                flag = true;
                            }
                        }
                        if (!flag){
                            //下侧
                            int y = j +1;
                            while (y <= m-1 && heightMap[i][y] <= heightMap[i][j]){
                                y++;
                            }
                            if (y > m-1){
                                flag = true;
                            }
                        }
                        visited[i][j] = flag;
                    }
                }
            }
        }
        //寻找可以储水的表格
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(!visited[i][j]){
                    find(heightMap,visited,m,i,j,set);
                    res += cal(heightMap,set);
                    set.clear();
                }
            }
        }
        return res;
    }

    private int cal(int[][] heightMap, Set<Integer> points) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        int m = heightMap[0].length;
        for(int point : points){
            int x = point / m;
            int y = point % m;
            for (int [] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (!points.contains(newX * m + newY)) {
                    min = Math.min(min,heightMap[newX][newY]);
                }
            }
        }
        for(int point : points){
            int x1 = point / m;
            int y1 = point % m;
            res += Math.max(0,min - heightMap[x1][y1]);
        }
        return res;
    }
    private void find(int[][] heightMap, boolean[][] visited,int m , int x, int y,Set<Integer> points) {
        points.add(x * m + y);
        visited[x][y] = true;
        for (int [] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < heightMap.length && newY >= 0 && newY < heightMap[0].length
                    && !visited[newX][newY]){
                find(heightMap,visited,m,newX,newY,points);
            }
        }

    }
    private void dfs(int[][] heightMap, boolean[][] visited, int x, int y) {
        //当前节点已经处理过
        if (visited[x][y]){
            return;
        }
        visited[x][y] = true;
        for (int [] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < heightMap.length && newY >= 0 && newY < heightMap[0].length
                    && !visited[newX][newY] && heightMap[newX][newY] >= heightMap[x][y]){
                dfs(heightMap,visited,newX,newY);
            }
        }
    }
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了34.06% 的Java用户
     * 	内存消耗:45.4 MB,击败了21.35% 的Java用户
     * @param heightMap
     * @return
     */
    public int trapRainWaterMy(int[][] heightMap) {
        int n = heightMap.length ,m = heightMap[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        //存储外墙
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(i == 0  ||  j == 0 || i == n -1 || j == m -1){
                    queue.offer(new int[]{i , j , heightMap[i][j]});
                    heightMap[i][j] = -1;
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()){
            int[] minPoint = queue.poll();
            int x = minPoint[0] , y = minPoint[1] , h = minPoint[2];
            for(int[] dir : DIRS){
                int nx = x + dir[0] , ny = y + dir[1];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || heightMap[nx][ny] == -1){
                    continue;
                }
                ans += Math.max(h - heightMap[nx][ny] , 0);
                queue.offer(new int[] {nx,ny,Math.max(heightMap[nx][ny],h)});
                heightMap[nx][ny] = -1;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了22.38% 的Java用户
     * 	内存消耗:45.6 MB,击败了12.77% 的Java用户
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.add(new int[]{heightMap[i][j], i, j});
                    heightMap[i][j] = -1; // 标记 (i,j) 访问过
                }
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            int[] t = pq.poll(); // 去掉短板
            int minHeight = t[0], i = t[1], j = t[2]; // minHeight 是木桶的短板
            for (int[] d : DIRS) {
                int x = i + d[0], y = j + d[1]; // (i,j) 的邻居
                if (0 <= x && x < m && 0 <= y && y < n && heightMap[x][y] >= 0) { // (x,y) 没有访问过
                    // 如果 (x,y) 的高度小于 minHeight，那么接水量为 minHeight - heightMap[x][y]
                    ans += Math.max(minHeight - heightMap[x][y], 0);
                    // 给木桶新增一块高为 max(minHeight, heightMap[x][y]) 的木板
                    pq.add(new int[]{Math.max(minHeight, heightMap[x][y]), x, y});
                    heightMap[x][y] = -1; // 标记 (x,y) 访问过
                }
            }
        }
        return ans;
    }
}
