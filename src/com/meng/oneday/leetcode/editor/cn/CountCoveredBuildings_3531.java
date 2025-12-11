package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CountCoveredBuildings_3531 {
    /**
     * 解答成功:
     * 	执行耗时:145 ms,击败了40.91% 的Java用户
     * 	内存消耗:271.8 MB,击败了13.64% 的Java用户
     * @param n
     * @param buildings
     * @return
     */
    public int countCoveredBuildings3531(int n, int[][] buildings) {
        //列存储
        Map<Integer,int[]> rowMap = new HashMap<>();
        //行存储
        Map<Integer,int[]> colMap = new HashMap<>();
        for (int[] building : buildings) {
            int row = building[0];
            int col = building[1];
            rowMap.computeIfAbsent(row, k -> new int[2]);
            colMap.computeIfAbsent(col, k -> new int[2]);
            int[] rowArr = rowMap.get(row);
            int[] colArr = colMap.get(col);
            if (rowArr[0] == 0){
                rowArr[0] = rowArr[1]= col;
            }else{
                if (rowArr[0] > col){
                    rowArr[0] = col;
                }else if (rowArr[1] < col){
                    rowArr[1] = col;
                }
            }
            if (colArr[0] == 0){
                colArr[0] = colArr[1] = row;
            }else{
                if (colArr[0] > row){
                    colArr[0] = row;
                }else if (colArr[1] < row){
                    colArr[1] = row;
                }
            }
        }
        int res = 0;
        for (int[] building : buildings) {
            int[] rowArr = rowMap.get(building[0]);
            int[] colArr = colMap.get(building[1]);
            if (building[1] > rowArr[0] && building[1] < rowArr[1] && building[0] > colArr[0] && building[0] < colArr[1]){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了90.91% 的Java用户
     * 	内存消耗:210.5 MB,击败了31.82% 的Java用户
     * @param n
     * @param buildings
     * @return
     */
    public int countCoveredBuildings(int n, int[][] buildings) {
        int[] rowMin = new int[n + 1];
        int[] rowMax = new int[n + 1];
        int[] colMin = new int[n + 1];
        int[] colMax = new int[n + 1];
        Arrays.fill(rowMin, n + 1);
        Arrays.fill(colMin, n + 1);

        for (int[] p : buildings) {
            int x = p[0], y = p[1];
            rowMin[y] = Math.min(rowMin[y], x);
            rowMax[y] = Math.max(rowMax[y], x);
            colMin[x] = Math.min(colMin[x], y);
            colMax[x] = Math.max(colMax[x], y);
        }

        int ans = 0;
        for (int[] p : buildings) {
            int x = p[0], y = p[1];
            if (rowMin[y] < x && x < rowMax[y] && colMin[x] < y && y < colMax[x]) {
                ans++;
            }
        }
        return ans;
    }

}
