package com.meng.interview150.leetcode.editor.cn;

class Interview105Exist {
    boolean[][] visited;
    int[][] indexs = {{-1,0},{1,0},{0,-1},{0,1}};

    /**
     * 解答成功:
     * 	执行耗时:191 ms,击败了33.41% 的Java用户
     * 	内存消耗:40.6 MB,击败了57.35% 的Java用户
     * @param board
     * @param word
     * @return
     */
    public boolean existMy(char[][] board, String word) {
        int m = board.length,n = board[0].length;
        if (m*n < word.length()){
            return false;
        }
        visited = new boolean[m][n];
        //记录不可抵达的路径
        for(int i = 0 ; i < m ; i ++){
            for(int j = 0 ; j < n ; j ++){
                if(board[i][j] == word.charAt(0)){
                    visited[i][j] = true;
                    if(dfs(board,i,j,word,1)){
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int x, int y, String word, int index) {
        //找到数据
        if (index == word.length()){
            return true;
        }
        for(int[] indexs : indexs){
            int newX = x + indexs[0];
            int newY = y + indexs[1];
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length &&
                    !visited[newX][newY] && board[newX][newY] == word.charAt(index)){
                visited[newX][newY] = true;
                if(dfs(board,newX,newY,word,index+1)){
                    return true;
                }
                visited[newX][newY] = false;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:273 ms,击败了17.21% 的Java用户
     * 	内存消耗:44.2 MB,击败了5.08% 的Java用户
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }
}
