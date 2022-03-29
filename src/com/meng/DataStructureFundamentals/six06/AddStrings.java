package com.meng.DataStructureFundamentals.six06;

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
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 14.11%
     * 的用户
     * 通过测试用例：
     * 317 / 317
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int left = num1.length() - 1 , right = num2.length() - 1;
        StringBuffer sb = new StringBuffer();
        int num = 0;
        while (left >= 0|| right >= 0){
            int l = (left >= 0 ? num1.charAt(left) : '0')-'0';
            int r = (right >= 0 ? num2.charAt(right) : '0')-'0';
            int sum = l + r +num;
            num = sum /10;
            sb.append(sum%10);
            left--;
            right--;
        }
        if (num > 0){
            sb.append(num);
        }
        return sb.reverse().toString();
    }
}
