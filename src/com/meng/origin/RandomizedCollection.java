package com.meng.origin;

import java.util.*;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 * 注意: 允许出现重复元素。
 *
 *     insert(val)：向集合中插入元素 val。
 *     remove(val)：当 val 存在时，从集合中移除一个 val。
 *     getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 * 示例:
 *
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 *
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 *
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 *
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 *
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 *
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 *
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RandomizedCollection {
    Map<Integer, Set<Integer>> idx;
    List<Integer> nums;

    /**
     * 方法一：哈希表
     *
     * 思路与算法
     *
     * 为了使得 O(1)O(1)O(1) 时间内能够随机获取一个元素，我们将每个数值（可以重复）存储在一个列表 nums\textit{nums}nums 中。这样，获取随机元素时，只需要随机生成一个列表中的索引，就能够得到一个随机元素。
     *
     * 这样做的问题在于：列表中的随机删除并不是 O(1)O(1)O(1) 的。然而我们可以发现，列表中元素的顺序是无关紧要的，只要它们正确地存在于列表中即可。因此，在删除元素时，我们可以将被删的元素与列表中最后一个元素交换位置，随后便可以在 O(1)O(1)O(1) 时间内，从列表中去除该元素。
     *
     * 这需要我们额外维护数值在列表中每一次出现的下标集合。对于数值 val\textit{val}val 而言，记其下标集合为 SidxS_{idx}Sidx​。
     *
     * 在删除时，我们找出 valvalval 出现的其中一个下标 iii，并将 nums[i]\textit{nums}[i]nums[i] 与 nums[nums.length−1]\textit{nums}[\textit{nums}.\textit{length}-1]nums[nums.length−1] 交换。随后，将 iii 从 SvalS_{val}Sval​ 中去除，并将 Snums[nums.length−1]S_{\textit{nums}[\textit{nums}.\textit{length}-1]}Snums[nums.length−1]​ 中原有的 nums[nums.length−1]\textit{nums}[\textit{nums}.\textit{length}-1]nums[nums.length−1] 替换成 iii。由于集合的每个操作都是 O(1)O(1)O(1) 的，因此总的平均时间复杂度也是 O(1)O(1)O(1) 的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed/solution/o1-shi-jian-cha-ru-shan-chu-he-huo-qu-sui-ji-yua-5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public RandomizedCollection() {
        idx = new HashMap<Integer, Set<Integer>>();
        nums = new ArrayList<Integer>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
        set.add(nums.size() - 1);
        idx.put(val, set);
        return set.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!idx.containsKey(val)) {
            return false;
        }
        Iterator<Integer> it = idx.get(val).iterator();
        int i = it.next();
        int lastNum = nums.get(nums.size() - 1);
        nums.set(i, lastNum);
        idx.get(val).remove(i);
        idx.get(lastNum).remove(nums.size() - 1);
        if (i < nums.size() - 1) {
            idx.get(lastNum).add(i);
        }
        if (idx.get(val).size() == 0) {
            idx.remove(val);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get((int) (Math.random() * nums.size()));
    }
}
