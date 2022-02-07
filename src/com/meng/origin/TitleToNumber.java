package com.meng.origin;

/**
 * 171. Excel表列序号
 *
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 *
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 *
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 *
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 *
 * 致谢：
 * 特别感谢 @ts 添加此问题并创建所有测试用例。
 */
public class TitleToNumber {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了54.84% 的用户
     * @param columnTitle
     * @return
     */
    public int titleToNumber(String columnTitle) {
        int len = columnTitle.length();
        int res = 0;
        for(int i = len -1 , j= 1 ; i >=0 ; i-- ,j = j* 26){
            res += j * (columnTitle.charAt(i) - 'A' + 1);
        }
        return res;
    }

    /**
     * 法一：进制转换
     *
     * 这道题要求将 Excel\text{Excel}Excel 表中的列名称转换成相对应的列序号。由于 Excel\text{Excel}Excel 表的列名称由大写字母组成，大写字母共有 262626 个，因此列名称的表示实质是 262626 进制，需要将 262626 进制转换成十进制。
     *
     * 但是这道题和标准的进制转换不同，Excel\text{Excel}Excel 表的列序号是从 111 开始的，因此在进制转换时需要进行处理。
     *
     * 当列名称的长度为 nnn 时，列名称的每个字母都有 262626 种不同的取值，因此长度为 nnn 的不同列名称有 26n26^n26n 个。
     *
     * 当 n=1n=1n=1 时，列名称的取值范围是 A\text{A}A 到 Z\text{Z}Z，分别对应 111 到 262626。当 n>1n>1n>1 时，首先得到除了最高位以外的 n−1n-1n−1 位部分对应的列序号，然后考虑最高位对列序号的增量。
     *
     * 计算最高位对列序号的增量时，需要考虑列名称的长度和最高位的值，假设列名称的长度为 nnn。
     *
     *     当最高位是 A\text{A}A 时，除了最高位的 n−1n-1n−1 位有 26n−126^{n-1}26n−1 种不同的取值，因此最高位 A\text{A}A 对列序号的增量为 26n−126^{n-1}26n−1。
     *
     *     当最高位是 B\text{B}B 时，和最高位是 A\text{A}A 相比（即最高位从 A\text{A}A 变成 B\text{B}B，其余 n−1n-1n−1 位不变），列序号增加了 26n−126^{n-1}26n−1，因此最高位 B\text{B}B 对列序号的增量为 2×26n−12 \times 26^{n-1}2×26n−1。
     *
     *     当最高位是第 kkk 个字母时（1≤k≤261 \le k \le 261≤k≤26），最高位对列序号的增量为 k×26n−1k \times 26^{n-1}k×26n−1。
     *
     * 上述结论对 n>1n>1n>1 的情况都成立。当 n=1n=1n=1 时，n−1=0n-1=0n−1=0，k×26n−1=k×1=kk \times 26^{n-1}=k \times 1 = kk×26n−1=k×1=k，因此 n=1n=1n=1 的情况也适用上述结论。
     *
     * 根据上述结论可知，列名称的每一位都会对列序号产生一个增量，列序号即为每一位的增量之和。如果列名称的每一位对应的序号为 [an−1,an−2,…,a0][a_{n-1}, a_{n-2}, \ldots, a_0][an−1​,an−2​,…,a0​]，其中对于任意 0≤i<n0 \le i < n0≤i<n 都有 1≤ai≤261 \le a_i \le 261≤ai​≤26，则列名称对应的列序号为：
     *
     * number=∑i=0n−1ai×26i\textit{number} = \sum_{i=0}^{n-1} a_i \times 26^i number=i=0∑n−1​ai​×26i
     *
     * 以 columnTitle=“FXSHRXW”\textit{columnTitle} = \text{``FXSHRXW''}columnTitle=“FXSHRXW” 为例，计算过程如下：
     *
     * “FXSHRXW”\text{``FXSHRXW''}“FXSHRXW” 中的每个字母对应的序号分别是：[6,24,19,8,18,24,23][6,24,19,8,18,24,23][6,24,19,8,18,24,23]（其中 A\text{A}A 到 Z\text{Z}Z 分别对应 111 到 262626），则列名称对应的列序号为：
     *
     * 23×260+24×261+18×262+8×263+19×264+24×265+6×266=214748364723 \times 26^0 + 24 \times 26^1 + 18 \times 26^2 + 8 \times 26^3 + 19 \times 26^4 + 24 \times 26^5 + 6 \times 26^6 = 2147483647 23×260+24×261+18×262+8×263+19×264+24×265+6×266=2147483647
     *
     * 由此可以得到如下实现：首先将列序号初始化为 000，然后从右往左遍历列名称，对于列名称的从右往左的第 iii 位（0≤i<n0 \le i < n0≤i<n），如果是第 kkk 个字母（1≤k≤261 \le k \le 261≤k≤26），则将列序号的值增加 k×26ik \times 26^ik×26i。遍历结束时即可得到列序号。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number/solution/excelbiao-lie-xu-hao-by-leetcode-solutio-r29l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param columnTitle
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了74.96% 的用户
     */
    public int titleToNumber1(String columnTitle) {
        int number = 0;
        int multiple = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int k = columnTitle.charAt(i) - 'A' + 1;
            number += k * multiple;
            multiple *= 26;
        }
        return number;
    }
}
