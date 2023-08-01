package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class ReverseVowels345 {
    /**
     * 双指针
     * @param s
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 43.43%
     * 的用户
     * 内存消耗：
     * 42.5 MB
     * , 在所有 Java 提交中击败了
     * 76.67%
     * 的用户
     * 通过测试用例：
     * 480 / 480
     */
    public String reverseVowels(String s) {
        Set<Character> cache = new HashSet<>(Arrays.asList('a','A','e','E','i','I','o','O','u','U'));
        int left = 0 , right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right){
            while (left < right && !cache.contains(s.charAt(left))){
                left++;
            }
            while (right > left && !cache.contains(s.charAt(right))){
                right--;
            }

            if (left >= right){
                break;
            }
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        ReverseVowels345 demo = new ReverseVowels345();
        System.out.println(demo.reverseVowels("leetcode"));
    }

    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 81.85%
     * 的用户
     * 内存消耗：
     * 42.7 MB
     * , 在所有 Java 提交中击败了
     * 65.95%
     * 的用户
     * 通过测试用例：
     * 480 / 480
     * @param s
     * @return
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

