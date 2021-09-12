package com.meng.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 678. 有效的括号字符串
 *
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 *     任何左括号 ( 必须有相应的右括号 )。
 *     任何右括号 ) 必须有相应的左括号 ( 。
 *     左括号 ( 必须在对应的右括号之前 )。
 *     * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 *     一个空字符串也被视为有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: True
 *
 * 示例 2:
 *
 * 输入: "(*)"
 * 输出: True
 *
 * 示例 3:
 *
 * 输入: "(*))"
 * 输出: True
 *
 * 注意:
 *
 *     字符串大小将在 [1，100] 范围内。
 */
public class CheckValidString {
    /**
     * 思路错误，会出现星号（*）早于左括号无法匹配的问题
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        int left = 0 , right = 0 , mid = 0;
        char[] chars = s.toCharArray();
        for(char c : chars){
            if (c == '('){
                left++;
            }else if(c == ')'){
                right++;
            }else if (c == '*'){
                mid++;
                int min = Math.min(left,right);
                left -= min;
                right -= min;
            }
            if (right > left + mid ){
                return false;
            }
        }
        return right + mid >= left;
    }

    /**
     * 方法一：动态规划
     *
     * 要判断 sss 是否为有效的括号字符串，需要判断 sss 的首尾字符以及 sss 的中间字符是否符合有效的括号字符串的要求。可以使用动态规划求解。
     *
     * 假设字符串 sss 的长度为 nnn。定义 dp[i][j]\textit{dp}[i][j]dp[i][j] 表示字符串 sss 从下标 iii 到 jjj 的子串是否为有效的括号字符串，其中 0≤i≤j<n0 \le i \le j < n0≤i≤j<n。
     *
     * 动态规划的边界情况是子串的长度为 111 或 222 的情况。
     *
     *     当子串的长度为 111 时，只有当该字符是 ‘*’\text{`*'}‘*’ 时，才是有效的括号字符串，此时子串可以看成空字符串；
     *
     *     当子串的长度为 222 时，只有当两个字符是 “()",“(*",“*)",“**"\text{``()"}, \text{``(*"}, \text{``*)"}, \text{``**"}“()",“(*",“*)",“**" 中的一种情况时，才是有效的括号字符串，此时子串可以看成 “()"\text{``()"}“()"。
     *
     * 当子串的长度大于 222 时，需要根据子串的首尾字符以及中间的字符判断子串是否为有效的括号字符串。字符串 sss 从下标 iii 到 jjj 的子串的长度大于 222 等价于 j−i≥2j - i \ge 2j−i≥2，此时 dp[i][j]\textit{dp}[i][j]dp[i][j] 的计算如下，只要满足以下一个条件就有 dp[i][j]=true\textit{dp}[i][j] = \text{true}dp[i][j]=true：
     *
     *     如果 s[i]s[i]s[i] 和 s[j]s[j]s[j] 分别为左括号和右括号，或者为 ‘*’\text{`*'}‘*’，则当 dp[i+1][j−1]=true\textit{dp}[i + 1][j - 1] = \text{true}dp[i+1][j−1]=true 时，dp[i][j]=true\textit{dp}[i][j] = \text{true}dp[i][j]=true，此时 s[i]s[i]s[i] 和 s[j]s[j]s[j] 可以分别看成左括号和右括号；
     *
     *     如果存在 i≤k<ji \le k < ji≤k<j 使得 dp[i][k]\textit{dp}[i][k]dp[i][k] 和 dp[k+1][j]\textit{dp}[k + 1][j]dp[k+1][j] 都为 true\textit{true}true，则 dp[i][j]=true\textit{dp}[i][j] = \text{true}dp[i][j]=true，因为字符串 sss 的下标范围 [i,k][i, k][i,k] 和 [k+1,j][k + 1, j][k+1,j] 的子串分别为有效的括号字符串，将两个子串拼接之后的子串也为有效的括号字符串，对应下标范围 [i,j][i, j][i,j]。
     *
     * 上述计算过程为从较短的子串的结果得到较长的子串的结果，因此需要注意动态规划的计算顺序。最终答案为 dp[0][n−1]\textit{dp}[0][n - 1]dp[0][n−1]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-by-leetc-osi3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param s
     * @return
     * 执行用时：11 ms, 在所有 Java 提交中击败了5.39% 的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了5.06% 的用户
     */
    public boolean checkValidString1(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                dp[i][i] = true;
            }
        }
        for (int i = 1; i < n; i++) {
            char c1 = s.charAt(i - 1), c2 = s.charAt(i);
            dp[i - 1][i] = (c1 == '(' || c1 == '*') && (c2 == ')' || c2 == '*');
        }
        for (int i = n - 3; i >= 0; i--) {
            char c1 = s.charAt(i);
            for (int j = i + 2; j < n; j++) {
                char c2 = s.charAt(j);
                if ((c1 == '(' || c1 == '*') && (c2 == ')' || c2 == '*')) {
                    dp[i][j] = dp[i + 1][j - 1];
                }
                for (int k = i; k < j && !dp[i][j]; k++) {
                    dp[i][j] = dp[i][k] && dp[k + 1][j];
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 方法二：栈
     *
     * 括号匹配的问题可以用栈求解。
     *
     * 如果字符串中没有星号，则只需要一个栈存储左括号，在从左到右遍历字符串的过程中检查括号是否匹配。
     *
     * 在有星号的情况下，需要两个栈分别存储左括号和星号。从左到右遍历字符串，进行如下操作。
     *
     *     如果遇到左括号，则将当前下标存入左括号栈。
     *
     *     如果遇到星号，则将当前下标存入星号栈。
     *
     *     如果遇到右括号，则需要有一个左括号或星号和右括号匹配，由于星号也可以看成右括号或者空字符串，因此当前的右括号应优先和左括号匹配，没有左括号时和星号匹配：
     *
     *         如果左括号栈不为空，则从左括号栈弹出栈顶元素；
     *
     *         如果左括号栈为空且星号栈不为空，则从星号栈弹出栈顶元素；
     *
     *         如果左括号栈和星号栈都为空，则没有字符可以和当前的右括号匹配，返回 false\text{false}false。
     *
     * 遍历结束之后，左括号栈和星号栈可能还有元素。为了将每个左括号匹配，需要将星号看成右括号，且每个左括号必须出现在其匹配的星号之前。当两个栈都不为空时，每次从左括号栈和星号栈分别弹出栈顶元素，对应左括号下标和星号下标，判断是否可以匹配，匹配的条件是左括号下标小于星号下标，如果左括号下标大于星号下标则返回 false\text{false}false。
     *
     * 最终判断左括号栈是否为空。如果左括号栈为空，则左括号全部匹配完毕，剩下的星号都可以看成空字符串，此时 sss 是有效的括号字符串，返回 true\text{true}true。如果左括号栈不为空，则还有左括号无法匹配，此时 sss 不是有效的括号字符串，返回 false\text{false}false。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-by-leetc-osi3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * @param s
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了52.53% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了35.62% 的用户
     */
    public boolean checkValidString2(String s) {
        Deque<Integer> leftStack = new LinkedList<Integer>();
        Deque<Integer> asteriskStack = new LinkedList<Integer>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                asteriskStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!asteriskStack.isEmpty()) {
                    asteriskStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!leftStack.isEmpty() && !asteriskStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int asteriskIndex = asteriskStack.pop();
            if (leftIndex > asteriskIndex) {
                return false;
            }
        }
        return leftStack.isEmpty();
    }

    /**
     * 方法三：贪心
     *
     * 使用贪心的思想，可以将空间复杂度降到 O(1)。
     *
     * 从左到右遍历字符串，遍历过程中，未匹配的左括号数量可能会出现如下变化：
     *
     *     如果遇到左括号，则未匹配的左括号数量加 1；
     *
     *     如果遇到右括号，则需要有一个左括号和右括号匹配，因此未匹配的左括号数量减 1；
     *
     *     如果遇到星号，由于星号可以看成左括号、右括号或空字符串，因此未匹配的左括号数量可能加 1、减 1 或不变。
     *
     * 基于上述结论，可以在遍历过程中维护未匹配的左括号数量可能的最小值和最大值，根据遍历到的字符更新最小值和最大值：
     *
     *     如果遇到左括号，则将最小值和最大值分别加 1；
     *
     *     如果遇到右括号，则将最小值和最大值分别减 1；
     *
     *     如果遇到星号，则将最小值减 1，将最大值加 1。
     *
     * 任何情况下，未匹配的左括号数量必须非负，因此当最大值变成负数时，说明没有左括号可以和右括号匹配，返回 false。
     *
     * 当最小值为 0 时，不应将最小值继续减少，以确保最小值非负。
     *
     * 遍历结束时，所有的左括号都应和右括号匹配，因此只有当最小值为 0 时，字符串 s 才是有效的括号字符串。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-by-leetc-osi3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了42.40% 的用户
     */
    public boolean checkValidString3(String s) {
        int minCount = 0, maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minCount++;
                maxCount++;
            } else if (c == ')') {
                minCount = Math.max(minCount - 1, 0);
                maxCount--;
                if (maxCount < 0) {
                    return false;
                }
            } else {
                minCount = Math.max(minCount - 1, 0);
                maxCount++;
            }
        }
        return minCount == 0;
    }
}
