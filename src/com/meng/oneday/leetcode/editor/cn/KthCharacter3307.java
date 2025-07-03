package com.meng.oneday.leetcode.editor.cn;

class KthCharacter3307 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了94.74% 的Java用户
     * 	内存消耗:42.7 MB,击败了7.89% 的Java用户
     * @param k
     * @param operations
     * @return
     */
    public char kthCharacter3307(long k, int[] operations) {
        int index = Long.toBinaryString(k-1).length() -1;
        int cnt = 0;
        while (k > 0){
            int num = Long.toBinaryString(k).length() - 1;
            //当前k为2的幂次，计算前置1的次数
            if (Long.bitCount(k) == 1){
                for (int i = 0; i < num ; i++) {
                    if (operations[i] == 1){
                        cnt ++;
                    }
                }
            }else {
                //当前k不为2的幂次，当前操作数为1，累计次数+1
                if (operations[num] == 1){
                    cnt ++;
                }
            }
            k -= (1L << num);
        }
        return (char)('a' + cnt%26);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了94.74% 的Java用户
     * 	内存消耗:42.2 MB,击败了86.84% 的Java用户
     * @param k
     * @param operations
     * @return
     */
    public char kthCharacter(long k, int[] operations) {
        int inc = 0;
        for (int i = 63 - Long.numberOfLeadingZeros(k - 1); i >= 0; i--) {
            if (k > (1L << i)) { // k 在右半边
                inc += operations[i];
                k -= 1L << i;
            }
        }
        return (char) ('a' + inc % 26);
    }

}
