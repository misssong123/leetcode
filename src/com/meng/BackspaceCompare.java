package com.meng;

import java.util.Stack;

/**
 *
 */
public class BackspaceCompare {
    public static void main(String[] args) {
        BackspaceCompare demo = new BackspaceCompare();
        System.out.println(demo.backspaceCompare("y#fo##f","y#f#o##f"));


    }

    /**
     * 通过栈来进行中间字符的操作
     * 若字符为#，这从栈（不为空）中弹出一个元素
     * 若不为#则入栈最后比较两个栈中剩余字符是否相同
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stack = new Stack<>();
        char c = ' ';
        for(int i = 0 ; i < S.length() ; i++) {
            c = S.charAt(i);
            if (c == '#' ){
                if (!stack.isEmpty())
                    stack.pop();
                continue;
            }
            stack.push(c);
        }
        S = stack.toString();
        stack.clear();
        for(int i = 0 ; i < T.length() ; i++) {
            c = T.charAt(i);
            if (c == '#' ){
                if (!stack.isEmpty())
                    stack.pop();
                continue;
            }
            stack.push(c);
        }
        T = stack.toString();
        return S.equals(T);
    }

    /**
     * 官方解法1
     * 方法一：重构字符串
     *
     * 思路及算法
     *
     * 最容易想到的方法是将给定的字符串中的退格符和应当被删除的字符都去除，还原给定字符串的一般形式。然后直接比较两字符串是否相等即可。
     *
     * 具体地，我们用栈处理遍历过程，每次我们遍历到一个字符：
     *
     *     如果它是退格符，那么我们将栈顶弹出；
     *
     *     如果它是普通字符，那么我们将其压入栈中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/backspace-string-compare/solution/bi-jiao-han-tui-ge-de-zi-fu-chuan-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare1(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String str) {
        StringBuffer ret = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            char ch = str.charAt(i);
            if (ch != '#') {
                ret.append(ch);
            } else {
                if (ret.length() > 0) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            }
        }
        return ret.toString();
    }

    /**
     * 官方解法二
     * 方法二：双指针
     *
     * 思路及算法
     *
     * 一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
     *
     * 具体地，我们定义 skip\textit{skip}skip 表示当前待删除的字符的数量。每次我们遍历到一个字符：
     *
     *     若该字符为退格符，则我们需要多删除一个普通字符，我们让 skip\textit{skip}skip 加 111；
     *
     *     若该字符为普通字符：
     *
     *         若 skip\textit{skip}skip 为 000，则说明当前字符不需要删去；
     *
     *         若 skip\textit{skip}skip 不为 000，则说明当前字符需要删去，我们让 skip\textit{skip}skip 减 111。
     *
     * 这样，我们定义两个指针，分别指向两字符串的末尾。每次我们让两指针逆序地遍历两字符串，直到两字符串能够各自确定一个字符，然后将这两个字符进行比较。重复这一过程直到找到的两个字符不相等，或遍历完字符串为止。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/backspace-string-compare/solution/bi-jiao-han-tui-ge-de-zi-fu-chuan-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            //寻找从i及i后中S第一个不被抵消的字符
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            //寻找从j及j后中T第一个不被抵消的字符
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }
}
