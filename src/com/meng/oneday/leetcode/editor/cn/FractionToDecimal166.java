package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class FractionToDecimal166 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了47.86% 的Java用户
     * 	内存消耗:40.3 MB,击败了49.57% 的Java用户
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal166(int numerator, int denominator) {
        long num1 = numerator;
        long num2 = denominator;
        //整除
        if(num1 % num2 == 0){
            return String.valueOf(num1 / num2);
        }
        StringBuilder sb = new StringBuilder();
        if (num1 < 0 ^ num2 < 0) {
            sb.append("-");
        }
        //避免负数越界
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        //整数部分
        sb.append(num1 / num2);
        sb.append(".");
        num1 = num1 % num2;
        //小数部分
        Map<Long, Integer> map = new HashMap<>();
        int index = sb.length();
        while (num1 != 0 && !map.containsKey(num1)) {
            map.put(num1, index++);
            sb.append(num1 * 10 / num2);
            num1 = num1 * 10 % num2;
        }
        if (num1 != 0) {
            sb.insert(map.get(num1), "(");
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了33.62% 的Java用户
     * 	内存消耗:40.4 MB,击败了27.35% 的Java用户
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimalOther(int numerator, int denominator) {
        // 转 long 计算，防止溢出
        long a = numerator, b = denominator;
        // 如果本身能够整除，直接返回计算结果
        if (a % b == 0) return String.valueOf(a / b);
        StringBuilder sb = new StringBuilder();
        // 如果其一为负数，先追加负号
        if (a * b < 0) sb.append('-');
        a = Math.abs(a); b = Math.abs(b);
        // 计算小数点前的部分，并将余数赋值给 a
        sb.append(String.valueOf(a / b) + ".");
        a %= b;
        Map<Long, Integer> map = new HashMap<>();
        while (a != 0) {
            // 记录当前余数所在答案的位置，并继续模拟除法运算
            map.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;
            // 如果当前余数之前出现过，则将 [出现位置 到 当前位置] 的部分抠出来（循环小数部分）
            if (map.containsKey(a)) {
                int u = map.get(a);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了33.05% 的Java用户
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimalOfficial(int numerator, int denominator) {
        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuffer sb = new StringBuffer();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }

        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        Map<Long, Integer> remainderIndexMap = new HashMap<Long, Integer>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        if (remainder != 0) { // 有循环节
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());

        return sb.toString();
    }

}
