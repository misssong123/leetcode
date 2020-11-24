package com.meng;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountNodes {
    /**
     * 遍历查找
     * @param root
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了84.34% 的用户
     */
    private int ans;
    public int countNodes(TreeNode root) {
        if (root != null)
            middleOrder(root);
        return ans;
    }

    private void middleOrder(TreeNode node) {
        if (node == null)
            return;
        middleOrder(node.left);
        ans++;
        middleOrder(node.right);
    }
    /**
     * 官方解法
     * 对于任意二叉树，都可以通过广度优先搜索或深度优先搜索计算节点个数，时间复杂度和空间复杂度都是 O(n)O(n)O(n)，其中 nnn 是二叉树的节点个数。这道题规定了给出的是完全二叉树，因此可以利用完全二叉树的特性计算节点个数。
     *
     * 规定根节点位于第 000 层，完全二叉树的最大层数为 hhh。根据完全二叉树的特性可知，完全二叉树的最左边的节点一定位于最底层，因此从根节点出发，每次访问左子节点，直到遇到叶子节点，该叶子节点即为完全二叉树的最左边的节点，经过的路径长度即为最大层数 hhh。
     *
     * 当 0≤i<h0 \le i < h0≤i<h 时，第 iii 层包含 2i2^i2i 个节点，最底层包含的节点数最少为 111，最多为 2h2^h2h。
     *
     * 当最底层包含 111 个节点时，完全二叉树的节点个数是
     *
     * ∑i=0h−12i+1=2h\sum_{i=0}^{h-1}2^i+1=2^h i=0∑h−1​2i+1=2h
     *
     * 当最底层包含 2h2^h2h 个节点时，完全二叉树的节点个数是
     *
     * ∑i=0h2i=2h+1−1\sum_{i=0}^{h}2^i=2^{h+1}-1 i=0∑h​2i=2h+1−1
     *
     * 因此对于最大层数为 hhh 的完全二叉树，节点个数一定在 [2h,2h+1−1][2^h,2^{h+1}-1][2h,2h+1−1] 的范围内，可以在该范围内通过二分查找的方式得到完全二叉树的节点个数。
     *
     * 具体做法是，根据节点个数范围的上下界得到当前需要判断的节点个数 kkk，如果第 kkk 个节点存在，则节点个数一定大于或等于 kkk，如果第 kkk 个节点不存在，则节点个数一定小于 kkk，由此可以将查找的范围缩小一半，直到得到节点个数。
     *
     * 如何判断第 kkk 个节点是否存在呢？如果第 kkk 个节点位于第 hhh 层，则 kkk 的二进制表示包含 h+1h+1h+1 位，其中最高位是 111，其余各位从高到低表示从根节点到第 kkk 个节点的路径，000 表示移动到左子节点，111 表示移动到右子节点。通过位运算得到第 kkk 个节点对应的路径，判断该路径对应的节点是否存在，即可判断第 kkk 个节点是否存在。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetco-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了91.35% 的用户
     * 复杂度分析
     *
     *     时间复杂度：O(log⁡2n)O(\log^2 n)O(log2n)，其中 nnn 是完全二叉树的节点数。
     *     首先需要 O(h)O(h)O(h) 的时间得到完全二叉树的最大层数，其中 hhh 是完全二叉树的最大层数。
     *     使用二分查找确定节点个数时，需要查找的次数为 O(log⁡2h)=O(h)O(\log 2^h)=O(h)O(log2h)=O(h)，每次查找需要遍历从根节点开始的一条长度为 hhh 的路径，需要 O(h)O(h)O(h) 的时间，因此二分查找的总时间复杂度是 O(h2)O(h^2)O(h2)。
     *     因此总时间复杂度是 O(h2)O(h^2)O(h2)。由于完全二叉树满足 2h≤n<2h+12^h \le n < 2^{h+1}2h≤n<2h+1，因此有 O(h)=O(log⁡n)O(h)=O(\log n)O(h)=O(logn)，O(h2)=O(log⁡2n)O(h^2)=O(\log^2 n)O(h2)=O(log2n)。
     *
     *     空间复杂度：O(1)O(1)O(1)。只需要维护有限的额外空间。
     */
    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}
