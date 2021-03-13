package com.meng;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 *
 * 实现 LRUCache 类：
 *
 *     LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 *     int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *     void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 *
 *
 * 提示：
 *
 *     1 <= capacity <= 3000
 *     0 <= key <= 3000
 *     0 <= value <= 104
 *     最多调用 3 * 104 次 get 和 put
 */

/**
 * 执行用时：19 ms, 在所有 Java 提交中击败了71.63% 的用户
 * 内存消耗：46.5 MB, 在所有 Java 提交中击败了59.73% 的用户
 */
public class LRUCache extends LinkedHashMap<Integer,Integer> {
    int size ;
    public LRUCache(int capacity) {
        super(capacity,0.75f,true);
        this.size = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > size;
    }
}

/**
 * 执行用时：18 ms, 在所有 Java 提交中击败了91.34% 的用户
 * 内存消耗：46.4 MB, 在所有 Java 提交中击败了67.92% 的用户
 */
class LruCache{
    int capacity ;
    int size ;
    Map<Integer,Node> cache = new HashMap<>();
    Node head,tail;
    class Node{
        int key,val;
        Node pre;
        Node next;
        Node(){

        }
        Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }
    public LruCache(int capacity) {
        this.capacity = capacity;
        head =  new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null)
            return -1;
        //将node移动到首位
        removeNode(node);
        moveHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        //判断节点是否存在,且是否超过指定容量
        if (node == null){
            Node cur  = new Node(key,value);
            cache.put(key,cur);
            moveHead(cur);
            size++;
            //已经存储满，删除最后一个节点
            if (size>capacity){
                Node remove = removeTail();
                cache.remove(remove.key);
                size--;
            }
        }else {
            //存在的话，更新该值，且将该数值移动到首位
            node.val = value;
            removeNode(node);
            moveHead(node);
        }
    }
    //将指定节点移动到首位
    public void moveHead(Node node){
        node.pre = head;
        node.next = head.next;
        head.next.pre =node;
        head.next = node;
    }
    //移出指定节点
    public void removeNode(Node node){
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }
    //删除尾部节点
    public Node removeTail(){
        Node node = tail.pre;
        removeNode(node);
        return node;
    }
}