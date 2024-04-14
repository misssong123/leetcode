package com.meng.thinking.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:14 ms,击败了64.89% 的Java用户
 * 	内存消耗:53.4 MB,击败了11.09% 的Java用户
 */
class MyHashSet705 {
    Set<Integer> cache;
    public MyHashSet705() {
        cache = new HashSet<>();
    }
    
    public void add(int key) {
        cache.add(key);
    }
    
    public void remove(int key) {
        cache.remove(key);
    }
    
    public boolean contains(int key) {
        return cache.contains(key);
    }
}

/**
 * 解答成功:
 * 	执行耗时:16 ms,击败了51.40% 的Java用户
 * 	内存消耗:48.9 MB,击败了49.94% 的Java用户
 */
class MyHashSet {
    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

