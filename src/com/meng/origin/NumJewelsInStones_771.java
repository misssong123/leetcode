package com.meng.origin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 771. 宝石与石头
 *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 示例 1:
 *
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 */
public class NumJewelsInStones_771 {
    /**
     * 使用字符串的contains方法
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.54% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了99.35% 的用户
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        for(int i = 0 ; i< S.length() ; i++){
            if (J.contains(S.substring(i,i+1)))
                count++;
        }
        return count;
    }

    /**
     * 使用List中的contains
     * 执行用时：2 ms, 在所有 Java 提交中击败了49.93% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了95.14% 的用户
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones1(String J, String S) {
        int count = 0;
        List<String> list = new ArrayList<>();
        for(int j = 0 ; j<J.length();j++)
            list.add(J.substring(j,j+1));
        for(int i = 0 ; i< S.length() ; i++){
            if (list.contains(S.substring(i,i+1)))
                count++;
        }
        return count;
    }

    /**
     * 使用set中的contains
     * 执行用时：2 ms, 在所有 Java 提交中击败了49.93% 的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了92.33% 的用户
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones2(String J, String S) {
        int count = 0;
        Set<Character> set = new HashSet<>();
        for(int j = 0 ; j<J.length();j++)
            set.add(J.charAt(j));
        for(int i = 0 ; i< S.length() ; i++){
            if (set.contains(S.charAt(i)))
                count++;
        }
        return count;
    }
    /**
     * 官方解法1
     * 方法一：暴力
     *
     * 思路与算法
     *
     * 暴力法的思路很直观，遍历字符串 SSS，对于 SSS 中的每个字符，遍历一次字符串 JJJ，如果其和 JJJ 中的某一个字符相同，则是宝石。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/jewels-and-stones/solution/bao-shi-yu-shi-tou-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int numJewelsInStones3(String J, String S) {
        int jewelsCount = 0;
        int jewelsLength = J.length(), stonesLength = S.length();
        for (int i = 0; i < stonesLength; i++) {
            char stone = S.charAt(i);
            for (int j = 0; j < jewelsLength; j++) {
                char jewel = J.charAt(j);
                if (stone == jewel) {
                    jewelsCount++;
                    break;
                }
            }
        }
        return jewelsCount;
    }
    /**
     * 官方解法2
     * 方法二：哈希集合
     *
     * 思路与算法
     *
     * 方法一中，对于字符串 SSS 中的每个字符，都需要遍历一次字符串 JJJ，导致时间复杂度较高。如果使用哈希集合存储字符串 JJJ 中的宝石，则可以降低判断的时间复杂度。
     *
     * 遍历字符串 JJJ，使用哈希集合存储其中的字符，然后遍历字符串 SSS，对于其中的每个字符，如果其在哈希集合中，则是宝石。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/jewels-and-stones/solution/bao-shi-yu-shi-tou-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.54% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了98.53% 的用户
     */
    public int numJewelsInStones4(String J, String S) {
        int jewelsCount = 0;
        Set<Character> jewelsSet = new HashSet<Character>();
        int jewelsLength = J.length(), stonesLength = S.length();
        for (int i = 0; i < jewelsLength; i++) {
            char jewel = J.charAt(i);
            jewelsSet.add(jewel);
        }
        for (int i = 0; i < stonesLength; i++) {
            char stone = S.charAt(i);
            if (jewelsSet.contains(stone)) {
                jewelsCount++;
            }
        }
        return jewelsCount;
    }
}
