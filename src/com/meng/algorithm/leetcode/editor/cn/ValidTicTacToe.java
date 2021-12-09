package com.meng.algorithm.leetcode.editor.cn;

import java.util.*;

/**
 * 794. 有效的井字游戏
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 *
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = ["O  ","   ","   "]
 * 输出：false
 * 解释：玩家 1 总是放字符 "X" 。
 * 示例 2：
 *
 *
 * 输入：board = ["XOX"," X ","   "]
 * 输出：false
 * 解释：玩家应该轮流放字符。
 * 示例 3：
 *
 *
 * 输入：board = ["XXX","   ","OOO"]
 * 输出：false
 * Example 4:
 *
 *
 * 输入：board = ["XOX","O O","XOX"]
 * 输出：true
 *
 *
 * 提示：
 *
 * board.length == 3
 * board[i].length == 3
 * board[i][j] 为 'X'、'O' 或 ' '
 */
public class ValidTicTacToe {

    int[][] indexs = new int[3][3];

    /**
     * 枚举法，时间较慢
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 13.61%
     * 的用户
     * 内存消耗：
     * 36 MB
     * , 在所有 Java 提交中击败了
     * 10.06%
     * 的用户
     * 通过测试用例：
     * 109 / 109
     * @param board
     * @return
     */
    public boolean validTicTacToe(String[] board) {
        Map<Character, List<int[]>> cache = new HashMap<>();
        cache.put('X',new ArrayList<>());
        cache.put('O',new ArrayList<>());

        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                char c = board[i].charAt(j);
                if(c == ' '){
                    continue;
                }
                List<int[]> temp = cache.get(c);
                temp.add(new int[]{i,j});
                cache.put(c,temp);
                if (c == 'O'){
                    indexs[i][j] = 2;
                }else {
                    indexs[i][j] = 1;
                }
            }
        }
        //1.O > X ,false
        if (cache.get('O').size() > cache.get('X').size()){
            return false;
        }
        if (cache.get('X').size() - cache.get('O').size() > 1){
            return false;
        }
        //X < 3 .true
        if (cache.get('X').size() < 3){
            return true;
        }
        //X == O ,X是否成行
        if (cache.get('O').size() == cache.get('X').size()){
            if (check(cache.get('X'),1)){
                return false;
            }
            return true;
        }
        //X > O ,O是否成行
        if (cache.get('O').size() < cache.get('X').size()){
            if (check(cache.get('O'),2)){
                return false;
            }
            return true;
        }
        return true;
    }

    private boolean check(List<int[]> list, int num) {
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        for(int i = 0 ; i <= list.size() - 3 ; i++){
            int[] ints = list.get(i);
            int x = ints[0],y=ints[1];
            if (x == 0){
                if (y==0){
                    if ((indexs[0][1] == num && indexs[0][2]==num)||(indexs[1][0]==num && indexs[2][0]==num)||(indexs[1][1]==num&&indexs[2][2]==num)){
                        return true;
                    }
                    return false;
                }else if (y == 1){
                    if (indexs[1][1]==num && indexs[2][2]==num){
                        return true;
                    }
                    return false;
                }else {
                    if ((indexs[1][2]==num && indexs[2][2]==num)||(indexs[1][1]==num && indexs[2][0]==num)){
                        return true;
                    }
                    return false;
                }
            }
            if (indexs[x][0] == num && indexs[x][1] == num && indexs[x][2]==num){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 方法一：分类讨论
     * 思路
     *
     * 题目要求判断当前游戏板是否生效，我们思考游戏板生效的规则：
     *
     * 玩家轮流将字符放入空位 \texttt{" "}" " 中。第一个玩家总是放字符 \texttt{"X"}"X"，且第二个玩家总是放字符 \texttt{"O"}"O"。因为第一个玩家总是先手，这就要求游戏板中字符 \texttt{"X"}"X" 的数量一定是大于等于字符 \texttt{"O"}"O" 的数量。
     * \texttt{"X"}"X" 和 \texttt{"O"}"O" 只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有 33 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。当所有位置非空时，也算为游戏结束。如果游戏结束，玩家不允许再放置字符，不可能能出现二者同时获胜的情况，因此游戏板上不可能同时出现 33 个 \texttt{"X"}"X" 在一行和 33 个 \texttt{"O"}"O" 在另一行。
     * 获胜的玩家一定是在自己放棋后赢得比赛，赢得比赛后，立马停止放置字符。
     * 如果第一个玩家获胜，由于第一个玩家是先手，则次数游戏板中 \texttt{"X"}"X" 的数量比 \texttt{"O"}"O" 的数量多 11。
     * 如果第二个玩家获胜，则 \texttt{"X"}"X" 的数量与 \texttt{"O"}"O" 的数量相同。
     * 以上条件包含了游戏板生效的全部情况，可以通过反证法验证上面分类条件的正确性。在合法的游戏板，只能有 33 种结果合法，要么没有任何玩家赢，要么玩家一赢，要么玩家二赢。我们可以通过检查两种棋的数量关系即可验证是否有效，同时我们要检测是否存在两个玩家同时赢这种非法情况。
     *
     * 算法实现细节如下:
     *
     * 首先统计游戏板上 \texttt{"X"}"X" 和 \texttt{"O"}"O" 的数量并记录在 \textit{xCount}xCount 和 \textit{oCount}oCount 中，如果不满足 \textit{xCount} \ge \textit{oCount}xCount≥oCount，则此时为非法，直接返回 \texttt{false}false。
     * 然后我们检查是否有玩家是否获胜，我们检查在棋盘的 33 行，33 列和 22 条对角线上是否有该玩家的连续 33 枚棋子。我们首先检测玩家一是否获胜，如果玩家一获胜,则检查 \textit{xCount}xCount 是否等于 \textit{oCount} + 1oCount+1；我们继续检测玩家二是否获胜，如果玩家二获胜，则检查 \textit{xCount}xCount 是否等于 \textit{oCount}oCount。
     * 对于特殊情况如果两个玩家都获胜，是否可以检测出该非法情况？如果同时满足两个玩家都获胜，则 \texttt{"X"}"X" 和 \texttt{"O"}"O" 数量的合法的组合可能为 (3,3),(4,3),(4,4),(5,4)(3,3),(4,3),(4,4),(5,4)，对于 (3,3),(4,4)(3,3),(4,4) 不满足玩家一获胜的检测条件，对于 (4,3),(5,4)(4,3),(5,4) 满足玩家一获胜的检测条件但不满足玩家二的获胜条件。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state/solution/you-xiao-de-jing-zi-you-xi-by-leetcode-s-c1j3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param board
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.4 MB
     * , 在所有 Java 提交中击败了
     * 98.22%
     * 的用户
     * 通过测试用例：
     * 109 / 109
     */
    public boolean validTicTacToe1(String[] board) {
        int xCount = 0, oCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                xCount = (c == 'X') ? (xCount + 1) : xCount;
                oCount = (c == 'O') ? (oCount + 1) : oCount;
            }
        }
        if (oCount != xCount && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'X') && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'O') && oCount != xCount) {
            return false;
        }
        return true;
    }

    public boolean win(String[] board, char p) {
        for (int i = 0; i < 3; ++i) {
            if (p == board[0].charAt(i) && p == board[1].charAt(i) && p == board[2].charAt(i)) {
                return true;
            }
            if (p == board[i].charAt(0) && p == board[i].charAt(1) && p == board[i].charAt(2)) {
                return true;
            }
        }
        if (p == board[0].charAt(0) && p == board[1].charAt(1) && p == board[2].charAt(2)) {
            return true;
        }
        if (p == board[0].charAt(2) && p == board[1].charAt(1) && p == board[2].charAt(0)) {
            return true;
        }
        return false;
    }

}
