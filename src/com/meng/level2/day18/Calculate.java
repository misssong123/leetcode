package com.meng.level2.day18;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 227. 基本计算器 II(中等)
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 整数除法仅保留整数部分。
 *
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 *
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 *
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 *
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 */
public class Calculate {
    /**
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 41.43%
     * 的用户
     * 内存消耗：
     * 43.1 MB
     * , 在所有 Java 提交中击败了
     * 32.15%
     * 的用户
     * 通过测试用例：
     * 109 / 109
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Integer> numStack = new LinkedList<>();
        Deque<Character> signStack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < chars.length ; i++){
            char c = chars[i];
            if (Character.isDigit(c)){
                int n = 0;
                if (i >0 && Character.isDigit(chars[i-1])){
                    n = numStack.pop() * 10;
                }
                numStack.push(n+(c-'0'));
                continue;
            }else if (c == ' '){
                continue;
            }else {
                if (signStack.isEmpty()){
                    signStack.push(c);
                    continue;
                }
                char pre = signStack.peek();
                while (!signStack.isEmpty() &&((c == '+' || c == '-') || ((c == '*' || c == '/')&&(pre == '*' || pre == '/')))){
                    numStack.push(cacultaion(numStack.pop(),numStack.pop(),signStack.pop()));
                    if (!signStack.isEmpty()){
                        pre = signStack.peek();
                    }
                }
                signStack.push(c);
            }
        }
        while (!signStack.isEmpty()){
            numStack.push(cacultaion(numStack.pop(),numStack.pop(),signStack.pop()));
        }
        return numStack.pop();
    }
    private int cacultaion(int num2,int num1,char c){
        int res = 0;
        switch (c){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        Calculate demo = new Calculate();
        String s = "1+2*5/3+6/4*2";
        System.out.println(demo.calculate(s));
        System.out.println(demo.calculate1(s));
    }
    /**
     * 执行用时：
     * 15 ms
     * , 在所有 Java 提交中击败了
     * 57.48%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 78.03%
     * 的用户
     * 通过测试用例：
     * 109 / 109
     * @param s
     * @return
     */
    public int calculate1(String s) {
        Deque<Integer> stack = new LinkedList<Integer>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

}
