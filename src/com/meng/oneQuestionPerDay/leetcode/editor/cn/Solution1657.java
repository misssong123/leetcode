package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1657 {
    /**
     * 解答成功:
     * 	执行耗时:72 ms,击败了22.76% 的Java用户
     * 	内存消耗:43.5 MB,击败了36.18% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings1(String word1, String word2) {
        if (word1.length() != word2.length()){
            return false;
        }
        Map<Character,Integer> cache1 = new HashMap<>();
        Map<Character,Integer> cache2 = new HashMap<>();
        for (int i = 0 ; i < word1.length() ;i++){
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            cache1.put(c1,cache1.getOrDefault(c1,0)+1);
            cache2.put(c2,cache2.getOrDefault(c2,0)+1);
        }
        if (cache1.keySet().size()!=cache2.keySet().size()||!cache1.keySet().containsAll(cache2.keySet())){
            return false;
        }
        List<Integer> list1 = new ArrayList<Integer>(cache1.values());
        List<Integer> list2 = new ArrayList<Integer>(cache2.values());
        Collections.sort(list1);
        Collections.sort(list2);
        for(int i = 0 ; i < list1.size();i++){
            if (!list1.get(i).equals(list2.get(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了51.27% 的Java用户
     * 	内存消耗:43 MB,击败了97.80% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        int[] count1 = new int[26], count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count1[i] > 0 && count2[i] == 0 || count1[i] == 0 && count2[i] > 0) {
                return false;
            }
        }
        Arrays.sort(count1);
        Arrays.sort(count2);
        return Arrays.equals(count1, count2);
    }


}
//leetcode submit region end(Prohibit modification and deletion)
