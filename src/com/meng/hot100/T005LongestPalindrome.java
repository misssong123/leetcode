package com.meng.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 5. 最长回文子串(中等)
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class T005LongestPalindrome {
    /**
     * 两种情况
     * 1.存在中心对称,以单个字符向左右扩展
     * 2.不存在中心对称,以左右相等的两个字符分别向左侧和右侧扩展
     * 3.计算当前下标能组成的长度是否小于已知的最长长度
     * @param s
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 97.56%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 67.55%
     * 的用户
     * 通过测试用例：
     * 140 / 140
     */
    String ans = "";
    int num = 0 ;
    public String longestPalindrome(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < len && (len - i - 1) * 2 >= num; i++){
            judge(i,i,len,chars,s);
            if (i+1<len && chars[i]== chars[i+1]){
                judge(i,i+1,len,chars,s);
            }
        }
        return ans;
    }

    private void judge(int left, int right, int len, char[] chars,String s) {
        while (chars[left] == chars[right]){
            left--;
            right++;
            if (left < 0 || right >= len){
                break;
            }
        }
        if (right-left-1 > num){
            ans = s.substring(++left,right);
            num = ans.length();
        }
    }

    public static void main(String[] args) {
        String s = "d";
        T005LongestPalindrome demo = new T005LongestPalindrome();
        System.out.println(demo.longestPalindrome(s));
    }

    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 对于一个子串而言，如果它是回文串，并且长度大于 22，那么将它首尾的两个字母去除之后，它仍然是个回文串。例如对于字符串 \textrm{``ababa''}“ababa”，如果我们已经知道 \textrm{``bab''}“bab” 是回文串，那么 \textrm{``ababa''}“ababa” 一定是回文串，这是因为它的首尾两个字母都是 \textrm{``a''}“a”。
     *
     * 根据这样的思路，我们就可以用动态规划的方法解决本题。我们用 P(i,j)P(i,j) 表示字符串 ss 的第 ii 到 jj 个字母组成的串（下文表示成 s[i:j]s[i:j]）是否为回文串：
     *
     * P(i,j) = \begin{cases} \text{true,} &\quad\text{如果子串~} S_i \dots S_j \text{~是回文串}\\ \text{false,} &\quad\text{其它情况} \end{cases}
     * P(i,j)={
     * true,
     * false,
     * ​
     *
     * 如果子串 S
     * i
     * ​
     *  …S
     * j
     * ​
     *   是回文串
     * 其它情况
     * ​
     *
     *
     * 这里的「其它情况」包含两种可能性：
     *
     * s[i, j]s[i,j] 本身不是一个回文串；
     *
     * i > ji>j，此时 s[i, j]s[i,j] 本身不合法。
     *
     * 那么我们就可以写出动态规划的状态转移方程：
     *
     * P(i, j) = P(i+1, j-1) \wedge (S_i == S_j)
     * P(i,j)=P(i+1,j−1)∧(S
     * i
     * ​
     *  ==S
     * j
     * ​
     *  )
     *
     * 也就是说，只有 s[i+1:j-1]s[i+1:j−1] 是回文串，并且 ss 的第 ii 和 jj 个字母相同时，s[i:j]s[i:j] 才会是回文串。
     *
     * 上文的所有讨论是建立在子串长度大于 22 的前提之上的，我们还需要考虑动态规划中的边界条件，即子串的长度为 11 或 22。对于长度为 11 的子串，它显然是个回文串；对于长度为 22 的子串，只要它的两个字母相同，它就是一个回文串。因此我们就可以写出动态规划的边界条件：
     *
     * \begin{cases} P(i, i) = \text{true} \\ P(i, i+1) = ( S_i == S_{i+1} ) \end{cases}
     * {
     * P(i,i)=true
     * P(i,i+1)=(S
     * i
     * ​
     *  ==S
     * i+1
     * ​
     *  )
     * ​
     *
     *
     * 根据这个思路，我们就可以完成动态规划了，最终的答案即为所有 P(i, j) = \text{true}P(i,j)=true 中 j-i+1j−i+1（即子串长度）的最大值。注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 117 ms
     * , 在所有 Java 提交中击败了
     * 44.69%
     * 的用户
     * 内存消耗：
     * 44.4 MB
     * , 在所有 Java 提交中击败了
     * 33.44%
     * 的用户
     * 通过测试用例：
     * 140 / 140
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 方法二：中心扩展算法
     * 思路与算法
     *
     * 我们仔细观察一下方法一中的状态转移方程：
     *
     * \begin{cases} P(i, i) &=\quad \text{true} \\ P(i, i+1) &=\quad ( S_i == S_{i+1} ) \\ P(i, j) &=\quad P(i+1, j-1) \wedge (S_i == S_j) \end{cases}
     * ⎩
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎧
     * ​
     *
     * P(i,i)
     * P(i,i+1)
     * P(i,j)
     * ​
     *
     * =true
     * =(S
     * i
     * ​
     *  ==S
     * i+1
     * ​
     *  )
     * =P(i+1,j−1)∧(S
     * i
     * ​
     *  ==S
     * j
     * ​
     *  )
     * ​
     *
     *
     * 找出其中的状态转移链：
     *
     * P(i, j) \leftarrow P(i+1, j-1) \leftarrow P(i+2, j-2) \leftarrow \cdots \leftarrow \text{某一边界情况}
     * P(i,j)←P(i+1,j−1)←P(i+2,j−2)←⋯←某一边界情况
     *
     * 可以发现，所有的状态在转移的时候的可能性都是唯一的。也就是说，我们可以从每一种边界情况开始「扩展」，也可以得出所有的状态对应的答案。
     *
     * 边界情况即为子串长度为 11 或 22 的情况。我们枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。如果两边的字母相同，我们就可以继续扩展，例如从 P(i+1,j-1)P(i+1,j−1) 扩展到 P(i,j)P(i,j)；如果两边的字母不同，我们就可以停止扩展，因为在这之后的子串都不能是回文串了。
     *
     * 聪明的读者此时应该可以发现，「边界情况」对应的子串实际上就是我们「扩展」出的回文串的「回文中心」。方法二的本质即为：我们枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。我们对所有的长度求出最大值，即可得到最终的答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 16 ms
     * , 在所有 Java 提交中击败了
     * 88.26%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 98.68%
     * 的用户
     * 通过测试用例：
     * 140 / 140
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    /**
     *方法三：\text{Manacher}Manacher 算法
     * 还有一个复杂度为 O(n)Manacher 算法。然而本算法十分复杂，一般不作为面试内容。这里给出，仅供有兴趣的同学挑战自己。
     *
     * 为了表述方便，我们定义一个新概念臂长，表示中心扩展算法向外扩展的长度。
     * 如果一个位置的最大回文字符串长度为 2 * length + 1 ，其臂长为 length。
     *
     * 下面的讨论只涉及长度为奇数的回文字符串。
     * 长度为偶数的回文字符串我们将会在最后与长度为奇数的情况统一起来。
     *
     * 思路与算法
     *
     * 在中心扩展算法的过程中，我们能够得出每个位置的臂长。那么当我们要得出以下一个位置 i 的臂长时，能不能利用之前得到的信息呢？
     *
     * 答案是肯定的。具体来说，如果位置 j 的臂长为 length，并且有 j + length > i，如下图所示：
     *
     *
     *
     * 当在位置 i 开始进行中心拓展时，我们可以先找到 i 关于 j 的对称点 2 * j - i。
     * 那么如果点 2 * j - i 的臂长等于 n，我们就可以知道，点 i 的臂长至少为 min(j + length - i, n)。
     * 那么我们就可以直接跳过 i 到 i + min(j + length - i, n) 这部分，从 i + min(j + length - i, n) + 1 开始拓展。
     *
     * 我们只需要在中心扩展法的过程中记录右臂在最右边的回文字符串，
     * 将其中心作为 j，在计算过程中就能最大限度地避免重复计算。
     *
     * 那么现在还有一个问题：如何处理长度为偶数的回文字符串呢？
     *
     * 我们可以通过一个特别的操作将奇偶数的情况统一起来：我们向字符串的头尾以及每两个字符中间添加一个特殊字符 #，比如字符串 aaba 处理后会变成 #a#a#b#a#。那么原先长度为偶数的回文字符串 aa 会变成长度为奇数的回文字符串 #a#a#，而长度为奇数的回文字符串 aba 会变成长度仍然为奇数的回文字符串 #a#b#a#，我们就不需要再考虑长度为偶数的回文字符串了。
     *
     * 注意这里的特殊字符不需要是没有出现过的字母，我们可以使用任何一个字符来作为这个特殊字符。这是因为，当我们只考虑长度为奇数的回文字符串时，每次我们比较的两个字符奇偶性一定是相同的，所以原来字符串中的字符不会与插入的特殊字符互相比较，不会因此产生问题。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 14 ms
     * , 在所有 Java 提交中击败了
     * 90.76%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 61.93%
     * 的用户
     * 通过测试用例：
     * 140 / 140
     */
    public String longestPalindrome3(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }

}
