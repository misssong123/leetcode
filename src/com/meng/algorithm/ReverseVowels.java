package com.meng.algorithm;

/**
 * 345. 反转字符串中的元音字母
 *
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 *
 *
 * 示例 1：
 *
 * 输入："hello"
 * 输出："holle"
 *
 * 示例 2：
 *
 * 输入："leetcode"
 * 输出："leotcede"
 *
 *
 *
 * 提示：
 *
 *     元音字母不包含字母 "y" 。
 */
public class ReverseVowels {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了83.83% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了86.22% 的用户
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        int left = 0 , right = s.length() -1;
        char[] chars = s.toCharArray();
        while (left < right){
            while (left < right && !isCheck(chars[left])){
                left++;
            }
            while (right > left && !isCheck(chars[right])){
                right--;
            }
            if (left < right){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    private boolean isCheck(char c) {
        c = Character.toLowerCase(c);
        if (c == 'a' || c == 'o' || c == 'i' || c == 'u' || c == 'e'){
            return true;
        }
        return false;
    }

    /**
     * 方法一：双指针
     *
     * 思路与算法
     *
     * 我们可以使用两个指针 iii 和 jjj 对字符串相向地进行遍历。
     *
     * 具体地，指针 iii 初始时指向字符串 sss 的首位，指针 jjj 初始时指向字符串 sss 的末位。在遍历的过程中，我们不停地将 iii 向右移动，直到 iii 指向一个元音字母（或者超出字符串的边界范围）；同时，我们不停地将 jjj 向左移动，直到 jjj 指向一个元音字母。此时，如果 i<ji<ji<j，那么我们交换 iii 和 jjj 指向的元音字母，否则说明所有的元音字母均已遍历过，就可以退出遍历的过程。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string/solution/fan-zhuan-zi-fu-chuan-zhong-de-yuan-yin-2bmos/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param s
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了83.83% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了53.16% 的用户
     */
    public String reverseVowels1(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < n && !isVowel(arr[i])) {
                ++i;
            }
            while (j > 0 && !isVowel(arr[j])) {
                --j;
            }
            if (i < j) {
                swap(arr, i, j);
                ++i;
                --j;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
