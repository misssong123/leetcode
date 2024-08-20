package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

class Interview052IsValid {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.67% 的Java用户
     * 	内存消耗:40.6 MB,击败了47.18% 的Java用户
     * @param s
     * @return
     */

    public boolean isValidMy(String s) {
        Stack<Character> cache = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                cache.push(c);
            }else {
                if (cache.isEmpty()) {
                    return false;
                }
                char top = cache.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return cache.isEmpty();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了53.18% 的Java用户
     * 	内存消耗:40.6 MB,击败了41.44% 的Java用户
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
