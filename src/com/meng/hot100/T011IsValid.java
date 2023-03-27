package com.meng.hot100;

import java.util.*;

/**
 * 20. 有效的括号(简单)
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
public class T011IsValid {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 98.91%
     * 的用户
     * 内存消耗：
     * 39.4 MB
     * , 在所有 Java 提交中击败了
     * 69.56%
     * 的用户
     * 通过测试用例：
     * 92 / 92
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0){
            return false;
        }
        List<Character> cache = new ArrayList<>();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        int index = 0;
        for(char c : s.toCharArray()){
            if (c == '(' || c == '[' || c == '{'){
                cache.add(c);
                index++;
            }else {
                if (index==0 || cache.get(--index) != pairs.get(c)){
                    return false;
                }
                cache.remove(index);
            }
        }
        return cache.size() == 0;
    }

    public static void main(String[] args) {
        T011IsValid demo = new T011IsValid();
        String s = "([)]";
        System.out.println(demo.isValid(s));
        System.out.println(demo.isValid1(s));
    }
    /**
     *方法一：栈
     * 判断括号的有效性可以使用「栈」这一数据结构来解决。
     *
     * 我们遍历给定的字符串 ss。当我们遇到一个左括号时，我们会期望在后续的遍历中，有一个相同类型的右括号将其闭合。由于后遇到的左括号要先闭合，因此我们可以将这个左括号放入栈顶。
     *
     * 当我们遇到一个右括号时，我们需要将一个相同类型的左括号闭合。此时，我们可以取出栈顶的左括号并判断它们是否是相同类型的括号。如果不是相同的类型，或者栈中并没有左括号，那么字符串 ss 无效，返回 \text{False}False。为了快速判断括号的类型，我们可以使用哈希表存储每一种括号。哈希表的键为右括号，值为相同类型的左括号。
     *
     * 在遍历结束后，如果栈中没有左括号，说明我们将字符串 ss 中的所有左括号闭合，返回 \text{True}True，否则返回 \text{False}False。
     *
     * 注意到有效字符串的长度一定为偶数，因此如果字符串的长度为奇数，我们可以直接返回 \text{False}False，省去后续的遍历判断过程。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 52.86%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 38.79%
     * 的用户
     * 通过测试用例：
     * 92 / 92
     */
    public boolean isValid1(String s) {
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
