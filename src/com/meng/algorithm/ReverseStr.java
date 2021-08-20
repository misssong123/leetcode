package com.meng.algorithm;

/**
 * 541. 反转字符串 II
 *
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 *
 *     如果剩余字符少于 k 个，则将剩余字符全部反转。
 *     如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 *
 * 示例 2：
 *
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 *
 *
 *
 * 提示：
 *
 *     1 <= s.length <= 104
 *     s 仅由小写英文组成
 *     1 <= k <= 104
 */
public class ReverseStr {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了93.16% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了23.32% 的用户
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        if (k == 1){
            return s;
        }
        int len = s.length(),index = 0;
        char[] chars = s.toCharArray();
        while(index + k <= len){
            int left = index,right = index+k-1;
            while (left < right){
               char c =  chars[left];
               chars[left] = chars[right];
               chars[right] = c;
               left++;
               right--;
            }
            index += 2 * k;
        }
        if (index < len){
            int left = index,right = len - 1;
            while (left < right){
                char c =  chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    /**
     * 我们直接按题意进行模拟：反转每个下标从 2k 的倍数开始的，长度为 k 的子串。若该子串长度不足 k，则反转整个子串。
     * @param s
     * @param k
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了93.16% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了44.71% 的用户
     */
    public String reverseStr1(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        ReverseStr demo = new ReverseStr();
        String s = "abcdefgh";
        int k = 3;
        System.out.println(demo.reverseStr(s,k));
    }
}
