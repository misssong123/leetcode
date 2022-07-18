package com.meng.level2.day05;

import java.util.HashMap;
import java.util.Map;

/**
 * 2131. 连接两字母单词得到的最长回文串
 * 给你一个字符串数组 words 。words 中每个元素都是一个包含 两个 小写英文字母的单词。
 *
 * 请你从 words 中选择一些元素并按 任意顺序 连接它们，并得到一个 尽可能长的回文串 。每个元素 至多 只能使用一次。
 *
 * 请你返回你能得到的最长回文串的 长度 。如果没办法得到任何一个回文串，请你返回 0 。
 *
 * 回文串 指的是从前往后和从后往前读一样的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["lc","cl","gg"]
 * 输出：6
 * 解释：一个最长的回文串为 "lc" + "gg" + "cl" = "lcggcl" ，长度为 6 。
 * "clgglc" 是另一个可以得到的最长回文串。
 * 示例 2：
 *
 * 输入：words = ["ab","ty","yt","lc","cl","ab"]
 * 输出：8
 * 解释：最长回文串是 "ty" + "lc" + "cl" + "yt" = "tylcclyt" ，长度为 8 。
 * "lcyttycl" 是另一个可以得到的最长回文串。
 * 示例 3：
 *
 * 输入：words = ["cc","ll","xx"]
 * 输出：2
 * 解释：最长回文串是 "cc" ，长度为 2 。
 * "ll" 是另一个可以得到的最长回文串。"xx" 也是。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 105
 * words[i].length == 2
 * words[i] 仅包含小写英文字母。
 */
public class LongestPalindrome {
    /**
     * 执行用时：
     * 162 ms
     * , 在所有 Java 提交中击败了
     * 12.86%
     * 的用户
     * 内存消耗：
     * 56.7 MB
     * , 在所有 Java 提交中击败了
     * 61.08%
     * 的用户
     * 通过测试用例：
     * 120 / 120
     * @param words
     * @return
     */
    public int longestPalindrome(String[] words) {
        int sum  = 0;
        Map<String,Integer> map = new HashMap<>();
        for(String word : words){
            char first = word.charAt(0);
            char second = word.charAt(1);
            String temp = ""+second+first;
            int count = map.getOrDefault(temp,0);
            if (count > 0){
                map.put(temp,--count);
                sum+=4;
                continue;
            }
            map.put(word,map.getOrDefault(word,0)+1);
        }
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if (entry.getValue() >0){
                String key = entry.getKey();
                if (key.charAt(1) == key.charAt(0)){
                    sum+=2;
                    break;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LongestPalindrome demo = new LongestPalindrome();
        String[] words = {"ll","lb","bb","bx","xx","lx","xx","lx","ll","xb","bx","lb","bb","lb","bl","bb","bx","xl","lb","xx"};
        System.out.println(demo.longestPalindrome(words));
        System.out.println(demo.longestPalindrome1(words));
    }

    /**
     * 方法一：贪心 + 哈希表
     * 思路与算法
     *
     * 根据回文串的定义，回文串可以由奇数或者偶数个 \textit{words}words 中的单词拼接而成，但必须满足以下条件：
     *
     * 如果数量为奇数，那么位于正中间的单词必须是回文字符串（即两个字符相等）；
     *
     * 每个单词和反转后对应位置的单词必须互为反转字符串。
     *
     * 根据上面的两个条件，我们可以得出构造最长回文串的规则：
     *
     * 对于两个字符不同的单词，需要尽可能多的成对选择它和它的反转字符串（如有）；
     *
     * 对于两个字符相同的单词，需要尽可能多的成对选择该单词；
     *
     * 如果按照上述条件挑选后，仍然存在未被选择的两个字符相同的单词（此时该字符串只可能有一个未被选择，且该字符串一定在 \textit{words}words 中出现奇数次），我们可以任意选择一个。
     *
     * 因此，我们用哈希表统计 \textit{words}words 中每个单词的出现次数。随后，我们遍历哈希表的所有元素，并用 \textit{res}res 维护可能构成回文字符串的最长长度，同时用初值为 \texttt{false}false 的布尔变量 \textit{mid}mid 判断是否存在可以作为中心单词的、出现奇数次的回文单词。在遍历到字符串 \textit{word}word 时，我们首先求出它反转后的字符串 \textit{rev}rev，此时根据 \textit{word}word 与 \textit{rev}rev 的关系，有以下两种情况：
     *
     * \textit{word} \not = \textit{rev}word
     * 
     * ​
     *  =rev，此时我们需要统计两者在 \textit{words}words 出现次数的最小值，即为成对选择的最多数目。假设此时对数为 nn，则其对最长回文字符串贡献的字符长度为 4n4n，我们将 \textit{res}res 加上对应值；
     *
     * \textit{word} = \textit{rev}word=rev，此时可以构成的对数为 \lfloor m / 2 \rfloor⌊m/2⌋，即对最长回文字符串贡献的字符长度为 4\lfloor m / 2 \rfloor4⌊m/2⌋，我们同样将 \textit{res}res 加上对应值。除此以外，我们还需要判断 \textit{word}word 的出现次数 mm 是否为奇数：
     *
     * 如果 mm 为奇数，则存在可以作为中心单词的剩余回文单词，我们将 \textit{mid}mid 置为 \texttt{true}true；
     *
     * 如果 mm 为偶数，则不存在可以作为中心单词的剩余回文单词，我们不改变 \textit{mid}mid 的取值。
     *
     * 最后，我们根据 \textit{mid}mid 的取值，判断最长回文串是否含有中心单词。如果 \textit{mid}mid 为 \texttt{true}true，则代表含有，我们将 \textit{res}res 加上 22；反之则没有，我们不进行任何操作。
     *
     * 最后，我们返回 \textit{res}res 作为最长回文串的长度。
     *
     * 细节
     *
     * 在遍历哈希表中的每个单词时，为了避免重复计算成对选择的单词，我们只在 \textit{word}word 的字典序大于等于 \textit{rev}rev 时更新 \textit{res}res。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words/solution/lian-jie-liang-zi-mu-dan-ci-de-dao-de-zu-vs99/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param words
     * @return
     * 执行用时：
     * 97 ms
     * , 在所有 Java 提交中击败了
     * 39.64%
     * 的用户
     * 内存消耗：
     * 56.5 MB
     * , 在所有 Java 提交中击败了
     * 71.79%
     * 的用户
     * 通过测试用例：
     * 120 / 120
     */
    public int longestPalindrome1(String[] words) {
        int ans = 0;
        Map<String, Integer> cnts = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        int oo = 0;
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            String pacur = new String(new char[] { cur.charAt(1), cur.charAt(0) });
            if (cnts.containsKey(pacur)) {
                ans += 4;
                int size = cnts.get(pacur);
                if (size > 1) {
                    cnts.put(pacur, size - 1);
                } else {
                    cnts.remove(pacur);
                }
                if (cur.charAt(1) == cur.charAt(0)) {
                    oo--;
                }
            } else {
                int size = cnts.getOrDefault(cur, 0) + 1;
                cnts.put(cur, size);
                if (cur.charAt(1) == cur.charAt(0)) {
                    oo++;
                }
            }
        }
        ans += oo > 0 ? 2 : 0;
        return ans;
    }

}
