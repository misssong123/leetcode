package com.meng.algorithm;

/**
 * 1446. 连续字符
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 *
 * 请你返回字符串的能量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "leetcode"
 * 输出：2
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * 示例 2：
 *
 * 输入：s = "abbcccddddeeeeedcba"
 * 输出：5
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
 * 示例 3：
 *
 * 输入：s = "triplepillooooow"
 * 输出：5
 * 示例 4：
 *
 * 输入：s = "hooraaaaaaaaaaay"
 * 输出：11
 * 示例 5：
 *
 * 输入：s = "tourist"
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 */
public class MaxPower {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.9 MB
     * , 在所有 Java 提交中击败了
     * 85.64%
     * 的用户
     * 通过测试用例：
     * 333 / 333
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int len = s.length();
        int res = 1;
        int temp = 1;
        for(int i =1; i < len ; i++){
            if (s.charAt(i) == s.charAt(i-1)){
                temp++;
                continue;
            }
            res = Math.max(res,temp);
            temp = 1;
        }
        res = Math.max(res,temp);
        return res;
    }

    /**
     * 方法一：一次遍历
     * 题目中的「只包含一种字符的最长非空子字符串的长度」，即为某个字符连续出现次数的最大值。据此可以设计如下算法来求解：
     *
     * 初始化当前字符连续出现次数 \textit{cnt}cnt 为 11。
     *
     * 从 s[1]s[1] 开始，向后遍历字符串，如果 s[i]=s[i-1]s[i]=s[i−1]，则将 \textit{cnt}cnt 加一，否则将 \textit{cnt}cnt 重置为 11。
     *
     * 维护上述过程中 \textit{cnt}cnt 的最大值，即为答案。
     *
     * Python3C++JavaC#GolangJavaScript
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/consecutive-characters/solution/lian-xu-zi-fu-by-leetcode-solution-lctm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.8 MB
     * , 在所有 Java 提交中击败了
     * 93.47%
     * 的用户
     * 通过测试用例：
     * 333 / 333
     */
    public int maxPower1(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                ++cnt;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }


}
