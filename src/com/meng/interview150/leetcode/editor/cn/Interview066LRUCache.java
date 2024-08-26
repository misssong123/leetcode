package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 解答成功:
 * 	执行耗时:49 ms,击败了40.38% 的Java用户
 * 	内存消耗:110.1 MB,击败了46.88% 的Java用户
 */
class Interview066LRUCache {
    int capacity;
    Cache head;
    Cache tail;
    Map<Integer,Cache> map;
    public Interview066LRUCache(int capacity) {
        this.head = new Cache();
        this.tail = new Cache();
        this.map = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Cache cache = map.get(key);
        if (Objects.isNull(cache)){
            return -1;
        }
        addHead(cache,true);
        return cache.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            Cache cache = map.get(key);
            cache.val = value;
            addHead(cache,true);
        }else {
            //判断是否满了
            if (map.containsKey(key)){
                Cache cache = map.get(key);
                cache.val = value;
                addHead(cache,true);
            }else {
                Cache cache = new Cache(key,value);
                //判断是否满了
                if(map.size() == capacity){
                    deleteTail();
                }
                addHead(cache,false);
                map.put(key,cache);
            }
        }
    }
    public void addHead(Cache cache,boolean exist){
        if (cache.pre == head){
            return;
        }
        if (exist){
            //移除元素当前位置
            cache.pre.next = cache.next;
            cache.next.pre = cache.pre;
        }
        //添加到头部
        cache.next = head.next;
        head.next.pre = cache;
        head.next = cache;
        cache.pre = head;
    }
    public void deleteTail(){
        map.remove(tail.pre.key);
        tail.pre = tail.pre.pre;
        tail.pre.next = tail;
    }

}
class Cache{
    public int key;
    public int val;
    public Cache next;
    public Cache pre;
    public Cache(int key ,int val){
        this.val = val;
        this.key = key;
    }
    public Cache(){
    }
}

/**
 * 解答成功:
 * 	执行耗时:44 ms,击败了85.11% 的Java用户
 * 	内存消耗:108.9 MB,击败了58.51% 的Java用户
 */
class LRUCache extends LinkedHashMap<Integer,Integer> {
    public int capacity;
    public LRUCache(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
