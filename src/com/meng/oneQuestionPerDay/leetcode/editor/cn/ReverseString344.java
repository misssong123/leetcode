package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class ReverseString344 {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 49.2 MB
     * , 在所有 Java 提交中击败了
     * 59.12%
     * 的用户
     * 通过测试用例：
     * 477 / 477
     * @param s
     */
    public void reverseString(char[] s) {
        int start = 0 ,  end = s.length-1;
        while (start < end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 49.6 MB
     * , 在所有 Java 提交中击败了
     * 19.76%
     * 的用户
     * 通过测试用例：
     * 477 / 477
     * @param s
     */
    public void reverseString1(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

}

