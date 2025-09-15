package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class Spellchecker966 {
    /**
     * 解答成功:
     * 	执行耗时:69 ms,击败了5.15% 的Java用户
     * 	内存消耗:51.5 MB,击败了7.35% 的Java用户
     * @param wordlist
     * @param queries
     * @return
     */
    public String[] spellchecker1935(String[] wordlist, String[] queries) {
        Set<String> set = new HashSet<>();
        Map<String,String> spelling = new HashMap<>();
        Map<String,String> vowel = new HashMap<>();
        for(String word : wordlist){
            set.add(word);
            String lower = word.toLowerCase();
            spelling.putIfAbsent(lower,word);
            vowel.putIfAbsent(lower.replaceAll("[aeiou]","*"),word);
        }
        String[] res = new String[queries.length];
        for(int i = 0 ; i < queries.length ; i++){
            String query = queries[i];
            if (set.contains(query)){
                res[i] = query;
            }else if (spelling.containsKey(query.toLowerCase())){
                res[i] = spelling.get(query.toLowerCase());
            }else {
                res[i] = vowel.getOrDefault(query.toLowerCase().replaceAll("[aeiou]", "*"), "");
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了21.32% 的Java用户
     * 	内存消耗:46.9 MB,击败了33.82% 的Java用户
     * @param wordlist
     * @param queries
     * @return
     */
    public String[] spellcheckerOther(String[] wordlist, String[] queries) {
        int n = wordlist.length;
        Set<String> origin = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> lowerToOrigin = new HashMap<>(n); // 预分配空间
        Map<String, String> vowelToOrigin = new HashMap<>(n);

        for (int i = n - 1; i >= 0; i--) {
            String s = wordlist[i];
            String lower = s.toLowerCase();
            lowerToOrigin.put(lower, s); // 例如 kite -> KiTe
            vowelToOrigin.put(replaceVowels(lower), s); // 例如 k?t? -> KiTe
        }

        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (origin.contains(q)) { // 完全匹配
                continue;
            }
            String lower = q.toLowerCase();
            if (lowerToOrigin.containsKey(lower)) { // 不区分大小写的匹配
                queries[i] = lowerToOrigin.get(lower);
            } else { // 不区分大小写+元音模糊匹配
                queries[i] = vowelToOrigin.getOrDefault(replaceVowels(lower), "");
            }
        }
        return queries;
    }

    private String replaceVowels(String str) {
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; ++i) {
            char c = s[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                s[i] = '?';
            }
        }
        return new String(s);
    }



    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了81.62% 的Java用户
     * 	内存消耗:46.6 MB,击败了50.00% 的Java用户
     * @param wordlist
     * @param queries
     * @return
     */
    Set<String> words_perfect;
    Map<String, String> words_cap;
    Map<String, String> words_vow;
    public String[] spellchecker(String[] wordlist, String[] queries) {
        words_perfect = new HashSet();
        words_cap = new HashMap();
        words_vow = new HashMap();

        for (String word: wordlist) {
            words_perfect.add(word);

            String wordlow = word.toLowerCase();
            words_cap.putIfAbsent(wordlow, word);

            String wordlowDV = devowel(wordlow);
            words_vow.putIfAbsent(wordlowDV, word);
        }

        String[] ans = new String[queries.length];
        int t = 0;
        for (String query: queries)
            ans[t++] = solve(query);
        return ans;
    }

    public String solve(String query) {
        if (words_perfect.contains(query))
            return query;

        String queryL = query.toLowerCase();
        if (words_cap.containsKey(queryL))
            return words_cap.get(queryL);

        String queryLV = devowel(queryL);
        if (words_vow.containsKey(queryLV))
            return words_vow.get(queryLV);

        return "";
    }

    public String devowel(String word) {
        StringBuilder ans = new StringBuilder();
        for (char c: word.toCharArray())
            ans.append(isVowel(c) ? '*' : c);
        return ans.toString();
    }

    public boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

}
