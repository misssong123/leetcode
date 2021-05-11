package com.meng;

/**
 * 1734. 解码异或后的排列
 *
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 *
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 *
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 *
 *
 * 示例 1：
 *
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 *
 * 示例 2：
 *
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 *
 *
 *
 * 提示：
 *
 *     3 <= n < 105
 *     n 是奇数。
 *     encoded.length == n - 1
 */
public class Decode_1734 {
    /**
     * 求出第一个元素或者最后一个元素就可以进行结果反推
     * 1.因为perm数组为n个数的排列，首先计算n个数的异或者
     * 2.encode中第i个元素为perm[i],perm[i+1]的异或结果，即将encode中的元素每隔一位进行异或，即可得到原数组perm[0]到perm[n-2]的值或者perm[1]到perm[n-1]的值
     * 3.将第一步的结果和第二步的结果进行异或既可以得到最后一个元素的值或者第一个元素的值
     * @param encoded
     * @return
     * 执行用时：4 ms, 在所有 Java 提交中击败了30.00% 的用户
     * 内存消耗：59.4 MB, 在所有 Java 提交中击败了97.33% 的用户
     */
    public int[] decode(int[] encoded) {
        int len = encoded.length;
        int[] res = new int[len+1];
        //获取原数组所有数据的结果
        int a = 0;
        for(int i = 1 ; i < len + 2 ; i++){
            a = a ^ i ;
        }
        int b = 0 ;
        for(int i = 0 ; i < len ; i += 2){
            b = b ^ encoded[i];
        }
        //获取原数组最后一个值
        res[len] = a ^ b;
        //依次获取原数组的值
        for(int i = len -1 ; i >= 0 ; i--){
            res[i] = encoded[i] ^ res[i+1];
        }
        return res;
    }
    /**
     * 方法一：利用异或运算解码
     *
     * 这道题规定了数组 perm\textit{perm}perm 是前 nnn 个正整数的排列，其中 nnn 是奇数，只有充分利用给定的条件，才能得到答案。
     *
     * 为了得到原始数组 perm\textit{perm}perm，应首先得到数组 perm\textit{perm}perm 的第一个元素（即下标为 000 的元素），这也是最容易得到的。如果能得到数组 perm\textit{perm}perm 的全部元素的异或运算结果，以及数组 perm\textit{perm}perm 除了 perm[0]\textit{perm}[0]perm[0] 以外的全部元素的异或运算结果，即可得到 perm[0]\textit{perm}[0]perm[0] 的值。
     *
     * 由于数组 perm\textit{perm}perm 是前 nnn 个正整数的排列，因此数组 perm\textit{perm}perm 的全部元素的异或运算结果即为从 111 到 nnn 的全部正整数的异或运算结果。用 total\textit{total}total 表示数组 perm\textit{perm}perm 的全部元素的异或运算结果，则有
     *
     * total=1⊕2⊕…⊕n=perm[0]⊕perm[1]⊕…⊕perm[n−1]\begin{aligned} \textit{total} &= 1 \oplus 2 \oplus \ldots \oplus n \\ &= \textit{perm}[0] \oplus \textit{perm}[1] \oplus \ldots \oplus \textit{perm}[n-1] \end{aligned} total​=1⊕2⊕…⊕n=perm[0]⊕perm[1]⊕…⊕perm[n−1]​
     *
     * 其中 ⊕\oplus⊕ 是异或运算符。
     *
     * 如何得到数组 perm\textit{perm}perm 除了 perm[0]\textit{perm}[0]perm[0] 以外的全部元素的异或运算结果？由于 nnn 是奇数，除了 perm[0]\textit{perm}[0]perm[0] 以外，数组 perm\textit{perm}perm 还有 n−1n-1n−1 个其他元素，n−1n-1n−1 是偶数，又由于数组 encoded\textit{encoded}encoded 的每个元素都是数组 perm\textit{perm}perm 的两个元素异或运算的结果，因此数组 encoded\textit{encoded}encoded 中存在 n−12\frac{n-1}{2}2n−1​ 个元素，这些元素的异或运算的结果为数组 perm\textit{perm}perm 除了 perm[0]\textit{perm}[0]perm[0] 以外的全部元素的异或运算结果。
     *
     * 具体而言，数组 encoded\textit{encoded}encoded 的所有下标为奇数的元素的异或运算结果即为数组 perm\textit{perm}perm 除了 perm[0]\textit{perm}[0]perm[0] 以外的全部元素的异或运算结果。用 odd\textit{odd}odd 表示数组 encoded\textit{encoded}encoded 的所有下标为奇数的元素的异或运算结果，则有
     *
     * odd=encoded[1]⊕encoded[3]⊕…⊕encoded[n−2]=perm[1]⊕perm[2]⊕…⊕perm[n]\begin{aligned} \textit{odd} &= \textit{encoded}[1] \oplus \textit{encoded}[3] \oplus \ldots \oplus \textit{encoded}[n-2] \\ &= \textit{perm}[1] \oplus \textit{perm}[2] \oplus \ldots \oplus \textit{perm}[n] \end{aligned} odd​=encoded[1]⊕encoded[3]⊕…⊕encoded[n−2]=perm[1]⊕perm[2]⊕…⊕perm[n]​
     *
     * 根据 total\textit{total}total 和 odd\textit{odd}odd 的值，即可计算得到 perm[0]\textit{perm}[0]perm[0] 的值：
     *
     * perm[0]=(perm[0]⊕…⊕perm[n])⊕(perm[1]⊕…⊕perm[n])=total⊕odd\begin{aligned} \textit{perm}[0] &= (\textit{perm}[0] \oplus \ldots \oplus \textit{perm}[n]) \oplus (\textit{perm}[1] \oplus \ldots \oplus \textit{perm}[n]) \\ &= \textit{total} \oplus \textit{odd} \end{aligned} perm[0]​=(perm[0]⊕…⊕perm[n])⊕(perm[1]⊕…⊕perm[n])=total⊕odd​
     *
     * 当 1≤i<n1 \le i<n1≤i<n 时，有 encoded[i−1]=perm[i−1]⊕perm[i]\textit{encoded}[i-1]=\textit{perm}[i-1] \oplus \textit{perm}[i]encoded[i−1]=perm[i−1]⊕perm[i]。在等号两边同时异或 perm[i−1]\textit{perm}[i-1]perm[i−1]，即可得到 perm[i]=perm[i−1]⊕encoded[i−1]\textit{perm}[i]=\textit{perm}[i-1] \oplus \textit{encoded}[i-1]perm[i]=perm[i−1]⊕encoded[i−1]。计算过程见「1720. 解码异或后的数组的官方题解」。
     *
     * 由于 perm[0]\textit{perm}[0]perm[0] 已知，因此对 iii 从 111 到 n−1n-1n−1 依次计算 perm[i]\textit{perm}[i]perm[i] 的值，即可得到原始数组 perm\textit{perm}perm。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/decode-xored-permutation/solution/jie-ma-yi-huo-hou-de-pai-lie-by-leetcode-9gw4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.67% 的用户
     * 内存消耗：59.6 MB, 在所有 Java 提交中击败了91.33% 的用户
     */
    public int[] decode1(int[] encoded) {
        int n = encoded.length + 1;
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }
        int odd = 0;
        for (int i = 1; i < n - 1; i += 2) {
            odd ^= encoded[i];
        }
        int[] perm = new int[n];
        perm[0] = total ^ odd;
        for (int i = 0; i < n - 1; i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }
        return perm;
    }
}
