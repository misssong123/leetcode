package com.meng.oneday.leetcode.editor.cn;

class AnswerString3403 {
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了77.50% 的Java用户
     * 	内存消耗:44.4 MB,击败了75.00% 的Java用户
     * @param word
     * @param numFriends
     * @return
     */
    public String answerString3403(String word, int numFriends) {
        if (numFriends == 1){
            return word;
        }
        String res = "";
        int len = word.length() - numFriends+1,n = word.length();
        for(int i = 0 ; i < n ; i++){
            String sub = word.substring(i,Math.min(i+len,n));
            if (sub.compareTo(res) > 0){
                res = sub;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了77.50% 的Java用户
     * 	内存消耗:44.5 MB,击败了45.00% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String answerStringOther1(String s, int k) {
        if (k == 1) {
            return s;
        }
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            String sub = s.substring(i, Math.min(i + n - k + 1, n));
            if (sub.compareTo(ans) > 0) {
                ans = sub;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.5 MB,击败了100.00% 的Java用户
     * @param s
     * @param numFriends
     * @return
     */
    public String answerString(String s, int numFriends) {
        if (numFriends == 1) {
            return s;
        }
        int n = s.length();
        int i = 0;
        int j = 1;
        while (j < n) {
            int k = 0;
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(j + 1, t + k + 1);
            } else {
                j += k + 1;
            }
        }
        return s.substring(i, Math.min(i + n - numFriends + 1, n));
    }
}
