package com.meng.level2.day15;

import java.util.ArrayList;
import java.util.List;

/**
 * 199. 二叉树的右视图(中等)
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class RightSideView {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 82.03%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 81.02%
     * 的用户
     * 通过测试用例：
     * 216 / 216
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        List<TreeNode> temp = new ArrayList<>();
        temp.add(root);
        while (temp.size() > 0){
            int size = temp.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode remove = temp.remove(0);
                if (i == size -1){
                    res.add(remove.val);
                }
                if (remove.left != null){
                    temp.add(remove.left);
                }
                if (remove.right != null){
                    temp.add(remove.right);
                }
            }
        }
        return res;
    }
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 65.94%
     * 的用户
     * 通过测试用例：
     * 216 / 216
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode node, int level) {
        if(node != null) {
            if(res.size() == level) {
                res.add(node.val);
            }
            dfs(res, node.right, level + 1);
            dfs(res, node.left, level + 1);
        }
    }
}
