package com.meng.dataStructureFoundation.six;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 *
 *
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 */
public class AddStrings {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 20.03%
     * 的用户
     * 通过测试用例：
     * 317 / 317
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int len1 = num1.length();
        int len2 = num2.length();
        int len = len1 > len2 ? len1 : len2;
        int index = 0;
        int num = 0;
        while (index < len){
            int m = len1 > index ? (num1.charAt(len1-index-1)-'0'):0;
            int n = len2 > index ? (num2.charAt(len2-index-1)-'0'):0;
            System.out.println(m+":"+n);
            sb.append((m + n + num)%10);
            num = (m + n + num) / 10;
            index++;
        }
        if (num > 0){
            sb.append(num);
        }
        return sb.reverse().toString();
    }

    /**
     * 方法一：模拟
     * 思路与算法
     *
     * 本题我们只需要对两个大整数模拟「竖式加法」的过程。竖式加法就是我们平常学习生活中常用的对两个整数相加的方法，回想一下我们在纸上对两个整数相加的操作，是不是如下图将相同数位对齐，从低到高逐位相加，如果当前位和超过 1010，则向高位进一位？因此我们只要将这个过程用代码写出来即可。
     *
     *
     *
     * 具体实现也不复杂，我们定义两个指针 ii 和 jj 分别指向 \textit{num}_1num
     * 1
     * ​
     *   和 \textit{num}_2num
     * 2
     * ​
     *   的末尾，即最低位，同时定义一个变量 \textit{add}add 维护当前是否有进位，然后从末尾到开头逐位相加即可。你可能会想两个数字位数不同怎么处理，这里我们统一在指针当前下标处于负数的时候返回 00，等价于对位数较短的数字进行了补零操作，这样就可以除去两个数字位数不同情况的处理，具体可以看下面的代码。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-strings/solution/zi-fu-chuan-xiang-jia-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num1
     * @param num2
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 15.52%
     * 的用户
     * 通过测试用例：
     * 317 / 317
     */
    public String addStrings1(String num1, String num2) {
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
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }


    public static void main(String[] args) {
        AddStrings demo = new AddStrings();
        System.out.println(demo.addStrings("77","456"));
    }
}
