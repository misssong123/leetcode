package com.meng.algorithmfundamentals.eighth;

import java.util.*;

/**
 * 1091. 二进制矩阵中的最短路径
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
    int res = Integer.MAX_VALUE;
    int[] X = {-1,0,1,1,1,0,-1,-1};
    int[] Y = {1,1,1,0,-1,-1,-1,0};
    //超出时间限制
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]!= 0){
            return -1;
        }
        int n = grid.length;
        boolean[][] flags = new boolean[n][n];
        flags[0][0] = true;
        dfs(0,0,n,1,flags,grid);
        return res==Integer.MAX_VALUE?-1:res;
    }

    private void dfs(int x, int y,int n,int num, boolean[][] flags,int[][] grid) {
        if (x == n-1 && y==n-1){
            res = Math.min(res,num);
            return;
        }
        for(int i = 0 ; i < 8 ; i++){
            int newX = x + X[i];
            int newY = y + Y[i];
            if (newX >=0 && newX < n && newY>=0 && newY < n && grid[newX][newY] == 0  && !flags[newX][newY]){
                flags[newX][newY] = true;
                dfs(newX,newY,n,num+1,flags,grid);
                flags[newX][newY] = false;
            }
        }
    }

    /**
     * Node表示Location(x,y)。数组dx、dy表示：上下左右， 左上右上左下右下 八个方向坐标变化。
     * BFS即宽度优先搜索，这样的搜索树，是一层层地。从出发点开始，第一次遍历到终点时过的那条路径就是最短的路径。因为这条路径没有多绕一个不相关节点，所以它是最短的,也符合题目最短畅通路径的长度。BFS这类也是盲目搜索，下面介绍启发式搜索算法。
     *
     * 作者：suny817
     * 链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/solution/bfsa-qi-fa-sou-suo-duo-chong-fang-fa-you-jrqp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 65.48%
     * 的用户
     * 内存消耗：
     * 42.8 MB
     * , 在所有 Java 提交中击败了
     * 20.50%
     * 的用户
     * 通过测试用例：
     * 88 / 88
     */
    static class Node {
        int x;
        int y;
        int step;

        public Node(int start, int end, int step) {
            this.x = start;
            this.y = end;
            this.step = step;
        }
    }

    int[] dx = {0, 0, -1, 1,-1, 1,-1, 1};
    int[] dy = {-1, 1, 0, 0, -1,-1, 1, 1};

    public int shortestPathBinaryMatrix1(int[][] grid) {

        Node node = new Node(0, 0, 2);
        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(node);

        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        } else if (n <= 2) {
            return n;
        }
        while (!queue.isEmpty()){
            Node cur = queue.removeFirst();
            int x = cur.x;
            int y = cur.y;
            int step = cur.step;
            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(0<=newX && newX<n && 0<=newY && newY<n && grid[newX][newY] == 0){
                    //找到终点
                    if(newX == n-1 && newY == n-1){
                        return step ;
                    }
                    queue.addLast(new Node(newX, newY, step + 1));
                    grid[newX][newY] = 1; //标记已遍历过，避免重复
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix demo = new ShortestPathBinaryMatrix();
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(demo.shortestPathBinaryMatrix(grid));
    }
}
