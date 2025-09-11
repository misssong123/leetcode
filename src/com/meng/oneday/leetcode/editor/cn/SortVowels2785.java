package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import java.util.ArrayList;

class SortVowels2785 {
    /**
     * 解答成功:
     * 	执行耗时:60 ms,击败了13.16% 的Java用户
     * 	内存消耗:48.7 MB,击败了13.16% 的Java用户
     * @param s
     * @return
     */
    public String sortVowels2785(String s) {
        List<Character> list = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = Character.toLowerCase(chars[i]);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                list.add(chars[i]);
                indexList.add(i);
            }
        }
        if (list.size() > 1) {
            list.sort(Comparator.comparingInt(a -> (int) a));
            int index = 0;
            for(int i : indexList){
                chars[i] = list.get(index++);
            }
        }
        return new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了68.42% 的Java用户
     * 	内存消耗:45.1 MB,击败了60.53% 的Java用户
     * @param S
     * @return
     */
    public String sortVowelsOther(String S) {
        StringBuilder vowels = new StringBuilder();
        char[] s = S.toCharArray();
        for (char ch : s) {
            char c = Character.toLowerCase(ch);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels.append(ch);
            }
        }

        char[] sortedVowels = vowels.toString().toCharArray();
        Arrays.sort(sortedVowels);

        int j = 0;
        for (int i = 0; i < s.length; i++) {
            char c = Character.toLowerCase(s[i]);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                s[i] = sortedVowels[j++];
            }
        }
        return new String(s);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了92.11% 的Java用户
     * 	内存消耗:45 MB,击败了68.42% 的Java用户
     * @param S
     * @return
     */
    public String sortVowelsOther2(String S) {
        final int VOWEL_MASK = 0x208222;

        char[] s = S.toCharArray();
        byte[] vowels = new byte[s.length]; // 比 StringBuilder 快
        int k = 0;
        for (char ch : s) {
            if ((VOWEL_MASK >> (ch & 31) & 1) > 0) {
                vowels[k++] = (byte) ch;
            }
        }

        Arrays.sort(vowels, 0, k);

        k = 0;
        for (int i = 0; i < s.length; i++) {
            if ((VOWEL_MASK >> (s[i] & 31) & 1) > 0) {
                s[i] = (char) vowels[k++];
            }
        }
        return new String(s);
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.9 MB,击败了71.05% 的Java用户
     * @param S
     * @return
     */
    public String sortVowels(String S) {
        final int VOWEL_MASK = 0x208222;

        char[] s = S.toCharArray();
        int[] cnt = new int['u' + 1];
        for (char ch : s) {
            if ((VOWEL_MASK >> (ch & 31) & 1) > 0) {
                cnt[ch]++;
            }
        }

        int j = 'A';
        for (int i = 0; i < s.length; i++) {
            if ((VOWEL_MASK >> (s[i] & 31) & 1) == 0) {
                continue;
            }
            // 找下一个出现次数大于 0 的元音字母
            while (cnt[j] == 0) {
                j = j == 'Z' ? 'a' : j + 1;
            }
            s[i] = (char) j;
            cnt[j]--;
        }
        return new String(s);
    }

}
