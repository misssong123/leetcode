package com.meng.algorithmfundamentals.seventh;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 *
 * 示例：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 *
 * 提示：
 *
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 */
public class Connect {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 66.44%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 9.43%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                Node poll = queue.poll();
                if (poll.left != null){
                    queue.add(poll.left);
                }
                if (poll.right != null){
                    queue.add(poll.right);
                }
                if (i != size -1 ){
                    poll.next = queue.peek();
                    System.out.println(poll.val +"---->"+queue.peek().val);
                }
            }
        }
        return root;
    }

    /**
     * 方法一：层次遍历
     * 思路与算法
     *
     * 这道题希望我们把二叉树各个层的点组织成链表，一个非常直观的思路是层次遍历。树的层次遍历基于广度优先搜索，它按照层的顺序遍历二叉树，在遍历第 ii 层前，一定会遍历完第 i - 1i−1 层。
     *
     * 算法如下：初始化一个队列 qq，将根结点放入队列中。当队列不为空的时候，记录当前队列大小为 nn，从队列中以此取出 nn 个元素并通过这 nn 个元素拓展新节点。如此循环，直到队列为空。我们不难写出这样的代码：
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-15/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 66.44%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 39.28%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            for (int i = 1; i <= n; ++i) {
                Node f = queue.poll();
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }


    /**
     * 方法二：使用已建立的 \text{next}next 指针
     * 思路与算法
     *
     * 因为必须处理树上的所有节点，所以无法降低时间复杂度，但是可以尝试降低空间复杂度。
     *
     * 在方法一中，因为对树的结构一无所知，所以使用队列保证有序访问同一层的所有节点，并建立它们之间的连接。然而不难发现：一旦在某层的节点之间建立了 \text{next}next 指针，那这层节点实际上形成了一个链表。因此，如果先去建立某一层的 \text{next}next 指针，再去遍历这一层，就无需再使用队列了。
     *
     * 基于该想法，提出降低空间复杂度的思路：如果第 ii 层节点之间已经建立 \text{next}next 指针，就可以通过 \text{next}next 指针访问该层的所有节点，同时对于每个第 ii 层的节点，我们又可以通过它的 \rm leftleft 和 \rm rightright 指针知道其第 i+1i+1 层的孩子节点是什么，所以遍历过程中就能够按顺序为第 i + 1i+1 层节点建立 \text{next}next 指针。
     *
     * 具体来说：
     *
     * 从根节点开始。因为第 00 层只有一个节点，不需要处理。可以在上一层为下一层建立 \text{next}next 指针。该方法最重要的一点是：位于第 xx 层时为第 x + 1x+1 层建立 \text{next}next 指针。一旦完成这些连接操作，移至第 x + 1x+1 层为第 x + 2x+2 层建立 \text{next}next 指针。
     * 当遍历到某层节点时，该层节点的 \text{next}next 指针已经建立。这样就不需要队列从而节省空间。每次只要知道下一层的最左边的节点，就可以从该节点开始，像遍历链表一样遍历该层的所有节点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-15/
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
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 34.38%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    Node last = null, nextStart = null;
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    public void handle(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }


}
