package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class CountPalindromicSubsequence1930 {
    /**
     * 解答成功:
     * 	执行耗时:1332 ms,击败了14.15% 的Java用户
     * 	内存消耗:52.7 MB,击败了5.54% 的Java用户
     * @param s
     * @return
     */
    public int countPalindromicSubsequence1930(String s) {
        Set<Character> pre = new HashSet<>();
        int[] suf = new int[26];
        char[] chars = s.toCharArray();
        Set<String> set = new HashSet<>(s.length()/2);
        //初始化
        for(int i = 2 ; i < s.length() ; i++){
            suf[chars[i] - 'a']++;
        }
        pre.add(chars[0]);
        //遍历
        for (int i = 1 ; i < s.length() -1 ; i++){
            for (Character c : pre){
                if(suf[c - 'a'] > 0){//存在后缀
                    set.add(""+c+chars[i]);
                }
            }
            pre.add(chars[i]);
            suf[chars[i+1] - 'a']--;
        }
        return set.size();
    }

    /**
     * 枚举中间
     *解答成功:
     * 	执行耗时:33 ms,击败了86.46% 的Java用户
     * 	内存消耗:46.1 MB,击败了39.38% 的Java用户
     * @param S
     * @return
     */
    public int countPalindromicSubsequence(String S) {
        char[] s = S.toCharArray();
        int n = s.length;

        // 统计 [1,n-1] 每个字母的个数
        int[] sufCnt = new int[26];
        for (int i = 1; i < n; i++) {
            sufCnt[s[i] - 'a']++;
        }

        boolean[] preHas = new boolean[26];
        boolean[][] has = new boolean[26][26];
        int ans = 0;
        for (int i = 1; i < n - 1; i++) { // 枚举中间字母 mid
            int mid = s[i] - 'a';
            sufCnt[mid]--; // 撤销 mid 的计数，sufCnt 剩下的就是后缀 [i+1,n-1] 每个字母的个数
            preHas[s[i - 1] - 'a'] = true; // 记录前缀 [0,i-1] 有哪些字母
            for (int alpha = 0; alpha < 26; alpha++) { // 枚举两侧字母 alpha
                // 判断 mid 的左右两侧是否都有字母 alpha
                if (preHas[alpha] && sufCnt[alpha] > 0 && !has[mid][alpha]) {
                    has[mid][alpha] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功: --枚举两侧
     * 	执行耗时:46 ms,击败了62.15% 的Java用户
     * 	内存消耗:46.4 MB,击败了29.23% 的Java用户
     * @param s
     * @return
     */
    public int countPalindromicSubsequenceSide(String s) {
        int ans = 0;
        for (char alpha = 'a'; alpha <= 'z'; alpha++) { // 枚举两侧字母 alpha
            int i = s.indexOf(alpha); // 最左边的 alpha 的下标
            if (i < 0) { // s 中没有 alpha
                continue;
            }
            int j = s.lastIndexOf(alpha); // 最右边的 alpha 的下标

            boolean[] has = new boolean[26];
            for (int k = i + 1; k < j; k++) { // 枚举中间字母 mid
                int mid = s.charAt(k) - 'a';
                if (!has[mid]) {
                    has[mid] = true; // 避免重复统计
                    ans++;
                }
            }
        }
        return ans;
    }
}
