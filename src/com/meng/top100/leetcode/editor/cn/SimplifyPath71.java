package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class SimplifyPath71 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了50.66% 的Java用户
     * 	内存消耗:43.1 MB,击败了25.56% 的Java用户
     * @param path
     * @return
     */
    public String simplifyPath71(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        for (String s : paths) {
            if (s.isEmpty()||s.equals(".")) {
                continue;
            }else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }else{
                stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了82.83% 的Java用户
     * 	内存消耗:42.7 MB,击败了72.30% 的Java用户
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        List<String> stk = new ArrayList<>();
        for (String s : path.split("/")) {
            if (s.isEmpty() || s.equals(".")) {
                continue;
            }
            if (!s.equals("..")) {
                stk.add(s);
            } else if (!stk.isEmpty()) {
                stk.remove(stk.size() - 1);
            }
        }
        return "/" + String.join("/", stk);
    }
}
