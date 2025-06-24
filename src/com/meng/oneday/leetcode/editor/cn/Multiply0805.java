package com.meng.oneday.leetcode.editor.cn;

class Multiply0805 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了5.05% 的Java用户
     * @param A
     * @param B
     * @return
     */
    public int multiply0805(int A, int B) {
        return A * B;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了65.14% 的Java用户
     * @param a
     * @param b
     * @return
     */
    public int multiply(int a, int b) {
        if (a == 0 || b == 0){
            return 0;
        }
        if(b > a){
            int temp = a;
            a = b;
            b = temp;
        }
        int res = 0;
        for(; b > 0 ; b /= 2){
            if (b % 2 == 1){
                res += a;
            }
            a += a;
        }
        return res;
    }
}
