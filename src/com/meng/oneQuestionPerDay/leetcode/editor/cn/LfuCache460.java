package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:131 ms,击败了5.03% 的Java用户
 * 	内存消耗:121.2 MB,击败了77.20% 的Java用户
 */
class LFUCacheOwner {
    int size;
    Map<Integer,NodeInner> cache ;
    Map<Integer,List<NodeInner>> nodes;
    public LFUCacheOwner(int size) {
        this.size = size;
        cache = new HashMap<>(size);
        nodes = new HashMap<>();
    }
    
    public int get(int key) {
        NodeInner node = cache.get(key);
        if (node == null){
            return -1;
        }
        //
        resetNode(node,false);
        return node.value;
    }
    public void put(int key, int value) {
        NodeInner node = cache.get(key);
        boolean addFlag = false;
        if (node == null){
            node = new NodeInner(key,value);
            addFlag = true;
        }
        //
        node.value = value;
        //放置存储
        cache.put(key,node);
        //
        resetNode(node,addFlag);
        if (cache.size() > size){
            deleteNode(node.num);
        }
    }
    public void deleteNode(int num){
        int key = Integer.MAX_VALUE;
        for (int k : nodes.keySet()){
            if (nodes.get(k).size()==0||(k==num && nodes.get(k).size()==1)){
                continue;
            }
            key = Math.min(key,k);
        }
        List<NodeInner> nodes = this.nodes.get(key);
        NodeInner node = nodes.remove(0);
        cache.remove(node.key);
    }
    //重置set位置,
    public void resetNode(NodeInner node,boolean addFlag){
        if (!addFlag){
            nodes.get(node.num).remove(node);
        }
        node.num++;
        nodes.computeIfAbsent(node.num, k -> new ArrayList<>());
        nodes.get(node.num).add(node);
    }
    class NodeInner{
        public int key;
        public int value;
        public int num;
        public NodeInner(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * 解答成功:
 * 	执行耗时:84 ms,击败了22.28% 的Java用户
 * 	内存消耗:126 MB,击败了13.97% 的Java用户
 */
class LFUCache1 {
    // 缓存容量，时间戳
    int capacity, time;
    Map<Integer, Node> key_table;
    TreeSet<Node> S;

    public LFUCache1(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        key_table = new HashMap<Integer, Node>();
        S = new TreeSet<Node>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // 如果哈希表中没有键 key，返回 -1
        if (!key_table.containsKey(key)) {
            return -1;
        }
        // 从哈希表中得到旧的缓存
        Node cache = key_table.get(key);
        // 从平衡二叉树中删除旧的缓存
        S.remove(cache);
        // 将旧缓存更新
        cache.cnt += 1;
        cache.time = ++time;
        // 将新缓存重新放入哈希表和平衡二叉树中
        S.add(cache);
        key_table.put(key, cache);
        return cache.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!key_table.containsKey(key)) {
            // 如果到达缓存容量上限
            if (key_table.size() == capacity) {
                // 从哈希表和平衡二叉树中删除最近最少使用的缓存
                key_table.remove(S.first().key);
                S.remove(S.first());
            }
            // 创建新的缓存
            Node cache = new Node(1, ++time, key, value);
            // 将新缓存放入哈希表和平衡二叉树中
            key_table.put(key, cache);
            S.add(cache);
        } else {
            // 这里和 get() 函数类似
            Node cache = key_table.get(key);
            S.remove(cache);
            cache.cnt += 1;
            cache.time = ++time;
            cache.value = value;
            S.add(cache);
            key_table.put(key, cache);
        }
    }
}

class Node implements Comparable<Node> {
    int cnt, time, key, value;

    Node(int cnt, int time, int key, int value) {
        this.cnt = cnt;
        this.time = time;
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Node) {
            Node rhs = (Node) anObject;
            return this.cnt == rhs.cnt && this.time == rhs.time;
        }
        return false;
    }

    public int compareTo(Node rhs) {
        return cnt == rhs.cnt ? time - rhs.time : cnt - rhs.cnt;
    }

    public int hashCode() {
        return cnt * 1000000007 + time;
    }
}

/**
 * 解答成功:
 * 	执行耗时:66 ms,击败了41.71% 的Java用户
 * 	内存消耗:123.8 MB,击败了40.29% 的Java用户
 */
class LFUCache {
    int minfreq, capacity;
    Map<Integer, Node2> keyTable;
    Map<Integer, DoublyLinkedList> freqTable;

    public LFUCache(int capacity) {
        this.minfreq = 0;
        this.capacity = capacity;
        keyTable = new HashMap<Integer, Node2>();
        freqTable = new HashMap<Integer, DoublyLinkedList>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!keyTable.containsKey(key)) {
            return -1;
        }
        Node2 node = keyTable.get(key);
        int val = node.val, freq = node.freq;
        freqTable.get(freq).remove(node);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if (freqTable.get(freq).size == 0) {
            freqTable.remove(freq);
            if (minfreq == freq) {
                minfreq += 1;
            }
        }
        // 插入到 freq + 1 中
        DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
        list.addFirst(new Node2(key, val, freq + 1));
        freqTable.put(freq + 1, list);
        keyTable.put(key, freqTable.get(freq + 1).getHead());
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!keyTable.containsKey(key)) {
            // 缓存已满，需要进行删除操作
            if (keyTable.size() == capacity) {
                // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
                Node2 node = freqTable.get(minfreq).getTail();
                keyTable.remove(node.key);
                freqTable.get(minfreq).remove(node);
                if (freqTable.get(minfreq).size == 0) {
                    freqTable.remove(minfreq);
                }
            }
            DoublyLinkedList list = freqTable.getOrDefault(1, new DoublyLinkedList());
            list.addFirst(new Node2(key, value, 1));
            freqTable.put(1, list);
            keyTable.put(key, freqTable.get(1).getHead());
            minfreq = 1;
        } else {
            // 与 get 操作基本一致，除了需要更新缓存的值
            Node2 node = keyTable.get(key);
            int freq = node.freq;
            freqTable.get(freq).remove(node);
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (minfreq == freq) {
                    minfreq += 1;
                }
            }
            DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
            list.addFirst(new Node2(key, value, freq + 1));
            freqTable.put(freq + 1, list);
            keyTable.put(key, freqTable.get(freq + 1).getHead());
        }
    }
}

class Node2 {
    int key, val, freq;
    Node2 prev, next;

    Node2() {
        this(-1, -1, 0);
    }

    Node2(int key, int val, int freq) {
        this.key = key;
        this.val = val;
        this.freq = freq;
    }
}

class DoublyLinkedList {
    Node2 dummyHead, dummyTail;
    int size;

    DoublyLinkedList() {
        dummyHead = new Node2();
        dummyTail = new Node2();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public void addFirst(Node2 node) {
        Node2 prevHead = dummyHead.next;
        node.prev = dummyHead;
        dummyHead.next = node;
        node.next = prevHead;
        prevHead.prev = node;
        size++;
    }

    public void remove(Node2 node) {
        Node2 prev = node.prev, next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    public Node2 getHead() {
        return dummyHead.next;
    }

    public Node2 getTail() {
        return dummyTail.prev;
    }
}


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
