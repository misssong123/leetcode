package com.meng.oneday.leetcode.editor.cn;

class BreakPalindrome1328 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了67.86% 的Java用户
     * @param palindrome
     * @return
     */
    public String breakPalindrome1328(String palindrome) {
        int len = palindrome.length();
        if (len == 1){
            return "";
        }
        char[] charArray = palindrome.toCharArray();
        //查找第一个不为a的位置
        int index = 0;
        while (index < len && palindrome.charAt(index) == 'a'){
            index++;
        }
        //如果全部为a，则将最后一个字符改为b
        if (index == len){
            charArray[len - 1] = 'b';
            return new String(charArray);
        }else if(palindrome.substring(0,index).equals(palindrome.substring(index+1,len))){
            charArray[len - 1] = 'b';
            return new String(charArray);
        }else {
            charArray[index] = 'a';
            return new String(charArray);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了35.71% 的Java用户
     * @param palindrome
     * @return
     */
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }
        // 把第一个不等于 a 的字母改成 a
        // 只需找前一半，如果前一半没有不等于 a 的字母，那么后一半肯定也没有
        char[] s = palindrome.toCharArray();
        for (int i = 0; i < n / 2; i++) {
            if (s[i] != 'a') {
                s[i] = 'a';
                return new String(s);
            }
        }
        // 除了回文中心，全是 a
        s[n - 1] = 'b';  // 最后一个字母改成 b
        return new String(s);
    }
}
