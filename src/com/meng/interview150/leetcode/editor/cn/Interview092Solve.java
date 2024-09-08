package com.meng.interview150.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class Interview092Solve {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    boolean[][] flag;
    int n,m;

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.71% 的Java用户
     * 	内存消耗:44.3 MB,击败了58.10% 的Java用户
     * @param board
     */
    public void solveMy(char[][] board) {
        n = board.length;m = board[0].length;
        flag = new boolean[n][m];
        //遍历第一行和最后一行
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O' && flag[0][i] == false){
                dfs(board,0,i);
            }
            if (board[n-1][i] == 'O' && flag[n-1][i] == false){
                dfs(board,n-1,i);
            }
        }
        //遍历第一列和最后一列
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O' && flag[i][0] == false) {
                dfs(board, i, 0);
            }
            if (board[i][m-1] == 'O' && flag[i][m-1] == false){
                dfs(board,i,m-1);
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m ; j++){
                if (!flag[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        flag[x][y] = true;
        for (int[] ints : dirs) {
            int nx = x + ints[0];
            int ny = y + ints[1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 'O' && !flag[nx][ny]){
                dfs(board,nx,ny);
            }
        }
    }

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了37.62% 的Java用户
     * 	内存消耗:44.3 MB,击败了54.27% 的Java用户
     * @param board
     */
    public void solve(char[][] board) {
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
