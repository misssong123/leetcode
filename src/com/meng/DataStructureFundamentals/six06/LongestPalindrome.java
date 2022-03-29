package com.meng.DataStructureFundamentals.six06;

import java.util.HashMap;
import java.util.Map;

/**
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
 *
 * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
 *
 *
 *
 * 示例 1:
 *
 * 输入:s = "abccccdd"
 * 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * 示例 2:
 *
 * 输入:s = "a"
 * 输入:1
 * 示例 3:
 *
 * 输入:s = "bb"
 * 输入: 2
 *
 *
 * 提示:
 *
 * 1 <= s.length <= 2000
 * s 只能由小写和/或大写英文字母组成
 */
public class LongestPalindrome {
    /**
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 22.32%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 8.29%
     * 的用户
     * 通过测试用例：
     * 95 / 95
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int ans = 0;
        Map<Character,Integer> cache = new HashMap<>();
        boolean flag = false;
        for(char c : s.toCharArray()){
            cache.put(c,cache.getOrDefault(c,0)+1);
        }

        for(Map.Entry<Character,Integer> entry : cache.entrySet()){
            ans += entry.getValue() / 2;
            if (!flag &&entry.getValue() % 2 != 0){
                flag = true;
            }
        }
        if (flag){
            ans++;
        }
        return ans;
    }
}
