package com.meng.origin;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 *
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 如下面的两个链表：
 *
 * 在节点 c1 开始相交。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *
 *
 * 示例 2：
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 *
 *
 * 示例 3：
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 *
 *
 * 注意：
 *
 *     如果两个链表没有交点，返回 null.
 *     在返回结果后，两个链表仍须保持原有的结构。
 *     可假定整个链表结构中没有循环。
 *     程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *     本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class GetIntersectionNode {
    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了11.92% 的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了6.68% 的用户
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode head = headA;
        Set<ListNode> cache = new HashSet<>();
        while (head != null){
            cache.add(head);
            head = head.next;
        }
        head = headB;
        while (head != null){
            if (!cache.add(head)){
                return head;
            }
            head = head.next;
        }
        return null;
    }
    /**
     * 方法一：哈希集合
     *
     * 思路和算法
     *
     * 判断两个链表是否相交，可以使用哈希集合存储链表节点。
     *
     * 首先遍历链表 headA\textit{headA}headA，并将链表 headA\textit{headA}headA 中的每个节点加入哈希集合中。然后遍历链表 headB\textit{headB}headB，对于遍历到的每个节点，判断该节点是否在哈希集合中：
     *
     *     如果当前节点不在哈希集合中，则继续遍历下一个节点；
     *
     *     如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都是两个链表的公共节点，因此在链表 headB\textit{headB}headB 中遍历到的第一个在哈希集合中的节点就是两个链表的第一个公共节点，返回该节点。
     *
     * 如果链表 headB\textit{headB}headB 中的所有节点都不在哈希集合中，则两个链表不相交，返回 null\text{null}null。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/liang-ge-lian-biao-de-di-yi-ge-gong-gong-pzbs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param headA
     * @param headB
     * @return
     * 执行用时：8 ms, 在所有 Java 提交中击败了11.92% 的用户
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了5.02% 的用户
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    /**
     *方法二：双指针
     *
     * 思路和算法
     *
     * 使用双指针的方法，可以将空间复杂度降至 O(1)O(1)O(1)。
     *
     * 只有当链表 headA\textit{headA}headA 和 headB\textit{headB}headB 都不为空时，两个链表才可能相交。因此首先判断链表 headA\textit{headA}headA 和 headB\textit{headB}headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回 null\text{null}null。
     *
     * 当链表 headA\textit{headA}headA 和 headB\textit{headB}headB 都不为空时，创建两个指针 pA\textit{pA}pA 和 pB\textit{pB}pB，初始时分别指向两个链表的头节点 headA\textit{headA}headA 和 headB\textit{headB}headB，然后将两个指针依次遍历两个链表的每个节点。具体做法如下：
     *
     *     每步操作需要同时更新指针 pA\textit{pA}pA 和 pB\textit{pB}pB。
     *
     *     如果指针 pA\textit{pA}pA 不为空，则将指针 pA\textit{pA}pA 移到下一个节点；如果指针 pB\textit{pB}pB 不为空，则将指针 pB\textit{pB}pB 移到下一个节点。
     *
     *     如果指针 pA\textit{pA}pA 为空， 则将指针 pA\textit{pA}pA 移到链表 headB\textit{headB}headB 的头节点；如果指针 pB\textit{pB}pB 为空，则将指针 pB\textit{pB}pB 移到链表 headA\textit{headA}headA 的头节点。
     *
     *     当指针 pA\textit{pA}pA 和 pB\textit{pB}pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null\text{null}null。
     *
     * 证明
     *
     * 下面提供双指针方法的正确性证明。考虑两种情况，第一种情况是两个链表相交，第二种情况是两个链表不相交。
     *
     * 情况一：两个链表相交
     *
     * 链表 headA\textit{headA}headA 和 headB\textit{headB}headB 的长度分别是 mmm 和 nnn。假设链表 headA\textit{headA}headA 的不相交部分有 aaa 个节点，链表 headB\textit{headB}headB 的不相交部分有 bbb 个节点，两个链表相交的部分有 ccc 个节点，则有 a+c=ma+c=ma+c=m，b+c=nb+c=nb+c=n。
     *
     *     如果 a=ba=ba=b，则两个指针会同时到达两个链表的第一个公共节点，此时返回两个链表的第一个公共节点；
     *
     *     如果 a≠ba \ne ba​=b，则指针 pA\textit{pA}pA 会遍历完链表 headA\textit{headA}headA，指针 pB\textit{pB}pB 会遍历完链表 headB\textit{headB}headB，两个指针不会同时到达链表的尾节点，然后指针 pA\textit{pA}pA 移到链表 headB\textit{headB}headB 的头节点，指针 pB\textit{pB}pB 移到链表 headA\textit{headA}headA 的头节点，然后两个指针继续移动，在指针 pA\textit{pA}pA 移动了 a+c+ba+c+ba+c+b 次、指针 pB\textit{pB}pB 移动了 b+c+ab+c+ab+c+a 次之后，两个指针会同时到达两个链表的第一个公共节点，该节点也是两个指针第一次同时指向的节点，此时返回两个链表的第一个公共节点。
     *
     * 情况二：两个链表不相交
     *
     * 链表 headA\textit{headA}headA 和 headB\textit{headB}headB 的长度分别是 mmm 和 nnn。考虑当 m=nm=nm=n 和 m≠nm \ne nm​=n 时，两个指针分别会如何移动：
     *
     *     如果 m=nm=nm=n，则两个指针会同时到达两个链表的尾节点，然后同时变成空值 null\text{null}null，此时返回 null\text{null}null；
     *
     *     如果 m≠nm \ne nm​=n，则由于两个链表没有公共节点，两个指针也不会同时到达两个链表的尾节点，因此两个指针都会遍历完两个链表，在指针 pA\textit{pA}pA 移动了 m+nm+nm+n 次、指针 pB\textit{pB}pB 移动了 n+mn+mn+m 次之后，两个指针会同时变成空值 null\text{null}null，此时返回 null\text{null}null。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/liang-ge-lian-biao-de-di-yi-ge-gong-gong-pzbs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * @param headA
     * @param headB
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了51.35% 的用户
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
