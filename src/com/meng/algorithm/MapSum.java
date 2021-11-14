package com.meng.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 677. 键值映射
 *
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 *
 *     MapSum() 初始化 MapSum 对象
 *     void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 *     int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 *
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 *
 *
 * 提示：
 *
 *     1 <= key.length, prefix.length <= 50
 *     key 和 prefix 仅由小写英文字母组成
 *     1 <= val <= 1000
 *     最多调用 50 次 insert 和 sum
 */
public class MapSum {
    /**
     * 执行用时：13 ms, 在所有 Java 提交中击败了35.15% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了96.56% 的用户
     * 通过测试用例：35 / 35
     */
    Map<String ,Integer> map ;
    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }


    public int sum(String prefix) {
        int sum = 0;
        for (String key : map.keySet()){
            if (key.startsWith(prefix)){
                sum+=map.get(key);
            }
        }
        return sum;
    }

}

/**
 * 方法二：前缀哈希映射
 *
 * 思路与算法
 *
 * 我们可以用哈希表存储所有可能前缀的值。当我们得到一个新的 key-val\texttt{key-val}key-val 键值，我们将 key\textit{key}key 的每个前缀 prefix[i]\textit{prefix}[i]prefix[i] 都在哈希表中进行存储，我们需要更新每个前缀 prefix[i]\textit{prefix}[i]prefix[i] 对应的值。我们计算出它对应的值的增加为 delta\textit{delta}delta，计算方式如下：
 *
 *     如果键 key\textit{key}key 不存在，则此时 delta\textit{delta}delta 等于 val\textit{val}val。
 *     如果键 key\textit{key}key 存在，则此时键 key\textit{key}key 对应得前缀的值都增加 val−map[key]\textit{val} - \textit{map}[\textit{key}]val−map[key]，其中 map[key]\textit{map}[\textit{key}]map[key] 表示键 key\textit{key}key 当前对应的值。
 *     我们在完成前缀的值改写后，同时要更新键 key\textit{key}key 对应的值为 val\textit{val}val。
 *
 * 求 sum\texttt{sum}sum 时,我们直接利用哈希表查找给定的前缀对应的值即可。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs/solution/jian-zhi-ying-she-by-leetcode-solution-j4xy/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：11 ms, 在所有 Java 提交中击败了99.71% 的用户
 * 内存消耗：38.3 MB, 在所有 Java 提交中击败了77.76% 的用户
 * 通过测试用例：35 / 35
 */
class MapSum1 {
    Map<String, Integer> map;
    Map<String, Integer> prefixmap;

    public MapSum1() {
        map = new HashMap<>();
        prefixmap = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        for (int i = 1; i <= key.length(); ++i) {
            String currprefix = key.substring(0, i);
            prefixmap.put(currprefix, prefixmap.getOrDefault(currprefix, 0) + delta);
        }
    }

    public int sum(String prefix) {
        return prefixmap.getOrDefault(prefix, 0);
    }
}

/**
 * 方法三：字典树
 *
 * 思路与算法
 *
 * 由于我们要处理前缀，自然而然联想到可以用 Trie\textit{Trie}Trie（前缀树）来处理此问题。处理方法跟方法二的原理一样，我们直接在前缀对应的 Trie\textit{Trie}Trie 的每个节点存储该前缀对应的值。
 *
 *     insert\texttt{insert}insert 操作：原理与方法二一样，我们首先求出前缀对应的值的改变 delta\textit{delta}delta，我们直接在 Trie\textit{Trie}Trie 节点上更新键 key\textit{key}key 的每个前缀对应的值。
 *     sum\texttt{sum}sum 操作: 我们直接在前缀树上搜索该给定的前缀对应的值即可，如果给定的前缀不在前缀树中，则返回 000。
 *     当然在实际中我们也可以在 Trie\textit{Trie}Trie 的节点只存储键 key\textit{key}key 对应的 val\textit{val}val, 每次求 sum\texttt{sum}sum 时利用 DFS\textit{DFS}DFS 或者 BFS\textit{BFS}BFS 遍历前缀树的子树即可。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs/solution/jian-zhi-ying-she-by-leetcode-solution-j4xy/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：13 ms, 在所有 Java 提交中击败了35.15% 的用户
 * 内存消耗：38.1 MB, 在所有 Java 提交中击败了95.98% 的用户
 * 通过测试用例：35 / 35
 */
class MapSum2 {
    class TrieNode {
        int val = 0;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root;
    Map<String, Integer> map;

    public MapSum2() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode node = root;
        for (char c : key.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
            node.val += delta;
        }
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                return 0;
            }
            node = node.next[c - 'a'];
        }
        return node.val;
    }
}