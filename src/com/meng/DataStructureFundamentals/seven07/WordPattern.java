package com.meng.DataStructureFundamentals.seven07;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 290. 单词规律
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 *
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= pattern.length <= 300
 * pattern 只包含小写英文字母
 * 1 <= s.length <= 3000
 * s 只包含小写英文字母和 ' '
 * s 不包含 任何前导或尾随对空格
 * s 中每个单词都被 单个空格 分隔
 */
public class WordPattern {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 61.46%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 43.37%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.split("\t");
        int len = pattern.length();
        if (pattern.length() == len){
            Map<Character,Integer> cachePattern = new HashMap<>();
            Map<String,Integer> sCache = new HashMap<>();
            char[] chars = pattern.toCharArray();
            for(int i = 0 ; i < len ; i++){
                Integer p = cachePattern.get(chars[i]);
                Integer t = sCache.get(strs[i]);
                if ((p==null && t != null) || !Objects.equals(p,t)){
                    return false;
                }
                cachePattern.put(chars[i],p==null?i:p);
                sCache.put(strs[i],t==null?i:t);
            }
            return true;
        }

        return false;
    }

    /**
     * 方法一：哈希表
     * 思路及解法
     *
     * 在本题中，我们需要判断字符与字符串之间是否恰好一一对应。即任意一个字符都对应着唯一的字符串，任意一个字符串也只被唯一的一个字符对应。在集合论中，这种关系被称为「双射」。
     *
     * 想要解决本题，我们可以利用哈希表记录每一个字符对应的字符串，以及每一个字符串对应的字符。然后我们枚举每一对字符与字符串的配对过程，不断更新哈希表，如果发生了冲突，则说明给定的输入不满足双射关系。
     *
     * 在实际代码中，我们枚举 \textit{pattern}pattern 中的每一个字符，利用双指针来均摊线性地找到该字符在 \textit{str}str 中对应的字符串。每次确定一个字符与字符串的组合，我们就检查是否出现冲突，最后我们再检查两字符串是否比较完毕即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/word-pattern/solution/dan-ci-gui-lu-by-leetcode-solution-6vqv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param pattern
     * @param str
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.4 MB
     * , 在所有 Java 提交中击败了
     * 16.21%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    public boolean wordPattern1(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }

}
