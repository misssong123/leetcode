package com.meng.top100.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class Solve130 {
    private final static int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.74% 的Java用户
     * 	内存消耗:46.9 MB,击败了10.34% 的Java用户
     * @param board
     */
    public void solve130(char[][] board) {
        int m = board.length,n = board[0].length;
        //左边界，右边界
        for (int i = 0 ; i < m ; i++){
            if(board[i][0] == 'O'){
                dfs(board,i,0);
            }
            if(board[i][n-1] == 'O'){
                dfs(board,i,n-1);
            }
        }
        //上边界，下边界
        for (int i = 0 ; i < n ; i++){
            if(board[0][i] == 'O'){
                dfs(board,0,i);
            }
            if(board[m-1][i] == 'O'){
                dfs(board,m-1,i);
            }
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 'P'){
                    board[i][j] = 'O';
                }else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O'){
            return;
        }
        board[x][y] = 'P';
        for(int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            dfs(board,nx,ny);
        }
    }
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了20.59% 的Java用户
     * 	内存消耗:47.2 MB,击败了8.49% 的Java用户
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][n - 1] == 'O') {
                queue.offer(new int[]{i, n - 1});
                board[i][n - 1] = 'A';
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
                board[0][i] = 'A';
            }
            if (board[m - 1][i] == 'O') {
                queue.offer(new int[]{m - 1, i});
                board[m - 1][i] = 'A';
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx < 0 || my < 0 || mx >= m || my >= n || board[mx][my] != 'O') {
                    continue;
                }
                queue.offer(new int[]{mx, my});
                board[mx][my] = 'A';
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
