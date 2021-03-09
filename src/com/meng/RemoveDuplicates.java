package com.meng;

import java.util.Stack;

/**
 * 1047. 删除字符串中的所有相邻重复项
 *
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 *
 *
 * 示例：
 *
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *
 *
 *
 * 提示：
 *
 *     1 <= S.length <= 20000
 *     S 仅由小写英文字母组成。
 */
public class RemoveDuplicates {
    /**
     * 执行用时：42 ms, 在所有 Java 提交中击败了25.56% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了24.38% 的用户
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        for(char c : S.toCharArray()){
            if (sb.length()>0 && sb.substring(sb.length()-1).equals(c+"")){
                sb.delete(sb.length()-1,sb.length());
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 执行用时：26 ms, 在所有 Java 提交中击败了54.16% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了20.84% 的用户
     * @param S
     * @return
     */
    public String removeDuplicates1(String S) {
        Stack<Character> stack = new Stack<>();
        for(char c : S.toCharArray()){
            if (stack.size()>0 && stack.peek().equals(c)){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        if (stack.size()>0){
            StringBuilder sb = new StringBuilder();
            for(char c : stack)
                sb.append(c);
            return sb.toString();
        }
        return "";
    }
    /**
     * 方法一：栈
     *
     * 充分理解题意后，我们可以发现，当字符串中同时有多组相邻重复项时，我们无论是先删除哪一个，都不会影响最终的结果。因此我们可以从左向右顺次处理该字符串。
     *
     * 而消除一对相邻重复项可能会导致新的相邻重复项出现，如从字符串 abba\text{abba}abba 中删除 bb\text{bb}bb 会导致出现新的相邻重复项 aa\text{aa}aa 出现。因此我们需要保存当前还未被删除的字符。一种显而易见的数据结构呼之欲出：栈。我们只需要遍历该字符串，如果当前字符和栈顶字符相同，我们就贪心地将其消去，否则就将其入栈即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xi-4ohr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：13 ms, 在所有 Java 提交中击败了77.81% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了90.58% 的用户
     */
    public String removeDuplicates2(String S) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }
}
