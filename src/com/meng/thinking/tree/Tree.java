package com.meng.thinking.tree;
public class Tree {
    int val;
    Tree left;
    Tree right;
    Tree() {}
    Tree(int val) { this.val = val; }
    Tree(int val, Tree left, Tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
