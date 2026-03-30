package com.meng.oneday.leetcode.editor.cn;

class AreSimilar2946 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了60.38% 的Java用户
     * 	内存消耗:46.1 MB,击败了54.83% 的Java用户
     * @param mat
     * @param k
     * @return
     */
    public boolean areSimilar2946(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        k = k % n;
        if (k == 0){
            return true;
        }
        for(int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j++){
                if (i % 2 == 0){
                    if(mat[i][j] != mat[i][(j-k+n)%n]){
                        return false;
                    }
                }else{
                    if(mat[i][j] != mat[i][(j+k)%n]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
