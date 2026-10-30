package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MostCommonWord819 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了49.11% 的Java用户
     * 	内存消耗:43.7 MB,击败了38.39% 的Java用户
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord819(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }else {
                String str = sb.toString();
                if (!str.isBlank()){
                    map.put(str, map.getOrDefault(str, 0) + 1);
                }
                sb.setLength(0);
            }
        }
        if (!sb.isEmpty()) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }
        for (String s : banned) {
            map.remove(s);
        }
        return map.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了91.89% 的Java用户
     * 	内存消耗:43.2 MB,击败了84.68% 的Java用户
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWordOfficial(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<String>();
        for (String word : banned) {
            bannedSet.add(word);
        }
        int maxFrequency = 0;
        Map<String, Integer> frequencies = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        int length = paragraph.length();
        for (int i = 0; i <= length; i++) {
            if (i < length && Character.isLetter(paragraph.charAt(i))) {
                sb.append(Character.toLowerCase(paragraph.charAt(i)));
            } else if (sb.length() > 0) {
                String word = sb.toString();
                if (!bannedSet.contains(word)) {
                    int frequency = frequencies.getOrDefault(word, 0) + 1;
                    frequencies.put(word, frequency);
                    maxFrequency = Math.max(maxFrequency, frequency);
                }
                sb.setLength(0);
            }
        }
        String mostCommon = "";
        Set<Map.Entry<String, Integer>> entries = frequencies.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String word = entry.getKey();
            int frequency = entry.getValue();
            if (frequency == maxFrequency) {
                mostCommon = word;
                break;
            }
        }
        return mostCommon;
    }

}
