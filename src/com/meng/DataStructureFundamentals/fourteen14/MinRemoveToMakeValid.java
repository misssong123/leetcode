package com.meng.DataStructureFundamentals.fourteen14;

import java.util.ArrayList;
import java.util.List;

/**
 * 1249. 移除无效的括号
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 *
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 *
 * 请返回任意一个合法字符串。
 *
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 *
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 *
 *
 * 示例 1：
 *
 * 输入：s = "lee(t(c)o)de)"
 * 输出："lee(t(c)o)de"
 * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
 * 示例 2：
 *
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 * 示例 3：
 *
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 可能是 '('、')' 或英文小写字母
 */
public class MinRemoveToMakeValid {
    /**
     *执行用时：
     * 17 ms
     * , 在所有 Java 提交中击败了
     * 70.75%
     * 的用户
     * 内存消耗：
     * 43.5 MB
     * , 在所有 Java 提交中击败了
     * 5.01%
     * 的用户
     * 通过测试用例：
     * 62 / 62
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for(int i = 0 ; i < chars.length ; i++ ){
            char c = chars[i];
            if (c == '('){
                left.add(i);
            }else if (c == ')'){
                right.add(i);
                if (right.size() > left.size()){
                    chars[right.remove(0)] = ' ';
                }
            }
        }
        left = new ArrayList<>();
        right = new ArrayList<>();
        for(int i = chars.length ; i >=0 ; i-- ){
            char c = chars[i];
            if (c == '('){
                left.add(i);
                if (left.size() > right.size()){
                    chars[right.remove(left.size()-1)] = ' ';
                }
            }else if (c == ')'){
                right.add(i);
            }
        }
        String res = new String(chars);
        return res.replace(" ","");
    }

    /**
     *方法三：改进的使用 StringBuilder 的两步法
     * 思路
     *
     * 这是方法二的简化版本，只需要保持平衡即可，不需要栈，也不需要执行两次完整的字符串扫描。在完成第一步扫描后，查看有多少个需要删除的 "("，然后从右侧开始扫描，删除对应个数的 "(" 即可。事实证明，如果删除最右边的 "("，一定可以保证字符串平衡。
     *
     * 这样可能很难理解，请看以下分析。
     *
     * 完成第一步扫描后，字符串中就不包含无效的 ")"。接下来考虑一种算法可以删除多余的 "("，使字符串有效。
     *
     *
     *
     * 要让一个 "(" 有效，必须在该 "(" 后面有比 "(" 数量更多的 ")"。在 s 中如果所有的 "(" 和 ")" 都一一匹配，则 s 是有效的。
     *
     * 因此，从右边开始根据余量删除 "("，每次删除都可以在保证字符串有效的情况下，修改余量。如果任何的 "(" 都是无效的，则说明在第一个 "(" 之前就存在 ")" 了，但是无效的 ")" 在第一步时就已经删除了，所以第二步中不存在这样的情况。
     *
     * 综上，这是一个可行的方法。
     *
     * 算法
     *
     * 为了避免第二步迭代（这会增加算法的复杂性），需要记录第一步扫描中字符串有多少个 "("。这样就可以在第二次扫描时计算出保留的 "(" 数量和删除的 "(" 数量。一旦达到足够数量的 "("，就可以直接删除后面的 "("。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses/solution/yi-chu-wu-xiao-gua-hao-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 16 ms
     * , 在所有 Java 提交中击败了
     * 73.98%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 55.48%
     * 的用户
     * 通过测试用例：
     * 62 / 62
     */
    public String minRemoveToMakeValid1(String s) {

        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            } if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) continue;
            }
            result.append(c);
        }

        return result.toString();
    }

}
