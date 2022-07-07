package com.meng.level2.day07;

import java.util.ArrayList;
import java.util.List;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree543 {
    /**
     * 理解出现偏差，出现错误
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        return getPathNum(root.left) + getPathNum(root.right);
    }
    private int getPathNum(TreeNode root){
        if (root == null){
            return 0;
        }
        int num = 1;
        List<TreeNode> list = new ArrayList<>();
        if (root.left != null){
            list.add(root.left);
        }
        if (root.right != null){
            list.add(root.right);
        }
        while (list.size() > 0){
            num++;
            int size = list.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode temp = list.remove(0);
                if (temp.left != null){
                    list.add(temp.left);
                }
                if (temp.right != null){
                    list.add(temp.right);
                }
            }
        }
        return num;
    }
    int ans;

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 22.89%
     * 的用户
     * 通过测试用例：
     * 104 / 104
     * @param root
     * @return
     */
    public int diameterOfBinaryTree1(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }

}
