package com.meng.origin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 387. 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 *
 *
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class FirstUniqChar {
    /**
     * 执行用时：34 ms, 在所有 Java 提交中击败了37.07% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了41.84% 的用户
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        for(int i = 0 ; i < len ; i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(int i = 0 ; i < len ; i++){
            if (map.get(s.charAt(i))==1)
                return i;
        }
        return -1;
    }
    /**方法一：使用哈希表存储频数

     思路与算法

     我们可以对字符串进行两次遍历。

     在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。在第二次遍历时，我们只要遍历到了一个只出现一次的字符，那么就返回它的索引，否则在遍历结束后返回 −1-1−1。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string/solution/zi-fu-chuan-zhong-de-di-yi-ge-wei-yi-zi-x9rok/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：34 ms, 在所有 Java 提交中击败了37.07% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了61.72% 的用户
     */
    public int firstUniqChar1(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
    /**方法二：使用哈希表存储索引

     思路与算法

     我们可以对方法一进行修改，使得第二次遍历的对象从字符串变为哈希映射。

     具体地，对于哈希映射中的每一个键值对，键表示一个字符，值表示它的首次出现的索引（如果该字符只出现一次）或者 −1-1−1（如果该字符出现多次）。当我们第一次遍历字符串时，设当前遍历到的字符为 ccc，如果 ccc 不在哈希映射中，我们就将 ccc 与它的索引作为一个键值对加入哈希映射中，否则我们将 ccc 在哈希映射中对应的值修改为 −1-1−1。

     在第一次遍历结束后，我们只需要再遍历一次哈希映射中的所有值，找出其中不为 −1-1−1 的最小值，即为第一个不重复字符的索引。如果哈希映射中的所有值均为 −1-1−1，我们就返回 −1-1−1。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string/solution/zi-fu-chuan-zhong-de-di-yi-ge-wei-yi-zi-x9rok/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：26 ms, 在所有 Java 提交中击败了59.45% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了83.91% 的用户
     */
    public int firstUniqChar2(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (position.containsKey(ch)) {
                position.put(ch, -1);
            } else {
                position.put(ch, i);
            }
        }
        int first = n;
        for (Map.Entry<Character, Integer> entry : position.entrySet()) {
            int pos = entry.getValue();
            if (pos != -1 && pos < first) {
                first = pos;
            }
        }
        if (first == n) {
            first = -1;
        }
        return first;
    }
    /**
     * 方法三：队列
     *
     * 思路与算法
     *
     * 我们也可以借助队列找到第一个不重复的字符。队列具有「先进先出」的性质，因此很适合用来找出第一个满足某个条件的元素。
     *
     * 具体地，我们使用与方法二相同的哈希映射，并且使用一个额外的队列，按照顺序存储每一个字符以及它们第一次出现的位置。当我们对字符串进行遍历时，设当前遍历到的字符为 ccc，如果 ccc 不在哈希映射中，我们就将 ccc 与它的索引作为一个二元组放入队尾，否则我们就需要检查队列中的元素是否都满足「只出现一次」的要求，即我们不断地根据哈希映射中存储的值（是否为 −1-1−1）选择弹出队首的元素，直到队首元素「真的」只出现了一次或者队列为空。
     *
     * 在遍历完成后，如果队列为空，说明没有不重复的字符，返回 −1-1−1，否则队首的元素即为第一个不重复的字符以及其索引的二元组。
     *
     * 小贴士
     *
     * 在维护队列时，我们使用了「延迟删除」这一技巧。也就是说，即使队列中有一些字符出现了超过一次，但它只要不位于队首，那么就不会对答案造成影响，我们也就可以不用去删除它。只有当它前面的所有字符被移出队列，它成为队首时，我们才需要将它移除。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string/solution/zi-fu-chuan-zhong-de-di-yi-ge-wei-yi-zi-x9rok/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法3
     * 执行用时：33 ms, 在所有 Java 提交中击败了43.03% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了50.16% 的用户
     */
    public int firstUniqChar3(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        Queue<Pair> queue = new LinkedList<Pair>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (!position.containsKey(ch)) {
                position.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                position.put(ch, -1);
                while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().pos;
    }

    class Pair {
        char ch;
        int pos;

        Pair(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }
}
