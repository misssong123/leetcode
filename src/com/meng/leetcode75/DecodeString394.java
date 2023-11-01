package com.meng.leetcode75;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class DecodeString394 {
    /**
     * 思路不清晰，出错
     * @param s
     * @return
     */
    public String decodeStringError(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int n = 0;
        for(char c : s.toCharArray()){
            if (c >= '0' && c <= '9'){
                n = n*10 + c-'0';
            }else if (c>='a'&&c<='z'){
                sb.append(c);
            }else if (c == '['){
                if (n>0){
                    numStack.push(n);
                    n=0;
                }
                if (sb.length()>0){
                    strStack.push(sb.toString());
                    sb.delete(0,sb.length());
                }
            }else {
                int num = numStack.isEmpty()?1:numStack.pop();
                String str = sb.length()==0?strStack.pop():sb.toString();
                sb.delete(0,sb.length());
                for (int i = 1 ; i <= num ; i++){
                    sb.append(str);
                }
                if (!strStack.isEmpty()){
                    sb.insert(0,strStack.pop());
                }
                if (numStack.isEmpty()){
                    res.append(sb);
                }else {
                    strStack.push(sb.toString());
                }
                sb.delete(0,sb.length());
            }
        }
        if (!strStack.isEmpty()){
            int num = numStack.isEmpty()?1:numStack.pop();
            String str = strStack.pop();
            for (int i = 1 ; i <= num ; i++){
                res.append(str);
            }
        }
        if (sb.length()>0){
            res.append(sb);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        DecodeString394 demo = new DecodeString394();
        String s =  "100[cd]";
        System.out.println(demo.decodeString(s));
    }

    /**
     * 时间
     * 详情
     * 1ms
     * 击败 76.39%使用 Java 的用户
     * 内存
     * 详情
     * 38.71MB
     * 击败 27.36%使用 Java 的用户
     * @param s
     * @return
     */
    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<>();
        ptr = 0;
        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    int ptr;
    String src;
    /**
     * 时间
     * 详情
     * 1ms
     * 击败 76.39%使用 Java 的用户
     * 内存
     * 详情
     * 38.51MB
     * 击败 61.30%使用 Java 的用户
     * @param s
     * @return
     */
    public String decodeStrin2(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }

}
