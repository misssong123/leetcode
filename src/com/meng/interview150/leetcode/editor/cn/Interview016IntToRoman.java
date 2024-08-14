package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview016IntToRoman {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了37.06% 的Java用户
     * 	内存消耗:43.1 MB,击败了94.29% 的Java用户
     * @param num
     * @return
     */
    public String intToRomanMy(int num) {
        StringBuffer sb = new StringBuffer();
        while (num != 0){
            if (num >= 1000){
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
            } else if(num >= 90){
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
            }else if(num == 9){
                sb.append("IX");
                num -= 9;
            }else if(num >= 5){
                sb.append("V");
                num -= 5;
            }else if(num == 4){
                sb.append("IV");
                num -= 4;
            }else if(num >= 1){
                sb.append("I");
                num -= 1;
            }
        }
        return sb.toString();
    }
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }


}
