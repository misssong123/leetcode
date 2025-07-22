package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

import java.util.*;

class GenerateTrees95 {
    boolean [] dp;
    List<TreeNode> res;
    Set<String> exist;

    /**
     * 解答成功:
     * 	执行耗时:51 ms,击败了1.60% 的Java用户
     * 	内存消耗:44.3 MB,击败了5.14% 的Java用户
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees95(int n) {
        res = new ArrayList<>();
        dp = new boolean[n+1];
        exist = new HashSet<>();
        for(int i = 1 ; i <= n ; i++){
            dp[i] = true;
            TreeNode root = new TreeNode(i);
            dfs(root,n,1);
            dp[i] = false;
        }
        return res;
    }
    private void dfs(TreeNode root, int n, int num) {
        //满足条件
        if (num >= n){
            //计算坐标
            String val = calculate(root);
            if (exist.contains(val)){
                return;
            }
            exist.add(val);
            res.add(copy(root));
            return;
        }
        //尝试每个数字
        for(int j = 1 ; j <= n ; j++){
            if(dp[j]){
                continue;
            }
            dp[j] =true;
            TreeNode node = new TreeNode(j);
            //添加节点
            addNode(node,root);
            dfs(root,n,num+1);
            dp[j] = false;
            //撤回节点
            removeNode(node,root);
        }
    }

    private String calculate(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        List<TreeNode> temp = new ArrayList<>();
        while (!list.isEmpty()){
            for (TreeNode node : list) {
                if (node == null) {
                    sb.append("#");
                } else {
                    sb.append(node.val);
                    if (node.left != null || node.right != null) {
                        if (node.left != null) {
                            temp.add(node.left);
                        }
                        if (node.right != null) {
                            if (node.left == null) {
                                temp.add(null);
                            }
                            temp.add(node.right);
                        }
                    }
                }
            }
            list.clear();
            list.addAll(temp);
            temp.clear();
        }
        return sb.toString();
    }

    private void removeNode(TreeNode node, TreeNode root) {
        TreeNode temp = root;
        while(true){
            if(node.val < temp.val){
                if (temp.left == node){
                    temp.left = null;
                    break;
                }else {
                    temp = temp.left;
                }
            }else {
                if (temp.right == node){
                    temp.right = null;
                    break;
                }else {
                    temp = temp.right;
                }
            }
        }
    }

    private void addNode(TreeNode node, TreeNode root) {
        TreeNode temp = root;
        while(true){
            if(node.val < temp.val){
                if (temp.left == null){
                    temp.left = node;
                    break;
                }else {
                    temp = temp.left;
                }
            }else {
                if (temp.right == null){
                    temp.right = node;
                    break;
                }else {
                    temp = temp.right;
                }
            }
        }
    }

    private TreeNode copy(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode node = new TreeNode(root.val);
        node.left = copy(root.left);
        node.right = copy(root.right);
        return node;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.57% 的Java用户
     * 	内存消耗:43.8 MB,击败了37.52% 的Java用户
     * @param n
     * @return
     */
    public List<TreeNode> generateTreesOfficial(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了11.21% 的Java用户
     * 	内存消耗:43.8 MB,击败了43.17% 的Java用户
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }
    List<TreeNode> dfs(int l, int r) {
        if (l > r) return new ArrayList(){{add(null);}};
        List<TreeNode> ans = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            for (TreeNode x : dfs(l, i - 1)) {
                for (TreeNode y : dfs(i + 1, r)) {
                    TreeNode root = new TreeNode(i);
                    root.left = x; root.right = y;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

}
