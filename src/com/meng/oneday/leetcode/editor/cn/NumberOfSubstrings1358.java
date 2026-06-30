package com.meng.oneday.leetcode.editor.cn;

class NumberOfSubstrings1358 {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了25.59% 的Java用户
     * 	内存消耗:45.3 MB,击败了99.57% 的Java用户
     * @param s
     * @return
     */
    public int numberOfSubstrings1358(String s) {
        int lastA = -1 ,lastB = -1 , lastC = -1;
        int res = 0;
        for (int i = 0 ; i < s.length() ; i++){
            if (s.charAt(i) == 'a'){
                lastA = i;
            }else if (s.charAt(i) == 'b'){
                lastB = i;
            }else{
                lastC = i;
            }
            int min = Math.min(lastA,Math.min(lastB,lastC));
            if (min != -1){
                res += min + 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了19.78% 的Java用户
     * 	内存消耗:45.6 MB,击败了34.19% 的Java用户
     * @param S
     * @return
     */
    public int numberOfSubstrings(String S) {
        char[] s = S.toCharArray();
        int[] cnt = new int[3];
        int ans = 0;
        int left = 0;
        for (char c : s) {
            cnt[c - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                cnt[s[left] - 'a']--;
                left++;
            }
            ans += left;
        }
        return ans;
    }

}
