package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class CheckMove1958 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了21.21% 的Java用户
     * @param board
     * @param rMove
     * @param cMove
     * @param color
     * @return
     */
    public boolean checkMoveMy(char[][] board, int rMove, int cMove, char color) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},{1, 1},{1, -1}, {-1, 1}, {-1, -1}};
        for (int[] direction : directions){
            if(isValid(board, rMove+direction[0], cMove+direction[1], direction,color)){
                return true;
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int rMove, int cMove, int[] direction, char color) {
        if (rMove < 0 || cMove <0 || rMove >=8 || cMove >= 8 || board[rMove][cMove] == '.' || board[rMove][cMove] == color){
            return false;
        }
        int flag = 1;
        int x =  rMove;
        int y = cMove;
        while (true){

            if (x < 0 || y <0 || x >=8 || y >= 8 || board[x][y] == '.'){
                return false;
            }
            if (board[x][y] == color){
                if (flag != 1){
                    return true;
                }
            }else {
                flag = 2;
            }
            x  = x + direction[0];
            y = y + direction[1];
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了93.94% 的Java用户
     * @param board
     * @param rMove
     * @param cMove
     * @param color
     * @return
     */
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        // 从 x 轴正方向开始逆时针枚举 8 个方向
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};   // 行改变量
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};   // 列改变量
        for (int k = 0; k < 8; ++k) {
            if (check(board, rMove, cMove, color, dx[k], dy[k])) {
                return true;
            }
        }
        return false;
    }

    // 判断每个方向是否存在以操作位置为起点的好线段
    public boolean check(char[][] board, int rMove, int cMove, char color, int dx, int dy) {
        int x = rMove + dx;
        int y = cMove + dy;
        int step = 1;   // 当前遍历到的节点序号
        while (x >= 0 && x < 8 && y >= 0 && y < 8) {
            if (step == 1) {
                // 第一个点必须为相反颜色
                if (board[x][y] == '.' || board[x][y] == color) {
                    return false;
                }
            } else {
                // 好线段中不应存在空格子
                if (board[x][y] == '.') {
                    return false;
                }
                // 遍历到好线段的终点，返回 true
                if (board[x][y] == color) {
                    return true;
                }
            }
            ++step;
            x += dx;
            y += dy;
        }
        // 不存在符合要求的好线段
        return false;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
