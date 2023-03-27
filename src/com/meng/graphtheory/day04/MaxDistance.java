package com.meng.graphtheory.day04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1162. 地图分析(中等)
 * 你现在手里有一份大小为 n x n 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地。
 *
 * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
 *
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
 *
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 不是 0 就是 1
 */
public class MaxDistance {
    public int maxDistance(int[][] grid) {
        return -1;
    }


    /**
     *超时
     * @param grid
     * @return
     */
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    int n;
    int[][] grid;


    public int maxDistance1(int[][] grid) {
        this.n = grid.length;
        this.grid = grid;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, findNearestLand(i, j));
                }
            }
        }
        return ans;
    }

    public int findNearestLand(int x, int y) {
        boolean[][] vis = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{x, y, 0});
        vis[x][y] = true;
        while (!queue.isEmpty()) {
            int[] f = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = f[0] + dx[i], ny = f[1] + dy[i];
                if (!(nx >= 0 && nx < n && ny >= 0 && ny < n)) {
                    continue;
                }
                if (!vis[nx][ny]) {
                    queue.offer(new int[]{nx, ny, f[2] + 1});
                    vis[nx][ny] = true;
                    if (grid[nx][ny] == 1) {
                        return f[2] + 1;
                    }
                }
            }
        }
        return -1;
    }

    /**
     *执行用时：
     * 14 ms
     * , 在所有 Java 提交中击败了
     * 44.49%
     * 的用户
     * 内存消耗：
     * 42.6 MB
     * , 在所有 Java 提交中击败了
     * 58.35%
     * 的用户
     * 通过测试用例：
     * 37 / 37
     * @param grid
     * @return
     */
    public int maxDistance2(int[][] grid) {
        final int INF = 1000000;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int n = grid.length;
        int[][] d = new int[n][n];
        Queue<int[]> queue = new LinkedList<int[]>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                d[i][j] = INF;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] f = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = f[0] + dx[i], ny = f[1] + dy[i];
                if (!(nx >= 0 && nx < n && ny >= 0 && ny < n)) {
                    continue;
                }
                if (d[nx][ny] > d[f[0]][f[1]] + 1) {
                    d[nx][ny] = d[f[0]][f[1]] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, d[i][j]);
                }
            }
        }

        return ans == INF ? -1 : ans;
    }

    /**
     *
     * @param grid
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 98.27%
     * 的用户
     * 内存消耗：
     * 42.5 MB
     * , 在所有 Java 提交中击败了
     * 62.05%
     * 的用户
     * 通过测试用例：
     * 37 / 37
     */
    public int maxDistance3(int[][] grid) {
        final int INF = 1000000;
        int n = grid.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j] = grid[i][j] == 1 ? 0 : INF;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i - 1 >= 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i + 1 < n) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }

        if (ans == INF) {
            return -1;
        } else {
            return ans;
        }
    }


}
