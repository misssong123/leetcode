package com.meng.oneday.leetcode.editor.cn;

class CanAliceWin3360 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了9.52% 的Java用户
     * @param n
     * @return
     */
    public boolean canAliceWin3360(int n) {
        int target = 10,step = 0;
        while (n > 0){
            if(n >= target){
                n -= target;
                step++;
                target--;
            }else {
                break;
            }
        }
        return step % 2 == 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了76.19% 的Java用户
     * @param n
     * @return
     */
    public boolean canAliceWin(int n) {
        int x = (21 - (int) Math.ceil(Math.sqrt(441 - n * 8))) / 2;
        return x % 2 > 0;
    }

}
