package com.meng.oneday.leetcode.editor.cn;

class BitwiseComplement1009 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了83.65% 的Java用户
     * @param n
     * @return
     */
    public int bitwiseComplement1009(int n) {
        String binaryString = Integer.toBinaryString(n);
        char[] chars = binaryString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = chars[i] == '0' ? '1' : '0';
        }
        return Integer.parseInt(String.valueOf(chars), 2);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了80.50% 的Java用户
     * @param n
     * @return
     */
    public int bitwiseComplement(int n) {
        int highbit = 0;
        for (int i = 1; i <= 30; ++i) {
            if (n >= 1 << i) {
                highbit = i;
            } else {
                break;
            }
        }
        int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
        return n ^ mask;
    }

}
