package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview038GameOfLife {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了45.52% 的Java用户
     * @param board
     */
    public void gameOfLifeMy(int[][] board) {
        int n = board.length, m = board[0].length;
        int[][] temp = new int[n][m];
        int[] x = {0,0,1,-1,-1,-1,1,1};
        int[] y = {1,-1,0,0,-1,1,-1,1};
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int num = 0;
                for(int k = 0; k < 8; k++) {
                    int a = i + x[k];
                    int b = j + y[k];
                    if(a >= 0 && a < n && b >= 0 && b < m&& board[a][b] == 1) {
                        num++;
                    }
                    if (num > 3){
                        break;
                    }
                }
                if (board[i][j] == 1 && (num < 2 || num > 3)) {
                    temp[i][j] = 0;
                } else if (board[i][j] == 0 && num == 3) {
                    temp[i][j] = 1;
                }else {
                    temp[i][j] = board[i][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(temp[i], 0, board[i], 0, m);
        }
    }
    public void gameOfLife(int[][] board) {
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            // 相邻位置的坐标
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // 规则 1 或规则 3
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // -1 代表这个细胞过去是活的现在死了
                    board[row][col] = -1;
                }
                // 规则 4
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    // 2 代表这个细胞过去是死的现在活了
                    board[row][col] = 2;
                }
            }
        }

        // 遍历 board 得到一次更新后的状态
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
