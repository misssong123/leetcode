package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MyAtoi8 {
    /**
     * 解答成功:
     * 解答成功:
     * 	执行耗时:2 ms,击败了25.16% 的Java用户
     * 	内存消耗:41.6 MB,击败了69.33% 的Java用户
     * @param s
     * @return
     */
    static String minNum  = "2147483648";
    static String maxNum = "2147483647";
    public int myAtoi(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c == ' '&& sb.length() == 0){
                continue;
            }if ((c == '-' || c == '+') && sb.length() == 0){
                sb.append(c);
            }else if (c >= '0' && c <= '9'){
                sb.append(c);
            }else {
                break;
            }
        }
        if (sb.length() == 0){
            return 0;
        }
        //确认操作符号
        int operation = 1;
        if (sb.charAt(0) == '+'||sb.charAt(0) == '-'){
            operation = sb.charAt(0) == '-' ? -1 : 1;
            sb.deleteCharAt(0);
        }
        //移除前导0
        while (sb.length() > 0 &&sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        if (sb.length() == 0){
            return 0;
        }
        if (sb.length() > 10){
            return operation == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        if (sb.length() == 10){
            if (operation == -1){
                if (sb.toString().compareTo(minNum) >= 0){
                    return Integer.MIN_VALUE;
                }
            }else {
                if (sb.toString().compareTo(maxNum) >= 0){
                    return Integer.MAX_VALUE;
                }
            }
        }
        return Integer.parseInt(sb.toString()) * operation;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了15.30% 的Java用户
     * 	内存消耗:42.2 MB,击败了8.14% 的Java用户
     * @param str
     * @return
     */
    public int myAtoiOfficial(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
}

class Automaton {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}
