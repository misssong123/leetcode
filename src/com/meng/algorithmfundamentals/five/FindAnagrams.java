package com.meng.algorithmfundamentals.five;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class FindAnagrams {
    /**
     * 执行用时：
     * 1050 ms
     * , 在所有 Java 提交中击败了
     * 7.74%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 52.99%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        int [] temp = new int[26];
        int [] demo = new int[26];
        Set<Character> set = new HashSet<>();
        for(int i = 0 ; i < pLen ; i++){
            temp[p.charAt(i)-'a']++;
            demo[p.charAt(i)-'a']++;
            set.add(p.charAt(i));
        }
        boolean flag = true;
        for(int i = 0 ; i < sLen - pLen +1 ; i++){
            flag = true;
            System.arraycopy(demo,0,temp,0,26);
            String s1 = s.substring(i, i + pLen);
            for(int j = 0 ; j < pLen ; j++){
                if (set.contains(s1.charAt(j))){
                    int index = s1.charAt(j) - 'a';
                    if (temp[index] > 0){
                        temp[index]--;
                        continue;
                    }
                    flag = false;
                    break;
                }
                flag = false;
                i = i + j ;
                break;
            }
            if (flag){
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        FindAnagrams demo = new FindAnagrams();
        System.out.println(demo.findAnagrams("acdcaeccde","c"));
    }
    /**
     * 方法一：滑动窗口
     * 思路
     *
     * 根据题目要求，我们需要在字符串 ss 寻找字符串 pp 的异位词。因为字符串 pp 的异位词的长度一定与字符串 pp 的长度相同，所以我们可以在字符串 ss 中构造一个长度为与字符串 pp 的长度相同的滑动窗口，并在滑动中维护窗口中每种字母的数量；当窗口中每种字母的数量与字符串 pp 中每种字母的数量相同时，则说明当前窗口为字符串 pp 的异位词。
     *
     * 算法
     *
     * 在算法的实现中，我们可以使用数组来存储字符串 pp 和滑动窗口中每种字母的数量。
     *
     * 细节
     *
     * 当字符串 ss 的长度小于字符串 pp 的长度时，字符串 ss 中一定不存在字符串 pp 的异位词。但是因为字符串 ss 中无法构造长度与字符串 pp 的长度相同的窗口，所以这种情况需要单独处理。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/zhao-dao-zi-fu-chuan-zhong-suo-you-zi-mu-xzin/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 84.67%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 25.73%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     */
    public List<Integer> findAnagrams1(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    /**
     * 方法二：优化的滑动窗口
     * 思路和算法
     *
     * 在方法一的基础上，我们不再分别统计滑动窗口和字符串 pp 中每种字母的数量，而是统计滑动窗口和字符串 pp 中每种字母数量的差；并引入变量 \textit{differ}differ 来记录当前窗口与字符串 pp 中数量不同的字母的个数，并在滑动窗口的过程中维护它。
     *
     * 在判断滑动窗口中每种字母的数量与字符串 pp 中每种字母的数量是否相同时，只需要判断 \textit{differ}differ 是否为零即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/zhao-dao-zi-fu-chuan-zhong-suo-you-zi-mu-xzin/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 51.41%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 26.60%
     * 的用户
     * 通过测试用例：
     * 61 / 61
     */
    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] count = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }

        int differ = 0;
        for (int j = 0; j < 26; ++j) {
            if (count[j] != 0) {
                ++differ;
            }
        }

        if (differ == 0) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            if (count[s.charAt(i) - 'a'] == 1) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从不同变得相同
                --differ;
            } else if (count[s.charAt(i) - 'a'] == 0) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }
            --count[s.charAt(i) - 'a'];

            if (count[s.charAt(i + pLen) - 'a'] == -1) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从不同变得相同
                --differ;
            } else if (count[s.charAt(i + pLen) - 'a'] == 0) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }
            ++count[s.charAt(i + pLen) - 'a'];

            if (differ == 0) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

}
