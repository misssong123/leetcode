package com.meng.algorithm;

/**
 * 1332. 删除回文子序列
 * 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
 *
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 *
 * 「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
 *
 * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ababa"
 * 输出：1
 * 解释：字符串本身就是回文序列，只需要删除一次。
 * 示例 2：
 *
 * 输入：s = "abb"
 * 输出：2
 * 解释："abb" -> "bb" -> "".
 * 先删除回文子序列 "a"，然后再删除 "bb"。
 * 示例 3：
 *
 * 输入：s = "baabb"
 * 输出：2
 * 解释："baabb" -> "b" -> "".
 * 先删除回文子序列 "baab"，然后再删除 "b"。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅包含字母 'a'  和 'b'
 */
public class RemovePalindromeSub {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.4 MB
     * , 在所有 Java 提交中击败了
     * 18.28%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     * @param s
     * @return
     */
    public int removePalindromeSub(String s) {
        int len = s.length();
        for(int i = 0 ; i < len / 2 ; i++){
            if (s.charAt(i) != s.charAt(len-i-1)){
                return 2;
            }
        }
        return 1;
    }

    /**
     *方法一：直接判断
     * 由于字符串本身只含有字母 \texttt{`a'}‘a’ 和 \texttt{`b'}‘b’ 两种字符，题目要求每次删除回文子序列（不一定连续）而使得字符串最终为空。题目中只包含两种不同的字符，由于相同的字符组成的子序列一定是回文子序列，因此最多只需要删除 22 次即可删除所有的字符。删除判断如下：
     *
     * 如果该字符串本身为回文串，此时只需删除 11 次即可，删除次数为 11。
     * 如果该字符串本身不是回文串，此时只需删除 22 次即可，比如可以先删除所有的 \texttt{`a'}‘a’，再删除所有的 \texttt{`b'}‘b’，删除次数为 22。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-palindromic-subsequences/solution/shan-chu-hui-wen-zi-xu-lie-by-leetcode-s-tqtb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.2 MB
     * , 在所有 Java 提交中击败了
     * 54.48%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     */
    public int removePalindromeSub1(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; ++i) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return 2;
            }
        }
        return 1;
    }

}
