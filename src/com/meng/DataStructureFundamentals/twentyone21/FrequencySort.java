package com.meng.DataStructureFundamentals.twentyone21;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 *
 * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "tree"
 * 输出: "eert"
 * 解释: 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入: s = "cccaaa"
 * 输出: "cccaaa"
 * 解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入: s = "Aabb"
 * 输出: "bbAa"
 * 解释: 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 *
 * 提示:
 *
 * 1 <= s.length <= 5 * 105
 * s 由大小写英文字母和数字组成
 */
public class FrequencySort {
    /**
     * 执行用时：
     * 14 ms
     * , 在所有 Java 提交中击败了
     * 62.41%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 47.72%
     * 的用户
     * 通过测试用例：
     * 33 / 33
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        int len = s.length();
        if (len == 1){
            return s;
        }
        Map<Character,Integer> cache = new HashMap<>();
        char[] chars = s.toCharArray();
        for(char c : chars){
            cache.put(c,cache.getOrDefault(c,0)+1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for(Map.Entry<Character,Integer> entry : cache.entrySet()){
            queue.add(new int[]{entry.getKey(),entry.getValue()});
        }
        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            for(int i = 0 ; i < poll[1] ; i++){
                sb.append((char) poll[0]);
            }
        }
        return sb.toString();
    }
}
