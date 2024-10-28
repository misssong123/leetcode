package com.meng.interview150.leetcode.editor.cn;

import java.util.List;

class Interview140minimumTotal {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了11.06% 的Java用户
     * 	内存消耗:43.3 MB,击败了21.53% 的Java用户
     * @param triangle
     * @return
     */
    public int minimumTotalMy(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 1){
            return triangle.get(0).get(0);
        }
        for (int i = n -2 ; i >=0 ; i--){
            for (int j = 0 ; j < triangle.get(i).size() ; j++){
                List<Integer> next = triangle.get(i + 1);
                triangle.get(i).set(j,triangle.get(i).get(j) +Math.min(next.get(j),next.get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了96.63% 的Java用户
     * 	内存消耗:42.8 MB,击败了83.02% 的Java用户
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }
}
