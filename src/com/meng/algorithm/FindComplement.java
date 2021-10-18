package com.meng.algorithm;

/**
 * 476. 数字的补数
 *
 * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
 *
 *     例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
 *
 * 给你一个整数 num ，输出它的补数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 5
 * 输出：2
 * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 *
 * 示例 2：
 *
 * 输入：num = 1
 * 输出：0
 * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 *
 *
 *
 * 提示：
 *
 *     1 <= num < 231
 */
public class FindComplement {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了13.18% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了35.72% 的用户
     * 通过测试用例：149 / 149
     * @param num
     * @return
     */
    public int findComplement(int num) {
        String s = Integer.toBinaryString(num);
        int res = 0 ;
        for(int i = s.length()-1,j=1 ; i > 0 ; i--,j*=2){
            if (s.charAt(i) == '0'){
                res+=j;
            }
        }
        return res;
    }

    /**
     * 方法一：位运算
     *
     * 思路与算法
     *
     * 根据题目的要求，我们需要将 num\textit{num}num 二进制表示的每一位取反。然而在计算机存储整数时，并不会仅仅存储有效的二进制位。例如当 num=5\textit{num} = 5num=5 时，它的二进制表示为 (101)2(101)_2(101)2​，而使用 323232 位整数存储时的结果为：
     *
     * (0000 0000 0000 0000 0000 0000 0000 0101)2(0000~0000~0000~0000~0000~0000~0000~0101)_2 (0000 0000 0000 0000 0000 0000 0000 0101)2​
     *
     * 因此我们需要首先找到 num\textit{num}num 二进制表示最高位的那个 111，再将这个 111 以及更低的位进行取反。
     *
     * 如果 num\textit{num}num 二进制表示最高位的 111 是第 i (0≤i≤30)i~(0 \leq i \leq 30)i (0≤i≤30) 位，那么一定有：
     *
     * 2i≤num<2i+12^i \leq \textit{num} < 2^{i+1} 2i≤num<2i+1
     *
     * 因此我们可以使用一次遍历，在 [0,30][0, 30][0,30] 中找出 iii 的值。
     *
     * 在这之后，我们就可以遍历 num\textit{num}num 的第 0∼i0 \sim i0∼i 个二进制位，将它们依次进行取反。我们也可以用更高效的方式，构造掩码 mask=2i+1−1\textit{mask} = 2^{i+1} - 1mask=2i+1−1，它是一个 i+1i+1i+1 位的二进制数，并且每一位都是 111。我们将 num\textit{num}num 与 mask\textit{mask}mask 进行异或运算，即可得到答案。
     *
     * 细节
     *
     * 当 i=30i=30i=30 时，构造 mask=2i+1−1\textit{mask} = 2^{i+1} - 1mask=2i+1−1 的过程中需要保证不会产生整数溢出。下面部分语言的代码中对该情况进行了特殊判断。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-complement/solution/shu-zi-de-bu-shu-by-leetcode-solution-xtn8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了40.93% 的用户
     * 通过测试用例：149 / 149
     */
    public int findComplement1(int num) {
        int highbit = 0;
        for (int i = 1; i <= 30; ++i) {
            if (num >= 1 << i) {
                highbit = i;
            } else {
                break;
            }
        }
        int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
        return num ^ mask;
    }


    public static void main(String[] args) {
        FindComplement demo = new FindComplement();
        System.out.println(demo.findComplement(5));
    }
}
