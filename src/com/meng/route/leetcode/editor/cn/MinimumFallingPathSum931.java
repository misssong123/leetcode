package com.meng.route.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionMinFallingPathSum931 {
    /**
     * 	执行耗时:3 ms,击败了86.12% 的Java用户
     * 	内存消耗:43 MB,击败了84.37% 的Java用户
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                int temp = Integer.MAX_VALUE;
                for(int k = -1 ; k < 2 ; k++){
                    if (j + k <0 || j +k>=n){
                        continue;
                    }
                    temp = Math.min(temp,matrix[i-1][j+k]);
                }
                matrix[i][j] = temp+matrix[i][j];
            }
        }
        return Arrays.stream(matrix[n-1]).min().getAsInt();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
