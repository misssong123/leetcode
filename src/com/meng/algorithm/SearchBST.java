package com.meng.algorithm;

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，
 *
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和值: 2
 * 你应该返回如下子树:
 *
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 */
public class SearchBST {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 85.34%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null){
            return null;
        }
        return dfs(root,val);
    }

    private TreeNode dfs(TreeNode root, int val) {
        if (root == null){
            return null;
        }
        if (root.val > val){
           return  dfs(root.left,val);
        }else if (root.val < val){
            return dfs(root.right,val);
        }else {
            return root;
        }
    }

    /**
     * 方法一：递归
     * 二叉搜索树满足如下性质：
     *
     * 左子树所有节点的元素值均小于根的元素值；
     * 右子树所有节点的元素值均大于根的元素值。
     * 据此可以得到如下算法：
     *
     * 若 \textit{root}root 为空则返回空节点；
     * 若 \textit{val}=\textit{root}.\textit{val}val=root.val，则返回 \textit{root}root；
     * 若 \textit{val}<\textit{root}.\textit{val}val<root.val，递归左子树；
     * 若 \textit{val}>\textit{root}.\textit{val}val>root.val，递归右子树。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-sou-suo-by-l-d8zi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param val
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 81.09%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val == root.val) {
            return root;
        }
        return searchBST1(val < root.val ? root.left : root.right, val);
    }

    /**
     * 方法二：迭代
     * 我们将方法一的递归改成迭代写法：
     *
     * 若 \textit{root}root 为空则跳出循环，并返回空节点；
     * 若 \textit{val}=\textit{root}.\textit{val}val=root.val，则返回 \textit{root}root；
     * 若 \textit{val}<\textit{root}.\textit{val}val<root.val，将 \textit{root}root 置为 \textit{root}.\textit{left}root.left；
     * 若 \textit{val}>\textit{root}.\textit{val}val>root.val，将 \textit{root}root 置为 \textit{root}.\textit{right}root.right。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-sou-suo-by-l-d8zi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param val
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.2 MB
     * , 在所有 Java 提交中击败了
     * 62.34%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (val == root.val) {
                return root;
            }
            root = val < root.val ? root.left : root.right;
        }
        return null;
    }


}
