package com.meng.graphtheory.day05;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1091. 二进制矩阵中的最短路径(中等)
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 *
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 *
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 * 示例 2：
 *
 *
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 * 示例 3：
 *
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 *
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 为 0 或 1
 */
public class ShortestPathBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        return -1;
    }

    /**
     *执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 60.65%
     * 的用户
     * 内存消耗：
     * 42.9 MB
     * , 在所有 Java 提交中击败了
     * 39.26%
     * 的用户
     * 通过测试用例：
     * 89 / 89
     */
    int n = 0;
    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    public int shortestPathBinaryMatrix1(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>(Comparator.comparingInt(v -> v.f));
        grid[0][0] = 1;
        pq.offer(new Node(0, 0, 1));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            if (x == n - 1 && y == n - 1) return grid[x][y];
            for (int j = 0; j < 8; j++) {
                int newX = x + dx[j];
                int newY = y + dy[j];

                if (newX < 0 || newX > n - 1 || newY < 0 || newY > n - 1) {
                    continue;
                }
                //注意判断 grid[newX][newY] > grid[x][y] + 1
                if (grid[newX][newY] == 0 || grid[newX][newY] > grid[x][y] + 1) {
                    grid[newX][newY] = grid[x][y] + 1;
                    pq.offer(new Node(newX, newY, grid[newX][newY]));
                }
            }
        }
        return -1;
    }

    public class Node {
        int x, y, f;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.f = distance(x, y, step);
        }

        public int distance(int x, int y, int step) {
            return step + Math.max(n - x - 1, n - y - 1);
        }
    }


}
