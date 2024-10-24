package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int val;
    public Node next;
    public Node random;
    public Node left;
    public Node right;
    public List<Node> neighbors;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
        neighbors = new ArrayList<Node>();
    }
    public Node(){
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
