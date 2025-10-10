package com.meng.oneday.leetcode.editor.cn;

class NumRookCaptures999 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了65.52% 的Java用户
     * @param board
     * @return
     */
    public int numRookCaptures999(char[][] board) {
        int n = board.length;
        //寻找车的位置
        int x = 0 , y = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 'R'){
                    //车的位置为(i,j)
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        int res = 0;
        //上侧移动
        int x1 = x - 1;
        while (x1 >=0 && board[x1][y] == '.'){
            x1--;
        }
        if (x1 >= 0 && board[x1][y] == 'p'){
            res++;
        }
        //下侧移动
        x1 = x + 1;
        while (x1 < n && board[x1][y] == '.'){
            x1++;
        }
        if (x1 < n && board[x1][y] == 'p'){
            res++;
        }
        //左侧移动
        int y1 = y - 1;
        while (y1 >=0 && board[x][y1] == '.'){
            y1--;
        }
        if (y1 >= 0 && board[x][y1] == 'p'){
            res++;
        }
        //右侧移动
        y1 = y + 1;
        while (y1 < n && board[x][y1] == '.'){
            y1++;
        }
        if (y1 < n && board[x][y1] == 'p'){
            res++;
        }
        return res;
    }

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了65.52% 的Java用户
     * @param board
     * @return
     */
    public int numRookCaptures(char[][] board) {
        final int SIZE = 8;
        int x0 = 0;
        int y0 = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 'R') {
                    x0 = i;
                    y0 = j;
                }
            }
        }
        int ans = 0;
        for (int[] d : DIRS) {
            int x = x0 + d[0];
            int y = y0 + d[1];
            while (0 <= x && x < SIZE && 0 <= y && y < SIZE && board[x][y] == '.') {
                x += d[0];
                y += d[1];
            }
            if (0 <= x && x < SIZE && 0 <= y && y < SIZE && board[x][y] == 'p') {
                ans++;
            }
        }
        return ans;
    }
}
