package com.meng.hot100;

import java.util.*;

/**
 *49. 字母异位词分组(中等)
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class T023GroupAnagrams {
    int[] nums = new int[26];

    /**
     * 执行用时：
     * 31 ms
     * , 在所有 Java 提交中击败了
     * 7.84%
     * 的用户
     * 内存消耗：
     * 45.2 MB
     * , 在所有 Java 提交中击败了
     * 10.20%
     * 的用户
     * 通过测试用例：
     * 117 / 117
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String,List<String>> cache = new HashMap<>();
        for(String str : strs){
            String key ;
            if (str.equals("")){
                key = "";
            }else {
                key = resetStr(str);
            }
            if (cache.get(key) == null){
                cache.put(key,new ArrayList<>());
            }
            cache.get(key).add(str);
        }
        for(List<String> list : cache.values()){
            res.add(list);
        }
        return res;
    }

    private String resetStr(String str) {
        Arrays.fill(nums,0);
        for(char c : str.toCharArray()){
            nums[c-'a']++;
        }
        StringBuffer sb = new StringBuffer();
        for(int num : nums){
            sb.append(num+":");
        }
        return sb.toString();
    }

    /**
     * 方法一：排序
     * 由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键。
     * @param strs
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 99.01%
     * 的用户
     * 内存消耗：
     * 44.7 MB
     * , 在所有 Java 提交中击败了
     * 32.83%
     * 的用户
     * 通过测试用例：
     * 117 / 117
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 方法二：计数
     * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
     *
     * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 2626 的数组记录每个字母出现的次数。需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同，因此不同语言的实现方式也不同。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param strs
     * @return
     *执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 19.71%
     * 的用户
     * 内存消耗：
     * 45.1 MB
     * , 在所有 Java 提交中击败了
     * 12.97%
     * 的用户
     * 通过测试用例：
     * 117 / 117
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }


}
