package com.meng.level2.day15;

/**
 * 100. 相同的树(简单)
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 *
 *
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 */
public class IsSameTree {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39 MB
     * , 在所有 Java 提交中击败了
     * 39.00%
     * 的用户
     * 通过测试用例：
     * 60 / 60
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) ||(p != null && q == null)){
            return false;
        }
        if(p == null && q == null){
            return true;
        }
        return p.val == q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }


        /**
         *
         0 ms
         , 在所有 Java 提交中击败了
         100.00%
         的用户
         内存消耗：
         38.8 MB
         , 在所有 Java 提交中击败了
         69.08%
         的用户
         通过测试用例：
         60 / 60
         * @param p
         * @param q
         * @return
         */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
        }
    }

}
