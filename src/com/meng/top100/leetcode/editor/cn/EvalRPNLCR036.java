package com.meng.top100.leetcode.editor.cn;

import java.util.*;

class EvalRPNLCR036 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了68.21% 的Java用户
     * 	内存消耗:43.6 MB,击败了76.54% 的Java用户
     * @param tokens
     * @return
     */
    public int evalRPNLCR036(String[] tokens) {
        Set<String> opertionSet = new HashSet<>(Arrays.asList("+","-","*","/"));
        Deque<String> stack = new LinkedList<>();
        for (String token : tokens){
            if(opertionSet.contains(token)){
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (token){
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                }
                stack.push(String.valueOf(res));
            }else {
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.69% 的Java用户
     * 	内存消耗:43.6 MB,击败了79.01% 的Java用户
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
