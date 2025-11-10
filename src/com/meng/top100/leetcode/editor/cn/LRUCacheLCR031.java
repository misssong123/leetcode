package com.meng.top100.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解答成功:
 * 	执行耗时:43 ms,击败了99.41% 的Java用户
 * 	内存消耗:128.8 MB,击败了6.49% 的Java用户
 */
class LRUCacheLCR031 extends LinkedHashMap<Integer,Integer> {
    int capacity;
    public LRUCacheLCR031(int capacity) {
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }
    public int get(int key) {
        return getOrDefault(key,-1);
    }
    public void put(int key, int value) {
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

/**
 * 解答成功:
 * 	执行耗时:45 ms,击败了87.91% 的Java用户
 * 	内存消耗:129.5 MB,击败了5.01% 的Java用户
 */
class LRUCacheLCR031NoExtend  {
    int capacity;
    Map<Integer,LRUNode> cache;
    LRUNode head;
    LRUNode tail;
    public LRUCacheLCR031NoExtend(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new LRUNode();
        tail = new LRUNode();
        head.next = tail;
        tail.pre = head;
    }
    public int get(int key) {
        if (cache.containsKey(key)){
            LRUNode node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }
    public void put(int key, int value) {
        if (cache.containsKey(key)){
            LRUNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
        }else {
            LRUNode node = new LRUNode(key,value);
            cache.put(key,node);
            addHead(node);
            if (cache.size() > capacity){
                removeTail();
            }
        }
    }
    //移除尾部元素
    private void removeTail(){
        LRUNode remove = cache.remove(tail.pre.key);
        remove.pre.next = tail;
        tail.pre = remove.pre;

    }
    //添加到头部
    private void addHead(LRUNode node){
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }
    //移动已有节点到头部
    private void moveToHead(LRUNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
        addHead(node);
    }
}

class LRUNode{
    public int key;
    public int value;
    public LRUNode pre;
    public LRUNode next;
    public LRUNode(int key,int value){
        this.key = key;
        this.value = value;
    }
    public LRUNode(){

    }
}

/**
 * > 2025/11/10 10:57:37
 * 解答成功:
 * 	执行耗时:45 ms,击败了87.91% 的Java用户
 * 	内存消耗:129.5 MB,击败了5.01% 的Java用户
 */
class LRUCache {
    private static class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity;
    private final Node dummy = new Node(0, 0); // 哨兵节点
    private final Map<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        Node node = getNode(key); // getNode 会把对应节点移到链表头部
        return node != null ? node.value : -1;
    }

    public void put(int key, int value) {
        Node node = getNode(key); // getNode 会把对应节点移到链表头部
        if (node != null) { // 有这本书
            node.value = value; // 更新 value
            return;
        }
        node = new Node(key, value); // 新书
        keyToNode.put(key, node);
        pushFront(node); // 放到最上面
        if (keyToNode.size() > capacity) { // 书太多了
            Node backNode = dummy.prev;
            keyToNode.remove(backNode.key);
            remove(backNode); // 去掉最后一本书
        }
    }

    // 获取 key 对应的节点，同时把该节点移到链表头部
    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) { // 没有这本书
            return null;
        }
        Node node = keyToNode.get(key); // 有这本书
        remove(node); // 把这本书抽出来
        pushFront(node); // 放到最上面
        return node;
    }

    // 删除一个节点（抽出一本书）
    private void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    // 在链表头添加一个节点（把一本书放到最上面）
    private void pushFront(Node x) {
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }
}
