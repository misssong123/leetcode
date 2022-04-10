package com.meng.DataStructureFundamentals.sixteen16;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * 示例 3:
 *
 * 输入: root = [], key = 0
 * 输出: []
 *
 *
 * 提示:
 *
 * 节点数的范围 [0, 104].
 * -105 <= Node.val <= 105
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -105 <= key <= 105
 *
 *
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 */
public class DeleteNode {
    /**
     * 二叉搜索树的三个特性：
     * 这些性质最好在面试之前了解清楚：
     *
     * 二叉搜索树的中序遍历的序列是递增排序的序列。中序遍历的遍历次序：Left -> Node -> Right。
     * JavaPython
     *
     * public LinkedList<Integer> inorder(TreeNode root, LinkedList<Integer> arr) {
     *   if (root == null) return arr;
     *   inorder(root.left, arr);
     *   arr.add(root.val);
     *   inorder(root.right, arr);
     *   return arr;
     * }
     *
     *
     * Successor 代表的是中序遍历序列的下一个节点。即比当前节点大的最小节点，简称后继节点。 先取当前节点的右节点，然后一直取该节点的左节点，直到左节点为空，则最后指向的节点为后继节点。
     * JavaPython
     *
     * public int successor(TreeNode root) {
     *   root = root.right;
     *   while (root.left != null) root = root.left;
     *   return root;
     * }
     * Predecessor 代表的是中序遍历序列的前一个节点。即比当前节点小的最大节点，简称前驱节点。先取当前节点的左节点，然后取该节点的右节点，直到右节点为空，则最后指向的节点为前驱节点。
     * JavaPython
     *
     * public int predecessor(TreeNode root) {
     *   root = root.left;
     *   while (root.right != null) root = root.right;
     *   return root;
     * }
     *
     *
     * 方法：递归
     * 这里有三种可能的情况：
     *
     * 要删除的节点为叶子节点，可以直接删除。
     *
     *
     * 要删除的节点不是叶子节点且拥有右节点，则该节点可以由该节点的后继节点进行替代，该后继节点位于右子树中较低的位置。然后可以从后继节点的位置递归向下操作以删除后继节点。
     *
     *
     * 要删除的节点不是叶子节点，且没有右节点但是有左节点。这意味着它的后继节点在它的上面，但是我们并不想返回。我们可以使用它的前驱节点进行替代，然后再递归的向下删除前驱节点。
     *
     *
     * 算法：
     *
     * 如果 key > root.val，说明要删除的节点在右子树，root.right = deleteNode(root.right, key)。
     * 如果 key < root.val，说明要删除的节点在左子树，root.left = deleteNode(root.left, key)。
     * 如果 key == root.val，则该节点就是我们要删除的节点，则：
     * 如果该节点是叶子节点，则直接删除它：root = null。
     * 如果该节点不是叶子节点且有右节点，则用它的后继节点的值替代 root.val = successor.val，然后删除后继节点。
     * 如果该节点不是叶子节点且只有左节点，则用它的前驱节点的值替代 root.val = predecessor.val，然后删除前驱节点。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/shan-chu-er-cha-sou-suo-shu-zhong-de-jie-dian-by-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 5.11%
     * 的用户
     * 通过测试用例：
     * 91 / 91
     */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) root.right = deleteNode(root.right, key);
        else if (key < root.val) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null && root.right == null) root = null;
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
