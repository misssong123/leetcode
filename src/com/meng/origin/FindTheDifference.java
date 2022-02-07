package com.meng.origin;

/**
 * 389. 找不同
 *
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 *
 * 示例 2：
 *
 * 输入：s = "", t = "y"
 * 输出："y"
 *
 * 示例 3：
 *
 * 输入：s = "a", t = "aa"
 * 输出："a"
 *
 * 示例 4：
 *
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 *
 *
 *
 * 提示：
 *
 *     0 <= s.length <= 1000
 *     t.length == s.length + 1
 *     s 和 t 只包含小写字母
 */
public class FindTheDifference {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了44.67% 的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了76.13% 的用户
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int ls = s.length(),lt = t.length();
        int [] nums = new int[26];
        for(int i = 0 ; i < ls ; i++)
            nums[s.charAt(i)-'a']++;
        for (int i = 0 ; i< lt ; i++){
            if (nums[t.charAt(i)-'a']==0)
                return t.charAt(i);
            nums[t.charAt(i)-'a']--;
        }
        return ' ';
    }
    /**
     * 方法一：计数
     *
     * 首先遍历字符串 sss，对其中的每个字符都将计数值加 111；然后遍历字符串 ttt，对其中的每个字符都将计数值减 111。当发现某个字符计数值为负数时，说明该字符在字符串 ttt 中出现的次数大于在字符串 sss 中出现的次数，因此该字符为被添加的字符。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-difference/solution/zhao-bu-tong-by-leetcode-solution-mtqf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：3 ms, 在所有 Java 提交中击败了44.67% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了57.06% 的用户
     */
    public char findTheDifference1(String s, String t) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            cnt[ch - 'a']++;
        }
        for (int i = 0; i < t.length(); ++i) {
            char ch = t.charAt(i);
            cnt[ch - 'a']--;
            if (cnt[ch - 'a'] < 0) {
                return ch;
            }
        }
        return ' ';
    }

    /**
     * 方法二：求和
     *
     * 将字符串 sss 中每个字符的 ASCII 码的值求和，得到 AsA_sAs​；对字符串 ttt 同样的方法得到 AtA_tAt​。两者的差值 At−AsA_t-A_sAt​−As​ 即代表了被添加的字符。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-difference/solution/zhao-bu-tong-by-leetcode-solution-mtqf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：3 ms, 在所有 Java 提交中击败了44.67% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了52.72% 的用户
     */
    public char findTheDifference2(String s, String t) {
        int as = 0, at = 0;
        for (int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            at += t.charAt(i);
        }
        return (char) (at - as);
    }

    /**
     * 方法三：位运算
     *
     * 如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符。类似于「136. 只出现一次的数字」，我们使用位运算的技巧解决本题。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-difference/solution/zhao-bu-tong-by-leetcode-solution-mtqf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法3
     *执行用时：2 ms, 在所有 Java 提交中击败了76.78% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了84.08% 的用户
     */
    public char findTheDifference3(String s, String t) {
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret ^= t.charAt(i);
        }
        return (char) ret;
    }

}
