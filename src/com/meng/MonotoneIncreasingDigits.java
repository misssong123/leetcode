package com.meng;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 738. 单调递增的数字
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 *
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 *
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 *
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class MonotoneIncreasingDigits {
    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了11.20% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了35.22% 的用户
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        if (N<10)
            return N;
        String s = N+"";
        int len = s.length();
        char [] chars = s.toCharArray();
        for(int i = len - 2 ; i>=0 ; i--){
                if (chars[i]<=chars[i+1]){
                    continue;
                }else {
                    chars[i] =(char)(chars[i]-1);
                    Arrays.fill(chars,i+1,len,'9');
                }
            }
        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        MonotoneIncreasingDigits demo = new MonotoneIncreasingDigits();
        System.out.println(demo.monotoneIncreasingDigits(10));
    }
    /**
     * 方法一：贪心
     *
     * 我们可以从高到低按位构造这个小于等于 NNN 的最大单调递增的数字。假设不考虑 NNN 的限制，那么对于一个长度为 nnn 的数字，最大单调递增的数字一定是每一位都为 999 的数字。
     *
     * 记 strN[i]\textit{strN}[i]strN[i] 表示数字 NNN 从高到低的第 iii 位的数字（iii 从 000 开始）。
     *
     * 如果整个数字 NNN 本身已经是按位单调递增的，那么最大的数字即为 NNN。
     *
     * 如果找到第一个位置 iii 使得 [0,i−1][0,i-1][0,i−1] 的数位单调递增且 strN[i−1]>strN[i]\textit{strN}[i-1]>\textit{strN}[i]strN[i−1]>strN[i]，此时 [0,i][0,i][0,i] 的数位都与 NNN 的对应数位相等，仍然被 NNN 限制着，即我们不能随意填写 [i+1,n−1][i+1,n-1][i+1,n−1] 位置上的数字。为了得到最大的数字，我们需要解除 NNN 的限制，来让剩余的低位全部变成 999 ，即能得到小于 NNN 的最大整数。而从贪心的角度考虑，我们需要尽量让高位与 NNN 的对应数位相等，故尝试让 strN[i−1]\textit{strN}[i-1]strN[i−1] 自身数位减 111。此时已经不再受 NNN 的限制，直接将 [i,n−1][i, n-1][i,n−1] 的位置上的数全部变为 999 即可。
     *
     * 但这里存在一个问题：当 strN[i−1]\textit{strN}[i-1]strN[i−1] 自身数位减 111 后可能会使得 strN[i−1]\textit{strN}[i-1]strN[i−1] 和 strN[i−2]\textit{strN}[i-2]strN[i−2] 不再满足递增的关系，因此我们需要从 i−1i-1i−1 开始递减比较相邻数位的关系，直到找到第一个位置 jjj 使得 strN[j]\textit{strN}[j]strN[j] 自身数位减 111 后 strN[j−1]\textit{strN}[j-1]strN[j−1] 和 strN[j]\textit{strN}[j]strN[j] 仍然保持递增关系，或者位置 jjj 已经到最左边（即 jjj 的值为 000），此时我们将 [j+1,n−1][j+1,n-1][j+1,n−1] 的数全部变为 999 才能得到最终正确的答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits/solution/dan-diao-di-zeng-de-shu-zi-by-leetcode-s-5908/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param N
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.96% 的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了39.07% 的用户
     */
    public int monotoneIncreasingDigits1(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}
