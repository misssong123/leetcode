package com.meng.oneday.leetcode.editor.cn;

class SmallestNumber3345 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了75.00% 的Java用户
     * @param n
     * @param t
     * @return
     */
    public int smallestNumber3345(int n, int t) {
        for (int i = n ; i <= 1000000000; i++) {
            int j = i;
            int sum = 1;
            while (j > 0){
                sum *= (j % 10);
                j /= 10;
            }
            if (sum % t == 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了31.25% 的Java用户
     * @param n
     * @param t
     * @return
     */
    public int smallestNumber(int n, int t) {
        for (int i = n; ; i++) {
            int prod = 1;
            for (int x = i; x > 0; x /= 10) {
                prod *= x % 10;
            }
            if (prod % t == 0) {
                return i;
            }
        }
    }
}
