package com.meng.hot100.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class IsValidSudoku36 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了41.87% 的Java用户
     * 	内存消耗:43.7 MB,击败了15.92% 的Java用户
     * @param board
     * @return
     */
    public boolean isValidSudoku36(char[][] board) {
        //行
        Set<Character>[] rows = new HashSet[9];
        //列
        Set<Character>[] cols = new HashSet[9];
        //块
        Set<Character>[] boxes = new HashSet[9];
        //初始化
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for(int i = 0 ; i < 9 ; i++){
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if(num != '.'){
                    if(!rows[i].add(num) || !cols[j].add(num) || !boxes[(i/3)*3 + j/3].add(num)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.88% 的Java用户
     * 	内存消耗:43.6 MB,击败了34.14% 的Java用户
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
