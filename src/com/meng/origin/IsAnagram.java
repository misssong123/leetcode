package com.meng.origin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsAnagram {
    /**
     * 执行用时：22 ms, 在所有 Java 提交中击败了10.29% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了18.85% 的用户
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null)
            return false;
        if (t.length() != s.length())
            return false;
        int m = s.length() ,n=s.length();
        Map<String ,Integer> map = new HashMap<>();
        for(int i = 0 ; i < m ; i++){
            String k = s.substring(i,i+1);
            map.put(k,map.getOrDefault(k,0)+1);
        }
        Map<String ,Integer> mapT = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            String k = t.substring(i,i+1);
            if (map.get(k)==null)
                return false;
            mapT.put(k,mapT.getOrDefault(k,0)+1);
        }
        for(String key : mapT.keySet()){
            if (!map.get(key).equals(mapT.get(key)))
                return false;
            map.remove(key);
        }
        if (map.size() != 0)
            return false;
        return true;
    }
    /**
     * 官方解法1
     * 方法一：排序
     *
     * ttt 是 sss 的异位词等价于「两个字符串排序后相等」。因此我们可以对字符串 sss 和 ttt 分别排序，看排序后的字符串是否相等即可判断。此外，如果 sss 和 ttt 的长度不同，ttt 必然不是 sss 的异位词。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了85.82% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了88.11% 的用户
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
    /**
     * 官方解法2
     * 方法二：哈希表
     *
     * 从另一个角度考虑，ttt 是 sss 的异位词等价于「两个字符串中字符出现的种类和次数均相等」。由于字符串只包含 262626 个小写字母，因此我们可以维护一个长度为 262626 的频次数组 table\textit{table}table，先遍历记录字符串 sss 中字符出现的频次，然后遍历字符串 ttt，减去 table\textit{table}table 中对应的频次，如果出现 table[i]<0\textit{table}[i]<0table[i]<0，则说明 ttt 包含一个不在 sss 中的额外字符，返回 false\text{false}false 即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：4 ms, 在所有 Java 提交中击败了64.37% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了92.38% 的用户
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * 官方解法3
     * 对于进阶问题，Unicode\text{Unicode}Unicode 是为了解决传统字符编码的局限性而产生的方案，它为每个语言中的字符规定了一个唯一的二进制编码。而 Unicode\text{Unicode}Unicode 中可能存在一个字符对应多个字节的问题，为了让计算机知道多少字节表示一个字符，面向传输的编码方式的 UTF−8\text{UTF}-8UTF−8 和 UTF−16\text{UTF}-16UTF−16 也随之诞生逐渐广泛使用，具体相关的知识读者可以继续查阅相关资料拓展视野，这里不再展开。
     *
     * 回到本题，进阶问题的核心点在于「字符是离散未知的」，因此我们用哈希表维护对应字符的频次即可。同时读者需要注意 Unicode\text{Unicode}Unicode 一个字符可能对应多个字节的问题，不同语言对于字符串读取处理的方式是不同的。
     *
     * class So
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：18 ms, 在所有 Java 提交中击败了15.86% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了34.50% 的用户
     */
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if (table.get(ch) < 0) {
                return false;
            }
        }
        return true;
    }

}
