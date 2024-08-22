package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class Interview053SimplifyPath {
    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了99.71% 的Java用户
     * 内存消耗:42.1 MB,击败了96.60% 的Java用户
     *
     * @param path
     * @return
     */
    public String simplifyPathMy(String path) {
        Deque<String> deque = new ArrayDeque<>();
        int index = 0, len = path.length();
        while (index < path.length()) {
            //无影响
            if (path.charAt(index) == '/') {
                index++;
            } else if (path.charAt(index) == '.') {
                //无影响
                if (index == len - 1 || path.charAt(index + 1) == '/') {
                    index++;
                } else if (path.charAt(index + 1) == '.' && (index + 2 == len || path.charAt(index + 2) == '/')) {//返回上一级
                    index += 3;
                    //弹出上一个数据
                    if (!deque.isEmpty()) {
                        deque.pop();
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (index < len && path.charAt(index) != '/') {
                        sb.append(path.charAt(index++));
                    }
                    deque.push(sb.toString());
                }
            } else {
                StringBuilder sb = new StringBuilder();
                while (index < len && path.charAt(index) != '/') {
                    sb.append(path.charAt(index++));
                }
                deque.push(sb.toString());
            }
        }
        if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/").append(deque.pollLast());
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了94.69% 的Java用户
     * 	内存消耗:42.5 MB,击败了46.17% 的Java用户
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (!name.isEmpty() && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuilder ans = new StringBuilder();
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
