package com.meng.oneday.leetcode.editor.cn;

class KthSmallest378 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:48.9 MB,击败了27.27% 的Java用户
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest378(int[][] matrix, int k) {
        int left = matrix[0][0],right = matrix[matrix.length-1][matrix[0].length-1];
        while (left < right){
            int mid = left + (right - left) / 2;
            if(checkNum(matrix,k,mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return right;
    }
    private boolean checkNum(int[][] matrix,int k,int target){
        int r = matrix.length,c = matrix[0].length;
        int x = 0,y = c -1;
        int num = 0;
        while(x < r && y >= 0){
            if(matrix[x][y] <= target){
                num += y + 1;
                x++;
            }else {
                y--;
            }
        }
        return num >= k;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:48.9 MB,击败了27.27% 的Java用户
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0] - 1;
        int right = matrix[n - 1][n - 1];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(matrix, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[][] matrix, int k, int mx) {
        int n = matrix.length;
        int cnt = 0; // matrix 中的 <= mx 的元素个数
        int i = 0;
        int j = n - 1; // 从右上角开始
        while (i < n && j >= 0 && cnt < k) {
            if (matrix[i][j] > mx) {
                j--; // 排除第 j 列
            } else {
                cnt += j + 1; // 从 matrix[i][0] 到 matrix[i][j] 都 <= mx
                i++;
            }
        }
        return cnt >= k;
    }

}
