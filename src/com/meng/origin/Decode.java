package com.meng.origin;

/**
 * 1720. 解码异或后的数组
 *
 * 未知 整数数组 arr 由 n 个非负整数组成。
 *
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 *
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
 *
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：encoded = [1,2,3], first = 1
 * 输出：[1,0,2,1]
 * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 *
 * 示例 2：
 *
 * 输入：encoded = [6,2,7,3], first = 4
 * 输出：[4,2,0,7,4]
 *
 *
 *
 * 提示：
 *
 *     2 <= n <= 104
 *     encoded.length == n - 1
 *     0 <= encoded[i] <= 105
 *     0 <= first <= 105
 */
public class Decode {
    /**
     *
     执行用时：
     2 ms
     , 在所有 Java 提交中击败了
     73.79%
     的用户
     内存消耗：
     39.3 MB
     , 在所有 Java 提交中击败了
     77.43%
     的用户
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length+1;
        int[] res  = new int[n];
        res[0] = first;
        for(int i = 1 ; i < n ; i++){
            res[i] = res[i-1] ^ encoded[i-1];
        }
        return res;
    }
    /**
     * 方法一：利用异或运算的性质
     *
     * 原数组 arr\textit{arr}arr 的长度为 nnn，对 arr\textit{arr}arr 编码后得到长度为 n−1n-1n−1 的数组 encoded\textit{encoded}encoded，编码规则为：encoded[i]=arr[i]⊕arr[i+1]\textit{encoded}[i]=\textit{arr}[i] \oplus \textit{arr}[i+1]encoded[i]=arr[i]⊕arr[i+1]，其中 ⊕\oplus⊕ 是异或运算符，0≤i<n−10 \le i<n-10≤i<n−1。
     *
     * 已知编码后的数组 encoded\textit{encoded}encoded 和原数组 arr\textit{arr}arr 的第一个元素 arr[0]=first\textit{arr}[0]=\textit{first}arr[0]=first，需要解码得到原数组 arr\textit{arr}arr。可以利用异或运算的性质实现。
     *
     * 异或运算具有如下性质：
     *
     *     异或运算满足交换律和结合律；
     *
     *     任意整数和自身做异或运算的结果都等于 000，即 x⊕x=0x \oplus x = 0x⊕x=0；
     *
     *     任意整数和 000 做异或运算的结果都等于其自身，即 x⊕0=0⊕x=xx \oplus 0 = 0 \oplus x = xx⊕0=0⊕x=x。
     *
     * 当 1≤i<n1 \le i<n1≤i<n 时，有 encoded[i−1]=arr[i−1]⊕arr[i]\textit{encoded}[i-1]=\textit{arr}[i-1] \oplus \textit{arr}[i]encoded[i−1]=arr[i−1]⊕arr[i]。在等号两边同时异或 arr[i−1]\textit{arr}[i-1]arr[i−1]，可以得到 arr[i]=arr[i−1]⊕encoded[i−1]\textit{arr}[i]=\textit{arr}[i-1] \oplus \textit{encoded}[i-1]arr[i]=arr[i−1]⊕encoded[i−1]，计算过程如下：
     *
     * encoded[i−1]=arr[i−1]⊕arr[i]encoded[i−1]⊕arr[i−1]=arr[i−1]⊕arr[i]⊕arr[i−1]arr[i−1]⊕encoded[i−1]=arr[i−1]⊕arr[i−1]⊕arr[i]arr[i−1]⊕encoded[i−1]=0⊕arr[i]arr[i−1]⊕encoded[i−1]=arr[i]\begin{aligned} \textit{encoded}[i-1] &= \textit{arr}[i-1] \oplus \textit{arr}[i] \\ \textit{encoded}[i-1] \oplus \textit{arr}[i-1] &= \textit{arr}[i-1] \oplus \textit{arr}[i] \oplus \textit{arr}[i-1] \\ \textit{arr}[i-1] \oplus \textit{encoded}[i-1] &= \textit{arr}[i-1] \oplus \textit{arr}[i-1] \oplus \textit{arr}[i] \\ \textit{arr}[i-1] \oplus \textit{encoded}[i-1] &= 0 \oplus \textit{arr}[i] \\ \textit{arr}[i-1] \oplus \textit{encoded}[i-1] &= \textit{arr}[i] \end{aligned} encoded[i−1]encoded[i−1]⊕arr[i−1]arr[i−1]⊕encoded[i−1]arr[i−1]⊕encoded[i−1]arr[i−1]⊕encoded[i−1]​=arr[i−1]⊕arr[i]=arr[i−1]⊕arr[i]⊕arr[i−1]=arr[i−1]⊕arr[i−1]⊕arr[i]=0⊕arr[i]=arr[i]​
     *
     * 因此当 1≤i<n1 \le i<n1≤i<n 时，有 arr[i]=arr[i−1]⊕encoded[i−1]\textit{arr}[i]=\textit{arr}[i-1] \oplus \textit{encoded}[i-1]arr[i]=arr[i−1]⊕encoded[i−1]。
     *
     * 由于 arr[0]=first\textit{arr}[0]=\textit{first}arr[0]=first 已知，因此对 iii 从 111 到 n−1n-1n−1 依次计算 arr[i]\textit{arr}[i]arr[i] 的值，即可解码得到原数组 arr\textit{arr}arr。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/decode-xored-array/solution/jie-ma-yi-huo-hou-de-shu-zu-by-leetcode-yp0mg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：2 ms, 在所有 Java 提交中击败了73.79% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了95.70% 的用户
     */
    public int[] decode1(int[] encoded, int first) {
        int n = encoded.length + 1;
        int[] arr = new int[n];
        arr[0] = first;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] ^ encoded[i - 1];
        }
        return arr;
    }
}
