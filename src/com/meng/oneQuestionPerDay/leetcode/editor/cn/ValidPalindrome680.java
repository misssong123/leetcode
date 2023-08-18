package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class ValidPalindrome680 {
    /**
     * 详情
     * 4ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 42.13MB
     * 击败 74.60%使用 Java 的用户
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        int left1 = left + 1, right1 = right;
        while (left1 < right1){
            if (s.charAt(left1) != s.charAt(right1)) {
                break;
            }
            left1++;
            right1--;
        }
        if (left1 >= right1) {
            return true;
        }
        int left2 = left, right2 = right - 1;
        while (left2 < right2){
            if (s.charAt(left2) != s.charAt(right2)) {
                break;
            }
            left2++;
            right2--;
        }
        return left2 >= right2;
    }

    public static void main(String[] args) {
        ValidPalindrome680 demo = new ValidPalindrome680();
        System.out.println(demo.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
}

