package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class CountBattleships419 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了35.20% 的Java用户
     * 	内存消耗:44.4 MB,击败了8.66% 的Java用户
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length,res = 0;
        for(int i = 0;i < m;i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 'X'){
                    if ((i>0&&board[i-1][j] == 'X')||(j>0&&board[i][j-1] == 'X')){
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.2 MB,击败了45.53% 的Java用户
     * @param board
     * @return
     */
    public int countBattleshipsOfficial(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
