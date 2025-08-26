package com.meng.oneday.leetcode.editor.cn;

class AreaOfMaxDiagonal3000 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了33.33% 的Java用户
     * @param dimensions
     * @return
     */
    public int areaOfMaxDiagonal3000(int[][] dimensions) {
        int res = 0 , max = 0;
        for(int [] dimension : dimensions){
            int t = dimension[0]*dimension[0] + dimension[1] * dimension[1];
            if (t > max){
                max = t;
                res = dimension[0] * dimension[1];
            }else if (t == max){
                res = Math.max(res , dimension[0] * dimension[1]);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了62.96% 的Java用户
     * @param dimensions
     * @return
     */
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, maxL = 0;
        for (int[] d : dimensions) {
            int x = d[0], y = d[1];
            int l = x * x + y * y;
            if (l > maxL || l == maxL && x * y > ans) {
                maxL = l;
                ans = x * y;
            }
        }
        return ans;
    }
}
