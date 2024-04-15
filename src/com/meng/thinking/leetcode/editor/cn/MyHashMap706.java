package com.meng.thinking.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:17 ms,击败了87.40% 的Java用户
 * 	内存消耗:47.1 MB,击败了43.29% 的Java用户
 */
class MyHashMap706 {
    Map<Integer,Integer> cache;
    public MyHashMap706() {
        cache = new HashMap<>();
    }
    
    public void put(int key, int value) {
        cache.put(key,value);
    }
    
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }
    
    public void remove(int key) {
        cache.remove(key);
    }
}
class MyHashMap {
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

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了87.40% 的Java用户
     * 	内存消耗:46.8 MB,击败了58.96% 的Java用户
     */
    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
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


