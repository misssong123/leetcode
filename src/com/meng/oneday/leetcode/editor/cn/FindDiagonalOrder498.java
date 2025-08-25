package com.meng.oneday.leetcode.editor.cn;

class FindDiagonalOrder498 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了38.32% 的Java用户
     * 	内存消耗:46.2 MB,击败了53.57% 的Java用户
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder498(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int x = 0 ,y =0,index = 0;
        int[] res = new int[m*n];
        res[index] = mat[x][y];
        int row = 0;
        int[][] dirs = {{-1,1},{1,-1}};
        while (index < m * n -1){
            int dir = row % 2;
            int newX = x + dirs[dir][0];
            int newY = y + dirs[dir][1];
            //符合条件
            if(newX >= 0 && newX < m && newY >= 0 && newY < n){
                x = newX;
                y = newY;
            }else {
                row++;
                if (dir == 0){
                    if (y + 1 < n){
                        y++;
                    }else {
                        x++;
                    }
                }else {
                    if (x + 1 < m){
                        x++;
                    }else {
                        y++;
                    }
                }
            }
            res[++index] = mat[x][y];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了68.82% 的Java用户
     * 	内存消耗:46.3 MB,击败了24.81% 的Java用户
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int idx = 0;
        for (int k = 0; k < m + n - 1; k++) {
            int minJ = Math.max(k - m + 1, 0);
            int maxJ = Math.min(k, n - 1);
            if (k % 2 == 0) { // 偶数从左到右
                for (int j = minJ; j <= maxJ; j++) {
                    ans[idx++] = mat[k - j][j];
                }
            } else { // 奇数从右到左
                for (int j = maxJ; j >= minJ; j--) {
                    ans[idx++] = mat[k - j][j];
                }
            }
        }
        return ans;
    }

}
