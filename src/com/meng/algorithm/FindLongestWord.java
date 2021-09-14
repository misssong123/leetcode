package com.meng.algorithm;

import java.util.*;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 难度
 * 中等
 *
 * 205
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 接收动态
 * 反馈
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 *
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 *
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 */
public class FindLongestWord {
    /**
     * 执行用时：
     * 21 ms
     * , 在所有 Java 提交中击败了
     * 54.25%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 87.60%
     * 的用户
     * @param s
     * @param dictionary
     * @return
     */
    public String findLongestWord(String s, List<String> dictionary) {
        List<String> order = new ArrayList<>(dictionary);
        Collections.sort(order, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()){
                    return o2.length() - o1.length();
                }
                return o1.compareTo(o2);
            }
        });
        int index = order.size();
        for(int i = 0 ; i < index ; i++){
            String temp = order.get(i);
            if (comp(temp,s)){
                return temp;
            }
        }
        return "";
    }

    private boolean comp(String sub, String s) {
        int len1 = sub.length(),len2 = s.length();
        int left = 0,right = 0;
        while (left < len1 && right < len2){
            if (sub.charAt(left) == s.charAt(right)){
                left++;
            }
            right++;
        }
        return left == len1;
    }

    /**
     * 方法一：双指针
     *
     * 思路和算法
     *
     * 根据题意，我们需要解决两个问题：
     *
     * 如何判断
     * dictionary
     * dictionary 中的字符串
     * t
     * t 是否可以通过删除
     * s
     * s 中的某些字符得到；
     * 如何找到长度最长且字典序最小的字符串。
     * 第
     * 1
     * 1 个问题实际上就是判断
     * t
     * t 是否是
     * s
     * s 的子序列。因此只要能找到任意一种
     * t
     * t 在
     * s
     * s 中出现的方式，即可认为
     * t
     * t 是
     * s
     * s 的子序列。而当我们从前往后匹配时，可以发现每次贪心地匹配最靠前的字符是最优决策。
     *
     * 假定当前需要匹配字符
     * c
     * c，而字符
     * c
     * c 在
     * s
     * s 中的位置
     * x
     * 1
     * x
     * 1
     * ​
     *   和
     * x
     * 2
     * x
     * 2
     * ​
     *   出现（
     * x
     * 1
     * <
     * x
     * 2
     * x
     * 1
     * ​
     *  <x
     * 2
     * ​
     *  ），那么贪心取
     * x
     * 1
     * x
     * 1
     * ​
     *   是最优解，因为
     * x
     * 2
     * x
     * 2
     * ​
     *   后面能取到的字符，
     * x
     * 1
     * x
     * 1
     * ​
     *   也都能取到，并且通过
     * x
     * 1
     * x
     * 1
     * ​
     *   与
     * x
     * 2
     * x
     * 2
     * ​
     *   之间的可选字符，更有希望能匹配成功。
     *
     * 这样，我们初始化两个指针
     * i
     * i 和
     * j
     * j，分别指向
     * t
     * t 和
     * s
     * s 的初始位置。每次贪心地匹配，匹配成功则
     * i
     * i 和
     * j
     * j 同时右移，匹配
     * t
     * t 的下一个位置，匹配失败则
     * j
     * j 右移，
     * i
     * i 不变，尝试用
     * s
     * s 的下一个字符匹配
     * t
     * t。
     *
     * 最终如果
     * i
     * i 移动到
     * t
     * t 的末尾，则说明
     * t
     * t 是
     * s
     * s 的子序列。
     *
     * 第
     * 2
     * 2 个问题可以通过遍历
     * dictionary
     * dictionary 中的字符串，并维护当前长度最长且字典序最小的字符串来找到。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/solution/tong-guo-shan-chu-zi-mu-pi-pei-dao-zi-di-at66/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param s
     * @param dictionary
     * @return
     * 执行用时：
     * 20 ms
     * , 在所有 Java 提交中击败了
     * 58.99%
     * 的用户
     * 内存消耗：
     * 39.4 MB
     * , 在所有 Java 提交中击败了
     * 42.13%
     * 的用户
     */
    public String findLongestWord1(String s, List<String> dictionary) {
        String res = "";
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < t.length() && j < s.length()) {
                if (t.charAt(i) == s.charAt(j)) {
                    ++i;
                }
                ++j;
            }
            if (i == t.length()) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }

    /**
     * 方法二：排序
     *
     * 思路和算法
     *
     * 在方法一的基础上，我们尝试通过对
     * dictionary
     * dictionary 的预处理，来优化第
     * 2
     * 2 个问题的处理。
     *
     * 我们可以先将
     * dictionary
     * dictionary 依据字符串长度的降序和字典序的升序进行排序，然后从前向后找到第一个符合条件的字符串直接返回即可。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/solution/tong-guo-shan-chu-zi-mu-pi-pei-dao-zi-di-at66/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * @param s
     * @param dictionary
     * @return
     * 执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 42.08%
     * 的用户
     * 内存消耗：
     * 39.4 MB
     * , 在所有 Java 提交中击败了
     * 46.22%
     * 的用户
     */
    public String findLongestWord2(String s, List<String> dictionary) {
        Collections.sort(dictionary, new Comparator<String>() {
            public int compare(String word1, String word2) {
                if (word1.length() != word2.length()) {
                    return word2.length() - word1.length();
                } else {
                    return word1.compareTo(word2);
                }
            }
        });
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < t.length() && j < s.length()) {
                if (t.charAt(i) == s.charAt(j)) {
                    ++i;
                }
                ++j;
            }
            if (i == t.length()) {
                return t;
            }
        }
        return "";
    }

    /**
     * 方法三：动态规划
     *
     * 思路和算法
     *
     * 在方法一的基础上，我们考虑通过对字符串
     * s
     * s 的预处理，来优化第
     * 1
     * 1 个问题的处理。
     *
     * 考虑前面的双指针的做法，我们注意到我们有大量的时间用于在
     * s
     * s 中找到下一个匹配字符。
     *
     * 这样我们通过预处理，得到：对于
     * s
     * s 的每一个位置，从该位置开始往后每一个字符第一次出现的位置。
     *
     * 我们可以使用动态规划的方法实现预处理，令
     * f
     * [
     * i
     * ]
     * [
     * j
     * ]
     * f[i][j] 表示字符串
     * s
     * s 中从位置
     * i
     * i 开始往后字符
     * j
     * j 第一次出现的位置。在进行状态转移时，如果
     * s
     * s 中位置
     * i
     * i 的字符就是
     * j
     * j，那么
     * f
     * [
     * i
     * ]
     * [
     * j
     * ]
     * =
     * i
     * f[i][j]=i，否则
     * j
     * j 出现在位置
     * i
     * +
     * 1
     * i+1 开始往后，即
     * f
     * [
     * i
     * ]
     * [
     * j
     * ]
     * =
     * f
     * [
     * i
     * +
     * 1
     * ]
     * [
     * j
     * ]
     * f[i][j]=f[i+1][j]；因此我们要倒过来进行动态规划，从后往前枚举
     * i
     * i。
     *
     * 这样我们可以写出状态转移方程：
     *
     * f
     * [
     * i
     * ]
     * [
     * j
     * ]
     * =
     * {
     * i
     * ,
     * s
     * [
     * i
     * ]
     * =
     * j
     * f
     * [
     * i
     * +
     * 1
     * ]
     * [
     * j
     * ]
     * ,
     * s
     * [
     * i
     * ]
     * ≠
     * j
     *
     * f[i][j]={
     * i,
     * f[i+1][j],
     * ​
     *
     * s[i]=j
     * s[i]
     * 
     * ​
     *  =j
     * ​
     *
     *
     * 假定下标从
     * 0
     * 0 开始，那么
     * f
     * [
     * i
     * ]
     * [
     * j
     * ]
     * f[i][j] 中有
     * 0
     * ≤
     * i
     * ≤
     * m
     * −
     * 1
     * 0≤i≤m−1 ，对于边界状态
     * f
     * [
     * m
     * −
     * 1
     * ]
     * [
     * .
     * .
     * ]
     * f[m−1][..]，我们置
     * f
     * [
     * m
     * ]
     * [
     * .
     * .
     * ]
     * f[m][..] 为
     * m
     * m，让
     * f
     * [
     * m
     * −
     * 1
     * ]
     * [
     * .
     * .
     * ]
     * f[m−1][..] 正常进行转移。这样如果
     * f
     * [
     * i
     * ]
     * [
     * j
     * ]
     * =
     * m
     * f[i][j]=m，则表示从位置
     * i
     * i 开始往后不存在字符
     * j
     * j。
     *
     * 这样，我们可以利用
     * f
     * f 数组，每次
     * O
     * (
     * 1
     * )
     * O(1) 地跳转到下一个位置，直到位置变为
     * m
     * m 或
     * t
     * t 中的每一个字符都匹配成功。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/solution/tong-guo-shan-chu-zi-mu-pi-pei-dao-zi-di-at66/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法3
     * @param s
     * @param dictionary
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 99.63%
     * 的用户
     * 内存消耗：
     * 39.2 MB
     * , 在所有 Java 提交中击败了
     * 83.51%
     * 的用户
     */
    public String findLongestWord3(String s, List<String> dictionary) {
        int m = s.length();
        int[][] f = new int[m + 1][26];
        Arrays.fill(f[m], m);

        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < 26; ++j) {
                if (s.charAt(i) == (char) ('a' + j)) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        Arrays.stream(f).forEach(c -> System.out.println(Arrays.toString(c)));
        String res = "";
        for (String t : dictionary) {
            boolean match = true;
            int j = 0;
            for (int i = 0; i < t.length(); ++i) {
                if (f[j][t.charAt(i) - 'a'] == m) {
                    match = false;
                    break;
                }
                j = f[j][t.charAt(i) - 'a'] + 1;
            }
            if (match) {
                if (t.length() > res.length() ||  (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
         FindLongestWord demo = new FindLongestWord();
         String s = "bab";
         List<String> list = new ArrayList<>(Arrays.asList("ab","ba","aaa"));
        System.out.println(demo.findLongestWord3(s,list));
    }
}
