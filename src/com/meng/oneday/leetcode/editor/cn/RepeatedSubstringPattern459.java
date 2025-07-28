package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class RepeatedSubstringPattern459 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.5 MB,击败了9.13% 的Java用户
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern459(String s) {
        if (s.length() <= 1){
            return false;
        }
        int len = s.length();
        if (s.substring(0,len/2).equals(s.substring(len/2))){
            return true;
        }
        for(int i = 3 ; i <= len ; i+=2){
            if (len % i == 0){
                int num = len / i;
                boolean flag = true;
                for(int j = 1 ; j < i ; j++){
                    if (!s.substring(0,num).equals(s.substring(num*j,num*(j+1)))){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了52.35% 的Java用户
     * 	内存消耗:43.7 MB,击败了96.57% 的Java用户
     * @param s
     * @return
     */
    public boolean repeatedSubstringPatternOfficial(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:82 ms,击败了36.99% 的Java用户
     * 	内存消耗:43.8 MB,击败了87.89% 的Java用户
     * @param s
     * @return
     */
    public boolean repeatedSubstringPatternOfficial2(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    public boolean repeatedSubstringPattern(String s) {
        return kmp(s);
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了77.10% 的Java用户
     * 	内存消耗:44.2 MB,击败了69.15% 的Java用户
     * @param pattern
     * @return
     */
    public boolean kmp(String pattern) {
        int n = pattern.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        return fail[n - 1] != -1 && n % (n - fail[n - 1] - 1) == 0;
    }

}
