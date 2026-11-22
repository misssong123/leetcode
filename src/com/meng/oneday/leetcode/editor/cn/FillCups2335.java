package com.meng.oneday.leetcode.editor.cn;

class FillCups2335 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了79.17% 的Java用户
     * @param amount
     * @return
     */
    public int fillCups2335(int[] amount) {
        int c = amount[0],w = amount[1],h = amount[2];
        int step = 0;
        while (c>0&&w>0&&h>0){
            if (c <= w && c <= h) {
                w--;
                h--;
            }else if (w <= c && w <= h) {
                c--;
                h--;
            }else {
                c--;
                w--;
            }
            step++;
        }
        if (c == 0){
            step += Math.max(w,h);
        }else if (w == 0){
            step += Math.max(c,h);
        }else {
            step += Math.max(c,w);
        }
        return step;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了62.50% 的Java用户
     * @param amount
     * @return
     */
    public int fillCups(int[] amount) {
        int s = 0;
        int m = 0;
        for (int x : amount) {
            s += x;
            m = Math.max(m, x);
        }
        return Math.max((s + 1) / 2, m);
    }
}
