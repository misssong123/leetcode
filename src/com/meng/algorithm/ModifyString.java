package com.meng.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 1576. 替换所有的问号
 * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
 *
 * 注意：你 不能 修改非 '?' 字符。
 *
 * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
 *
 * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "?zs"
 * 输出："azs"
 * 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
 * 示例 2：
 *
 * 输入：s = "ubv?w"
 * 输出："ubvaw"
 * 解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
 * 示例 3：
 *
 * 输入：s = "j?qg??b"
 * 输出："jaqgacb"
 * 示例 4：
 *
 * 输入：s = "??yw?ipkj?"
 * 输出："acywaipkja"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 *
 * s 仅包含小写英文字母和 '?' 字符
 */
public class ModifyString {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 7.09%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 7.48%
     * 的用户
     * 通过测试用例：
     * 776 / 776
     * @param s
     * @return
     */
    public String modifyString(String s) {
        StringBuffer sb = new StringBuffer();
        Set<Character> set = new HashSet<>(4);
        int len = s.length()-1;
        while (len >=0){
            char temp = s.charAt(len);
            if (temp == '?'){
                if (sb.length()>0){
                    set.add(sb.charAt(sb.length()-1));
                }
                if (len>0 && s.charAt(len-1) != '?'){
                    set.add(s.charAt(len-1));
                }
                for(int i=0 ; i < 26 ; i++){
                    if (set.contains((char)('a'+i))){
                        continue;
                    }else {
                        sb.append((char)('a'+i));
                        break;
                    }
                }
            }else {
                sb.append(temp);
            }
            len--;
            set.clear();
        }
        return sb.reverse().toString();
    }

    /**
     * 方法一：遍历扫描
     * 题目要求将字符串 ss 中的 \texttt{`?'}‘?’ 转换为若干小写字母，转换后的字母与该字母的前后字母均不相同。遍历字符串 ss，如果遇到第 ii 个字符 s[i]s[i] 为 \texttt{`?'}‘?’ 时，此时直接在英文字母 \texttt{`a'-`z'}‘a’-‘z’ 中找到一个与 s[i-1]s[i−1] 和 s[i+1]s[i+1] 均不相同的字母进行替换即可。
     *
     * 在替换时，实际不需要遍历所有的小写字母，只需要遍历三个互不相同的字母，就能保证一定找到一个与前后字符均不相同的字母，在此我们可以限定三个不同的字母为 \texttt{(`a',`b',`c')}(‘a’,‘b’,‘c’)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/solution/ti-huan-suo-you-de-wen-hao-by-leetcode-s-f7mp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.4 MB
     * , 在所有 Java 提交中击败了
     * 64.96%
     * 的用户
     * 通过测试用例：
     * 776 / 776
     */
    public String modifyString1(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            if (arr[i] == '?') {
                for (char ch = 'a'; ch <= 'c'; ++ch) {
                    if ((i > 0 && arr[i - 1] == ch) || (i < n - 1 && arr[i + 1] == ch)) {
                        continue;
                    }
                    arr[i] = ch;
                    break;
                }
            }
        }
        return new String(arr);
    }
    public static void main(String[] args) {
        ModifyString demo = new ModifyString();
        System.out.println(demo.modifyString("??yw?ipkj?"));
    }
}
