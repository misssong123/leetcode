package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MaxFreq1297 {
    /**
     * 解答成功:
     * 	执行耗时:35 ms,击败了53.05% 的Java用户
     * 	内存消耗:46.8 MB,击败了98.12% 的Java用户
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq1297(String s, int maxLetters, int minSize, int maxSize) {
        Map<String,Integer> map = new HashMap<>();
        int[] arr = new int[26];
        int cnt = 0;
        int res = 0;
        for (int i = 0 ; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            arr[index]++;
            if (arr[index] == 1){
                cnt++;
            }
            if (i < minSize - 1){
                continue;
            }
            if(cnt <= maxLetters){
                String str = s.substring(i - minSize + 1,i + 1);
                map.put(str,map.getOrDefault(str,0) + 1);
                res = Math.max(res,map.get(str));
            }
            //移除窗口最左边的字符
            int leftIndex = s.charAt(i - minSize + 1) - 'a';
            if (--arr[leftIndex] == 0){
                cnt--;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了84.51% 的Java用户
     * 	内存消耗:47.1 MB,击败了70.89% 的Java用户
     * @param S
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq(String S, int maxLetters, int minSize, int maxSize) {
        char[] s = S.toCharArray();
        Map<String, Integer> strCnt = new HashMap<>();
        int[] charCnt = new int[26];
        int kinds = 0;
        int ans = 0;

        for (int i = 0; i < s.length; i++) {
            // 1. 进入窗口
            int in = s[i] - 'a';
            if (charCnt[in] == 0) {
                kinds++;
            }
            charCnt[in]++;

            int left = i - minSize + 1;
            if (left < 0) { // 窗口大小不足 minSize
                continue;
            }

            // 2. 更新统计量
            if (kinds <= maxLetters) {
                String subStr = S.substring(left, left + minSize);
                int cnt = strCnt.merge(subStr, 1, Integer::sum); // cnt = ++strCnt[subStr];
                ans = Math.max(ans, cnt);
            }

            // 3. 离开窗口，为下一个循环做准备
            int out = s[left] - 'a';
            charCnt[out]--;
            if (charCnt[out] == 0) {
                kinds--;
            }
        }

        return ans;
    }

}
