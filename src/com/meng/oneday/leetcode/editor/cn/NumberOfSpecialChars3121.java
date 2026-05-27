package com.meng.oneday.leetcode.editor.cn;

class NumberOfSpecialChars3121 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了50.00% 的Java用户
     * 	内存消耗:47.1 MB,击败了77.78% 的Java用户
     * @param word
     * @return
     */
    public int numberOfSpecialChars3121(String word) {
        int[] cnts = new int[26];
        for (char c : word.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int index =  c - 'A';
                if (cnts[index] == 1 || cnts[index] == 2) {
                    cnts[index] = 2;
                }else{
                    cnts[index] = -1;
                }
            }else {
                int index =  c - 'a';
                if (cnts[index] == 0 || cnts[index] == 1) {
                    cnts[index] = 1;
                }else{
                    cnts[index] = -1;
                }
            }
        }
        int cnt = 0;
        for (int c : cnts) {
            if (c == 2) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了88.89% 的Java用户
     * 	内存消耗:47.1 MB,击败了77.78% 的Java用户
     * @param word
     * @return
     */
    public int numberOfSpecialCharsOther(String word) {
        int ans = 0;
        int[] state = new int[27];
        for (char c : word.toCharArray()) {
            int x = c & 31; // 转成数字 1~26
            if ((c & 32) > 0) { // 小写字母
                if (state[x] == 0) {
                    state[x] = 1;
                } else if (state[x] == 2) { // 大写的后面不能有小写
                    state[x] = -1;
                    ans--;
                }
            } else { // 大写字母
                if (state[x] == 0) { // 还没遇到小写，就先遇到大写了
                    state[x] = -1;
                } else if (state[x] == 1) {
                    state[x] = 2;
                    ans++;
                }
            }
        }
        return ans;
    }

}
