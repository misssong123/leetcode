package com.meng.algorithm;

/**
 * 748. 最短补全词
 * 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
 *
 * 补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
 *
 * 在匹配 licensePlate 中的字母时：
 *
 * 忽略 licensePlate 中的 数字和空格 。
 * 不区分大小写。
 * 如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
 * 例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
 *
 * 请你找出并返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 最靠前的 那个。
 *
 *
 *
 * 示例 1：
 *
 * 输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 * 输出："steps"
 * 解释：最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
 * "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
 * "steps" 包含 "t"、"p" 和两个 "s"。
 * "stripe" 缺一个 "s"。
 * "stepple" 缺一个 "s"。
 * 因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。
 * 示例 2：
 *
 * 输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 * 输出："pest"
 * 解释：licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
 * 示例 3：
 *
 * 输入：licensePlate = "Ah71752", words = ["suggest","letter","of","husband","easy","education","drug","prevent","writer","old"]
 * 输出："husband"
 * 示例 4：
 *
 * 输入：licensePlate = "OgEu755", words = ["enough","these","play","wide","wonder","box","arrive","money","tax","thus"]
 * 输出："enough"
 * 示例 5：
 *
 * 输入：licensePlate = "iMSlpe4", words = ["claim","consumer","student","camera","public","never","wonder","simple","thought","use"]
 * 输出："simple"
 *
 *
 * 提示：
 *
 * 1 <= licensePlate.length <= 7
 * licensePlate 由数字、大小写字母或空格 ' ' 组成
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 15
 * words[i] 由小写英文字母组成
 */
public class ShortestCompletingWord {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 95.31%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 98.83%
     * 的用户
     * 通过测试用例：
     * 102 / 102
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        StringBuffer sb = new StringBuffer();
        for(char c : licensePlate.toCharArray()){
            if (c == ' ' || (c >= '0' && c <= '9')){
                continue;
            }
            sb.append(c);
        }
        String plate = sb.toString().toLowerCase();
        int[] nums = new int[26];
        for(char c : plate.toCharArray()){
            nums[c-'a']++;
        }
        String res = "";
        int[] temp = new int[26];
        for(String word : words){
            if (word.length() < plate.length()){
                continue;
            }
            if (res.length() !=0 && word.length() >= res.length()){
                continue;
            }
            System.arraycopy(nums,0,temp,0,26);
            for(char c : word.toCharArray()){
                temp[c-'a']--;
            }
            boolean flag = true;
            for(int i : temp){
                if (i>0){
                    flag = false;
                    break;
                }
            }
            if (flag){
                res = word;
            }
        }
        return res;
    }

    /**
     * 方法一：统计字符出现次数
     * 根据题意，我们先统计 \textit{licensePlate}licensePlate 中每个字母的出现次数（忽略大小写），然后遍历 \textit{words}words 中的每个单词，若 2626 个字母在该单词中的出现次数均不小于在 \textit{licensePlate}licensePlate 中的出现次数，则该单词是一个补全词。返回最短且最靠前的补全词。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/shortest-completing-word/solution/zui-duan-bu-quan-ci-by-leetcode-solution-35pt/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param licensePlate
     * @param words
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 95.31%
     * 的用户
     * 内存消耗：
     * 39 MB
     * , 在所有 Java 提交中击败了
     * 45.16%
     * 的用户
     * 通过测试用例：
     * 102 / 102
     */
    public String shortestCompletingWord1(String licensePlate, String[] words) {
        int[] cnt = new int[26];
        for (int i = 0; i < licensePlate.length(); ++i) {
            char ch = licensePlate.charAt(i);
            if (Character.isLetter(ch)) {
                ++cnt[Character.toLowerCase(ch) - 'a'];
            }
        }
        int idx = -1;
        for (int i = 0; i < words.length; ++i) {
            int[] c = new int[26];
            for (int j = 0; j < words[i].length(); ++j) {
                char ch = words[i].charAt(j);
                ++c[ch - 'a'];
            }
            boolean ok = true;
            for (int j = 0; j < 26; ++j) {
                if (c[j] < cnt[j]) {
                    ok = false;
                    break;
                }
            }
            if (ok && (idx < 0 || words[i].length() < words[idx].length())) {
                idx = i;
            }
        }
        return words[idx];
    }
}
