package com.meng.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成(中等)
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 */
public class T013GenerateParenthesis {

    /**
     * 回溯
     * @param n
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.07%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 97.21%
     * 的用户
     * 通过测试用例：
     * 8 / 8
     */
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n == 1){
            res.add("()");
        }else {
            dfs(0,0,n,new StringBuffer());
        }
        return res;
    }
    private void dfs(int left, int right, int n, StringBuffer sb) {
        if (left == n && right == n){
            res.add(sb.toString());
            return;
        }
        if (left < n){
            sb.append("(");
            dfs(left+1,right,n,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if (right<left){
            sb.append(")");
            dfs(left,right+1,n,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        T013GenerateParenthesis demo = new T013GenerateParenthesis();
        int  n = 2;
        System.out.println(demo.generateParenthesis(n));
    }

    /**
     * 方法一：暴力法
     * 思路
     *
     * 我们可以生成所有 2^{2n}2
     * 2n
     *   个 \text{`('}‘(’ 和 \text{`)'}‘)’ 字符构成的序列，然后我们检查每一个是否有效即可。
     *
     * 算法
     *
     * 为了生成所有序列，我们可以使用递归。长度为 nn 的序列就是在长度为 n - 1n−1 的序列前加一个 \text{`('}‘(’ 或 \text{`)'}‘)’。
     *
     * 为了检查序列是否有效，我们遍历这个序列，并使用一个变量 \textit{balance}balance 表示左括号的数量减去右括号的数量。如果在遍历过程中 \textit{balance}balance 的值小于零，或者结束时 \textit{balance}balance 的值不为零，那么该序列就是无效的，否则它是有效的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 21.74%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 70.10%
     * 的用户
     * 通过测试用例：
     * 8 / 8
     */
    public List<String> generateParenthesis1(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    /**
     * 方法二：回溯法
     * 思路和算法
     *
     * 方法一还有改进的余地：我们可以只在序列仍然保持有效时才添加 \text{`('}‘(’ 或 \text{`)'}‘)’，而不是像 方法一 那样每次添加。我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
     *
     * 如果左括号数量不大于 nn，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 73.42%
     * 的用户
     * 通过测试用例：
     * 8 / 8
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


    /**
     *方法三：按括号序列的长度递归
     * 思路与算法
     *
     * 任何一个括号序列都一定是由 \text{`('}‘(’ 开头，并且第一个 \text{`('}‘(’ 一定有一个唯一与之对应的 \text{`)'}‘)’。这样一来，每一个括号序列可以用 (a)b(a)b 来表示，其中 aa 与 bb 分别是一个合法的括号序列（可以为空）。
     *
     * 那么，要生成所有长度为 2n2n 的括号序列，我们定义一个函数 \textit{generate}(n)generate(n) 来返回所有可能的括号序列。那么在函数 \textit{generate}(n)generate(n) 的过程中：
     *
     * 我们需要枚举与第一个 \text{`('}‘(’ 对应的 \text{`)'}‘)’ 的位置 2i + 12i+1；
     * 递归调用 \textit{generate}(i)generate(i) 即可计算 aa 的所有可能性；
     * 递归调用 \textit{generate}(n - i - 1)generate(n−i−1) 即可计算 bb 的所有可能性；
     * 遍历 aa 与 bb 的所有可能性并拼接，即可得到所有长度为 2n2n 的括号序列。
     * 为了节省计算时间，我们在每次 \textit{generate}(i)generate(i) 函数返回之前，把返回值存储起来，下次再调用 \textit{generate}(i)generate(i) 时可以直接返回，不需要再递归计算。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 9.42%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 44.82%
     * 的用户
     * 通过测试用例：
     * 8 / 8
     */
    ArrayList[] cache = new ArrayList[100];
    public List<String> generateParenthesis3(int n) {
        return generate(n);
    }

    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }


}
