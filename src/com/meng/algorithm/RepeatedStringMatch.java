package com.meng.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 686. 重复叠加字符串匹配
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 *
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 *
 *
 *
 * 示例 1：
 *
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 * 示例 2：
 *
 * 输入：a = "a", b = "aa"
 * 输出：2
 * 示例 3：
 *
 * 输入：a = "a", b = "a"
 * 输出：1
 * 示例 4：
 *
 * 输入：a = "abc", b = "wxyz"
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= a.length <= 104
 * 1 <= b.length <= 104
 * a 和 b 由小写英文字母组成
 */
public class RepeatedStringMatch {
    /**
     * 执行用时：
     * 193 ms
     * , 在所有 Java 提交中击败了
     * 68.19%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 37.83%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     * @param a
     * @param b
     * @return
     */
    public int repeatedStringMatch(String a, String b) {
        int res = -1;
        int count = b.length() / a.length() + 2;
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < count; i++){
            sb.append(a);
        }
        while (sb.length() >0 && sb.toString().contains(b)){
            res = count;
            sb.delete(sb.length()-a.length(),sb.length());
            count--;
        }
        return res;
    }

    static final int kMod1 = 1000000007;
    static final int kMod2 = 1337;

    /**
     * 方法一：Rabin-Karp 算法
     * 思路与算法
     *
     * 命题「存在重复叠加字符串 s_1=a \ldots as
     * 1
     * ​
     *  =a…a，使得字符串 bb 成为叠加后的字符串 s_1s
     * 1
     * ​
     *   的子串」等价于「字符串 bb 成为无限重复叠加字符串 s_2=aa \ldotss
     * 2
     * ​
     *  =aa… 的子串」。而后者成立的前提是任一 s_2[i:\infty], 0 \le i < \textit{len}(a)s
     * 2
     * ​
     *  [i:∞],0≤i<len(a) 以 bb 为前缀，即 bb 可以从第一个叠加的 aa 开始匹配成功。
     *
     * 因此我们可以分两种情况：
     *
     * bb 可以从第一个叠加的 aa 开始匹配成功，则明显匹配的下标越小，最终需要的叠加数目 kk 越小，记成功匹配的最小下标为 \textit{index}index，0 \le \textit{index} < \textit{len}(a)0≤index<len(a)，于是：
     *
     * k = \begin{cases} 1, & \textit{len}(b) \le \textit{len}(a) - \textit{index} \\ \displaystyle{\Big\lceil \frac{\textit{len}(b) - [\textit{len}(a) - \textit{index}]}{\textit{len}(a)} \Big\rceil} + 1, & \textit{len}(b) > \textit{len}(a) - \textit{index} \end{cases}
     * k=
     * ⎩
     * ⎪
     * ⎨
     * ⎪
     * ⎧
     * ​
     *
     * 1,
     * ⌈
     * len(a)
     * len(b)−[len(a)−index]
     * ​
     *  ⌉+1,
     * ​
     *
     * len(b)≤len(a)−index
     * len(b)>len(a)−index
     * ​
     *
     *
     * bb 无法从第一个叠加的 aa 开始匹配成功，说明不存在重复叠加字符串 s_1=a \ldots as
     * 1
     * ​
     *  =a…a，使得字符串 bb 成为叠加后的字符串 s_1=a \ldots as
     * 1
     * ​
     *  =a…a 的子串。
     *
     * 在应用 Rabin-Karp 算法时，被匹配字符串是循环叠加的字符串，所以下标要进行取余操作，并且匹配终止的条件为 bb 开始匹配的位置超过第一个叠加的 aa。我们采用随机数来生成 Rabin-Karp 算法的哈希函数，希望避免后续哈希冲突的发生。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/repeated-string-match/solution/zhong-fu-die-jia-zi-fu-chuan-pi-pei-by-l-vnye/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param a
     * @param b
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 96.14%
     * 的用户
     * 内存消耗：
     * 36.9 MB
     * , 在所有 Java 提交中击败了
     * 86.50%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public int repeatedStringMatch1(String a, String b) {
        int an = a.length(), bn = b.length();
        int index = strStr(a, b);
        if (index == -1) {
            return -1;
        }
        if (an - index >= bn) {
            return 1;
        }
        return (bn + index - an - 1) / an + 2;
    }

    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }

        int k1 = 1000000009;
        int k2 = 1337;
        Random random = new Random();
        int kMod1 = random.nextInt(k1) + k1;
        int kMod2 = random.nextInt(k2) + k2;

        long hashNeedle = 0;
        for (int i = 0; i < m; i++) {
            char c = needle.charAt(i);
            hashNeedle = (hashNeedle * kMod2 + c) % kMod1;
        }
        long hashHaystack = 0, extra = 1;
        for (int i = 0; i < m - 1; i++) {
            hashHaystack = (hashHaystack * kMod2 + haystack.charAt(i % n)) % kMod1;
            extra = (extra * kMod2) % kMod1;
        }
        for (int i = m - 1; (i - m + 1) < n; i++) {
            hashHaystack = (hashHaystack * kMod2 + haystack.charAt(i % n)) % kMod1;
            if (hashHaystack == hashNeedle) {
                return i - m + 1;
            }
            hashHaystack = (hashHaystack - extra * haystack.charAt((i - m + 1) % n)) % kMod1;
            hashHaystack = (hashHaystack + kMod1) % kMod1;
        }
        return -1;
    }

    /**
     * 方法二：Knuth-Morris-Pratt 算法
     *     前言
     *
     *     关于 Knuth-Morris-Pratt 算法的具体实现，读者可以参阅官方题解「28. 实现 strStr()」，笔者就不作详细介绍了。
     *
     *     思路与算法
     *
     *     类似于方法一，我们也可以使用 Knuth-Morris-Pratt 算法来实现字符串匹配的功能。在应用 Knuth-Morris-Pratt 算法时，被匹配字符串是循环叠加的字符串，所以下标要进行取余操作，并且匹配终止的条件为 bb 开始匹配的位置超过第一个叠加的 aa。
     *
     *     代码
     *
     *     C++JavaC#CGolangJavaScriptPython3
     * @param a
     * @param b
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 91.33%
     * 的用户
     * 内存消耗：
     * 36.9 MB
     * , 在所有 Java 提交中击败了
     * 88.67%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
        public int repeatedStringMatch2(String a, String b) {
            int an = a.length(), bn = b.length();
            int index = strStr2(a, b);
            if (index == -1) {
                return -1;
            }
            if (an - index >= bn) {
                return 1;
            }
            return (bn + index - an - 1) / an + 2;
        }

        public int strStr2(String haystack, String needle) {
            int n = haystack.length(), m = needle.length();
            if (m == 0) {
                return 0;
            }
            int[] pi = new int[m];
            for (int i = 1, j = 0; i < m; i++) {
                while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                    j = pi[j - 1];
                }
                if (needle.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                pi[i] = j;
            }
            for (int i = 0, j = 0; i - j < n; i++) { // b 开始匹配的位置是否超过第一个叠加的 a
                while (j > 0 && haystack.charAt(i % n) != needle.charAt(j)) { // haystack 是循环叠加的字符串，所以取 i % n
                    j = pi[j - 1];
                }
                if (haystack.charAt(i % n) == needle.charAt(j)) {
                    j++;
                }
                if (j == m) {
                    return i - m + 1;
                }
            }
            return -1;
        }


    public static void main(String[] args) {
        String s = "cdabcdab";
        String[] strs = s.split("abcd");
        System.out.println(Arrays.toString(strs));
    }
}
