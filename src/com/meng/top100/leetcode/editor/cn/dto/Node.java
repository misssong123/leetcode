package com.meng.top100.leetcode.editor.cn.dto;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int val;
    public Node next;
    public Node random;
    public Node left;
    public Node right;
    public List<Node> neighbors;
    public Node() {
        this.val = -1;
        this.next = null;
        this.random = null;
    }
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
        this.random = null;
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
