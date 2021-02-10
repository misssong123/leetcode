package com.meng;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 567. 字符串的排列
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 *
 * 注意：
 *
 *     输入的字符串只包含小写字母
 *     两个字符串的长度都在 [1, 10,000] 之间
 */
public class CheckInclusion {
    /**
     * 执行用时：165 ms, 在所有 Java 提交中击败了10.04% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了26.32% 的用户
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length()>s2.length())
            return false;
        Set<Integer> set = new HashSet<>();
        int [] index = new int[26];
        int [] tempIndex = new int[26];
        int count = s1.length();
        int cur = 0;
        for(int i = 0 ; i < count ; i++){
            cur = s1.charAt(i)-'a';
            ++index[cur];
            set.add(cur);
        }
        System.arraycopy(index,0,tempIndex,0,26);
        int len = s2.length(),right = 0;
        while (right<len){
            cur = s2.charAt(right)-'a';
            if (tempIndex[cur]>0){
                tempIndex[cur]--;
                count--;
            }else {
                if (set.contains(cur)){
                    right = right + count - s1.length();
                }
                if (count != s1.length()){
                    System.arraycopy(index,0,tempIndex,0,tempIndex.length);
                    count = s1.length();
                }
            }
            if (count==0)
                return true;
            right++;
        }
        return count == 0;
    }

    /**
     *     排列不讲究顺序，但是字符出现的 种类 和 次数 要恰好对应相等；
     *     暴力解法做了很多重复和没有必要的工作；
     *     思考可以使用双指针（一前一后、交替向右边移动）的原因：少考虑很多暴力解法须要考虑的子区间，已经得到了一部分子区间的信息以后，很多子区间都不必要考虑，因此根据特定的情况右指针、左指针交替向右移动，从时间复杂度 O(N2)O(N^2)O(N2) 降到 O(N)O(N)O(N)；
     *     由于我们只关心子区间里的元素的种数和各个字符出现的次数。因此须要统计字符串 s1 出现的字符的种数和次数，和在字符串 s2 上的两个变量所确定的滑动窗口中出现的字符种数和次数；
     *     还须要设计一个变量 winCount，表示滑动窗口在 s2 上滑动的时候，出现在 s1 中的字符的种类数，规则如下：
     *         如果某一个字符出现的次数恰好等于 s1 中对应字符出现的次数，winCount += 1；
     *         在左边界向右移动的过程当中，某一个字符对应的次数减少以后，恰好小于了 s1 对应的字符出现的次数，winCount -= 1；
     *         当滑动窗口中出现的字符种类数和 s1 中出现的字符种类数相等（根据 winCount 的定义，对应次数也相等），并且 s2 上的滑动窗口的长度等于字符串 s1 的长度的时候，就找到了 s1 的一个排列。
     *
     * 注意：请大家特别留意 winCount 的含义：滑动窗口中的字符要满足字符出现的次数 大于等于 s1 中对应字符出现的次数的时候才加 111，winCount 不仅统计了种类，还代表了次数。使得我们可以通过 winCount 的数值去了解整个滑动窗口的信息。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/permutation-in-string/solution/zi-fu-chuan-de-pai-lie-by-leetcode-q6tp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param s1
     * @param s2
     * @return
     * 执行用时：6 ms, 在所有 Java 提交中击败了86.53% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了43.56% 的用户
     */
    public boolean checkInclusion1(String s1, String s2) {
        char[] pattern = s1.toCharArray();
        char[] text = s2.toCharArray();

        int pLen = s1.length();
        int tLen = s2.length();

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];

        for (int i = 0; i < pLen; i++) {
            pFreq[pattern[i] - 'a']++;
        }

        int pCount = 0;
        for (int i = 0; i < 26; i++) {
            if (pFreq[i] > 0){
                pCount++;
            }
        }

        int left = 0;
        int right = 0;
        // 当滑动窗口中的某个字符个数与 s1 中对应相等的时候才计数
        int winCount = 0;
        while (right < tLen){
            if (pFreq[text[right] - 'a'] > 0 ) {
                winFreq[text[right] - 'a']++;
                if (winFreq[text[right] - 'a'] == pFreq[text[right] - 'a']){
                    winCount++;
                }
            }
            right++;

            while (pCount == winCount){
                if (right - left == pLen){
                    return true;
                }
                if (pFreq[text[left] - 'a'] > 0 ) {
                    winFreq[text[left] - 'a']--;
                    if (winFreq[text[left] - 'a'] < pFreq[text[left] - 'a']){
                        winCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }

    /**
     * 方法一：滑动窗口
     *
     * 由于排列不会改变字符串中每个字符的个数，所以只有当两个字符串每个字符的个数均相等时，一个字符串才是另一个字符串的排列。
     *
     * 根据这一性质，记 s1s_1s1​ 的长度为 nnn，我们可以遍历 s2s_2s2​ 中的每个长度为 nnn 的子串，判断子串和 s1s_1s1​ 中每个字符的个数是否相等，若相等则说明该子串是 s1s_1s1​ 的一个排列。
     *
     * 使用两个数组 cnt1\textit{cnt}_1cnt1​ 和 cnt2\textit{cnt}_2cnt2​，cnt1\textit{cnt}_1cnt1​ 统计 s1s_1s1​ 中各个字符的个数，cnt2\textit{cnt}_2cnt2​ 统计当前遍历的子串中各个字符的个数。
     *
     * 由于需要遍历的子串长度均为 nnn，我们可以使用一个固定长度为 nnn 的滑动窗口来维护 cnt2\textit{cnt}_2cnt2​：滑动窗口每向右滑动一次，就多统计一次进入窗口的字符，少统计一次离开窗口的字符。然后，判断 cnt1\textit{cnt}_1cnt1​ 是否与 cnt2\textit{cnt}_2cnt2​ 相等，若相等则意味着 s1s_1s1​ 的排列之一是 s2s_2s2​ 的子串。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/permutation-in-string/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-7k7u/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param s1
     * @param s2
     * @return
     * 执行用时：6 ms, 在所有 Java 提交中击败了86.53% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了49.94% 的用户
     */
    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        CheckInclusion demo = new CheckInclusion();
        System.out.println(demo.checkInclusion("ccc","cabc"));
    }
}
