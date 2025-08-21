package com.meng.oneday.leetcode.editor.cn;

class NumSubmat1504 {
    /**
     * 解答成功:
     * 	执行耗时:120 ms,击败了6.67% 的Java用户
     * 	内存消耗:45.1 MB,击败了33.34% 的Java用户
     * @param mat
     * @return
     */
    public int numSubmat1504(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int res = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(mat[i][j] == 1){
                    res++;
                    //获取左侧连接1的坐标
                    int l = j;
                    while (l >=0 && mat[i][l] == 1){
                        l--;
                    }
                    l++;
                    res += (j - l);
                    //获取上侧连接1的坐标
                    int t = i;
                    while (t >=0 && mat[t][j] == 1){
                        t--;
                    }
                    t++;
                    res += (i - t);
                    int step = 1;
                    while(i - step >= t && j - step >= l){
                        int x = i - step;
                        int y = j - step;
                        if(mat[x][y] == 0){
                            break;
                        }
                        res++;
                        int newL = y;
                        while (newL >=l && mat[x][newL] == 1){
                            newL--;
                        }
                        newL++;
                        res += (y - newL);
                        //获取上侧连接1的坐标
                        int newT = x;
                        while (newT >=t && mat[newT][y] == 1){
                            newT--;
                        }
                        newT++;
                        res += (x - newT);
                        step++;
                        l = newL;
                        t = newT;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了15.38% 的Java用户
     * 	内存消耗:44.7 MB,击败了57.44% 的Java用户
     * @param mat
     * @return
     */
    public int numSubmatOther(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int ans = 0;
        for (int top = 0; top < m; top++) { // 枚举上边界
            int[] a = new int[n];
            for (int bottom = top; bottom < m; bottom++) { // 枚举下边界
                int h = bottom - top + 1; // 高
                // 2348. 全 h 子数组的数目
                int last = -1;
                for (int j = 0; j < n; j++) {
                    a[j] += mat[bottom][j]; // 把 bottom 这一行的值加到 a 中
                    if (a[j] != h) {
                        last = j; // 记录上一个非 h 元素的位置
                    } else {
                        ans += j - last;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.1 MB,击败了32.31% 的Java用户
     * @param mat
     * @return
     */
    public int numSubmat(int[][] mat) {
        int n = mat[0].length;
        int[] heights = new int[n];
        int ans = 0;

        int[][] st = new int[n + 1][3]; // (j, f, heights[j])
        for (int[] row : mat) {
            for (int j = 0; j < n; j++) {
                if (row[j] == 0) {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }

            st[0][0] = st[0][2] = -1; // 哨兵，方便处理 left=-1 的情况
            int top = 0;
            for (int j = 0; j < n; j++) {
                int h = heights[j];
                while (st[top][2] >= h) {
                    top--; // 出栈
                }
                int left = st[top][0];
                int f = st[top][1];
                // 计算底边为 row，右边界为 j 的子矩形个数
                // 左边界 <= left 的矩形，每个矩形的右边界都可以扩展到 j，一共有 f 个
                // 左边界 >  left 的矩形，左边界有 j-left 种，高度有 h 种，一共有 (j-left)*h 个
                f += (j - left) * h;
                ans += f;
                top++;
                st[top][0] = j; // 入栈
                st[top][1] = f;
                st[top][2] = h;
            }
        }

        return ans;
    }

}
