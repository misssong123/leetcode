package com.meng.origin;

import java.util.*;

/**
 * 872. 叶子相似的树
 *
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 *
 * 示例 3：
 *
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 *
 * 示例 4：
 *
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 *
 * 示例 5：
 *
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 *
 *
 *
 * 提示：
 *
 *     给定的两棵树可能会有 1 到 200 个结点。
 *     给定的两棵树上的值介于 0 到 200 之间。
 */
public class LeafSimilar {
    /**
     * 1.依次获取每个数的叶子结点
     * 2.依次比较叶子结点是否相同
     * @param root1
     * @param root2
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了25.48% 的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了48.08% 的用户
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<TreeNode> list1 = getLeafNode(root1);
        List<TreeNode> list2 = getLeafNode(root2);
        return judgeLeafSimilar(list1,list2);
    }

    private boolean judgeLeafSimilar(List<TreeNode> list1, List<TreeNode> list2) {
        if (list1.size() != list2.size()){
            return false;
        }
        int len = list1.size();
        for(int i = 0 ; i < len ; i++){
            if (list1.get(i).val != list2.get(i).val){
                return false;
            }
        }
        return true;
    }


    private List<TreeNode> getLeafNode(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<TreeNode> res = new ArrayList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if (temp.left != null || temp.right != null){
                if (temp.right != null){
                    queue.addFirst(temp.right);
                }
                if (temp.left != null){
                    queue.addFirst(temp.left);
                }
                continue;
            }
            res.add(temp);
        }
        return res;
    }
    /**
     * 方法一：深度优先搜索
     *
     * 思路与算法
     *
     * 我们可以使用深度优先搜索的方法得到一棵树的「叶值序列」。
     *
     * 具体地，在深度优先搜索的过程中，我们总是先搜索当前节点的左子节点，再搜索当前节点的右子节点。如果我们搜索到一个叶节点，就将它的值放入序列中。
     *
     * 在得到了两棵树分别的「叶值序列」后，我们比较它们是否相等即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/leaf-similar-trees/solution/xie-zi-xiang-si-de-shu-by-leetcode-solut-z0w6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了62.96% 的用户
     */
    public boolean leafSimilar1(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<Integer>();
        if (root1 != null) {
            dfs(root1, seq1);
        }

        List<Integer> seq2 = new ArrayList<Integer>();
        if (root2 != null) {
            dfs(root2, seq2);
        }

        return seq1.equals(seq2);
    }

    public void dfs(TreeNode node, List<Integer> seq) {
        if (node.left == null && node.right == null) {
            seq.add(node.val);
        } else {
            if (node.left != null) {
                dfs(node.left, seq);
            }
            if (node.right != null) {
                dfs(node.right, seq);
            }
        }
    }
}
