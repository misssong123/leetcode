package com.meng.hot100;

/**
 * 10. 正则表达式匹配(困难)
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *
 * 示例 1：
 *
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class T006IsMatch {
    /**
     * 暂无思路
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (p.length() > s.length()){
            return false;
        }
        if (p.equals(s) || p.equals(".*")){
            return true;
        }
        return s.matches(p);
    }

    public static void main(String[] args) {
        String s = "aaa";
        String p = "a*";
//        "aab"
//        "c*a*b"
        System.out.println(s.matches(p));
    }
    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 题目中的匹配是一个「逐步匹配」的过程：我们每次从字符串 p 中取出一个字符或者「字符 + 星号」的组合，并在
     * s 中进行匹配。对于 pp 中一个字符而言，它只能在 s 中匹配一个字符，匹配的方法具有唯一性；而对于 p 中字符 + 星号的组合而言，
     * 它可以在 s 中匹配任意自然数个字符，并不具有唯一性。因此我们可以考虑使用动态规划，对匹配的方案进行枚举。
     *
     * 我们用 f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配。
     * 在进行状态转移时，我们考虑 p 的第 j 个字符的匹配情况：
     *
     * 如果 p 的第 j 个字符是一个小写字母，
     * 那么我们必须在 s 中匹配一个相同的小写字母，即
     *
     * f[i][j] = f[i - 1][j - 1], & s[i] = p[j], & s[i] \neq p[j] \end{cases}
     * f[i][j]={
     * f[i−1][j−1],
     * false,
     *
     * s[i]=p[j]
     * s[i]
     *  =p[j]
     *
     *
     * 也就是说，如果 s 的第 i 个字符与 p 的第 j 个字符不相同，那么无法进行匹配；
     * 否则我们可以匹配两个字符串的最后一个字符，完整的匹配结果取决于两个字符串前面的部分。
     *
     * 如果 p 的第 j 个字符是 *，那么就表示我们可以对 p 的第 j-1
     * 个字符匹配任意自然数次。在匹配 0 次的情况下，我们有
     *
     * f[i][j] = f[i][j - 2]
     * f[i][j]=f[i][j−2]
     *
     * 也就是我们「浪费」了一个字符 + 星号的组合，没有匹配任何 ss 中的字符。
     *
     * 在匹配 1,2,3,⋯ 次的情况下，类似地我们有
     *
     * \begin{aligned} & f[i][j] = f[i - 1][j - 2],
     * \quad && \text{if~} s[i] = p[j - 1] \\ & f[i][j] = f[i - 2][j - 2],
     * \quad && \text{if~} s[i - 1] = s[i] = p[j - 1] \\ & f[i][j] = f[i - 3][j - 2],
     * \quad && \text{if~} s[i - 2] = s[i - 1] = s[i] = p[j - 1] \\ & \cdots\cdots & \end{aligned}
     *
     * f[i][j]=f[i−1][j−2],
     * f[i][j]=f[i−2][j−2],
     * f[i][j]=f[i−3][j−2],
     * ⋯⋯
     *
     * s[i]=p[j−1]
     * s[i−1]=s[i]=p[j−1]
     * s[i−2]=s[i−1]=s[i]=p[j−1]
     *
     *
     *
     * 如果我们通过这种方法进行转移，那么我们就需要枚举这个组合到底匹配了 s
     * 中的几个字符，会增导致时间复杂度增加，
     * 并且代码编写起来十分麻烦。我们不妨换个角度考虑这个问题：字母 + 星号的组合在匹配的过程中，本质上只会有两种情况：
     *
     * 匹配 s 末尾的一个字符，将该字符扔掉，
     * 而该组合还可以继续进行匹配；
     *
     * 不匹配字符，将该组合扔掉，
     * 不再进行匹配。
     *
     * 如果按照这个角度进行思考，
     * 我们可以写出很精巧的状态转移方程：
     *
     * f[i][j] = \begin{cases} f[i - 1][j] \text{~or~} f[i][j - 2], & s[i] = p[j - 1] \\ f[i][j - 2], & s[i] \neq p[j - 1] \end{cases}
     * f[i][j]={
     * f[i−1][j] or f[i][j−2],
     * f[i][j−2],
     * ​
     *
     * s[i]=p[j−1]
     * s[i]
     * 
     * ​
     *  =p[j−1]
     * ​
     *
     *
     * 在任意情况下，只要 p[j]p[j] 是 .，那么 p[j]p[j] 一定成功匹配 ss 中的任意一个小写字母。
     *
     * 最终的状态转移方程如下：
     *
     * f[i][j] = \begin{cases} \text{if~} (p[j] \neq \text{~`*'}) = \begin{cases} f[i - 1][j - 1], & \textit{matches}(s[i], p[j])\\ \text{false}, & \text{otherwise} \end{cases} \\ \text{otherwise} = \begin{cases} f[i - 1][j] \text{~or~} f[i][j - 2], & \textit{matches}(s[i], p[j-1]) \\ f[i][j - 2], & \text{otherwise} \end{cases} \end{cases}
     * f[i][j]=
     * ⎩
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎧
     * ​
     *
     * if (p[j]
     * 
     * ​
     *  = ‘*’)={
     * f[i−1][j−1],
     * false,
     * ​
     *
     * matches(s[i],p[j])
     * otherwise
     * ​
     *
     * otherwise={
     * f[i−1][j] or f[i][j−2],
     * f[i][j−2],
     * ​
     *
     * matches(s[i],p[j−1])
     * otherwise
     * ​
     *
     * ​
     *
     *
     * 其中 \textit{matches}(x, y)matches(x,y) 判断两个字符是否匹配的辅助函数。只有当 yy 是 . 或者 xx 和 yy 本身相同时，这两个字符才会匹配。
     *
     * 细节
     *
     * 动态规划的边界条件为 f[0][0] = \text{true}f[0][0]=true，即两个空字符串是可以匹配的。最终的答案即为 f[m][n]f[m][n]，其中 mm 和 nn 分别是字符串 ss 和 pp 的长度。由于大部分语言中，字符串的字符下标是从 00 开始的，因此在实现上面的状态转移方程时，需要注意状态中每一维下标与实际字符下标的对应关系。
     *
     * 在上面的状态转移方程中，如果字符串 pp 中包含一个「字符 + 星号」的组合（例如 a*），那么在进行状态转移时，会先将 a 进行匹配（当 p[j]p[j] 为 a 时），再将 a* 作为整体进行匹配（当 p[j]p[j] 为 * 时）。然而，在题目描述中，我们必须将 a* 看成一个整体，因此将 a 进行匹配是不符合题目要求的。看来我们进行了额外的状态转移，这样会对最终的答案产生影响吗？这个问题留给读者进行思考。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.1 MB
     * , 在所有 Java 提交中击败了
     * 55.62%
     * 的用户
     * 通过测试用例：
     * 353 / 353
     */
    public boolean isMatch1(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
