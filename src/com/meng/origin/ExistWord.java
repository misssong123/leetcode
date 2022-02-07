package com.meng.origin;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 *
 *
 * 提示：
 *
 *     board 和 word 中只包含大写和小写英文字母。
 *     1 <= board.length <= 200
 *     1 <= board[i].length <= 200
 *     1 <= word.length <= 10^3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ExistWord {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'a'}};
        String word = "a";
        ExistWord demo = new ExistWord();
        System.out.println(demo.exist(board,word));
    }
    //记录当前单词是否使用过
    boolean[][] flags = null;
    //记录最终结果
    boolean flag = false;
    public boolean exist(char[][] board, String word) {
        flags = new boolean[board.length][board[0].length];
        dfs(board,0,0,0,word);
        return flag;
    }

    /**
     *
     * @param board
     * @param i
     * @param j
     * @param index 当前匹配的单词下标
     * @param word
     */
    private void dfs(char[][] board, int i, int j, int index, String word) {
        if (i < 0 || j<0 || i >= board.length || j>=board[i].length){
            if (index == word.length())
                //匹配完成
                flag = true ;
            return;
        }
        else {
            if (index == word.length()){
                //匹配完成
                flag = true ;
                return;
            }
            //第一个位置特殊
            if (index == 0 ){
                for (int m = i;m<board.length;m++){
                    for (int n = j ; n<board[m].length;n++){
                        recuiver(board,m,n,index,word);
                    }
                }
            }else
                recuiver(board,i,j,index,word);
        }
    }

    private void recuiver(char[][] board, int m, int n, int index, String word) {
        if(isExists(board,m,n,index,word)){
            //将当前位置变为已访问
            flags[m][n] = true;

            dfs(board,m,n+1,index+1,word);
            dfs(board,m+1,n,index+1,word);
            dfs(board,m,n-1,index+1,word);
            dfs(board,m-1,n,index+1,word);
            //将当前位置变为未访问
            flags[m][n] = false;
        }
    }

    private boolean isExists(char[][] board, int m, int n, int index, String word) {
        return (board[m][n]+"").equals(word.substring(index,index+1))  && !flags[m][n];
    }
}
