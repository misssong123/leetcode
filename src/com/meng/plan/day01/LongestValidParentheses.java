package com.meng.plan.day01;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号(困难)
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 */
public class LongestValidParentheses {
    /**
     *执行用时：
     * 17 ms
     * , 在所有 Java 提交中击败了
     * 5.71%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 71.27%
     * 的用户
     * 通过测试用例：
     * 231 / 231
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len < 2){
            return 0;
        }
        int max = 0;
        int[] nums = new int[len];
        for(int i = 1 ; i < len ; i++){
            if (s.charAt(i) == ')'){
                char c = s.charAt(i-1);
                if (c == '('){
                    nums[i] = 2;
                    if (i-2>=0){
                        nums[i] += nums[i-2];
                    }
                }else {
                    int pre = i - nums[i-1] -1;
                    System.out.println(pre);
                    if ( pre >= 0 && s.charAt(pre)=='('){
                        //()(())
                        nums[i] += nums[i-1] + 2;
                        if (pre - 1 > 0){
                            nums[i] += nums[pre-1];
                        }
                    }
                }
                max = Math.max(max,nums[i]);
                System.out.println(Arrays.toString(nums));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        /**
         * (((()))))
         * ((()()))
         * (()
         * )()())
         * ()((()((((()))))))
         * (())
         */
        LongestValidParentheses demo = new LongestValidParentheses();
        String s = "(())";
        System.out.println(demo.longestValidParentheses(s));
        System.out.println(demo.longestValidParentheses1(s));
    }

    /**
     * https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     * 方法一：动态规划
     * 思路和算法
     *
     * 我们定义 \textit{dp}[i]dp[i] 表示以下标 ii 字符结尾的最长有效括号的长度。我们将 \textit{dp}dp 数组全部初始化为 00 。显然有效的子串一定以 \text{‘)’}‘)’ 结尾，因此我们可以知道以 \text{‘(’}‘(’ 结尾的子串对应的 \textit{dp}dp 值必定为 00 ，我们只需要求解 \text{‘)’}‘)’ 在 \textit{dp}dp 数组中对应位置的值。
     *
     * 我们从前往后遍历字符串求解 \textit{dp}dp 值，我们每两个字符检查一次：
     *
     * s[i] = \text{‘)’}s[i]=‘)’ 且 s[i - 1] = \text{‘(’}s[i−1]=‘(’，也就是字符串形如 “……()”“……()”，我们可以推出：
     *
     * \textit{dp}[i]=\textit{dp}[i-2]+2
     * dp[i]=dp[i−2]+2
     *
     * 我们可以进行这样的转移，是因为结束部分的 "()" 是一个有效子字符串，并且将之前有效子字符串的长度增加了 22 。
     *
     * s[i] = \text{‘)’}s[i]=‘)’ 且 s[i - 1] = \text{‘)’}s[i−1]=‘)’，也就是字符串形如 “……))”“……))”，我们可以推出：
     * 如果 s[i - \textit{dp}[i - 1] - 1] = \text{‘(’}s[i−dp[i−1]−1]=‘(’，那么
     *
     * \textit{dp}[i]=\textit{dp}[i-1]+\textit{dp}[i-\textit{dp}[i-1]-2]+2
     * dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2
     *
     * 我们考虑如果倒数第二个 \text{‘)’}‘)’ 是一个有效子字符串的一部分（记作 sub_ssub
     * s
     * ​
     *  ），对于最后一个 \text{‘)’}‘)’ ，如果它是一个更长子字符串的一部分，那么它一定有一个对应的 \text{‘(’}‘(’ ，且它的位置在倒数第二个 \text{‘)’}‘)’ 所在的有效子字符串的前面（也就是 sub_ssub
     * s
     * ​
     *   的前面）。因此，如果子字符串 sub_ssub
     * s
     * ​
     *   的前面恰好是 \text{‘(’}‘(’ ，那么我们就用 22 加上 sub_ssub
     * s
     * ​
     *   的长度（\textit{dp}[i-1]dp[i−1]）去更新 \textit{dp}[i]dp[i]。同时，我们也会把有效子串 “(sub_s)”“(sub
     * s
     * ​
     *  )” 之前的有效子串的长度也加上，也就是再加上 \textit{dp}[i-\textit{dp}[i-1]-2]dp[i−dp[i−1]−2]。
     *
     * 最后的答案即为 \textit{dp}dp 数组中的最大值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 方法二：栈
     * 思路和算法
     *
     * 撇开方法一提及的动态规划方法，相信大多数人对于这题的第一直觉是找到每个可能的子串后判断它的有效性，但这样的时间复杂度会达到 O(n^3)O(n
     * 3
     *  )，无法通过所有测试用例。但是通过栈，我们可以在遍历给定字符串的过程中去判断到目前为止扫描的子串的有效性，同时能得到最长有效括号的长度。
     *
     * 具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：
     *
     * 对于遇到的每个 \text{‘(’}‘(’ ，我们将它的下标放入栈中
     * 对于遇到的每个 \text{‘)’}‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
     * 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     * 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     * 我们从前往后遍历字符串并更新答案即可。
     *
     * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，为了保持统一，我们在一开始的时候往栈中放入一个值为 -1−1 的元素。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * 方法三：不需要额外的空间
     * 思路和算法
     *
     * 在此方法中，我们利用两个计数器 left 和 right 。首先，我们从左到右遍历字符串，对于遇到的每个 ‘(’，我们增加 left 计数器，
     * 对于遇到的每个 ‘)’ ，我们增加 right 计数器。每当 left 计数器与 right 计数器相等时，我们计算当前有效字符串的长度，
     * 并且记录目前为止找到的最长子字符串。当 right 计数器比 left 计数器大时，我们将 left 和 light 计数器同时变回 0。
     *
     * 这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符我们都扔掉不再考虑
     * ，重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() ，这种时候最长有效括号是求不出来的。
     *
     * 解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来：
     *
     * 当 left 计数器比 right 计数器大时，我们将 left 和 right 计数器同时变回 0
     * 当 left 计数器与 right 计数器相等时，
     * 我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串
     * 这样我们就能涵盖所有情况从而求解出答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }


}
