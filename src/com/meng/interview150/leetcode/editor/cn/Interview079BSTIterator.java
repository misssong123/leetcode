package com.meng.interview150.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Interview079BSTIterator {
    List<Integer> cache;
    int index = -1;

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了13.84% 的Java用户
     * 	内存消耗:47.1 MB,击败了18.19% 的Java用户
     * @param root
     */
    public Interview079BSTIterator(TreeNode root) {
        cache = new ArrayList<>();
        dfs(root);
    }
    public void dfs(TreeNode root){
        if (root == null){
            return;
        }
        dfs(root.left);
        cache.add(root.val);
        dfs(root.right);
    }
    public int next() {
        return cache.get(++index);
    }
    
    public boolean hasNext() {
        return index+1<cache.size();
    }
}

/**
 * 解答成功:
 * 	执行耗时:17 ms,击败了51.10% 的Java用户
 * 	内存消耗:47.1 MB,击败了23.48% 的Java用户
 */
class BSTIterator {
    private TreeNode cur;
    private Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new LinkedList<TreeNode>();
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int ret = cur.val;
        cur = cur.right;
        return ret;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}


