package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class NumSmallerByFrequency1170 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了88.94% 的Java用户
     * 	内存消耗:45.9 MB,击败了61.98% 的Java用户
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency1170(String[] queries, String[] words) {
        //计算频次
        int[] cnts = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            cnts[i] = getNum(words[i]);
        }
        Arrays.sort(cnts);
        //计算结果
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int cnt = getNum(queries[i]);
            res[i] = cal(cnt,cnts);
        }
        return res;
    }

    private int cal(int cnt, int[] cnts) {
        int l = 0 , r = cnts.length-1,temp = cnts.length;
        while (l<=r){
            int mid = (l+r)>>1;
            if (cnts[mid] > cnt){
                temp = mid;
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return cnts.length - temp ;
    }

    private int getNum(String s){
        int character = 'z';
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == character){
                cnt++;
            }else if (c < character){
                character = c;
                cnt = 1;
            }
        }
        return cnt;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了98.62% 的Java用户
     * 	内存消耗:45.8 MB,击败了75.58% 的Java用户
     * @param queries
     * @param words
     * @return
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] count = new int[12];
        for (String s : words) {
            count[f(s)]++;
        }
        for (int i = 9; i >= 1; i--) {
            count[i] += count[i + 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String s = queries[i];
            res[i] = count[f(s) + 1];
        }
        return res;
    }

    public int f(String s) {
        int cnt = 0;
        char ch = 'z';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < ch) {
                ch = c;
                cnt = 1;
            } else if (c == ch) {
                cnt++;
            }
        }
        return cnt;
    }

}
