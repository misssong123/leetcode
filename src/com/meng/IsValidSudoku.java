package com.meng;

import com.sun.javafx.collections.MappingChange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 *     数字 1-9 在每一行只能出现一次。
 *     数字 1-9 在每一列只能出现一次。
 *     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 示例 1:
 * 输入:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 *
 * 示例 2:
 *
 * 输入:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 *      但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *
 * 说明:
 *
 *     一个有效的数独（部分已被填充）不一定是可解的。
 *     只需要根据以上规则，验证已经填入的数字是否有效即可。
 *     给定数独序列只包含数字 1-9 和字符 '.' 。
 *     给定数独永远是 9x9 形式的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsValidSudoku {
    //记录每一行的存在情况
    List<Integer>[] rows = new ArrayList[9];
    //记录每一列的存在情况
    List<Integer>[] columns = new ArrayList[9];
    //记录每一个小方块存在的情况
    List<Integer>[] ranges = new ArrayList[9];

    public boolean isValidSudoku(char[][] board) {
        //初始化，避免后面出现空异常
        for(int i = 0 ; i<9;i++){
            rows[i] = new ArrayList<>();
            columns[i] = new ArrayList<>();
            ranges[i] = new ArrayList<>();
        }
        for (int i = 0;i<board.length;i++){
            for (int j = 0 ; j < board[i].length;j++){
                if (board[i][j]!='.' && !flag(i,j,board))
                    return false;
            }
        }
        return true;
    }

    private boolean flag(int i, int j, char[][] board) {
        int target = board[i][j]-'0';
        int index = i/3*3+j/3;
        if (rows[i].contains(target) || columns[j].contains(target) || ranges[index].contains(target))
            return false;
        rows[i].add(target);
        columns[j].add(target);
        ranges[index].add(target);
        return true;
    }
}
