package com.meng.oneday.leetcode.editor.cn;

class KthCharacter_07_04 {
    /**
     * 逐步反推k是由那个元素演变几次得到的
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.35% 的Java用户
     * 	内存消耗:42.6 MB,击败了16.28% 的Java用户
     * @param k
     * @param operations
     * @return
     */
    public char kthCharacter_07_04(long k, int[] operations) {
        int cnt = 0;
        while ( k > 0){
            int index = Long.toBinaryString(k).length() - 1;
            if (Long.bitCount(k) == 1){
                for(int i = 0 ; i < index ; i++){
                    if (operations[i] == 1){
                        cnt ++;
                    }
                }
            }else {
                if (operations[index] == 1){
                    cnt ++;
                }
            }
            k -= 1L << index;
        }
        return (char)('a' + cnt % 26);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.35% 的Java用户
     * 	内存消耗:42.2 MB,击败了88.37% 的Java用户
     * @param k
     * @param operations
     * @return
     */
    public char kthCharacterOfficial(long k, int[] operations) {
        int ans = 0;
        int t;
        while (k != 1) {
            t = 63 - Long.numberOfLeadingZeros(k);
            if ((1L << t) == k) {
                t--;
            }
            k = k - (1L << t);
            if (operations[t] != 0) {
                ans++;
            }
        }
        return (char) ('a' + (ans % 26));
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.35% 的Java用户
     * 	内存消耗:42.3 MB,击败了79.07% 的Java用户
     * @param k
     * @param operations
     * @return
     */
    public char kthCharacter(long k, int[] operations) {
        int ans = 0;
        k--;
        for (int i = 63 - Long.numberOfLeadingZeros(k); i >= 0; i--) {
            if ((k >> i & 1) == 1) {
                ans += operations[i];
            }
        }
        return (char) ('a' + (ans % 26));
    }

}
