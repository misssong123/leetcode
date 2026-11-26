package com.meng.oneday.leetcode.editor.cn;

class CanAliceWin_3360 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了9.09% 的Java用户
     * @param n
     * @return
     */
    public boolean canAliceWin3360(int n) {
        int step = 0 , target = 10;
        while (n >= target){
            n -= target;
            step++;
            target--;
        }
        return step % 2 == 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了13.64% 的Java用户
     * @param n
     * @return
     */
    public boolean canAliceWin(int n) {
        int x = (21 - (int) Math.ceil(Math.sqrt(441 - n * 8))) / 2;
        return x % 2 > 0;
    }
}
