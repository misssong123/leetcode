package com.meng.level2.day02;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 */
public class Multiply43 {
    /**
     * 方法一：做加法
     * 如果 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   之一是 00，则直接将 00 作为结果返回即可。
     *
     * 如果 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   都不是 00，则可以通过模拟「竖式乘法」的方法计算乘积。从右往左遍历乘数，将乘数的每一位与被乘数相乘得到对应的结果，再将每次得到的结果累加。这道题中，被乘数是 \textit{num}_1num
     * 1
     * ​
     *  ，乘数是 \textit{num}_2num
     * 2
     * ​
     *  。
     *
     * 需要注意的是，\textit{num}_2num
     * 2
     * ​
     *   除了最低位以外，其余的每一位的运算结果都需要补 00。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num1
     * @param num2
     * @return
     * 执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 19.95%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 13.92%
     * 的用户
     * 通过测试用例：
     * 311 / 311
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    /**
     * 方法二：做乘法
     * 方法一的做法是从右往左遍历乘数，将乘数的每一位与被乘数相乘得到对应的结果，再将每次得到的结果累加，整个过程中涉及到较多字符串相加的操作。如果使用数组代替字符串存储结果，则可以减少对字符串的操作。
     *
     * 令 mm 和 nn 分别表示 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   的长度，并且它们均不为 00，则 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   的乘积的长度为 m+n-1m+n−1 或 m+nm+n。简单证明如下：
     *
     * 如果 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   都取最小值，则 \textit{num}_1=10^{m-1}num
     * 1
     * ​
     *  =10
     * m−1
     *  ，\textit{num2}=10^{n-1}num2=10
     * n−1
     *  ，\textit{num}_1 \times \textit{num}_2=10^{m+n-2}num
     * 1
     * ​
     *  ×num
     * 2
     * ​
     *  =10
     * m+n−2
     *  ，乘积的长度为 m+n-1m+n−1；
     *
     * 如果 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   都取最大值，则 \textit{num}_1=10^m-1num
     * 1
     * ​
     *  =10
     * m
     *  −1，\textit{num}_2=10^n-1num
     * 2
     * ​
     *  =10
     * n
     *  −1，\textit{num}_1 \times \textit{num}_2=10^{m+n}-10^m-10^n+1num
     * 1
     * ​
     *  ×num
     * 2
     * ​
     *  =10
     * m+n
     *  −10
     * m
     *  −10
     * n
     *  +1，乘积显然小于 10^{m+n}10
     * m+n
     *   且大于 10^{m+n-1}10
     * m+n−1
     *  ，因此乘积的长度为 m+nm+n。
     *
     * 由于 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   的乘积的最大长度为 m+nm+n，因此创建长度为 m+nm+n 的数组 \textit{ansArr}ansArr 用于存储乘积。对于任意 0 \le i < m0≤i<m 和 0 \le j < n0≤j<n，\textit{num}_1[i] \times \textit{num}_2[j]num
     * 1
     * ​
     *  [i]×num
     * 2
     * ​
     *  [j] 的结果位于 \textit{ansArr}[i+j+1]ansArr[i+j+1]，如果 \textit{ansArr}[i+j+1] \ge 10ansArr[i+j+1]≥10，则将进位部分加到 \textit{ansArr}[i+j]ansArr[i+j]。
     *
     * 最后，将数组 \textit{ansArr}ansArr 转成字符串，如果最高位是 00 则舍弃最高位。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num1
     * @param num2
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 91.93%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 38.33%
     * 的用户
     * 通过测试用例：
     * 311 / 311
     */
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

}
