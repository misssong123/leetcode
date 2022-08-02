package com.meng.level2.day14;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串(中等)
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class LengthOfLongestSubstring {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 87.39%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 80.22%
     * 的用户
     * 通过测试用例：
     * 987 / 987
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0){
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int left = 0 ,right = 0,max = 0;
        char[] chars = s.toCharArray();
        for(char c : chars){
            if (!set.add(c)){
                max = Math.max(max,right-left);
                for(int i = left ; i < right ; i++){
                    char chr = chars[left];
                    left++;
                    if (chr == c){
                        break;
                    }
                    set.remove(chr);
                }
            }
            right++;
        }
        max = Math.max(max,right-left);
        return max;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        LengthOfLongestSubstring demo = new LengthOfLongestSubstring();
        System.out.println(demo.lengthOfLongestSubstring(s));
    }

    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 60.13%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 25.20%
     * 的用户
     * 通过测试用例：
     * 987 / 987
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}
