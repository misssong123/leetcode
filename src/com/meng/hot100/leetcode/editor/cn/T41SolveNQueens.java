package com.meng.hot100.leetcode.editor.cn;

import java.util.*;

class T41SolveNQueens {
    List<List<String>> res = null;
    List<String> temp = null;

    /**
     * 执行用时分布
     * 10
     * ms
     * 击败
     * 6.58%
     * 复杂度分析
     * 消耗内存分布
     * 43.98
     * MB
     * 击败
     * 23.53%
     * @param n
     * @return
     */
    public List<List<String>> solveNQueensMy(int n) {
        if(n == 1){
            return new ArrayList<>(Arrays.asList(Arrays.asList("Q")));
        }
        res = new ArrayList<>();
        temp = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        //初始化
        for (int i = 0; i < 3; i++) {
            map.put(i, new HashSet<>());
        }
        if (n > 3){
            dfs(0,n,map);
        }
        return res;
    }

    private void dfs(int x, int n, Map<Integer, Set<Integer>> map) {
        if(x == n){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int y = 0 ; y < n ; y++){
            //检查通过
            if (check(x,y,map)){
                add(x,y,map);
                temp.add(getStr(y,n));
                dfs(x+1,n,map);
                temp.remove(temp.size()-1);
                del(x,y,map);
            }
        }
    }
    private String getStr(int y, int n){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            if(i == y){
                sb.append("Q");
            }else{
                sb.append(".");
            }
        }
        return sb.toString();
    }
    private boolean check(int x,int y,Map<Integer, Set<Integer>> map){
        for(int i = 0 ; i < 3 ; i ++){
            switch (i){
                case 0:
                    if(map.get(i).contains(y)){
                        return false;
                    }
                    break;
                case 1:
                    if(map.get(i).contains(x-y)){
                        return false;
                    }
                    break;
                case 2:
                    if(map.get(i).contains(x+y)){
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
    private void add(int x,int y,Map<Integer, Set<Integer>> map){
        map.get(0).add(y);
        map.get(1).add(x-y);
        map.get(2).add(x+y);
    }
    private void del(int x,int y,Map<Integer, Set<Integer>> map){
        map.get(0).remove(y);
        map.get(1).remove(x-y);
        map.get(2).remove(x+y);
    }
    /**
     * 执行用时分布
     * 5
     * ms
     * 击败
     * 35.83%
     * 复杂度分析
     * 消耗内存分布
     * 43.97
     * MB
     * 击败
     * 25.21%
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
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
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

}
