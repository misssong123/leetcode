package com.meng.interview150.leetcode.editor.cn;

class Interview123IsPalindrome {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了42.12% 的Java用户
     * 	内存消耗:42.8 MB,击败了93.42% 的Java用户
     * @param x
     * @return
     */
    public boolean isPalindromeMy(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10){
            return true;
        }
        String xStr = String.valueOf(x);
        int left = 0,right = xStr.length()-1;
        while (left < right){
            if (xStr.charAt(left) != xStr.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.9 MB,击败了79.18% 的Java用户
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
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
