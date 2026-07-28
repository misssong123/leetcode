package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class SmallestPalindrome3517 {
    /**
     * 解答成功:
     * 	执行耗时:26 ms,击败了56.25% 的Java用户
     * 	内存消耗:47.1 MB,击败了93.75% 的Java用户
     * @param s
     * @return
     */
    public String smallestPalindrome3517(String s) {
        int len = s.length();
        int[] cnts = new int[26];
        char[] chars = new char[len];
        //计数
        for(char c : s.toCharArray()){
            cnts[c-'a']++;
        }
        //构建字符串
        int index = 0;
        for (int i = 0 ; i < 26 ; i++) {
            if (cnts[i] == 0){
                continue;
            }
            char c = (char)('a' + i);
            while (cnts[i] > 1){
                chars[index] = c;
                chars[len - index - 1] = c;
                index++;
                cnts[i] -= 2;
            }
            if (cnts[i] == 1){
                chars[len / 2] = c;
            }
        }
        return new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:42 ms,击败了6.25% 的Java用户
     * 	内存消耗:47.3 MB,击败了56.25% 的Java用户
     * @param s
     * @return
     */
    public String smallestPalindromeOther1(String s) {
        int n = s.length();
        int m = n / 2;
        char[] t = s.substring(0, m).toCharArray();
        Arrays.sort(t);

        StringBuilder ans = new StringBuilder(n); // 预分配空间
        ans.append(t);
        if (n % 2 > 0) {
            ans.append(s.charAt(m));
        }
        for (int i = m - 1; i >= 0; i--) {
            ans.append(t[i]);
        }
        return ans.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了87.50% 的Java用户
     * 	内存消耗:47.2 MB,击败了81.25% 的Java用户
     * @param s
     * @return
     */
   /* public String smallestPalindrome(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n / 2; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        StringBuilder ans = new StringBuilder(n); // 预分配空间
        for (int i = 0; i < 26; i++) {
            ans.repeat('a' + i, cnt[i]);
        }

        StringBuilder t = new StringBuilder(ans);
        if (n % 2 > 0) {
            ans.append(s.charAt(n / 2));
        }
        ans.append(t.reverse());
        return ans.toString();
    }*/
}
