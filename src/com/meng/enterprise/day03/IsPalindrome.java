package com.meng.enterprise.day03;

/**
 * 9. 回文数(简单)
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 *
 * 示例 1：
 *
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 *
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 */
public class IsPalindrome {
    /**
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 40.37%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 40.55%
     * 的用户
     * 通过测试用例：
     * 11510 / 11510
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        if (x < 10){
            return true;
        }
        char[] chars = (x + "").toCharArray();
        int left = 0 ,right = chars.length-1;
        while (left < right){
            if (chars[left] != chars[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome demo = new IsPalindrome();
        System.out.println(demo.isPalindrome(121));
    }

    /**
     *执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.5 MB
     * , 在所有 Java 提交中击败了
     * 90.85%
     * 的用户
     * 通过测试用例：
     * 11510 / 11510
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

}
