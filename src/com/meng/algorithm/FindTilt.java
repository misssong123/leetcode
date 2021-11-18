package com.meng.algorithm;
/**
 * 563. 二叉树的坡度
 * 给定一个二叉树，计算 整个树 的坡度 。
 *
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
 *
 * 整个树 的坡度就是其所有节点的坡度之和。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：1
 * 解释：
 * 节点 2 的坡度：|0-0| = 0（没有子节点）
 * 节点 3 的坡度：|0-0| = 0（没有子节点）
 * 节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
 * 坡度总和：0 + 0 + 1 = 1
 * 示例 2：
 *
 *
 * 输入：root = [4,2,9,3,5,null,7]
 * 输出：15
 * 解释：
 * 节点 3 的坡度：|0-0| = 0（没有子节点）
 * 节点 5 的坡度：|0-0| = 0（没有子节点）
 * 节点 7 的坡度：|0-0| = 0（没有子节点）
 * 节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
 * 节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
 * 节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，和是 16 ）
 * 坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
 * 示例 3：
 *
 *
 * 输入：root = [21,7,14,1,1,2,2,3,3]
 * 输出：9
 *
 *
 * 提示：
 *
 * 树中节点数目的范围在 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
public class FindTilt {
    int sum = 0;

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 19.81%
     * 的用户
     * 通过测试用例：
     * 77 / 77
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return 0;
        }
        dfs(root);
        return sum;
    }

    private int dfs(TreeNode node) {
        if (node==null){
            return 0;
        }
        if (node.left== null && node.right==null){
            return node.val;
        }
        int left = node.val + dfs(node.left);
        int right = node.val + dfs(node.right);
        sum += Math.abs(left - right);
        return left + right - node.val;
    }
    int ans = 0;

    /**
     * 方法一：深度优先搜索
     * 思路和算法
     *
     * 根据题意，我们需要累计二叉树中所有结点的左子树结点之和与右子树结点之和的差的绝对值。因此，我们可以使用深度优先搜索，在遍历每个结点时，累加其左子树结点之和与右子树结点之和的差的绝对值，并返回以其为根结点的树的结点之和。
     *
     * 具体地，我们实现算法如下：
     *
     * 从根结点开始遍历，设当前遍历的结点为 \textit{node}node；
     * 遍历 \textit{node}node 的左子结点，得到左子树结点之和 \textit{sum\_left}sum_left；遍历 \textit{node}node 的右子结点，得到右子树结点之和 \textit{sum\_right}sum_right；
     * 将左子树结点之和与右子树结点之和的差的绝对值累加到结果变量 \textit{ans}ans；
     * 返回以 \textit{node}node 作为根结点的树的结点之和 \textit{sum\_left} + \textit{sum\_right} + \textit{node}.\textit{val}sum_left+sum_right+node.val。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/binary-tree-tilt/solution/er-cha-shu-de-po-du-by-leetcode-solution-7rha/
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
     * 38.4 MB
     * , 在所有 Java 提交中击败了
     * 68.56%
     * 的用户
     * 通过测试用例：
     * 77 / 77
     */
    public int findTilt1(TreeNode root) {
        dfs1(root);
        return ans;
    }

    public int dfs1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sumLeft = dfs1(node.left);
        int sumRight = dfs1(node.right);
        ans += Math.abs(sumLeft - sumRight);
        return sumLeft + sumRight + node.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        FindTilt demo = new FindTilt();
        System.out.println(demo.findTilt(root));
    }
}
