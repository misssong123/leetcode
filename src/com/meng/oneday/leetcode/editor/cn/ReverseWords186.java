package com.meng.oneday.leetcode.editor.cn;

class ReverseWords186 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.09% 的Java用户
     * 	内存消耗:47.7 MB,击败了70.06% 的Java用户
     * @param s
     */
    public void reverseWords186(char[] s) {
        //反转整个单词
        reverse(s,0,s.length-1);
        //反转每个单词
        int pre = 0;
        for(int i = 0 ; i < s.length; i++){
            if (s[i] == ' '){
                reverse(s,pre,i-1);
                pre = i+1;
            }
        }
        reverse(s,pre,s.length-1);
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
