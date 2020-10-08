package com.meng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InorderTraversal {
    List<Integer> result = null;
    public List<Integer> inorderTraversal(TreeNode root) {
       result = new ArrayList<>();
       if (root != null)
           inorder(root);
       return result;
    }

    /**
     * 递归遍历
     * @param node
     */
    private void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        result.add(node.val);
        inorder(node.right);
    }
    /**
     * 递归遍历非递归遍历
     * @param node
     */
    private List<Integer> inorder2(TreeNode node) {
      List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()){
            //获取当前节点的最左子节点
            while (node !=null){
                stack.push(node);
                node = node.left;
            }
            //弹出最上层节点
            node = stack.pop();
            list.add(node.val);
            //获取右节点
            node = node.right;
        }
        return list;
    }
}
