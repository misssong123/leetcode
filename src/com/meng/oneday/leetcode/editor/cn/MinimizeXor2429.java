package com.meng.oneday.leetcode.editor.cn;

class MinimizeXor2429 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了87.50% 的Java用户
     * @param num1
     * @param num2
     * @return
     */
    public int minimizeXor2429(int num1, int num2) {
        int n2 = Integer.bitCount(num2);
        int n1 = Integer.bitCount(num1);
        if (n1 == n2){
            return num1;
        }
        if (n1 > n2){
            int binLen = Integer.toBinaryString(num1).length();
            int step = 1 << (binLen - 1);
            int result = 0;
            int n = n2;
            while (n > 0){
                if ((step & num1) != 0){
                    result += step;
                    n--;
                }
                step >>= 1;
            }
            return result;
        }else{
            int diff = n2 - n1;
            int result = 0;
            int step = 1;
            int onesSeen = 0;
            while (diff > onesSeen){
                if ((num1 & step) == 0){
                    result |= step;
                    onesSeen++;
                }
                step <<= 1;
            }
            return result | num1;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了87.50% 的Java用户
     * @param num1
     * @param num2
     * @return
     */
    public int minimizeXor(int num1, int num2) {
        int c1 = Integer.bitCount(num1);
        int c2 = Integer.bitCount(num2);
        for (; c2 < c1; c2++) num1 &= num1 - 1; // 最低的 1 变成 0
        for (; c2 > c1; c2--) num1 |= num1 + 1; // 最低的 0 变成 1
        return num1;
    }

}
