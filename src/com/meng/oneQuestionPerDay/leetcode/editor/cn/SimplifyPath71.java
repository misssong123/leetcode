package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

class SimplifyPath71 {
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 43.45%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 86.36%
     * 的用户
     * 通过测试用例：
     * 258 / 258
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        System.out.println(Arrays.toString(paths));
        for(String p : paths){
            if ("..".equals(p)){
                if (!stack.isEmpty()){
                    stack.pop();
                }
            }else if (!("".equals(p)||".".equals(p))){
                stack.push(p);
            }
        }
        System.out.println(stack);
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()){
            sb.insert(0,"/"+stack.pop());
        }
        if (sb.length()==0){
            sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath71 demo = new SimplifyPath71();
        String path = "/../";
        System.out.println(demo.simplifyPath(path));
        System.out.println(demo.simplifyPath1(path));
    }

    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 43.45%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 20.61%
     * 的用户
     * 通过测试用例：
     * 258 / 258
     * @param path
     * @return
     */
    public String simplifyPath1(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }

}

