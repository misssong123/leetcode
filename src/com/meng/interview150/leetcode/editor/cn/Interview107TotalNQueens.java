package com.meng.interview150.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class Interview107TotalNQueens {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了78.10% 的Java用户
     * 	内存消耗:39.3 MB,击败了82.92% 的Java用户
     * @param n
     * @return
     */
    boolean[] used;
    boolean[][] cols;
    public int totalNQueensMy(int n) {
        used = new boolean[n];
        cols = new boolean[n][n];
        return dfs(n,0,-2);
    }

    private int dfs(int n, int index, int pre) {
        if (n == index){
            return 1;
        }
        int temp = 0;
        for (int i = 0; i < n; i++) {
            //当前行未使用过，且与上一行不相连
            if (!used[i]&&Math.abs(i-pre)!=1){
                //对角线判断
                if (judge(index,i,n,cols)){
                    used[i] = true;
                    cols[index][i] = true;
                    temp += dfs(n,index+1,i);
                    used[i] = false;
                    cols[index][i] = false;
                }

            }
        }
        return temp;
    }

    private boolean judge(int x, int y, int n, boolean[][] cols) {
        for(int i = 2; i <= x ; i++){
            if(y - i >= 0 && cols[x-i][y-i]){
                return false;
            }
            if (y + i < n && cols[x-i][y+i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了36.13% 的Java用户
     * 	内存消耗:40.5 MB,击败了14.72% 的Java用户
     * @param n
     * @return
     */
    public int totalNQueens1(int n) {
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        return backtrack(n, 0, columns, diagonals1, diagonals2);
    }

    public int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrack(n, row + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了72.32% 的Java用户
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        return solve(n, 0, 0, 0, 0);
    }

    public int solve(int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                count += solve(n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
            }
            return count;
        }
    }
}
