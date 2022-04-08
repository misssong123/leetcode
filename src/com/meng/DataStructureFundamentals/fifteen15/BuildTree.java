package com.meng.DataStructureFundamentals.fifteen15;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 *
 * 提示:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 */
public class BuildTree {
    Map<Integer,Integer> midCache = new HashMap<>();

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 63.53%
     * 的用户
     * 内存消耗：
     * 40.3 MB
     * , 在所有 Java 提交中击败了
     * 81.36%
     * 的用户
     * 通过测试用例：
     * 203 / 203
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0 ; i < preorder.length ; i++){
            midCache.put(inorder[i],i);
        }
        TreeNode root = dfs(preorder,0,inorder.length-1,0,inorder.length-1);
        return root;
    }

    private TreeNode dfs(int[] preorder,int preLeft , int preRight , int midLeft, int midRight) {
        if (preLeft > preRight){
            return null;
        }
        int rootIndex = preLeft;
        //确定根节点的值
        TreeNode root = new TreeNode(preorder[rootIndex]);
        Integer index = midCache.get(preorder[rootIndex]);
        int dis = index - midLeft;
        //左节点
        root.left = dfs(preorder,preLeft+1,preLeft + dis,midLeft,index-1);
        //右节点
        root.right = dfs(preorder,preLeft+ dis+1,preRight,index+1,midRight);
        return root;
    }
    /**
     * 方法一：递归
     * 思路
     *
     * 对于任意一颗树而言，前序遍历的形式总是
     *
     *
     * [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
     * 即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是
     *
     *
     * [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
     * 只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。
     *
     * 这样以来，我们就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，我们就可以递归地对构造出左子树和右子树，再将这两颗子树接到根节点的左右位置。
     *
     * 细节
     *
     * 在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样做的时间复杂度较高。我们可以考虑使用哈希表来帮助我们快速地定位根节点。对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。在此后构造二叉树的过程中，我们就只需要 O(1)O(1) 的时间对根节点进行定位了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param preorder
     * @param inorder
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 63.61%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 74.53%
     * 的用户
     * 通过测试用例：
     * 203 / 203
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    /**
     * 方法二：迭代
     * 思路
     *
     * 迭代法是一种非常巧妙的实现方法。
     *
     * 对于前序遍历中的任意两个连续节点 uu 和 vv，根据前序遍历的流程，我们可以知道 uu 和 vv 只有两种可能的关系：
     *
     * vv 是 uu 的左儿子。这是因为在遍历到 uu 之后，下一个遍历的节点就是 uu 的左儿子，即 vv；
     *
     * uu 没有左儿子，并且 vv 是 uu 的某个祖先节点（或者 uu 本身）的右儿子。如果 uu 没有左儿子，那么下一个遍历的节点就是 uu 的右儿子。如果 uu 没有右儿子，我们就会向上回溯，直到遇到第一个有右儿子（且 uu 不在它的右儿子的子树中）的节点 u_au
     * a
     * ​
     *  ，那么 vv 就是 u_au
     * a
     * ​
     *   的右儿子。
     *
     * 第二种关系看上去有些复杂。我们举一个例子来说明其正确性，并在例子中给出我们的迭代算法。
     *
     * 例子
     *
     * 我们以树
     *
     *
     *         3
     *        / \
     *       9  20
     *      /  /  \
     *     8  15   7
     *    / \
     *   5  10
     *  /
     * 4
     * 为例，它的前序遍历和中序遍历分别为
     *
     *
     * preorder = [3, 9, 8, 5, 4, 10, 20, 15, 7]
     * inorder = [4, 5, 8, 10, 9, 3, 15, 20, 7]
     * 我们用一个栈 stack 来维护「当前节点的所有还没有考虑过右儿子的祖先节点」，栈顶就是当前节点。也就是说，只有在栈中的节点才可能连接一个新的右儿子。同时，我们用一个指针 index 指向中序遍历的某个位置，初始值为 0。index 对应的节点是「当前节点不断往左走达到的最终节点」，这也是符合中序遍历的，它的作用在下面的过程中会有所体现。
     *
     * 首先我们将根节点 3 入栈，再初始化 index 所指向的节点为 4，随后对于前序遍历中的每个节点，我们依次判断它是栈顶节点的左儿子，还是栈中某个节点的右儿子。
     *
     * 我们遍历 9。9 一定是栈顶节点 3 的左儿子。我们使用反证法，假设 9 是 3 的右儿子，那么 3 没有左儿子，index 应该恰好指向 3，但实际上为 4，因此产生了矛盾。所以我们将 9 作为 3 的左儿子，并将 9 入栈。
     *
     * stack = [3, 9]
     * index -> inorder[0] = 4
     * 我们遍历 8，5 和 4。同理可得它们都是上一个节点（栈顶节点）的左儿子，所以它们会依次入栈。
     *
     * stack = [3, 9, 8, 5, 4]
     * index -> inorder[0] = 4
     * 我们遍历 10，这时情况就不一样了。我们发现 index 恰好指向当前的栈顶节点 4，也就是说 4 没有左儿子，那么 10 必须为栈中某个节点的右儿子。那么如何找到这个节点呢？栈中的节点的顺序和它们在前序遍历中出现的顺序是一致的，而且每一个节点的右儿子都还没有被遍历过，那么这些节点的顺序和它们在中序遍历中出现的顺序一定是相反的。
     *
     * 这是因为栈中的任意两个相邻的节点，前者都是后者的某个祖先。并且我们知道，栈中的任意一个节点的右儿子还没有被遍历过，说明后者一定是前者左儿子的子树中的节点，那么后者就先于前者出现在中序遍历中。
     *
     * 因此我们可以把 index 不断向右移动，并与栈顶节点进行比较。如果 index 对应的元素恰好等于栈顶节点，那么说明我们在中序遍历中找到了栈顶节点，所以将 index 增加 1 并弹出栈顶节点，直到 index 对应的元素不等于栈顶节点。按照这样的过程，我们弹出的最后一个节点 x 就是 10 的双亲节点，这是因为 10 出现在了 x 与 x 在栈中的下一个节点的中序遍历之间，因此 10 就是 x 的右儿子。
     *
     * 回到我们的例子，我们会依次从栈顶弹出 4，5 和 8，并且将 index 向右移动了三次。我们将 10 作为最后弹出的节点 8 的右儿子，并将 10 入栈。
     *
     * stack = [3, 9, 10]
     * index -> inorder[3] = 10
     * 我们遍历 20。同理，index 恰好指向当前栈顶节点 10，那么我们会依次从栈顶弹出 10，9 和 3，并且将 index 向右移动了三次。我们将 20 作为最后弹出的节点 3 的右儿子，并将 20 入栈。
     *
     * stack = [20]
     * index -> inorder[6] = 15
     * 我们遍历 15，将 15 作为栈顶节点 20 的左儿子，并将 15 入栈。
     *
     * stack = [20, 15]
     * index -> inorder[6] = 15
     * 我们遍历 7。index 恰好指向当前栈顶节点 15，那么我们会依次从栈顶弹出 15 和 20，并且将 index 向右移动了两次。我们将 7 作为最后弹出的节点 20 的右儿子，并将 7 入栈。
     *
     * stack = [7]
     * index -> inorder[8] = 7
     * 此时遍历结束，我们就构造出了正确的二叉树。
     *
     * 算法
     *
     * 我们归纳出上述例子中的算法流程：
     *
     * 我们用一个栈和一个指针辅助进行二叉树的构造。初始时栈中存放了根节点（前序遍历的第一个节点），指针指向中序遍历的第一个节点；
     *
     * 我们依次枚举前序遍历中除了第一个节点以外的每个节点。如果 index 恰好指向栈顶节点，那么我们不断地弹出栈顶节点并向右移动 index，并将当前节点作为最后一个弹出的节点的右儿子；如果 index 和栈顶节点不同，我们将当前节点作为栈顶节点的左儿子；
     *
     * 无论是哪一种情况，我们最后都将当前的节点入栈。
     *
     * 最后得到的二叉树即为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param preorder
     * @param inorder
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.33%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 14.42%
     * 的用户
     * 通过测试用例：
     * 203 / 203
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

}
