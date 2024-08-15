package com.meng.interview150.leetcode.editor.cn;

class Interview024IsPalindrome {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了39.27% 的Java用户
     * 	内存消耗:42.6 MB,击败了37.76% 的Java用户
     * @param s
     * @return
     */
    public boolean isPalindromeMy(String s) {
        StringBuffer sb = new StringBuffer();
        for(char c : s.toCharArray()){
            if (c>='A' && c<='Z'){
                sb.append((char)(c+32));
            }else if (c>='a' && c<='z'){
                sb.append(c);
            }else if (c>='0' && c<='9'){
                sb.append(c);
            }
        }
        int left = 0,right = sb.length()-1;
        while (left<right){
            if (sb.charAt(left)!=sb.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了96.54% 的Java用户
     * 	内存消耗:41.7 MB,击败了99.68% 的Java用户
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }


}
