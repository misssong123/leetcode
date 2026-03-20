package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class MinAbsDiff3567 {
    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了45.45% 的Java用户
     * 	内存消耗:46.5 MB,击败了81.82% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int[][] minAbsDiff3567(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if(k == 1){
            return new int[m-k+1][n-k+1];
        }
        int[][] res = new int[m-k+1][n-k+1];
        int len = k * k ;
        List<Integer> list = new ArrayList<>(k * k);
        for(int i = 0 ; i <= m - k ; i++ ){
            for(int j = 0 ; j <= n -k ; j++ ){
                //组装矩阵
                for(int p = i ; p < i + k ; p++){
                    for(int q = j ; q < j + k ; q++){
                        list.add(grid[p][q]);
                    }
                }
                list.sort(Integer::compareTo);
                if (Objects.equals(list.get(0), list.get(len - 1))) {
                    res[i][j] = list.get(0);
                }else{
                    int num = Integer.MAX_VALUE;
                    for (int l = 0; l < len -1; l++) {
                        int val = list.get(l+1) - list.get(l);
                        if (val  != 0) {
                            num = Math.min(num, val);
                        }
                    }
                    res[i][j] = num;
                }
                list.clear();
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了90.91% 的Java用户
     * 	内存消耗:46.4 MB,击败了81.82% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        int[] a = new int[k * k];
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                int idx = 0;
                for (int x = 0; x < k; x++) {
                    for (int y = 0; y < k; y++) {
                        a[idx++] = grid[i + x][j + y];
                    }
                }
                Arrays.sort(a);

                int res = Integer.MAX_VALUE;
                for (int p = 1; p < a.length; p++) {
                    if (a[p] > a[p - 1]) { // 题目要求相减的两个数必须不同
                        res = Math.min(res, a[p] - a[p - 1]);
                    }
                }
                if (res < Integer.MAX_VALUE) {
                    ans[i][j] = res;
                }
            }
        }
        return ans;
    }

}
