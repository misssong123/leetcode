package com.meng;

/**
 * 338. 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * 进阶:
 *
 *     给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 *     要求算法的空间复杂度为O(n)。
 *     你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */
public class CountBits {
    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了13.12% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了34.20% 的用户
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        for(int i = 0 ; i <= num ; i++){
            result[i] = Integer.bitCount(i);
        }
        return result;
    }
    /**
     * 方法一：直接计算
     *
     * 最直观的方法是对从 000 到 num\textit{num}num 的每个数直接计算「一比特数」。
     *
     * 每个 int\texttt{int}int 型的数都可以用 323232 位二进制数表示，只要遍历其二进制表示的每一位即可得到 111 的数目。
     *
     * 利用位运算的技巧，可以在一定程度上提升计算速度。按位与运算（&\&&）的一个性质是：对于任意整数 xxx，令 x=x&(x−1)x=x \&(x-1)x=x&(x−1)，该运算将 xxx 的二进制表示的最后一个 111 变成 000。因此，对 xxx 重复该操作，直到 xxx 变成 000，则操作次数即为 xxx 的「一比特数」。
     *
     * 另外，部分编程语言有相应的内置函数，例如 Java\texttt{Java}Java 的 Integer.bitCount\texttt{Integer.bitCount}Integer.bitCount，C++\texttt{C++}C++ 的 __builtin_popcount\texttt{\_\_builtin\_popcount}__builtin_popcount，Go\texttt{Go}Go 的 bits.OnesCount\texttt{bits.OnesCount}bits.OnesCount 等，读者可以自行尝试。需要注意的是，使用编程语言的内置函数时，不适用本方法的时间复杂度分析。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：2 ms, 在所有 Java 提交中击败了60.00% 的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了15.09% 的用户
     */
    public int[] countBits1(int num) {
        int[] bits = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }
    /**
     * 方法二：动态规划——最高有效位
     *
     * 方法一需要对每个数遍历其二进制表示的每一位。可以换一个思路，当计算 iii 的「一比特数」时，如果存在 0≤j<i0 \le j<i0≤j<i，jjj 的「一比特数」已知，且 iii 和 jjj 相比，iii 的二进制表示只多了一个 111，则可以快速得到 iii 的「一比特数」。
     *
     * 令 bits[i]\textit{bits}[i]bits[i] 表示 iii 的「一比特数」，则上述关系可以表示成：bits[i]=bits[j]+1\textit{bits}[i]= \textit{bits}[j]+1bits[i]=bits[j]+1。
     *
     * 对于正整数 xxx，如果可以知道最大的正整数 yyy，使得 y≤xy \le xy≤x 且 yyy 是 222 的整数次幂，则 yyy 的二进制表示中只有最高位是 111，其余都是 000，此时称 yyy 为 xxx 的「最高有效位」。令 z=x−yz=x-yz=x−y，显然 0≤z<x0 \le z<x0≤z<x，则 bits[x]=bits[z]+1\textit{bits}[x]=\textit{bits}[z]+1bits[x]=bits[z]+1。
     *
     * 为了判断一个正整数是不是 222 的整数次幂，可以利用方法一中提到的按位与运算的性质。如果正整数 yyy 是 222 的整数次幂，则 yyy 的二进制表示中只有最高位是 111，其余都是 000，因此 y&(y−1)=0y \&(y-1)=0y&(y−1)=0。由此可见，正整数 yyy 是 222 的整数次幂，当且仅当 y&(y−1)=0y \&(y-1)=0y&(y−1)=0。
     *
     * 显然，000 的「一比特数」为 000。使用 highBit\textit{highBit}highBit 表示当前的最高有效位，遍历从 111 到 num\textit{num}num 的每个正整数 iii，进行如下操作。
     *
     *     如果 i&(i−1)=0i \&(i-1)=0i&(i−1)=0，则令 highBit=i\textit{highBit}=ihighBit=i，更新当前的最高有效位。
     *
     *     iii 比 i−highBiti-\textit{highBit}i−highBit 的「一比特数」多 111，由于是从小到大遍历每个数，因此遍历到 iii 时，i−highBiti-\textit{highBit}i−highBit 的「一比特数」已知，令 bits[i]=bits[i−highBit]+1\textit{bits}[i]=\textit{bits}[i-\textit{highBit}]+1bits[i]=bits[i−highBit]+1。
     *
     * 最终得到的数组 bits\textit{bits}bits 即为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.95% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了32.44% 的用户
     */
    public int[] countBits2(int num) {
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }
    /**
     * 方法三：动态规划——最低有效位
     *
     * 方法二需要实时维护最高有效位，当遍历到的数是 222 的整数次幂时，需要更新最高有效位。如果再换一个思路，可以使用「最低有效位」计算「一比特数」。
     *
     * 对于正整数 xxx，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，得到的数是 ⌊x2⌋\lfloor \frac{x}{2} \rfloor⌊2x​⌋。如果 bits[⌊x2⌋]\textit{bits}\big[\lfloor \frac{x}{2} \rfloor\big]bits[⌊2x​⌋] 的值已知，则可以得到 bits[x]\textit{bits}[x]bits[x] 的值：
     *
     *     如果 xxx 是偶数，则 bits[x]=bits[⌊x2⌋]\textit{bits}[x]=\textit{bits}\big[\lfloor \frac{x}{2} \rfloor\big]bits[x]=bits[⌊2x​⌋]；
     *
     *     如果 xxx 是奇数，则 bits[x]=bits[⌊x2⌋]+1\textit{bits}[x]=\textit{bits}\big[\lfloor \frac{x}{2} \rfloor\big]+1bits[x]=bits[⌊2x​⌋]+1。
     *
     * 上述两种情况可以合并成：bits[x]\textit{bits}[x]bits[x] 的值等于 bits[⌊x2⌋]\textit{bits}\big[\lfloor \frac{x}{2} \rfloor\big]bits[⌊2x​⌋] 的值加上 xxx 除以 222 的余数。
     *
     * 由于 ⌊x2⌋\lfloor \frac{x}{2} \rfloor⌊2x​⌋ 可以通过 x>>1x >> 1x>>1 得到，xxx 除以 222 的余数可以通过 x&1x \& 1x&1 得到，因此有：bits[x]=bits[x>>1]+(x&1)\textit{bits}[x]=\textit{bits}[x>>1]+(x \& 1)bits[x]=bits[x>>1]+(x&1)。
     *
     * 遍历从 111 到 num\textit{num}num 的每个正整数 iii，计算 bits\textit{bits}bits 的值。最终得到的数组 bits\textit{bits}bits 即为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法3
     */
    public int[] countBits3(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
    /**
     * 方法四：动态规划——最低设置位
     *
     * 定义正整数 xxx 的「最低设置位」为 xxx 的二进制表示中的最低的 111 所在位。例如，101010 的二进制表示是 1010(2)1010_{(2)}1010(2)​，其最低设置位为 222，对应的二进制表示是 10(2)10_{(2)}10(2)​。
     *
     * 令 y=x&(x−1)y=x \&(x-1)y=x&(x−1)，则 yyy 为将 xxx 的最低设置位从 111 变成 000 之后的数，显然 0≤y<x0 \le y<x0≤y<x，bits[x]=bits[y]+1\textit{bits}[x]=\textit{bits}[y]+1bits[x]=bits[y]+1。因此对任意正整数 xxx，都有 bits[x]=bits[x&(x−1)]+1\textit{bits}[x]=\textit{bits}[x \&(x-1)]+1bits[x]=bits[x&(x−1)]+1。
     *
     * 遍历从 111 到 num\textit{num}num 的每个正整数 iii，计算 bits\textit{bits}bits 的值。最终得到的数组 bits\textit{bits}bits 即为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法4
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.95% 的用户
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了5.12% 的用户
     */
    public int[] countBits4(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}
