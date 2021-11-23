package com.meng.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 859. 亲密字符串
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 *
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 *
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ab", goal = "ba"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
 * 示例 2：
 *
 * 输入：s = "ab", goal = "ab"
 * 输出：false
 * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
 * 示例 3：
 *
 * 输入：s = "aa", goal = "aa"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
 * 示例 4：
 *
 * 输入：s = "aaaaaaabc", goal = "aaaaaaacb"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length, goal.length <= 2 * 104
 * s 和 goal 由小写英文字母组成
 */
public class BuddyStrings {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 65.61%
     * 的用户
     * 内存消耗：
     * 38.5 MB
     * , 在所有 Java 提交中击败了
     * 27.76%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     * @param s
     * @param goal
     * @return
     */
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length() || goal.length() == 1){
            return false;
        }
        int first = -1,second = -1,len=goal.length();
        for(int i = 0 ; i < len ; i++){
            if (s.charAt(i)==goal.charAt(i)){
                continue;
            }else {
                if (first == -1){
                    first = i;
                }else if(second == -1){
                    second = i;
                }else {
                    return false;
                }
            }
        }
        if (first == -1){
            Set<Character> set = new HashSet<>();
            for(int i = 0 ; i < len ; i++){
                if (!set.add(s.charAt(i))){
                    return true;
                }
            }
        }else if (second == -1){
            return false;
        }else {
            if (s.charAt(first) == goal.charAt(second)&& s.charAt(second) == goal.charAt(first)){
                return true;
            }
        }
        return false;
    }

    /**
     *方法一：枚举
     * 思路与算法
     *
     * 设两个字符串分别为 ss 和 \textit{goal}goal，其中 s[i]s[i] 表示 ss 的第 ii 个字符，其中 \textit{goal}[i]goal[i] 表示 \textit{goal}goal 的第 ii 个字符。如果 s[i] = \textit{goal}[i]s[i]=goal[i]，我们就说 ii 是匹配的，否则称 ii 是不匹配的。亲密字符串定义为：需要交换 ss 中的第 ii 个字符 s[i]s[i] 与 \textit{s}s 中第 jj 个字符且满足 i \neq ji
     * 
     * ​
     *  =j，交换后 ss 与 \textit{goal}goal 相等。亲密字符串的两个字符串需要交换一次索引不相等的两个字符后相等。
     *
     * 如果满足交换 s[i]s[i] 和 s[j]s[j] 后两个字符串相等，那么需要满足以下几个条件使得 ss 和 \textit{goal}goal 为亲密字符串：
     *
     * 字符串 ss 的长度与字符串 \textit{goal}goal 的长度相等；
     * 存在 i \neq ji
     * 
     * ​
     *  =j 且满足 s[i] = \textit{goal}[j]s[i]=goal[j] 以及 s[j] = \textit{goal}[i]s[j]=goal[i]，实际在 s[i], s[j], \textit{goal}[i], \textit{goal}[j]s[i],s[j],goal[i],goal[j] 这四个自由变量中，只存在两种情况：
     * 满足 s[i] = s[j]s[i]=s[j]：则此时必然满足 s[i] = s[j] = \textit{goal}[i] = \textit{goal}[j]s[i]=s[j]=goal[i]=goal[j]，字符串 ss 与 \textit{goal}goal 相等，我们应当能够在 ss 中找到两个不同的索引 i,ji,j，且满足 s[i] = s[j]s[i]=s[j]，如果能够找到两个索引不同但值相等的字符则满足 ss 与 \textit{goal}goal 为亲密字符串；否则不为亲密字符串。
     * 满足 s[i] \neq s[j]s[i]
     * 
     * ​
     *  =s[j]：满足 s[i] = \textit{goal}[j], s[j] = \textit{goal}[i], s[i] \neq s[j]s[i]=goal[j],s[j]=goal[i],s[i]
     * 
     * ​
     *  =s[j] 的情况下，两个字符串 ss 与 \textit{goal}goal 除了索引 i,ji,j 以外的字符都是匹配的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/buddy-strings/solution/qin-mi-zi-fu-chuan-by-leetcode-solution-yyis/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param goal
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.45%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 70.58%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
        public boolean buddyStrings1(String s, String goal) {
            if (s.length() != goal.length()) {
                return false;
            }

            if (s.equals(goal)) {
                int[] count = new int[26];
                for (int i = 0; i < s.length(); i++) {
                    count[s.charAt(i) - 'a']++;
                    if (count[s.charAt(i) - 'a'] > 1) {
                        return true;
                    }
                }
                return false;
            } else {
                int first = -1, second = -1;
                for (int i = 0; i < goal.length(); i++) {
                    if (s.charAt(i) != goal.charAt(i)) {
                        if (first == -1)
                            first = i;
                        else if (second == -1)
                            second = i;
                        else
                            return false;
                    }
                }

                return (second != -1 && s.charAt(first) == goal.charAt(second) &&
                        s.charAt(second) == goal.charAt(first));
            }
        }
}
