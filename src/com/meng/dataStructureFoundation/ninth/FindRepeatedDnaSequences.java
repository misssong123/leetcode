package com.meng.dataStructureFoundation.ninth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 187. 重复的DNA序列
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 *
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 *
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 105
 * s[i]=='A'、'C'、'G' or 'T'
 */
public class FindRepeatedDnaSequences {
    /**
     * 执行用时：
     * 20 ms
     * , 在所有 Java 提交中击败了
     * 64.04%
     * 的用户
     * 内存消耗：
     * 49.8 MB
     * , 在所有 Java 提交中击败了
     * 7.29%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        int len = s.length();
        if (len > 11){
            Map<String,Integer> cache = new HashMap<>();
            for(int i = 0 ; i <= len - 10 ; i++){
                String temp = s.substring(i,i+10);
                if (cache.get(temp) == null){
                    cache.put(temp,1);
                }else {
                    cache.put(temp,2);
                }
            }
            for(Map.Entry<String,Integer> entry : cache.entrySet()){
                if (entry.getValue() > 1){
                    list.add(entry.getKey());
                }
            }
        }
        return list;
    }

    /**
     * 方法一：哈希表
     * 我们可以用一个哈希表统计 ss 所有长度为 1010 的子串的出现次数，返回所有出现次数超过 1010 的子串。
     *
     * 代码实现时，可以一边遍历子串一边记录答案，为了不重复记录答案，我们只统计当前出现次数为 22 的子串。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences/solution/zhong-fu-de-dnaxu-lie-by-leetcode-soluti-z8zn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 22 ms
     * , 在所有 Java 提交中击败了
     * 51.15%
     * 的用户
     * 内存消耗：
     * 49.7 MB
     * , 在所有 Java 提交中击败了
     * 9.65%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    public List<String> findRepeatedDnaSequences1(String s) {
        List<String> ans = new ArrayList<String>();
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        int n = s.length();
        for (int i = 0; i <= n - L; ++i) {
            String sub = s.substring(i, i + L);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }

    static final int L = 10;
    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    /**
     * 方法二：哈希表 + 滑动窗口 + 位运算
     * 由于 ss 中只含有 44 种字符，我们可以将每个字符用 22 个比特表示，即：
     *
     * \texttt{A}A 表示为二进制 \texttt{00}00；
     * \texttt{C}C 表示为二进制 \texttt{01}01；
     * \texttt{G}G 表示为二进制 \texttt{10}10；
     * \texttt{T}T 表示为二进制 \texttt{11}11。
     * 如此一来，一个长为 1010 的字符串就可以用 2020 个比特表示，而一个 \texttt{int}int 整数有 3232 个比特，足够容纳该字符串，因此我们可以将 ss 的每个长为 1010 的子串用一个 \texttt{int}int 整数表示（只用低 2020 位）。
     *
     * 注意到上述字符串到整数的映射是一一映射，每个整数都对应着一个唯一的字符串，因此我们可以将方法一中的哈希表改为存储每个长为 1010 的子串的整数表示。
     *
     * 如果我们对每个长为 1010 的子串都单独计算其整数表示，那么时间复杂度仍然和方法一一样为 O(NL)O(NL)。为了优化时间复杂度，我们可以用一个大小固定为 1010 的滑动窗口来计算子串的整数表示。设当前滑动窗口对应的整数表示为 xx，当我们要计算下一个子串时，就将滑动窗口向右移动一位，此时会有一个新的字符进入窗口，以及窗口最左边的字符离开窗口，这些操作对应的位运算，按计算顺序表示如下：
     *
     * 滑动窗口向右移动一位：x = x << 2，由于每个字符用 22 个比特表示，所以要左移 22 位；
     * 一个新的字符 \textit{ch}ch 进入窗口：x = x | bin[ch]，这里 \textit{bin}[\textit{ch}]bin[ch] 为字符 \textit{ch}ch 的对应二进制；
     * 窗口最左边的字符离开窗口：x = x & ((1 << 20) - 1)，由于我们只考虑 xx 的低 2020 位比特，需要将其余位置零，即与上 (1 << 20) - 1。
     * 将这三步合并，就可以用 O(1)O(1) 的时间计算出下一个子串的整数表示，即 x = ((x << 2) | bin[ch]) & ((1 << 20) - 1)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences/solution/zhong-fu-de-dnaxu-lie-by-leetcode-soluti-z8zn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 27 ms
     * , 在所有 Java 提交中击败了
     * 18.60%
     * 的用户
     * 内存消耗：
     * 47.1 MB
     * , 在所有 Java 提交中击败了
     * 31.60%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> ans = new ArrayList<String>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n - L; ++i) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;
    }


}
