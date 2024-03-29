package com.meng.origin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1207. 独一无二的出现次数
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 *
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 *
 * 示例 2：
 *
 * 输入：arr = [1,2]
 * 输出：false
 *
 * 示例 3：
 *
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniqueOccurrences {
    /**
     * 利用map和set进行解答
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i : arr){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        /**
         *      * 执行用时：2 ms, 在所有 Java 提交中击败了91.43% 的用户
         *      * 内存消耗：36.4 MB, 在所有 Java 提交中击败了91.90% 的用户
         */
        /*Set<Integer> set = new HashSet<>();
        for(int key : map.keySet()){
            if (!set.add(map.get(key)))
                return false;
        }
        return true;
        */
        /**
         * 执行用时：2 ms, 在所有 Java 提交中击败了91.43% 的用户
         * 内存消耗：36.4 MB, 在所有 Java 提交中击败了90.69% 的用户
         */
        return map.size() == new HashSet<Integer>(map.values()).size();
    }
    /**
     * 官方解法
     * 方法一：哈希表
     *
     * 首先使用哈希表记录每个数字的出现次数；随后再利用新的哈希表，统计不同的出现次数的数目。如果不同的出现次数的数目等于不同数字的数目，则返回 true\textit{true}true，否则返回 false\textit{false}false。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences/solution/du-yi-wu-er-de-chu-xian-ci-shu-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.43% 的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了94.52% 的用户
     */
    public boolean uniqueOccurrences1(int[] arr) {
        Map<Integer, Integer> occur = new HashMap<Integer, Integer>();
        for (int x : arr) {
            occur.put(x, occur.getOrDefault(x, 0) + 1);
        }
        Set<Integer> times = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> x : occur.entrySet()) {
            times.add(x.getValue());
        }
        return times.size() == occur.size();
    }
}
