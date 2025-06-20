package com.meng.oneday.leetcode.editor.cn;

class IntToRoman12 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了98.29% 的Java用户
     * 	内存消耗:43.4 MB,击败了84.66% 的Java用户
     * @param num
     * @return
     */
    public String intToRoman12(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0){
            if(num >= 1000){
                sb.append("M");
                num -= 1000;
            }else if(num >= 900){
                sb.append("CM");
                num -= 900;
            }else if(num >= 500){
                sb.append("D");
                num -= 500;
            }else if(num >= 400){
                sb.append("CD");
                num -= 400;
            }else if(num >= 100){
                sb.append("C");
                num -= 100;
            }else if(num >= 90){
                sb.append("XC");
                num -= 90;
            }else if(num >= 50){
                sb.append("L");
                num -= 50;
            }else if(num >= 40){
                sb.append("XL");
                num -= 40;
            }else if(num >= 10){
                sb.append("X");
                num -= 10;
            }else if(num >= 9){
                sb.append("IX");
                num -= 9;
            }else if(num >= 5){
                sb.append("V");
                num -= 5;
            }else if(num >= 4){
                sb.append("IV");
                num -= 4;
            }else {
                sb.append("I");
                num -= 1;
            }
        }
        return sb.toString();
    }
    private static final String[][] R = new String[][]{
            {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 个位
            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 十位
            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 百位
            {"", "M", "MM", "MMM"}, // 千位
    };

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了14.60% 的Java用户
     * 	内存消耗:43.9 MB,击败了19.89% 的Java用户
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        return R[3][num / 1000] + R[2][num / 100 % 10] + R[1][num / 10 % 10] + R[0][num % 10];
    }

}
