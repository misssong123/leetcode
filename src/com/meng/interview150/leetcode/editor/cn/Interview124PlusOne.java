package com.meng.interview150.leetcode.editor.cn;

class Interview124PlusOne {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了96.82% 的Java用户
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int index = digits.length - 1;
        int diff = 1;
        while (index >= 0) {
            digits[index] += diff;
            if (digits[index] == 10){
                diff = 1;
                digits[index] = 0;
            }else {
                return digits;
            }
            index--;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了34.31% 的Java用户
     * @param digits
     * @return
     */
    public int[] plusOneOfficial(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
