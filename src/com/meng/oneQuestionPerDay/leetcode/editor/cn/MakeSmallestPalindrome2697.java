package com.meng.oneQuestionPerDay.leetcode.editor.cn;
class MakeSmallestPalindrome2697 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了64.76% 的Java用户
     * 	内存消耗:44.4 MB,击败了5.38% 的Java用户
     * @param s
     * @return
     */
    public String makeSmallestPalindromeMy(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < len /2 ; i++){
            if (chars[i] !=chars[len - 1 - i]){
                char c = chars[i] - chars[len -1 - i]>0?chars[len-1-i]:chars[i];
                chars[i] = c;
                chars[len - 1 - i] = c;
            }
        }
        return new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了99.92% 的Java用户
     * 	内存消耗:44 MB,击败了5.38% 的Java用户
     * @param s
     * @return
     */
    public String makeSmallestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] != arr[right]) {
                arr[left] = arr[right] = (char) Math.min(arr[left], arr[right]);
            }
            ++left;
            --right;
        }
        return new String(arr);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
