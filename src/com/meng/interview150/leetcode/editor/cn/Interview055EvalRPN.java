package com.meng.interview150.leetcode.editor.cn;

import java.util.Stack;

class Interview055EvalRPN {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了93.17% 的Java用户
     * 	内存消耗:43.7 MB,击败了17.77% 的Java用户
     * @param tokens
     * @return
     */
    public int evalRPNMy(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int first = stack.pop();
                    int second = stack.pop();
                    stack.push(second - first);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int firstNum = stack.pop();
                    int secondNum = stack.pop();
                    stack.push(secondNum / firstNum);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了97.45% 的Java用户
     * 	内存消耗:43.4 MB,击败了86.59% 的Java用户
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }

}
