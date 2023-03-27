package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

/**
 * 执行用时：
 * 26 ms
 * , 在所有 Java 提交中击败了
 * 99.03%
 * 的用户
 * 内存消耗：
 * 42.3 MB
 * , 在所有 Java 提交中击败了
 * 99.61%
 * 的用户
 * 通过测试用例：
 * 90 / 90
 */
class AuthenticationManager1797 {
    NodeT head;
    NodeT tail;
    Map<String, NodeT> map;
    int timeToLive;

    public AuthenticationManager1797(int timeToLive) {
        this.map = new HashMap<>();
        this.timeToLive = timeToLive;
        head = new NodeT("", -1);
        tail = new NodeT("", -1);
        head.next = tail;
        tail.pre = head;
    }

    public void generate(String tokenId, int currentTime) {
        NodeT node = new NodeT(tokenId, currentTime + timeToLive);
        map.put(tokenId, node);
        node.pre = tail.pre;
        node.pre.next = node;
        node.next = tail;
        tail.pre = node;
    }

    public void renew(String tokenId, int currentTime) {
        if (map.get(tokenId) != null && map.get(tokenId).currentTime > currentTime) {
            NodeT node = map.get(tokenId);
            node.currentTime = currentTime + timeToLive;
            if (node.next != tail){
                node.next.pre = node.pre;
                node.pre.next = node.next;
                node.pre = tail.pre;
                node.pre.next = node;
                node.next = tail;
                tail.pre = node;
            }
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        while (head.next.currentTime>0&&head.next.currentTime<=currentTime){
            NodeT node = head.next;
            map.remove(node.tokenId);
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }
        return map.size();
    }
}
class NodeT{
    public String tokenId;
    public int currentTime;
    public NodeT pre;
    public NodeT next;
    public NodeT(String tokenId,int currentTime){
        this.tokenId = tokenId;
        this.currentTime = currentTime;
    }
}

/**
 * 执行用时：
 * 58 ms
 * , 在所有 Java 提交中击败了
 * 34.17%
 * 的用户
 * 内存消耗：
 * 42.6 MB
 * , 在所有 Java 提交中击败了
 * 81.75%
 * 的用户
 * 通过测试用例：
 * 90 / 90
 */
class AuthenticationManager {
    int timeToLive;
    Map<String, Integer> map;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        this.map = new HashMap<String, Integer>();
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        if (map.getOrDefault(tokenId, 0) > currentTime) {
            map.put(tokenId, currentTime + timeToLive);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int res = 0;
        for (int time : map.values()) {
            if (time > currentTime) {
                res++;
            }
        }
        return res;
    }
}

/**
 * 执行用时：
 * 35 ms
 * , 在所有 Java 提交中击败了
 * 78.64%
 * 的用户
 * 内存消耗：
 * 42.5 MB
 * , 在所有 Java 提交中击败了
 * 95.53%
 * 的用户
 * 通过测试用例：
 * 90 / 90
 */
class AuthenticationManager1 {
    int timeToLive;
    Node head;
    Node tail;
    Map<String, Node> map;

    public AuthenticationManager1(int timeToLive) {
        this.timeToLive = timeToLive;
        this.head = new Node(-1);
        this.tail = new Node(-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<String, Node>();
    }

    public void generate(String tokenId, int currentTime) {
        Node node = new Node(currentTime + timeToLive, tokenId);
        map.put(tokenId, node);
        Node last = tail.prev;
        last.next = node;
        node.prev = last;
        tail.prev = node;
        node.next = tail;
    }

    public void renew(String tokenId, int currentTime) {
        if (map.containsKey(tokenId) && map.get(tokenId).expire > currentTime) {
            Node node = map.get(tokenId);
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            node.expire = currentTime + timeToLive;
            Node last = tail.prev;
            last.next = node;
            node.prev = last;
            tail.prev = node;
            node.next = tail;
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        while (head.next.expire > 0 && head.next.expire <= currentTime) {
            Node node = head.next;
            map.remove(node.key);
            head.next = node.next;
            node.next.prev = head;
        }
        return map.size();
    }
}

class Node {
    int expire;
    String key;
    Node prev;
    Node next;

    public Node(int expire) {
        this(expire, null, null, null);
    }

    public Node(int expire, String key) {
        this(expire, key, null, null);
    }

    public Node(int expire, String key, Node prev, Node next) {
        this.expire = expire;
        this.key = key;
        this.prev = prev;
        this.next = next;
    }
}
