package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 解答成功:
 * 	执行耗时:20 ms,击败了13.04% 的Java用户
 * 	内存消耗:49.2 MB,击败了5.43% 的Java用户
 */
class BSTIteratorLCR055 {
    List<TreeNode> cache ;
    int index;
    public BSTIteratorLCR055(TreeNode root) {
        cache = new ArrayList<>();
        index = 0;
        //中序遍历
        middleOrder(root,cache);
    }

    private void middleOrder(TreeNode root, List<TreeNode> cache) {
        if (root == null){
            return;
        }
        middleOrder(root.left,cache);
        cache.add(root);
        middleOrder(root.right,cache);
    }

    public int next() {
        return cache.get(index++).val;
    }
    
    public boolean hasNext() {
        return index < cache.size();
    }
}

/**
 * 解答成功:
 * 	执行耗时:17 ms,击败了69.57% 的Java用户
 * 	内存消耗:49.3 MB,击败了5.43% 的Java用户
 */
class BSTIterator {
    Deque<TreeNode> d = new ArrayDeque<>();
    public BSTIterator(TreeNode root) {
        // 步骤 1
        dfsLeft(root);
    }

    public int next() {
        // 步骤 2
        TreeNode root = d.pollLast();
        int ans = root.val;
        // 步骤 3
        root = root.right;
        // 步骤 1
        dfsLeft(root);
        return ans;
    }

    void dfsLeft(TreeNode root) {
        while (root != null) {
            d.addLast(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !d.isEmpty();
    }
}


