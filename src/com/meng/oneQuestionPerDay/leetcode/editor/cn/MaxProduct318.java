package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class MaxProduct318 {
    /**
     * 解答成功:
     * 	执行耗时:26 ms,击败了31.60% 的Java用户
     * 	内存消耗:44.2 MB,击败了18.40% 的Java用户
     * @param words
     * @return
     */
    public int maxProductMy(String[] words) {
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        int res = 0;
        int len = words.length;
        int[] cache = new int[len];
        int[] nums = new int[26];
        int index = 0;
        for (String word : words){
            Arrays.fill(nums,0);
            char[] chars = word.toCharArray();
            for(char c : chars){
                nums[c-'a'] = 1;
            }
            int num = 0;
            for (int i = 0 ; i < 26 ;i++){
                if (nums[i]==1){
                    num += Math.pow(2, i);
                }
            }
            cache[index++] = num;
        }
        for (int i = 0 ; i < len -1 ; i++){
            for(int j = i+1 ; j < len ; j++){
                int l = words[i].length() * words[j].length();
                if (l <= res){
                    break;
                }
                if (((cache[i] | cache[j]) == (cache[i] ^ cache[j]))){
                    res = l;
                }
            }
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了51.89% 的Java用户
     * 	内存消耗:42.9 MB,击败了86.79% 的Java用户
     */
    public int maxProduct1(String[] words) {
        int length = words.length;
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int maxProd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }

    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了40.25% 的Java用户
     * 	内存消耗:42.6 MB,击败了96.38% 的Java用户
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = words.length;
        for (int i = 0; i < length; i++) {
            int mask = 0;
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                mask |= 1 << (word.charAt(j) - 'a');
            }
            if (wordLength > map.getOrDefault(mask, 0)) {
                map.put(mask, wordLength);
            }
        }
        int maxProd = 0;
        Set<Integer> maskSet = map.keySet();
        for (int mask1 : maskSet) {
            int wordLength1 = map.get(mask1);
            for (int mask2 : maskSet) {
                if ((mask1 & mask2) == 0) {
                    int wordLength2 = map.get(mask2);
                    maxProd = Math.max(maxProd, wordLength1 * wordLength2);
                }
            }
        }
        return maxProd;
    }

}
