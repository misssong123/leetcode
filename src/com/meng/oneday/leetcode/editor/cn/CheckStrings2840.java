package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class CheckStrings2840 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了33.33% 的Java用户
     * 	内存消耗:47.3 MB,击败了23.81% 的Java用户
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkStrings2840(String s1, String s2) {
        int[] evenCnt = new int[26];
        int[] oddCnt = new int[26];
        for(int i = 0 ;i < s1.length();i++){
            int index = s1.charAt(i) - 'a';
            if(i % 2 == 0){
                evenCnt[index]++;
            }else {
                oddCnt[index]++;
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            int index = s2.charAt(i) - 'a';
            if (i % 2 == 0) {
                evenCnt[index]--;
                if (evenCnt[index] < 0) {
                    return false;
                }
            }else {
                oddCnt[index]--;
                if (oddCnt[index] < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了61.90% 的Java用户
     * 	内存消耗:47.1 MB,击败了71.43% 的Java用户
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkStrings(String s1, String s2) {
        int[][] cnt1 = new int[2][26];
        int[][] cnt2 = new int[2][26];
        for (int i = 0; i < s1.length(); i++) {
            cnt1[i % 2][s1.charAt(i) - 'a']++;
            cnt2[i % 2][s2.charAt(i) - 'a']++;
        }
        return Arrays.deepEquals(cnt1, cnt2);
    }

}
