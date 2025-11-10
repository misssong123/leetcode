package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class MinimumTotalLCR100 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了23.08% 的Java用户
     * 	内存消耗:44.2 MB,击败了5.59% 的Java用户
     * @param triangle
     * @return
     */
    public int minimumTotalLCR100(List<List<Integer>> triangle) {
        int n = triangle.size();
        List<Integer> dp = new ArrayList<>(triangle.get(n-1));
        for (int i = n - 1 ; i >= 1 ; i--){
            for(int j = 0 ; j < i ; j++){
                dp.set(j,Math.min(dp.get(j),dp.get(j+1)) + triangle.get(i-1).get(j));
            }
        }
        return dp.get(0);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了10.49% 的Java用户
     * 	内存消耗:44.1 MB,击败了5.59% 的Java用户
     * @param f
     * @return
     */
    public int minimumTotal(List<List<Integer>> f) {
        for (int i = f.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f.get(i).set(j, f.get(i).get(j) + Math.min(f.get(i + 1).get(j), f.get(i + 1).get(j + 1)));
            }
        }
        return f.get(0).get(0);
    }
}
