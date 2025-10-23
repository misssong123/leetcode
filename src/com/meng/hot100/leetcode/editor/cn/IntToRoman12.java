package com.meng.hot100.leetcode.editor.cn;

class IntToRoman12 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了98.82% 的Java用户
     * 	内存消耗:43.4 MB,击败了82.51% 的Java用户
     */
    private final static int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private final static String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public String intToRoman12(int num) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (num > 0){
            while (values[i] > num){
                i++;
            }
            sb.append(symbols[i]);
            num -= values[i];
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了13.77% 的Java用户
     * 	内存消耗:44.1 MB,击败了10.53% 的Java用户
     */
    private static final String[][] R = new String[][]{
            {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 个位
            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 十位
            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 百位
            {"", "M", "MM", "MMM"}, // 千位
    };

    public String intToRoman(int num) {
        return R[3][num / 1000] + R[2][num / 100 % 10] + R[1][num / 10 % 10] + R[0][num % 10];
    }
}
