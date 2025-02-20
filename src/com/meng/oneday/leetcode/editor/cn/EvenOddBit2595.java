package com.meng.oneday.leetcode.editor.cn;

class EvenOddBit2595 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.71% 的Java用户
     * 	内存消耗:41.6 MB,击败了51.71% 的Java用户
     * @param n
     * @return
     */
    public int[] evenOddBitMy(int n) {
        String str = Integer.toBinaryString(n);
        int odd = 0, even = 0,index = str.length()-1;
        for(char c : str.toCharArray()){
            if (c == '1'){
                if (index % 2 == 0){
                    even++;
                }else {
                    odd++;
                }
            }
            index--;
        }
        return new int[]{even,odd};
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.71% 的Java用户
     * 	内存消耗:41.7 MB,击败了36.59% 的Java用户
     * @param n
     * @return
     */
    public int[] evenOddBit1(int n) {
        int[] ans = new int[2];
        for (int i = 0; n > 0; n >>= 1) {
            ans[i] += n & 1;
            i ^= 1; // 切换奇偶
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.71% 的Java用户
     * 	内存消耗:41.7 MB,击败了37.56% 的Java用户
     * @param n
     * @return
     */
    public int[] evenOddBit(int n) {
        final int MASK = 0x55555555;
        return new int[]{Integer.bitCount(n & MASK), Integer.bitCount(n & (MASK << 1))};
    }


}
