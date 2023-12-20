package com.meng.oneQuestionPerDay.leetcode.editor.cn;
class FindPeakGrid1901{
    static int[] x = new int[]{-1,0,1,0};
    static int[] y = new int[]{0,-1,0,1};

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了8.42% 的Java用户
     * 	内存消耗:86.8 MB,击败了5.26% 的Java用户
     * @param mat
     * @return
     */
    public int[] findPeakGridMy(int[][] mat) {
        int n = mat.length , m = mat[0].length;
        for(int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < m ; j++){
                boolean flag =  true;
                for(int k= 0 ; k < 4 ; k++){
                    int newX = i + x[k];
                    int newY = j + y[k];
                    if(newX < 0 || newX >= n || newY < 0 || newY >= m){
                        continue;
                    }
                    if (mat[newX][newY] >=mat[i][j]){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    return new int[]{i,j};
                }
            }
        }
        return  new int[]{};
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:86.2 MB,击败了5.47% 的Java用户
     * @param mat
     * @return
     */
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = m - 1;
        while (low <= high) {
            int i = (low + high) / 2;
            int j = -1, maxElement = -1;
            for (int k = 0; k < n; k++) {
                if (mat[i][k] > maxElement) {
                    j = k;
                    maxElement = mat[i][k];
                }
            }
            if (i - 1 >= 0 && mat[i][j] < mat[i - 1][j]) {
                high = i - 1;
                continue;
            }
            if (i + 1 < m && mat[i][j] < mat[i + 1][j]) {
                low = i + 1;
                continue;
            }
            return new int[]{i, j};
        }
        return new int[0]; // impossible
    }

}
//leetcode submit region end(Prohibit modification and deletion)
