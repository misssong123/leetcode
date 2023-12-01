package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class FirstCompleteIndex2661 {
    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了35.51% 的Java用户
     * 	内存消耗:61.3 MB,击败了68.22% 的Java用户
     * @param arr
     * @param mat
     * @return
     */
    public int firstCompleteIndex1(int[] arr, int[][] mat) {
        int m = mat.length,n=mat[0].length;
        int[] mArr = new int[n];
        int[] nArr = new int[m];
        Map<Integer,int[]> cache = new HashMap<>();
        for (int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j++){
                cache.put(mat[i][j],new int[]{i,j});
            }
        }

        for(int i = 0 ; i < arr.length ; i++){
            int[] ints = cache.get(arr[i]);
            mArr[ints[1]]++;
            nArr[ints[0]]++;
            if (mArr[ints[1]]==m||nArr[ints[0]]==n){
                return i;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了70.09% 的Java用户
     * 	内存消耗:61.3 MB,击败了69.16% 的Java用户
     * @param arr
     * @param mat
     * @return
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                map.put(mat[i][j], new int[]{i, j});
            }
        }
        int[] rowCnt = new int[n];
        int[] colCnt = new int[m];
        for (int i = 0; i < arr.length; ++i) {
            int[] v = map.get(arr[i]);
            ++rowCnt[v[0]];
            if (rowCnt[v[0]] == m) {
                return i;
            }
            ++colCnt[v[1]];
            if (colCnt[v[1]] == n) {
                return i;
            }
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
