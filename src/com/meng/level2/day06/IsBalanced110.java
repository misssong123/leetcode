package com.meng.level2.day06;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *
 *
 * 提示：
 *
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 */
public class IsBalanced110 {
    boolean flag = true;

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 24.05%
     * 的用户
     * 通过测试用例：
     * 228 / 228
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return flag;
        }
        int left = getDept(root.left);
        int right = getDept(root.right);
        return flag && Math.abs(left - right)<=1;
    }

    private int getDept(TreeNode root) {
        if (!flag){
            return -1;
        }
        if (root == null){
            return 0;
        }
        int left = getDept(root.left) + 1;
        int right = getDept(root.right) + 1;
        flag = flag && Math.abs(left - right)<=1;
        return Math.max(left,right);
    }

    /**
     * 方法一：自顶向下的递归
     * 定义函数 \texttt{height}height，用于计算二叉树中的任意一个节点 pp 的高度：
     *
     * \texttt{height}(p) = \begin{cases} 0 & p \text{ 是空节点}\\ \max(\texttt{height}(p.\textit{left}), \texttt{height}(p.\textit{right}))+1 & p \text{ 是非空节点} \end{cases}
     * height(p)={
     * 0
     * max(height(p.left),height(p.right))+1
     * ​
     *
     * p 是空节点
     * p 是非空节点
     * ​
     *
     *
     * 有了计算节点高度的函数，即可判断二叉树是否平衡。具体做法类似于二叉树的前序遍历，即对于当前遍历到的节点，首先计算左右子树的高度，如果左右子树的高度差是否不超过 11，再分别递归地遍历左右子节点，并判断左子树和右子树是否平衡。这是一个自顶向下的递归的过程。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/balanced-binary-tree/solution/ping-heng-er-cha-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 45.24%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 61.85%
     * 的用户
     * 通过测试用例：
     * 228 / 228
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    /**
     * 方法二：自底向上的递归
     * 方法一由于是自顶向下递归，因此对于同一个节点，函数 \texttt{height}height 会被重复调用，导致时间复杂度较高。如果使用自底向上的做法，则对于每个节点，函数 \texttt{height}height 只会被调用一次。
     *
     * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 -1−1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/balanced-binary-tree/solution/ping-heng-er-cha-shu-by-leetcode-solution/
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
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 14.92%
     * 的用户
     * 通过测试用例：
     * 228 / 228
     */
    public boolean isBalanced2(TreeNode root) {
        return height2(root) >= 0;
    }

    public int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height2(root.left);
        int rightHeight = height2(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }



}
