package com.meng.interview150.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class Interview034IsValidSudoku {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了37.93% 的Java用户
     * 	内存消耗:43.4 MB,击败了47.32% 的Java用户
     * @param board
     * @return
     */
    public boolean isValidSudokuMy(char[][] board) {
        Set<Integer>[] rows = new HashSet[9];
        Set<Integer>[] cols = new HashSet[9];
        Set<Integer>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                if (board[i][j]=='.'){
                    continue;
                }
                int num = board[i][j]-'0';
                if (!rows[i].add(num)){
                    return false;
                }
                if (!cols[j].add(num)){
                    return false;
                }
                int boxIndex = (i / 3) * 3 + j / 3;
                if (!boxes[boxIndex].add(num)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.85% 的Java用户
     * 	内存消耗:43.4 MB,击败了36.18% 的Java用户
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
