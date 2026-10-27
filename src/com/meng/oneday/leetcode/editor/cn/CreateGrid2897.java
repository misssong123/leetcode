package com.meng.oneday.leetcode.editor.cn;

class CreateGrid2897 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了12.50% 的Java用户
     * 	内存消耗:46 MB,击败了37.50% 的Java用户
     * @param m
     * @param n
     * @return
     */
    public String[] createGrid2897(int m, int n) {
        String[] res = new String[m];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 ||j == n -1){
                    sb.append(".");
                }else {
                    sb.append("#");
                }
            }
            res[i] = sb.toString();
            sb.setLength(0);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了65.63% 的Java用户
     * 	内存消耗:46 MB,击败了25.00% 的Java用户
     * @param m
     * @param n
     * @return
     */
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        ans[0] = ".".repeat(n);
        String row = "#".repeat(n - 1) + "."; // 避免在循环中反复创建字符串
        for (int i = 1; i < m; i++) {
            ans[i] = row;
        }
        return ans;
    }

}
