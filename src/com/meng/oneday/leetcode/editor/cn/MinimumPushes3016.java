package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumPushes3016 {
    /**
     * > 2026/07/31 09:56:04
     * 解答成功:
     * 	执行耗时:10 ms,击败了94.44% 的Java用户
     * 	内存消耗:47.4 MB,击败了11.11% 的Java用户
     * @param word
     * @return
     */
    public int minimumPushes3016(String word) {
        int size = 26;
        int[] cnt = new int[size];
        int len = word.length();
        for (int i = 0; i < len; i++) {
            cnt[word.charAt(i) - 'a'] ++;
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = size - 1,index = 0 ; i >= 0 ; i--,index++){
            if (cnt[i] == 0) {
                break;
            }
            ans += cnt[i] * (index / 8 + 1);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了94.44% 的Java用户
     * 	内存消耗:47.2 MB,击败了66.67% 的Java用户
     * @param word
     * @return
     */
    public int minimumPushes(String word) {
        int[] cnt = new int[26];
        for (char b : word.toCharArray()) {
            cnt[b - 'a']++;
        }
        Arrays.sort(cnt);

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += cnt[25 - i] * (i / 8 + 1);
        }
        return ans;
    }

}
