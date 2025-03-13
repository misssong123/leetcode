package com.meng.oneday.leetcode.editor.cn;

class CountOfSubstrings3306 {
    /**
     * 解答成功:
     * 	执行耗时:52 ms,击败了84.18% 的Java用户
     * 	内存消耗:45.2 MB,击败了78.28% 的Java用户
     * @param word
     * @param k
     * @return
     */
    public long countOfSubstrings(String word, int k) {
        final int VOWEL_MASK = 1065233;
        char[] s = word.toCharArray();
        long ans = 0;
        int[] cntVowel1 = new int['u' - 'a' + 1], cntVowel2 = new int['u' - 'a' + 1];
        long sizeVowel1 = 0, sizeVowel2 = 0; // 元音种类数
        long cntConsonant1 = 0, cntConsonant2 = 0;
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
