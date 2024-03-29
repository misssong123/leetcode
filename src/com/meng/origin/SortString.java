package com.meng.origin;

/**
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 *
 *     从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 *     从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 *     重复步骤 2 ，直到你没法从 s 中选择字符。
 *     从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 *     从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 *     重复步骤 5 ，直到你没法从 s 中选择字符。
 *     重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 *
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 *
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 *
 * 示例 2：
 *
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 *
 * 示例 3：
 *
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 *
 * 示例 4：
 *
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 *
 * 示例 5：
 *
 * 输入：s = "spo"
 * 输出："ops"
 *
 *
 *
 * 提示：
 *
 *     1 <= s.length <= 500
 *     s 只包含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-decreasing-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortString {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.12% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了72.23% 的用户
     * @param s
     * @return
     */
    public String sortString(String s) {
        int count = s.length();
        if (s.length()>1){
            int [] nums = new int[26];
            for(char c : s.toCharArray()){
                nums[c-'a']++;
            }
            boolean flag =true;
            StringBuffer sb = new StringBuffer();
            while (count>0){
                if (flag){
                    for(int i = 0 ; i < 26 ;i++){
                        if (nums[i]>0){
                            sb.append((char)('a'+i));
                            nums[i]--;
                            count--;
                        }
                    }
                }else {
                    for(int i = 25 ; i >=0 ;i--){
                        if (nums[i]>0){
                            sb.append((char)('a'+i));
                            nums[i]--;
                            count--;
                        }
                    }
                }
                flag=!flag;
            }
            return sb.toString();
        }
        return s;
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.12% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了72.14% 的用户
     * @param s
     * @return
     */
    public String sortString1(String s) {
        int count = s.length();
        if (s.length()>1){
            int [] nums = new int[26];
            for(char c : s.toCharArray()){
                nums[c-'a']++;
            }
            boolean flag =true;
            StringBuffer sb = new StringBuffer();
            while (count>0){
                    for(int i = 0 ; i < 26 ;i++){
                        if (nums[i]>0){
                            sb.append((char)('a'+i));
                            nums[i]--;
                            count--;
                        }
                    }
                    for(int i = 25 ; i >=0 ;i--){
                        if (nums[i]>0){
                            sb.append((char)('a'+i));
                            nums[i]--;
                            count--;
                        }
                    }
            }
            return sb.toString();
        }
        return s;
    }
    /**
     * 官方解法
     * 方法一：桶计数
     *
     * 思路及解法
     *
     * 仔细分析步骤，我们发现：
     *
     *     每个字符被选择且仅被选择一次；
     *
     *     每一轮会在字符串末尾加入一个先升后降的字符串，且该串的上升部分和下降部分都会尽可能长。
     *
     * 于是我们重复若干轮下述操作，直到每一个字符都被选择过，这样就可以构造出这个字符串：
     *
     *     先从未被选择的字符中提取出最长的上升字符串，将其加入答案。
     *
     *     然后从未被选择的字符中提取出最长的下降字符串，将其加入答案。
     *
     * 注意到在构造时我们只关注字符本身，而不关注字符在原字符串中的位置。因此我们可以直接创建一个大小为 262626 的桶，记录每种字符的数量。每次提取最长的上升或下降字符串时，我们直接顺序或逆序遍历这个桶。
     *
     * 具体地，在遍历桶的过程中，如果当前桶的计数值不为零，那么将当前桶对应的字符加入到答案中，并将当前桶的计数值减一即可。我们重复这一过程，直到答案字符串的长度与传入的字符串的长度相等。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/increasing-decreasing-string/solution/shang-sheng-xia-jiang-zi-fu-chuan-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 复杂度分析
     *
     *     时间复杂度：O(∣Σ∣×∣s∣)O(|\Sigma|\times|s|)O(∣Σ∣×∣s∣)，其中 Σ\SigmaΣ 为字符集，sss 为传入的字符串，在这道题中，字符集为全部小写字母，∣Σ∣=26|\Sigma|=26∣Σ∣=26。最坏情况下字符串中所有字符都相同，那么对于每一个字符，我们需要遍历一次整个桶。
     *
     *     空间复杂度：O(∣Σ∣)O(|\Sigma|)O(∣Σ∣)，其中 Σ\SigmaΣ 为字符集。我们需要和 ∣Σ∣|\Sigma|∣Σ∣ 等大的桶来记录每一类字符的数量。
     *执行用时：3 ms, 在所有 Java 提交中击败了98.12% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了83.92% 的用户
     */
    public String sortString2(String s) {
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer ret = new StringBuffer();
        while (ret.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (num[i] > 0) {
                    ret.append((char) (i + 'a'));
                    num[i]--;
                }
            }
        }
        return ret.toString();
    }
}
