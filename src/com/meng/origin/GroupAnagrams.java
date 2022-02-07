package com.meng.origin;

import java.util.*;

/**
 * 49. 字母异位词分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 *
 *     所有输入均为小写字母。
 *     不考虑答案输出的顺序。
 */
public class GroupAnagrams {
    /**
     * 执行用时：40 ms, 在所有 Java 提交中击败了9.96% 的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了9.44% 的用户
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        int[] nums = new int[26];
        StringBuffer sb = new StringBuffer();
        for (String str : strs){
            for(int i = 0 ; i < str.length() ; i++){
                nums[str.charAt(i)-'a']++;
            }
            for (int num : nums){
                sb.append(num+"@");
            }
            List<String> temp = map.getOrDefault(sb.toString(), new ArrayList<>());
            temp.add(str);
            map.put(sb.toString(),temp);
            Arrays.fill(nums,0);
            sb.delete(0,sb.length());
        }
        List<List<String>> result = new ArrayList<>();
        for(List<String> list : map.values())
            result.add(list);
        return result;
    }

    /**
     * 官方解法一
     * 方法一：排序
     *
     * 由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：8 ms, 在所有 Java 提交中击败了74.94% 的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了56.07% 的用户
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
     * 官方解法2
     * 方法二：计数
     *
     * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
     *
     * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 262626 的数组记录每个字母出现的次数。需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同，因此不同语言的实现方式也不同。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param strs
     * @return
     * 执行用时：12 ms, 在所有 Java 提交中击败了31.57% 的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了35.72% 的用户
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

    public static void main(String[] args) {
        String [] strs ={"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        System.out.println(groupAnagrams.groupAnagrams(strs));
    }
}
