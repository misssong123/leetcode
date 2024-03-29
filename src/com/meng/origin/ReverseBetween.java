package com.meng.origin;

import com.meng.origin.ListNode;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 *
 *
 * 提示：
 *
 *     链表中节点数目为 n
 *     1 <= n <= 500
 *     -500 <= Node.val <= 500
 *     1 <= left <= right <= n
 */
public class ReverseBetween {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了83.31% 的用户
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right)
            return head;
        //保证next节点指向头结点
        ListNode h = new ListNode();
        h.next = head;
        //指向第一个交换节点的前一个节点
        ListNode pre = h;
        //指向第一个节点
        ListNode first = head;
        //临时交换节点
        ListNode swap = null;
        int index = 1;
        //找到第一交换节点
        while (index < left){
            pre = first;
            first = first.next;
            index++;
        }
        //需要前移的节点
        swap = first.next;
        while (index < right){
            first.next = swap.next;
            swap.next = pre.next;
            pre.next = swap;
            swap = first.next;
            index++;
        }
        return h.next;
    }
    /**
     * 官方解法1
     * 方法一：穿针引线
     *
     * 我们以下图中黄色区域的链表反转为例。
     *
     * image.png
     *
     * 使用「206. 反转链表」的解法，反转 left 到 right 部分以后，再拼接起来。我们还需要记录 left 的前一个节点，和 right 的后一个节点。如图所示：
     *
     * image.png
     *
     * 算法步骤：
     *
     *     第 1 步：先将待反转的区域反转；
     *     第 2 步：把 pre 的 next 指针指向反转以后的链表头节点，把反转以后的链表的尾节点的 next 指针指向 succ。
     *
     * image.png
     *
     * 说明：编码细节我们不在题解中介绍了，请见下方代码。思路想明白以后，编码不是一件很难的事情。这里要提醒大家的是，链接什么时候切断，什么时候补上去，先后顺序一定要想清楚，如果想不清楚，可以在纸上模拟，让思路清晰。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了86.11% 的用户
     */
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }
    /**
     * 方法二：一次遍历「穿针引线」反转链表（头插法）
     *
     * 方法一的缺点是：如果 left 和 right 的区域很大，恰好是链表的头节点和尾节点时，找到 left 和 right 需要遍历一次，反转它们之间的链表还需要遍历一次，虽然总的时间复杂度为 O(N)O(N)O(N)，但遍历了链表 222 次，可不可以只遍历一次呢？答案是可以的。我们依然画图进行说明。
     *
     * 我们依然以方法一的示例为例进行说明。
     *
     * image.png
     *
     * 整体思想是：在需要反转的区间里，每遍历到一个节点，让这个新节点来到反转部分的起始位置。下面的图展示了整个流程。
     *
     * image.png
     *
     * 下面我们具体解释如何实现。使用三个指针变量 pre、curr、next 来记录反转的过程中需要的变量，它们的意义如下：
     *
     *     curr：指向待反转区域的第一个节点 left；
     *     next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
     *     pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。
     *
     * 第 1 步，我们使用 ①、②、③ 标注「穿针引线」的步骤。
     *
     * image.png
     *
     * 操作步骤：
     *
     *     先将 curr 的下一个节点记录为 next；
     *     执行操作 ①：把 curr 的下一个节点指向 next 的下一个节点；
     *     执行操作 ②：把 next 的下一个节点指向 pre 的下一个节点；
     *     执行操作 ③：把 pre 的下一个节点指向 next。
     *
     * 第 1 步完成以后「拉直」的效果如下：
     *
     * image.png
     *
     * 第 2 步，同理。同样需要注意 「穿针引线」操作的先后顺序。
     *
     * image.png
     *
     * 第 2 步完成以后「拉直」的效果如下：
     *
     * image.png
     *
     * 第 3 步，同理。
     *
     * image.png
     *
     * 第 3 步完成以后「拉直」的效果如下：
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了16.15% 的用户
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}
