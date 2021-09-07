package com.meng.algorithm;

/**
 * 1221. 分割平衡字符串
 *
 * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
 *
 * 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 *
 * 注意：分割得到的每个字符串都必须是平衡字符串。
 *
 * 返回可以通过分割得到的平衡字符串的 最大数量 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "RLRRLLRLRL"
 * 输出：4
 * 解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 *
 * 示例 2：
 *
 * 输入：s = "RLLLLRRRLR"
 * 输出：3
 * 解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 *
 * 示例 3：
 *
 * 输入：s = "LLLLRRRR"
 * 输出：1
 * 解释：s 只能保持原样 "LLLLRRRR".
 *
 * 示例 4：
 *
 * 输入：s = "RLRRRLLRLL"
 * 输出：2
 * 解释：s 可以分割为 "RL"、"RRRLLRLL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 *
 *
 *
 * 提示：
 *
 *     1 <= s.length <= 1000
 *     s[i] = 'L' 或 'R'
 *     s 是一个 平衡 字符串
 */
public class BalancedStringSplit {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了71.56% 的用户
     * @param s
     * @return
     */
    public int balancedStringSplit(String s) {
        int count = 0 , ans = 0 , len = s.length();
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < len ; i++){
            if (chars[i] == 'R'){
                count++;
            }else {
                count--;
            }
            if (count == 0){
                ans++;
            }
        }
        return ans;
    }

    /**
     * 方法一：贪心
     *
     * 根据题意，对于一个平衡字符串 sss，若 sss 能从中间某处分割成左右两个子串，若其中一个是平衡字符串，则另一个的 L\texttt{L}L 和 R\texttt{R}R 字符的数量必然是相同的，所以也一定是平衡字符串。
     *
     * 为了最大化分割数量，我们可以不断循环，每次从 sss 中分割出一个最短的平衡前缀，由于剩余部分也是平衡字符串，我们可以将其当作 sss 继续分割，直至 sss 为空时，结束循环。
     *
     * 代码实现中，可以在遍历 sss 时用一个变量 ddd 维护 L\texttt{L}L 和 R\texttt{R}R 字符的数量之差，当 d=0d=0d=0 时就说明找到了一个平衡字符串，将答案加一。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/solution/fen-ge-ping-heng-zi-fu-chuan-by-leetcode-7y8u/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了33.33% 的用户
     */
    public int balancedStringSplit1(String s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == 'L') {
                ++d;
            } else {
                --d;
            }
            if (d == 0) {
                ++ans;
            }
        }
        return ans;
    }

}
