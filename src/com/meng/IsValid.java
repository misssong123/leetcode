package com.meng;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 *
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */
public class IsValid {
    public static void main(String[] args) {
        IsValid demo = new IsValid();
        System.out.println(demo.isValid("{[]}"));
        LinkedList linkedList = new LinkedList();
    }
    /**
     * 1.如果字符长度不为2的倍数则返回false
     * 2.将左括号依次放入栈中
     * 3.碰到右括号，则弹出栈顶元素，若匹配则继续
     * 4.直到到达字符串的最后一位，若此时栈为空，则表示正常结束
     * 5.否则返回false
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length()%2 != 0)
            return  false;
        int n = s.length();
        LinkedList<Integer> list = new LinkedList<>();
        Map<Character,Integer> map = new HashMap<>();
        map.put('(',1);
        map.put('[',2);
        map.put('{',3);
        map.put(')',-1);
        map.put(']',-2);
        map.put('}',-3);
        for(int i =0 ; i< n ; i++){
            char c = s.charAt(i);
            if (map.get(c)<0 ){
                if (list.isEmpty() || (list.removeLast() != -(map.get(c))))
                    return false;
            }else{
                list.add(map.get(c));
            }

        }
        if (!list.isEmpty())
            return false;
        return true;
    }
    public boolean officeResult(String s){
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
