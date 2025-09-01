package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class IsValidSudoku36 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了17.63% 的Java用户
     * 	内存消耗:43.6 MB,击败了32.83% 的Java用户
     * @param board
     * @return
     */
    public boolean isValidSudoku36(char[][] board) {
        //行
        Set[] rows = new HashSet[9];
        //列
        Set[] lines = new HashSet[9];
        //块
        Set[] blocks = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet();
            lines[i] = new HashSet();
            blocks[i] = new HashSet();
        }
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                char c = board[i][j];
                if(c == '.'){
                    continue;
                }
                if(rows[i].contains(c) || lines[j].contains(c) || blocks[(i/3)*3+j/3].contains(c)){
                    return false;
                }
                rows[i].add(c);
                lines[j].add(c);
                blocks[(i/3)*3+j/3].add(c);
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.86% 的Java用户
     * 	内存消耗:43.5 MB,击败了54.20% 的Java用户
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowHas = new boolean[9][9]; // rowHas[i][x] 表示 i 行是否有数字 x
        boolean[][] colHas = new boolean[9][9]; // colHas[j][x] 表示 j 列是否有数字 x
        boolean[][][] subBoxHas = new boolean[3][3][9]; // subBoxHas[i'][j'][x] 表示 (i',j') 宫是否有数字 x

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char b = board[i][j];
                if (b == '.') {
                    continue;
                }
                int x = b - '1'; // 字符 '1'~'9' 转成数字 0~8
                if (rowHas[i][x] || colHas[j][x] || subBoxHas[i / 3][j / 3][x]) { // 重复遇到数字 x
                    return false;
                }
                // 标记行、列、宫包含数字 x
                rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = true;
            }
        }

        return true;
    }

}
