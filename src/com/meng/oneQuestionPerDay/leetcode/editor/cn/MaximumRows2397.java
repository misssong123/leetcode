package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class MaximumRows2397 {
    int res = 0;
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了74.72% 的Java用户
     * 	内存消耗:41 MB,击败了24.72% 的Java用户
     * @param matrix
     * @param numSelect
     * @return
     */
    public int maximumRowsMy(int[][] matrix, int numSelect) {
        int n = matrix.length, m = matrix[0].length;
        int[] preNum = new int[n];
        for(int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < m ; j++){
                if (matrix[i][j] == 1){
                    preNum[i]++;
                }
            }
        }
        List<Integer> selected = new ArrayList<>();
        dfs(matrix, numSelect, 0, selected, preNum);
        return res;
    }

    private void dfs(int[][] matrix, int numSelect, int index, List<Integer> selected, int[] preNum) {
        if (selected.size() == numSelect){
            sum(matrix, selected, preNum);
            return;
        }
        if (selected.size() + (matrix[0].length - index) < numSelect){
            return;
        }
        //不选择当前列
        if (selected.size() + (matrix[0].length - index) >= numSelect){
            dfs(matrix, numSelect, index+1, selected, preNum);
        }
        //选择当前列
        selected.add(index);
        dfs(matrix, numSelect, index+1, selected, preNum);
        selected.remove(selected.size()-1);
    }

    private void sum(int[][] matrix, List<Integer> selected, int[] preNum) {
        int temp = 0;
        for(int k = 0 ; k < matrix.length ; k++){
            int num = 0;
            for(int index : selected){
                if (matrix[k][index]==1){
                    num++;
                }
            }
            if (num == preNum[k]){
                temp++;
            }
        }
        res =  Math.max(res,temp);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了74.72% 的Java用户
     * 	内存消耗:40.3 MB,击败了30.34% 的Java用户
     * @param matrix
     * @param numSelect
     * @return
     */
    public int maximumRows1(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                mask[i] += matrix[i][j] << (n - j - 1);
            }
        }
        int res = 0;
        int cur = 0;
        int limit = (1 << n);
        while (++cur < limit) {
            if (Integer.bitCount(cur) != numSelect) {
                continue;
            }
            int t = 0;
            for (int j = 0; j < m; j++) {
                if ((mask[j] & cur) == mask[j]) {
                    ++t;
                }
            }
            res = Math.max(res, t);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了92.13% 的Java用户
     * 	内存消耗:40.4 MB,击败了29.77% 的Java用户
     * @param matrix
     * @param numSelect
     * @return
     */
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                mask[i] += matrix[i][j] << (n - j - 1);
            }
        }
        int res = 0;
        int cur = (1 << numSelect) - 1;
        int limit = (1 << n);
        while (cur < limit) {
            int t = 0;
            for (int j = 0; j < m; j++) {
                if ((mask[j] & cur) == mask[j]) {
                    ++t;
                }
            }
            res = Math.max(res, t);
            int lb = cur & -cur;
            int r = cur + lb;
            cur = ((r ^ cur) >> Integer.numberOfTrailingZeros(lb) + 2) | r;
        }
        return res;
    }

}
