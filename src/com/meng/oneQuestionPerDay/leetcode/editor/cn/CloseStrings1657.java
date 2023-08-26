package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class CloseStrings1657 {
    /**
     * 时间
     * 详情
     * 65ms
     * 击败 31.28%使用 Java 的用户
     * 内存
     * 详情
     * 42.55MB
     * 击败 30.14%使用 Java 的用户
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character,Integer> cache1 = new HashMap<>();
        for (char c : word1.toCharArray()) {
            cache1.put(c,cache1.getOrDefault(c,0)+1);
        }
        Map<Character,Integer> cache2 = new HashMap<>();
        for (char c : word2.toCharArray()) {
            if (!cache1.containsKey(c)) {
                return false;
            }
            cache2.put(c,cache2.getOrDefault(c,0)+1);
        }
        Map<Integer, List<Character>> numCache1 = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : cache1.entrySet()) {
            if (!numCache1.containsKey(entry.getValue())) {
                numCache1.put(entry.getValue(),new ArrayList<>());
            }
            numCache1.get(entry.getValue()).add(entry.getKey());
        }
        Map<Integer, List<Character>> numCache2 = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : cache2.entrySet()) {
            if (!numCache2.containsKey(entry.getValue())) {
                numCache2.put(entry.getValue(),new ArrayList<>());
            }
            numCache2.get(entry.getValue()).add(entry.getKey());
        }
        if (numCache1.size() != numCache2.size()) {
            return false;
        }
        for (Map.Entry<Integer, List<Character>> entry : numCache1.entrySet()) {
            if (!numCache2.containsKey(entry.getKey())) {
                return false;
            }
            List<Character> list1 = entry.getValue();
            List<Character> list2 = numCache2.get(entry.getKey());
            if (list1.size() != list2.size()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CloseStrings1657 demo = new CloseStrings1657();
        System.out.println(demo.closeStrings("cabbba","abbccc"));
    }

    /**
     * 时间
     * 详情
     * 9ms
     * 击败 98.28%使用 Java 的用户
     * 内存
     * 详情
     * 42.23MB
     * 击败 84.31%使用 Java 的用户
     * @param s1
     * @param s2
     * @return
     */
    public boolean closeStrings1(String s1, String s2) {
        int[] c1 = new int[26], c2 = new int[26];
        for (char c : s1.toCharArray())
            c1[c - 'a']++;
        for (char c : s2.toCharArray())
            c2[c - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (c1[i] + c2[i] == 0)
                continue;
            if (c1[i] == 0 || c2[i] == 0)
                return false;
        }
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i])
                return false;
        }
        return true;
    }

}

