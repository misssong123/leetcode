package com.meng.enterprise.day01;

/**
 * 1545. 找出第 N 个二进制字符串中的第 K 位(中等)
 * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
 *
 * S1 = "0"
 * 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
 * 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。
 *
 * 例如，符合上述描述的序列的前 4 个字符串依次是：
 *
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 1
 * 输出："0"
 * 解释：S3 为 "0111001"，其第 1 位为 "0" 。
 * 示例 2：
 *
 * 输入：n = 4, k = 11
 * 输出："1"
 * 解释：S4 为 "011100110110001"，其第 11 位为 "1" 。
 * 示例 3：
 *
 * 输入：n = 1, k = 1
 * 输出："0"
 * 示例 4：
 *
 * 输入：n = 2, k = 3
 * 输出："1"
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= 2n - 1
 */
public class FindKthBit {
    /**
     * @param n
     * @param k
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 69.81%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     */
    public char findKthBit(int n, int k){
        if (k == 1){
            return '0';
        }
        boolean flag = true;
        while (k > 1){
            int mid = 1<<n;
            if (mid == k){
                return flag?'1':'0';
            }else if(k < mid){
                n--;
            }else {
                k = mid * 2 - k;
                flag = ! flag;
            }
        }
        return flag?'0':'1';
    }

    public static void main(String[] args) {
        FindKthBit demo = new FindKthBit();
        for(int i = 1 ; i < 16 ;i++){
            System.out.print("i:"+i+"ans:");
            System.out.println(demo.findKthBit(4,i));
        }
    }

    /**
     * 方法一：递归
     * 观察二进制字符串 S_nS
     * n
     * ​
     *  ，可以发现，当 n>1n>1 时，S_nS
     * n
     * ​
     *   是在 S_{n-1}S
     * n−1
     * ​
     *   的基础上形成的。用 \text{len}_nlen
     * n
     * ​
     *   表示 S_nS
     * n
     * ​
     *   的长度，则 S_nS
     * n
     * ​
     *   的前 \text{len}_{n-1}len
     * n−1
     * ​
     *   个字符与 S_{n-1}S
     * n−1
     * ​
     *   相同。还可以发现，当 n>1n>1 时，\text{len}_n=\text{len}_{n-1} \times 2 + 1len
     * n
     * ​
     *  =len
     * n−1
     * ​
     *  ×2+1，根据 \text{len}_1=1len
     * 1
     * ​
     *  =1 可知 \text{len}_n=2^n-1len
     * n
     * ​
     *  =2
     * n
     *  −1。
     *
     * 由于 S_1=``0"S
     * 1
     * ​
     *  =‘‘0"，且对于任意 n \ge 1n≥1，S_nS
     * n
     * ​
     *   的第 11 位字符也一定是 `0'‘0
     * ′
     *  ，因此当 k=1k=1 时，直接返回字符 `0'‘0
     * ′
     *  。
     *
     * 当 n>1n>1 时，S_nS
     * n
     * ​
     *   的长度是 2^n-12
     * n
     *  −1。S_nS
     * n
     * ​
     *   可以分成三个部分，左边 2^{n-1}-12
     * n−1
     *  −1 个字符是 S_{n-1}S
     * n−1
     * ​
     *  ，中间 11 个字符是 `1'‘1
     * ′
     *  ，右边 2^{n-1}-12
     * n−1
     *  −1 个字符是 S_{n-1}S
     * n−1
     * ​
     *   翻转与反转之后的结果。中间的字符 `1'‘1
     * ′
     *   是 S_nS
     * n
     * ​
     *   的第 2^{n-1}2
     * n−1
     *   位字符，因此如果 k=2^{n-1}k=2
     * n−1
     *  ，直接返回字符 `1'‘1
     * ′
     *  。
     *
     * 当 k \ne 2^{n-1}k
     * 
     * ​
     *  =2
     * n−1
     *   时，考虑以下两种情况：
     *
     * 如果 k<2^{n-1}k<2
     * n−1
     *  ，则第 kk 位字符在 S_nS
     * n
     * ​
     *   的前半部分，即第 kk 位字符在 S_{n-1}S
     * n−1
     * ​
     *   中，因此在 S_{n-1}S
     * n−1
     * ​
     *   中寻找第 kk 位字符；
     *
     * 如果 k>2^{n-1}k>2
     * n−1
     *  ，则第 kk 位字符在 S_nS
     * n
     * ​
     *   的后半部分，由于后半部分为前半部分进行翻转与反转之后的结果，因此在前半部分寻找第 2^n-k2
     * n
     *  −k 位字符，将其反转之后即为 S_nS
     * n
     * ​
     *   的第 kk 位字符。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string/solution/zhao-chu-di-n-ge-er-jin-zhi-zi-fu-chuan-zhong-de-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @param k
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 81.40%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     */
    public char findKthBit1(int n, int k) {
        if (k == 1) {
            return '0';
        }
        int mid = 1 << (n - 1);
        if (k == mid) {
            return '1';
        } else if (k < mid) {
            return findKthBit1(n - 1, k);
        } else {
            k = mid * 2 - k;
            return invert(findKthBit1(n - 1, k));
        }
    }

    public char invert(char bit) {
        return (char) ('0' + '1' - bit);
    }


}
