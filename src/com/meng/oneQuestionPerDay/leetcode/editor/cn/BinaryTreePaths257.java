package com.meng.oneQuestionPerDay.leetcode.editor.cn;
import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinaryTreePaths257 {
    /**
     *递归
     * @param root
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 15.86%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 53.23%
     * 的用户
     * 通过测试用例：
     * 208 / 208
     */
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root,"");
        return res;
    }

    private void dfs(TreeNode root, String s) {
        if (root.left == null && root.right == null){
            res.add(s+root.val);
            return;
        }
        if (root.left != null){
            dfs(root.left,s+root.val+"->");
        }
        if (root.right != null){
            dfs(root.right,s+root.val+"->");
        }
    }

    /**
     * 深度遍历
     * @param root
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 78.32%
     * 的用户
     * 通过测试用例：
     * 208 / 208
     */
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        constructPaths(root, "", paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(Integer.toString(root.val));
            if (root.left == null && root.right == null) {  // 当前节点是叶子节点
                paths.add(pathSB.toString());  // 把路径加入到答案中
            } else {
                pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }

    /**
     * 广度遍历
     * @param root
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 50.10%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 43.79%
     * 的用户
     * 通过测试用例：
     * 208 / 208
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.left.val).toString());
                }

                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return paths;
    }

}
