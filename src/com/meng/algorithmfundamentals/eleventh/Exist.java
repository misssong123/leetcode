package com.meng.algorithmfundamentals.eleventh;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *
 *
 * 提示：
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class Exist {
    int[] X = {0,0,1,-1};
    int[] Y = {1,-1,0,0};
    boolean flag = false;
    int m = 0 , n = 0;
    boolean[][] flags = null;

    /**
     * 执行用时：
     * 107 ms
     * , 在所有 Java 提交中击败了
     * 50.58%
     * 的用户
     * 内存消耗：
     * 39.5 MB
     * , 在所有 Java 提交中击败了
     * 34.63%
     * 的用户
     * 通过测试用例：
     * 83 / 83
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        m = board.length;
        n = board[0].length;
        flags = new boolean[m][n];
        for(int i = 0 ; i < m ; i ++){
            for(int j = 0 ; j < n ; j++){
                dfs(0,i,j,chars,chars.length,board);
                if (flag){
                    return true;
                }
            }
        }
        return false;
    }

    private void dfs(int index, int x, int y, char[] chars,int size,char[][] board) {
        if (flag){
            return;
        }
        if (board[x][y] != chars[index]){
            return;
        }else if (index == size-1){
            flag = true;
            return;
        }
        flags[x][y] = true;
        for(int i = 0 ; i < 4 ;i++){
            int newX = x + X[i];
            int newY = y + Y[i];
            if (newX >=0 && newX < m && newY >=0 && newY < n && !flags[newX][newY]){
                dfs(index+1,newX,newY,chars,size,board);
            }
        }
        flags[x][y] = false;
    }

    public static void main(String[] args) {
        Exist demo = new Exist();
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        System.out.println(demo.exist(board,"ABCESEEEFS"));
    }

    /**
     *方法一：回溯
     * 思路与算法
     *
     * 设函数 \text{check}(i, j, k)check(i,j,k) 表示判断以网格的 (i, j)(i,j) 位置出发，能否搜索到单词 \textit{word}[k..]word[k..]，其中 \textit{word}[k..]word[k..] 表示字符串 \textit{word}word 从第 kk 个字符开始的后缀子串。如果能搜索到，则返回 \texttt{true}true，反之返回 \texttt{false}false。函数 \text{check}(i, j, k)check(i,j,k) 的执行步骤如下：
     *
     * 如果 \textit{board}[i][j] \neq s[k]board[i][j]
     * 
     * ​
     *  =s[k]，当前字符不匹配，直接返回 \texttt{false}false。
     * 如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时直接返回 \texttt{true}true。
     * 否则，遍历当前位置的所有相邻位置。如果从某个相邻位置出发，能够搜索到子串 \textit{word}[k+1..]word[k+1..]，则返回 \texttt{true}true，否则返回 \texttt{false}false。
     * 这样，我们对每一个位置 (i,j)(i,j) 都调用函数 \text{check}(i, j, 0)check(i,j,0) 进行检查：只要有一处返回 \texttt{true}true，就说明网格中能够找到相应的单词，否则说明不能找到。
     *
     * 为了防止重复遍历相同的位置，需要额外维护一个与 \textit{board}board 等大的 \textit{visited}visited 数组，用于标识每个位置是否被访问过。每次遍历相邻位置时，需要跳过已经被访问的位置。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/word-search/solution/dan-ci-sou-suo-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param board
     * @param word
     * @return
     * 执行用时：
     * 165 ms
     * , 在所有 Java 提交中击败了
     * 25.23%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 5.10%
     * 的用户
     * 通过测试用例：
     * 83 / 83
     */
    public boolean exist1(char[][] board, String word) {
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
