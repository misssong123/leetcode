package com.meng.oneday.leetcode.editor.cn;

class NumberOfSteps1342 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.8 MB,击败了15.19% 的Java用户
     * @param num
     * @return
     */
    public int numberOfSteps1342(int num) {
        int step = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num /= 2;
            }else {
                num -= 1;
            }
            step++;
        }
        return step;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了79.11% 的Java用户
     * @param num
     * @return
     */
    public int numberOfSteps(int num) {
        return num == 0 ? 0 : 31 - Integer.numberOfLeadingZeros(num) + Integer.bitCount(num);
    }

}
