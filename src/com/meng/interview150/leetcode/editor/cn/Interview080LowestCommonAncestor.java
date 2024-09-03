package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Interview080LowestCommonAncestor {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了5.66% 的Java用户
     * 	内存消耗:44.2 MB,击败了10.80% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorMy(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || q == root){
            return root;
        }
        List<TreeNode> cache = new ArrayList<>();
        int count = 0;
        Map<TreeNode,TreeNode> map = new HashMap<>();
        cache.add(root);
        while (!cache.isEmpty() &&count < 2){
            List<TreeNode> temp = new ArrayList<>();
            for(TreeNode node : cache){
                if (node.left != null){
                    temp.add(node.left);
                    map.put(node.left,node);
                    if (node.left == p || node.left == q){
                        count++;
                    }
                }
                if (node.right != null){
                    temp.add(node.right);
                    map.put(node.right,node);
                    if (node.right == p || node.right == q){
                        count++;
                    }
                }
            }
            cache.clear();
            cache.addAll(temp);
        }
        //获取q的链路
        List<TreeNode> qPath = new ArrayList<>();
        while (q != null){
            qPath.add(q);
            q = map.get(q);
        }
        //获取p的链路
        List<TreeNode> pPath = new ArrayList<>();
        while (p != null){
            pPath.add(p);
            p = map.get(p);
        }
        int qSize = qPath.size(),pSize = pPath.size();
        int size = Math.min(qPath.size(),pPath.size());
        int index = 1;
        TreeNode parent = null;
        while (index <= size && qPath.get(qSize-index) == pPath.get(pSize-index)){
            parent = qPath.get(qSize-index);
            index++;
        }
        return parent;
    }


    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了99.73% 的Java用户
     * 	内存消耗:43.8 MB,击败了51.91% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
    private TreeNode ans;
    private int count = 0;
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }
}
