package com.meng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 783. 二叉搜索树节点最小距离
 *给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树节点对象(TreeNode object)，而不是数组。
 *
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinDiffInBST {
    int min=Integer.MAX_VALUE;
    List<Integer> list = new ArrayList<>();
    public int minDiffInBST(TreeNode root) {
        if (root != null)
            dfs(root);
        for (int i = 0 ; i < list.size()-1;i++)
            min = Math.min(min,list.get(i+1)-list.get(i));
        return min;
    }

    /**
     * 中序取出所有的数字，比较每两个之间的大小差，去最小的那个
     * @param node
     */
    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了26.90% 的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了14.14% 的用户
     * @param root
     * @return
     */
    public int minDiffInBST1(TreeNode root) {
        int result = 0;
        int pre = -1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        while (!queue.isEmpty() || root != null){
            while (root != null){
                queue.push(root);
                root = root.left;
            }
            root = queue.poll();
            if (pre == -1)
                pre = root.val;
            else {
                result = Math.min(result,root.val - pre);
                pre = root.val;
            }
            root = root.right;
        }
        return 1;
    }
    /**
     * 方法一：中序遍历
     *
     * 思路与算法
     *
     * 考虑对升序数组 aaa 求任意两个元素之差的最小值，答案一定为相邻两个元素之差的最小值，即
     *
     * ans=min⁡i=0n−2{a[i+1]−a[i]}\textit{ans}=\min_{i=0}^{n-2}\left\{a[i+1]-a[i]\right\} ans=i=0minn−2​{a[i+1]−a[i]}
     *
     * 其中 nnn 为数组 aaa 的长度。其他任意间隔距离大于等于 222 的下标对 (i,j)(i,j)(i,j) 的元素之差一定大于下标对 (i,i+1)(i,i+1)(i,i+1) 的元素之差，故不需要再被考虑。
     *
     * 回到本题，本题要求二叉搜索树任意两节点差的最小值，而我们知道二叉搜索树有个性质为二叉搜索树中序遍历得到的值序列是递增有序的，因此我们只要得到中序遍历后的值序列即能用上文提及的方法来解决。
     *
     * 朴素的方法是经过一次中序遍历将值保存在一个数组中再进行遍历求解，我们也可以在中序遍历的过程中用 pre\textit{pre}pre 变量保存前驱节点的值，这样即能边遍历边更新答案，不再需要显式创建数组来保存，需要注意的是 pre\textit{pre}pre 的初始值需要设置成任意负数标记开头，下文代码中设置为 −1-1−1。
     *
     * 二叉树的中序遍历有多种方式，包括递归、栈、Morris 遍历等，读者可选择自己最擅长的来实现。下文代码提供最普遍的递归方法来实现，其他遍历方法的介绍可以详细看「94. 二叉树的中序遍历的官方题解」，这里不再赘述。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/solution/er-cha-sou-suo-shu-jie-dian-zui-xiao-ju-8u87w/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了87.31% 的用户
     */
    int pre;
    int ans;

    public int minDiffInBST2(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs1(root);
        return ans;
    }

    public void dfs1(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs1(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs1(root.right);
    }
}
