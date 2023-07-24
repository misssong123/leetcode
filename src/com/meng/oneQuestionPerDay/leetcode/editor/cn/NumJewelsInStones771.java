package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class NumJewelsInStones771 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 67.58%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 46.87%
     * 的用户
     * 通过测试用例：
     * 255 / 255
     * @param jewels
     * @param stones
     * @return
     */
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> cache = new HashSet<>();
        for(char c : jewels.toCharArray()){
            cache.add(c);
        }
        int res = 0 ;
        for(char c : stones.toCharArray()){
            if(cache.contains(c)){
                res++;
            }
        }
        return res;
    }

    /**
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 67.58%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 28.28%
     * 的用户
     * 通过测试用例：
     * 255 / 255
     * @param jewels
     * @param stones
     * @return
     */
    public int numJewelsInStones1(String jewels, String stones) {
        int jewelsCount = 0;
        Set<Character> jewelsSet = new HashSet<Character>();
        int jewelsLength = jewels.length(), stonesLength = stones.length();
        for (int i = 0; i < jewelsLength; i++) {
            char jewel = jewels.charAt(i);
            jewelsSet.add(jewel);
        }
        for (int i = 0; i < stonesLength; i++) {
            char stone = stones.charAt(i);
            if (jewelsSet.contains(stone)) {
                jewelsCount++;
            }
        }
        return jewelsCount;
    }


}
