package com.meng.algorithmfundamentals.eighth;

import java.util.*;

/**
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 *
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 */
public class Solve {
    /**
     *执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 54.07%
     * 的用户
     * 内存消耗：
     * 43.5 MB
     * , 在所有 Java 提交中击败了
     * 19.63%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] flags = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        for(int i = 0 ; i < m ; i++){
            if (board[i][0] == 'O'){
                queue.add(new int[]{i,0});
                flags[i][0] = true;
            }
            if (board[i][n-1] == 'O'){
                queue.add(new int[]{i,n-1});
                flags[i][n-1] = true;
            }
        }
        for(int i = 1 ; i < n-1 ; i++){
            if (board[0][i] == 'O'){
                queue.add(new int[]{0,i});
                flags[0][i] = true;
            }
            if (board[m-1][i] == 'O'){
                queue.add(new int[]{m-1,i});
                flags[m-1][i] = true;
            }
        }
        int[] X = {0,0,1,-1};
        int[] Y = {1,-1,0,0};
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                int newX = poll[0] + X[i];
                int newY = poll[1] + Y[i];
                if (newX >=0 && newX < m && newY>=0 && newY < n && !flags[newX][newY] && board[newX][newY]=='O'){
                    queue.add(new int[]{newX,newY});
                    flags[newX][newY] = true;
                }
            }
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if (flags[i][j] == true){
                    board[i][j] = 'O';
                }else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        print(board,1);
        Solve demo = new Solve();
        demo.solve(board);
        print(board,1);
    }
    private static  void print(Object object,int index){
        if (index == 1){
            char[][] board = (char[][]) object;
            int len = board.length;
            for(int i = 0 ; i < len ; i++){
                System.out.println(Arrays.toString(board[i]));
            }
        }else {
            boolean[][] board = (boolean[][])object;
            int len = board.length;
            for(int i = 0 ; i < len ; i++){
                System.out.println(Arrays.toString(board[i]));
            }
        }

        System.out.println("----------------------------------------");
    }
    /**
     * 方法一：深度优先搜索
     * 思路及解法
     *
     * 我们可以使用深度优先搜索实现标记操作。在下面的代码中，我们把标记过的字母 O 修改为字母 A。
     * @param board
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.84%
     * 的用户
     * 内存消耗：
     * 43.3 MB
     * , 在所有 Java 提交中击败了
     * 40.04%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     */
    int n, m;
    public void solve1(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    /**
     *方法二：广度优先搜索
     * 思路及解法
     *
     * 我们可以使用广度优先搜索实现标记操作。在下面的代码中，我们把标记过的字母 O 修改为字母 A。
     * @param board
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 56.52%
     * 的用户
     * 内存消耗：
     * 43.1 MB
     * , 在所有 Java 提交中击败了
     * 42.67%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     */
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public void solve2(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][m - 1] == 'O') {
                queue.offer(new int[]{i, m - 1});
                board[i][m - 1] = 'A';
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
                board[0][i] = 'A';
            }
            if (board[n - 1][i] == 'O') {
                queue.offer(new int[]{n - 1, i});
                board[n - 1][i] = 'A';
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
                    continue;
                }
                queue.offer(new int[]{mx, my});
                board[mx][my] = 'A';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

}
