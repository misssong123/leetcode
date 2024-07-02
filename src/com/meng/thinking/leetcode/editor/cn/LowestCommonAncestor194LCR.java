package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LowestCommonAncestor194LCR {
    Map<Integer,TreeNode> map = new HashMap<>();

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了5.46% 的Java用户
     * 	内存消耗:44.1 MB,击败了9.84% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorMy(TreeNode root, TreeNode p, TreeNode q) {
        dfsMy(root);
        //构建链路
        List<TreeNode> pList = new ArrayList<>();
        while (map.get(p.val)!=null){
            pList.add(p);
            p = map.get(p.val);
        }
        pList.add(root);
        List<TreeNode> qList = new ArrayList<>();
        while (map.get(q.val)!=null){
            qList.add(q);
            q = map.get(q.val);
        }
        qList.add(root);
        int qLen =  qList.size()-1;
        int pLen =  pList.size()-1;
        while (pLen>=0&&qLen>=0&&pList.get(pLen)==qList.get(qLen)){
            pLen--;
            qLen--;
        }
        return qList.get(qLen+1);
    }

    private void dfsMy(TreeNode root) {
        if(root.left!=null){
            map.put(root.left.val,root);
            dfs(root.left);
        }
        if (root.right!=null){
            map.put(root.right.val,root);
            dfs(root.right);
        }
    }

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了12.09% 的Java用户
     * 	内存消耗:44.6 MB,击败了5.16% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorOfficial(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }


    private TreeNode ans;
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了99.71% 的Java用户
     * 	内存消耗:42.9 MB,击败了51.46% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
