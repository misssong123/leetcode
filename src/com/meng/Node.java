package com.meng;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int val;
    Node next;
    Node random;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
        this.neighbors = new ArrayList<Node>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
