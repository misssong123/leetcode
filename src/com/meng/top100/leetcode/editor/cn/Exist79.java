package com.meng.top100.leetcode.editor.cn;

class Exist79 {
    private static final int[][] DIR = {{-1,0},{1,0},{0,-1},{0,1}};

    /**
     * 解答成功:
     * 	执行耗时:217 ms,击败了22.07% 的Java用户
     * 	内存消耗:42.1 MB,击败了24.12% 的Java用户
     * @param board
     * @param word
     * @return
     */
    public boolean exist79(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for (int j = 0; j < n; j++) {
                if (dfs79(board,word,visited,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs79(char[][] board, String word, boolean[][] visited, int x, int y, int index) {
        if (index == word.length()){
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y] || board[x][y] != word.charAt(index)){
            return false;
        }
        visited[x][y] = true;
        for (int[] dir : DIR){
            int newX = x + dir[0], newY = y + dir[1];
            if (dfs79(board,word,visited,newX,newY,index+1)){
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:178 ms,击败了38.72% 的Java用户
     * 	内存消耗:42.1 MB,击败了23.41% 的Java用户
     * @param board
     * @param word
     * @return
     */
    public boolean existImprove(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)
                        && dfsImprove(board,word,visited,i,j,1)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsImprove(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
        if (index == word.length()){
            return true;
        }
        visited[i][j] = true;
        for (int[] dir : DIR){
            int newX = i + dir[0], newY = j + dir[1];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                    && !visited[newX][newY] && board[newX][newY] == word.charAt(index)){
                if (dfsImprove(board,word,visited,newX,newY,index+1)){
                    return true;
                }
            }
        }
        return visited[i][j] = false;
    }
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了22.26% 的Java用户
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        // 为了方便，直接用数组代替哈希表
        int[] cnt = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                cnt[c]++;
            }
        }

        // 优化一
        char[] w = word.toCharArray();
        int[] wordCnt = new int[128];
        for (char c : w) {
            if (++wordCnt[c] > cnt[c]) {
                return false;
            }
        }

        // 优化二
        if (cnt[w[w.length - 1]] < cnt[w[0]]) {
            w = new StringBuilder(word).reverse().toString().toCharArray();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(i, j, 0, board, w)) {
                    return true; // 搜到了！
                }
            }
        }
        return false; // 没搜到
    }

    private boolean dfs(int i, int j, int k, char[][] board, char[] word) {
        if (board[i][j] != word[k]) { // 匹配失败
            return false;
        }
        if (k == word.length - 1) { // 匹配成功！
            return true;
        }
        board[i][j] = 0; // 标记访问过
        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1]; // 相邻格子
            if (0 <= x && x < board.length && 0 <= y && y < board[x].length && dfs(x, y, k + 1, board, word)) {
                return true; // 搜到了！
            }
        }
        board[i][j] = word[k]; // 恢复现场
        return false; // 没搜到
    }
}
