package com.meng.algorithm;

import java.util.*;

/**
 * 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 *
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 *
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *
 *
 * 提示：
 *
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 */
public class MaxProduct {
    Set<Character> cache = new HashSet<>();

    /**
     * 执行用时：
     * 104 ms
     * , 在所有 Java 提交中击败了
     * 24.67%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 29.50%
     * 的用户
     * 通过测试用例：
     * 167 / 167
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int max = 0;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        int len = words.length;
        for(int i = 0 ; i < len ; i++){
            cache.clear();
            for(char c : words[i].toCharArray()){
                cache.add(c);
            }
            for(int j = i+1 ; j < len ; j++){
                if (words[i].length() * words[j].length() <= max){
                    break;
                }
                if (diffWord(words[j])){
                    max = Math.max(words[i].length() * words[j].length(),max);
                }
            }
        }
        return max;
    }
    public boolean diffWord(String word){
        for(char c : word.toCharArray()){
            if (cache.contains(c)){
                return false;
            }
        }
        return true;
    }

    /**
     * 方法一：位运算
     * 为了得到最大单词长度乘积，朴素的做法是，遍历字符串数组 \textit{words}words 中的每一对单词，判断这一对单词是否有公共字母，如果没有公共字母，则用这一对单词的长度乘积更新最大单词长度乘积。
     *
     * 用 nn 表示数组 \textit{words}words 的长度，用 l_il
     * i
     * ​
     *   表示单词 \textit{words}[i]words[i] 的长度，其中 0 \le i < n0≤i<n，则上述做法需要遍历字符串数组 \textit{words}words 中的每一对单词，对于下标为 ii 和 jj 的单词，其中 i < ji<j，需要 O(l_i \times l_j)O(l
     * i
     * ​
     *  ×l
     * j
     * ​
     *  ) 的时间判断是否有公共字母和计算长度乘积。因此上述做法的时间复杂度是 O(\sum_{0 \le i < j < n} l_i \times l_j)O(∑
     * 0≤i<j<n
     * ​
     *  l
     * i
     * ​
     *  ×l
     * j
     * ​
     *  )，该时间复杂度高于 O(n^2)O(n
     * 2
     *  )。
     *
     * 如果可以将判断两个单词是否有公共字母的时间复杂度降低到 O(1)O(1)，则可以将总时间复杂度降低到 O(n^2)O(n
     * 2
     *  )。可以使用位运算预处理每个单词，通过位运算操作判断两个单词是否有公共字母。由于单词只包含小写字母，共有 2626 个小写字母，因此可以使用位掩码的最低 2626 位分别表示每个字母是否在这个单词中出现。将 \text{a}a 到 \text{z}z 分别记为第 00 个字母到第 2525 个字母，则位掩码的从低到高的第 ii 位是 11 当且仅当第 ii 个字母在这个单词中，其中 0 \le i \le 250≤i≤25。
     *
     * 用数组 \textit{masks}masks 记录每个单词的位掩码表示。计算数组 \textit{masks}masks 之后，判断第 ii 个单词和第 jj 个单词是否有公共字母可以通过判断 \textit{masks}[i]~\&~\textit{masks}[j]masks[i] & masks[j] 是否等于 00 实现，当且仅当 \textit{masks}[i]~\&~\textit{masks}[j] = 0masks[i] & masks[j]=0 时第 ii 个单词和第 jj 个单词没有公共字母，此时使用这两个单词的长度乘积更新最大单词长度乘积。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths/solution/zui-da-dan-ci-chang-du-cheng-ji-by-leetc-lym9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param words
     * @return
     */
    public int maxProduct1(String[] words) {
        int length = words.length;
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int maxProd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }

    /**
     * 方法二：位运算优化
     * 方法一需要对数组 \textit{words}words 中的每个单词计算位掩码，如果数组 \textit{words}words 中存在由相同的字母组成的不同单词，则会造成不必要的重复计算。例如单词 \text{meet}meet 和 \text{met}met 包含的字母相同，只是字母的出现次数和单词长度不同，因此这两个单词的位掩码表示也相同。由于判断两个单词是否有公共字母是通过判断两个单词的位掩码的按位与运算实现，因此在位掩码相同的情况下，单词的长度不会影响是否有公共字母，当两个位掩码的按位与运算等于 00 时，为了得到最大单词长度乘积，这两个位掩码对应的单词长度应该尽可能大。根据上述分析可知，如果有多个单词的位掩码相同，则只需要记录该位掩码对应的最大单词长度即可。
     *
     * 可以使用哈希表记录每个位掩码对应的最大单词长度，然后遍历哈希表中的每一对位掩码，如果这一对位掩码的按位与运算等于 00，则用这一对位掩码对应的长度乘积更新最大单词长度乘积。
     *
     * 由于每个单词的位掩码都不等于 00，任何一个不等于 00 的数和自身做按位与运算的结果一定不等于 00，因此当一对位掩码的按位与运算等于 00 时，这两个位掩码一定是不同的，对应的单词也一定是不同的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths/solution/zui-da-dan-ci-chang-du-cheng-ji-by-leetc-lym9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param words
     * @return
     * 执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 41.67%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 36.75%
     * 的用户
     * 通过测试用例：
     * 167 / 167
     */
    public int maxProduct2(String[] words) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = words.length;
        for (int i = 0; i < length; i++) {
            int mask = 0;
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                mask |= 1 << (word.charAt(j) - 'a');
            }
            if (wordLength > map.getOrDefault(mask, 0)) {
                map.put(mask, wordLength);
            }
        }
        int maxProd = 0;
        Set<Integer> maskSet = map.keySet();
        for (int mask1 : maskSet) {
            int wordLength1 = map.get(mask1);
            for (int mask2 : maskSet) {
                if ((mask1 & mask2) == 0) {
                    int wordLength2 = map.get(mask2);
                    maxProd = Math.max(maxProd, wordLength1 * wordLength2);
                }
            }
        }
        return maxProd;
    }


    public static void main(String[] args) {
        String [] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
        MaxProduct demo = new MaxProduct();
        System.out.println(demo.maxProduct(words));
    }
}
