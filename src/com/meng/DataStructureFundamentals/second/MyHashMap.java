package com.meng.DataStructureFundamentals.second;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 706. 设计哈希映射
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * 实现 MyHashMap 类：
 *
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 *
 *
 * 提示：
 *
 * 0 <= key, value <= 106
 * 最多调用 104 次 put、get 和 remove 方法
 */

/**
 * 执行用时：
 * 16 ms
 * , 在所有 Java 提交中击败了
 * 86.31%
 * 的用户
 * 内存消耗：
 * 47.1 MB
 * , 在所有 Java 提交中击败了
 * 44.36%
 * 的用户
 * 通过测试用例：
 * 36 / 36
 */
public class MyHashMap {
    private List<int[]>[] cache;
    private int size = 1024;
    public MyHashMap() {
        cache =  new List[1024];
        for(int i = 0 ; i < size ; i++){
            cache[i] =  new ArrayList<>();
        }
    }

    public void put(int key, int value) {
        int index = key % size;
        List<int[]> list = cache[index];
        for(int[] temp : list){
            if (temp[0] == key){
                temp[1] = value;
                return;
            }
        }
        list.add(new int[]{key,value});
    }

    public int get(int key) {
        int index = key % size;
        List<int[]> list = cache[index];
        for(int[] temp : list){
            if (temp[0] == key){
                return temp[1];
            }
        }
        return -1;
    }

    public void remove(int key) {
        int res = -1;
        int index = key % size;
        List<int[]> list = cache[index];
        for(int i = 0 ; i < list.size() ; i++){
            int[] temp = list.get(i);
            if (temp[0] == key){
                res = i;
                break;
            }
        }
        if (res != -1){
            cache[index].remove(res);
        }
    }
}

/**
 * 方法一：链地址法
 * 我们假定读者已经完成了「705. 设计哈希集合」这一题目。
 *
 * 「设计哈希映射」与「设计哈希集合」解法接近，唯一的区别在于我们存储的不是 \textit{key}key 本身，而是 (\textit{key}, \textit{value})(key,value) 对。除此之外，代码基本是类似的。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/design-hashmap/solution/she-ji-ha-xi-ying-she-by-leetcode-soluti-klu9/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：
 * 19 ms
 * , 在所有 Java 提交中击败了
 * 47.46%
 * 的用户
 * 内存消耗：
 * 48.4 MB
 * , 在所有 Java 提交中击败了
 * 17.79%
 * 的用户
 * 通过测试用例：
 * 36 / 36
 */
class MyHashMap1 {
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap1() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Pair>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                return pair.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.key == key) {
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }
}
