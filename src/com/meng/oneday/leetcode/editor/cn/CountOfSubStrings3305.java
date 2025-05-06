package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CountOfSubStrings3305 {
    /**
     * 思路有误
     * @param word
     * @param k
     * @return
     */
    public int countOfSubstringsMy(String word, int k) {
        int[] count = new int[26];
        int len = word.length();
        boolean[] vowel = new boolean[len];
        for(int i = 0 ; i < len ; i++){
            vowel[i] = check(word.charAt(i));
        }
        int t = 0,res = 0,left = 0;
        for(int i = 0 ; i < word.length() ; i++){
            if (vowel[i]){
                count[word.charAt(i) - 'a']++;
            }else {
                t++;
            }
            //符合条件
            if (t == k&&check(count)){
                res++;
            }else if (t >k&&!vowel[i]){
                boolean flag = check(count);
                //移除第一个辅音字母
                while (t > k && left <= i){
                    if (vowel[left]){
                        int n = --count[word.charAt(left) - 'a'];
                        flag = flag && ( n > 0);
                    }else {
                        t--;
                    }
                    if (flag){
                        res++;
                    }
                    left++;
                }
            }
        }
        //避免最后一个元音字母
        if (t >= k && check(count)){
            boolean flag = true;
            //移除第一个辅音字母
            while (t >= k &&flag&& left <= len-1){
                if (vowel[left]){
                    flag = --count[word.charAt(left) - 'a'] > 0;
                }else {
                    t--;
                }
                if (t == k && flag){
                    res++;
                }
                left++;
            }
        }
        return res;
    }
    private boolean check(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    private boolean check(int[] count){
        return count[0] > 0 && count[4] > 0 && count[8] > 0 && count[14] > 0 && count[20] > 0;
    }

    /**
     *解答成功:
     * 	执行耗时:4 ms,击败了86.59% 的Java用户
     * 	内存消耗:41.9 MB,击败了86.23% 的Java用户
     * @param word
     * @param k
     * @return
     */
    public int countOfSubstrings(String word, int k) {
        int ans = 0;
        Map<Character,Integer> vowelMapK = new HashMap<>(8);
        Map<Character,Integer> vowelMapK1 = new HashMap<>(8);
        int vowelCount = 0,vowelCount1 = 0,left = 0,left1 = 0,consonantCount = 0, consonantCount1 = 0;
        char[] chars = word.toCharArray();
        for(char c : chars){
            if (isVowel(c)){
                vowelMapK.put(c,vowelMapK.getOrDefault(c,0)+1);
                vowelMapK1.put(c,vowelMapK1.getOrDefault(c,0)+1);
                if (vowelMapK.get(c) == 1){
                    vowelCount++;
                }
                if (vowelMapK1.get(c) == 1){
                    vowelCount1++;
                }
            }else {
                consonantCount++;
                consonantCount1++;
            }
            //计算至少包含k个辅音的子串
            while(consonantCount >= k && vowelCount ==5){
                //向左侧移除字符
                if(isVowel(chars[left])){
                    vowelMapK.put(chars[left],vowelMapK.get(chars[left])-1);
                    if (vowelMapK.get(chars[left]) == 0){
                        vowelCount--;
                    }
                }else {
                    consonantCount--;
                }
                left++;
            }
            //计算至少包含k+1个辅音的子串
            while(consonantCount1 > k && vowelCount1 ==5){
                //向左侧移除字符
                if(isVowel(chars[left1])){
                    vowelMapK1.put(chars[left1],vowelMapK1.get(chars[left1])-1);
                    if (vowelMapK1.get(chars[left1]) == 0){
                        vowelCount1--;
                    }
                }else {
                    consonantCount1--;
                }
                left1++;
            }
            //计算包含k个辅音的子串
            ans += left - left1;
        }
        return ans;
    }
    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了91.55% 的Java用户
     * @param word
     * @param k
     * @return
     */
    public int countOfSubstringsOther(String word, int k) {
        final int VOWEL_MASK = 1065233;
        char[] s = word.toCharArray();
        int ans = 0;
        int[] cntVowel1 = new int['u' - 'a' + 1], cntVowel2 = new int['u' - 'a' + 1];
        int sizeVowel1 = 0, sizeVowel2 = 0; // 元音种类数
        int cntConsonant1 = 0, cntConsonant2 = 0;
        int left1 = 0, left2 = 0;
        for (char b : s) {
            b -= 'a';
            if ((VOWEL_MASK >> b & 1) > 0) {
                if (cntVowel1[b]++ == 0) {
                    sizeVowel1++;
                }
                if (cntVowel2[b]++ == 0) {
                    sizeVowel2++;
                }
            } else {
                cntConsonant1++;
                cntConsonant2++;
            }

            while (sizeVowel1 == 5 && cntConsonant1 >= k) {
                int out = s[left1] - 'a';
                if ((VOWEL_MASK >> out & 1) > 0) {
                    if (--cntVowel1[out] == 0) {
                        sizeVowel1--;
                    }
                } else {
                    cntConsonant1--;
                }
                left1++;
            }

            while (sizeVowel2 == 5 && cntConsonant2 > k) {
                int out = s[left2] - 'a';
                if ((VOWEL_MASK >> out & 1) > 0) {
                    if (--cntVowel2[out] == 0) {
                        sizeVowel2--;
                    }
                } else {
                    cntConsonant2--;
                }
                left2++;
            }

            ans += left1 - left2;
        }
        return ans;
    }
}
