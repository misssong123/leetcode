package com.meng.dynamicprogramming.nine09;

import java.util.*;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class WordBreak {
    boolean flag = false;

    /**
     * 超时
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Integer max = wordDict.stream().map(e -> e.length()).max(Integer::compareTo).get();
        dfs(0,1,max,s,wordDict);
        return flag;
    }

    private void dfs(int index, int len, Integer max, String source, List<String> wordDict) {
        if (!flag){
            //单词长度不能大于词组最大长度
            if (len > max || index + len > source.length()){
                return;
            }
            String s = source.substring(index,index+len);
            System.out.println(s);
            if (wordDict.contains(s)){
                if (index + len == source.length()){
                    flag = true;
                    return;
                }
                dfs(index+len,1,max,source,wordDict);
            }
            dfs(index,len+1,max,source,wordDict);
        }
    }

    public static void main(String[] args) {
        WordBreak demo = new WordBreak();
        System.out.println(demo.wordBreak("aaaaaaa", Arrays.asList("aaaa","aaa")));
    }

    /**
     * 方法一：动态规划
     * 思路和算法
     *
     * 我们定义 \textit{dp}[i]dp[i] 表示字符串 ss 前 ii 个字符组成的字符串 s[0..i-1]s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词。从前往后计算考虑转移方程，每次转移的时候我们需要枚举包含位置 i-1i−1 的最后一个单词，看它是否出现在字典中以及除去这部分的字符串是否合法即可。公式化来说，我们需要枚举 s[0..i-1]s[0..i−1] 中的分割点 jj ，看 s[0..j-1]s[0..j−1] 组成的字符串 s_1s
     * 1
     * ​
     *  （默认 j = 0j=0 时 s_1s
     * 1
     * ​
     *   为空串）和 s[j..i-1]s[j..i−1] 组成的字符串 s_2s
     * 2
     * ​
     *   是否都合法，如果两个字符串均合法，那么按照定义 s_1s
     * 1
     * ​
     *   和 s_2s
     * 2
     * ​
     *   拼接成的字符串也同样合法。由于计算到 \textit{dp}[i]dp[i] 时我们已经计算出了 \textit{dp}[0..i-1]dp[0..i−1] 的值，因此字符串 s_1s
     * 1
     * ​
     *   是否合法可以直接由 dp[j]dp[j] 得知，剩下的我们只需要看 s_2s
     * 2
     * ​
     *   是否合法即可，因此我们可以得出如下转移方程：
     *
     * \textit{dp}[i]=\textit{dp}[j]\ \&\&\ \textit{check}(s[j..i-1])
     * dp[i]=dp[j] && check(s[j..i−1])
     *
     * 其中 \textit{check}(s[j..i-1])check(s[j..i−1]) 表示子串 s[j..i-1]s[j..i−1] 是否出现在字典中。
     *
     * 对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断，同时也可以做一些简单的剪枝，枚举分割点的时候倒着枚举，如果分割点 jj 到 ii 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举，但是需要注意的是下面的代码给出的是不带剪枝的写法。
     *
     * 对于边界条件，我们定义 \textit{dp}[0]=truedp[0]=true 表示空串且合法。
     *
     * 有能力的读者也可以考虑怎么结合字典树 \text{Trie}Trie 来实现，这里不再展开。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param wordDict
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 74.89%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 71.91%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
