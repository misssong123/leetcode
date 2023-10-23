package com.meng.plan.day02;

import java.util.Stack;

public class RemoveStars2390 {
    /**
     * 时间
     * 详情
     * 259ms
     * 击败 16.98%使用 Java 的用户
     * 内存
     * 详情
     * 43.63MB
     * 击败 22.12%使用 Java 的用户
     * @param s
     * @return
     */
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if (c == '*' ){
                if (!stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
    /**
     *时间
     * 详情
     * 30ms
     * 击败 82.32%使用 Java 的用户
     * 内存
     * 详情
     * 42.62MB
     */
    public String removeStars1(String s) {
        StringBuilder rs = new StringBuilder();
        // 遍历每个字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                // 尾部移除一位
                rs.deleteCharAt(rs.length() - 1);
            } else {
                rs.append(c);
            }
        }

        return rs.toString();
    }

}
