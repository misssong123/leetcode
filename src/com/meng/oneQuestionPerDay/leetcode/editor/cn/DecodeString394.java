package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

class DecodeString394 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.49%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 98.32%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     * @param s
     * @return
     */
    public String decodeString(String s) {
        StringBuffer res = new StringBuffer();
        Stack<String> stack = new Stack<>();
        int count = 0;//统计当前入口[个数
        char[] chars = s.toCharArray();
        for(char c : chars){
            int type = judgeType(c);
                switch (type){
                    case 1:
                        if (count == 0){
                            res.append(c);
                        }else {
                            stack.push(c+"");
                        }
                        break;
                    case 2:
                        stack.push(c+"");
                        break;
                    case 3:
                        stack.push("[");
                        count++;
                        break;
                    case 4:
                        StringBuffer temp = new StringBuffer();
                        String s1 = "";
                        while (!(s1 = stack.pop()).equals("[")){
                            temp.append(s1);
                        }
                        int m=1,num = 0;
                        while (!stack.isEmpty() &&(s1 = stack.pop())!=null){
                            if (s1.length() == 1 && Character.isDigit(s1.charAt(0))){
                                int n = Integer.parseInt(s1);
                                num += n * m;
                                m *= 10;
                            }else {
                                stack.push(s1);
                                break;
                            }
                        }

                        String s2 = temp.toString();
                        for(int i = 0 ; i < num -1; i++){
                            temp.append(s2);
                        }
                        count--;
                        if (count==0){
                            res.append(temp.reverse());
                        }else {
                            stack.push(temp.toString());
                        }
                        break;
                }
            }
        return res.toString();
    }
    private int judgeType(char c){
        if (c >= 'a' && c <= 'z'){
            return 1;
        }else if (c >= '0' && c <= '9'){
            return 2;
        }else if (c == '['){
            return 3;
        }else {
            return 4;
        }
    }

    public static void main(String[] args) {
        DecodeString394 demo = new DecodeString394();
        String s = "2[ab2[cd]]";
        System.out.println(demo.decodeString(s));
    }


    /**
     * 栈操作
     * @param s
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.49%
     * 的用户
     * 内存消耗：
     * 39.3 MB
     * , 在所有 Java 提交中击败了
     * 85.65%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    int ptr;
    public String decodeString1(String s) {
        LinkedList<String> stk = new LinkedList<String>();
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


    /**
     * 递归
     * @param s
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.49%
     * 的用户
     * 内存消耗：
     * 39.4 MB
     * , 在所有 Java 提交中击败了
     * 68.80%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    String src;
    int count;
    public String decodeString2(String s) {
        src = s;
        count = 0;
        return getString();
    }

    public String getString() {
        if (count == src.length() || src.charAt(count) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(count);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++count;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++count;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(count++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (count < src.length() && Character.isDigit(src.charAt(count))) {
            ret = ret * 10 + src.charAt(count++) - '0';
        }
        return ret;
    }

}
