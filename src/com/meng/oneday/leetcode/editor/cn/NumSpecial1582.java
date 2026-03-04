package com.meng.oneday.leetcode.editor.cn;

class NumSpecial1582 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.5 MB,击败了35.29% 的Java用户
     * @param mat
     * @return
     */
    public int numSpecial1582(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.4 MB,击败了50.00% 的Java用户
     * @param mat
     * @return
     */
    public int numSpecialOther(int[][] mat) {
        int ans = 0;
        for (int[] row : mat) {
            int rowSum = 0;
            int j = -1;
            for (int i = 0; i < row.length; i++) {
                if (row[i] == 1) {
                    rowSum++;
                    j = i;
                }
            }
            if (rowSum != 1) {
                continue;
            }

            // 计算 j 列的元素和，必须恰好是 1
            int colSum = 0;
            for (int[] r : mat) {
                colSum += r[j];
            }
            if (colSum == 1) {
                ans++;
            }
        }
        return ans;
    }

}
