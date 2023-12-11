package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;
class MinimumEffortPath1631 {
    int res = Integer.MAX_VALUE;
    int[][] indexChange = {{0,1},{1,0},{-1,0},{0,-1}};

    /**
     * 超时
     * @param heights
     * @return
     */
    public int minimumEffortPathMy(int[][] heights) {
        int n = heights.length, m = heights[0].length;
        boolean[][] flags = new boolean[n][m];
        flags[0][0] = true;
        dfs(0,0,n,m,0,flags,heights);
        return res;
    }

    private void dfs(int x, int y, int n, int m, int temp, boolean[][] flags,int[][] heights) {
        if (x == n - 1 && y == m -1 ){
            res = Math.min(res,temp);
            return;
        }
        for(int i  = 0 ; i < 4 ; i++){
            int newX = x + indexChange[i][0];
            int newY = y + indexChange[i][1];
            if (newX < 0 || newX >= n || newY < 0 || newY >= m || flags[newX][newY]){
                continue;
            }
            flags[newX][newY] = true;
            int max = Math.max(temp, Math.abs(heights[newX][newY] - heights[x][y]));
            if (res != Integer.MAX_VALUE && max > res){
                continue;
            }
            dfs(newX,newY,n,m,max,flags,heights);
            flags[newX][newY] = false;
        }
    }

    /**
     * 最小路径
     * 解答成功:
     * 	执行耗时:42 ms,击败了84.93% 的Java用户
     * 	内存消耗:43.8 MB,击败了8.05% 的Java用户
     * @param heights
     * @return
     */
    public int minimumEffortPath3(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        pq.offer(new int[]{0, 0, 0});

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (seen[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            seen[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }

        return dist[m * n - 1];
    }


    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 二分查找
     * 解答成功:
     * 	执行耗时:163 ms,击败了6.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了19.93% 的Java用户
     * @param heights
     * @return
     */
    public int minimumEffortPath1(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0, right = 999999, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }
            if (seen[m * n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 并查集
     * 解答成功:
     * 	执行耗时:81 ms,击败了33.59% 的Java用户
     * 	内存消耗:44 MB,击败了6.26% 的Java用户
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<int[]>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int id = i * n + j;
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });

        UnionFind uf = new UnionFind(m * n);
        int ans = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], v = edge[2];
            uf.unite(x, y);
            if (uf.connected(0, m * n - 1)) {
                ans = v;
                break;
            }
        }
        return ans;
    }
}

// 并查集模板
class UnionFind {
    int[] parent;
    int[] size;
    int n;
    // 当前连通分量数目
    int setCount;

    public UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int findset(int x) {
        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
    }

    public boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) {
            return false;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }

    public boolean connected(int x, int y) {
        x = findset(x);
        y = findset(y);
        return x == y;
    }

}

