package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:1338 ms,击败了5.01% 的Java用户
 * 	内存消耗:117.4 MB,击败了24.50% 的Java用户
 */
class LRUCacheDemo {
    Map<Integer,Node> cache;
    Node start;
    Node end;
    int capacity;
    public LRUCacheDemo(int capacity) {
        cache = new HashMap<>(capacity);
        start = new Node();
        end = new Node();
        start.next = end;
        end.pre = start;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (cache.get(key) == null){
            return -1;
        }
        Node selected = cache.get(key);
        //调整当前元素为第一位
        removeNode(key);
        addFirst(selected);
        return selected.val;
    }
    
    public void put(int key, int value) {
        if (cache.get(key) != null){//更新参数
            Node selected = cache.get(key);
            //调整当前元素为第一位
            removeNode(key);
            addFirst(selected);
            selected.val = value;
        }else {
            Node node = new Node();
            node.val = value;
            node.key = key;
            addFirst(node);
            cache.put(key,node);
            if (cache.size() > capacity){
                Node deleted = deleteTail();
                cache.remove(deleted.key);
            }
        }
    }
    //放置到首位
    public void addFirst(Node node){
        node.next = start.next;
        start.next.pre = node;
        start.next = node;
        node.pre = start;
    }
    //移除最后一位
    public Node deleteTail(){
        Node temp = end.pre;
        end.pre = temp.pre;
        temp.pre.next = end;
        return temp;
    }
    //移除指定元素
    public void removeNode(int key){
        Node temp = start.next;
        while (temp != null && temp.key != key){
            temp = temp.next;
        }
        temp.pre.next = temp.next;
        temp.next.pre = temp.pre;
    }
    class Node{
        public Node pre;
        public Node next;
        public int val;
        public int key;
    }
}

/**
 * 解答成功:
 * 	执行耗时:41 ms,击败了95.30% 的Java用户
 * 	内存消耗:107.5 MB,击败了63.86% 的Java用户
 */
class LRUCacheMap extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > this.capacity;
    }
}

class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
