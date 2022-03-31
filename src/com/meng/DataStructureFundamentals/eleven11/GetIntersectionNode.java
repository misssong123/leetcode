package com.meng.DataStructureFundamentals.eleven11;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 *
 *
 *
 * 题目数据 保证 整个链式结构中不存在环。
 *
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 *
 * 自定义评测：
 *
 * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
 *
 * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
 * listA - 第一个链表
 * listB - 第二个链表
 * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
 * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
 * 评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例 2：
 *
 *
 *
 * 输入：intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 示例 3：
 *
 *
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 *
 *
 * 提示：
 *
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
 *
 *
 * 进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
 */
public class GetIntersectionNode {
    /**
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 7.64%
     * 的用户
     * 内存消耗：
     * 43.9 MB
     * , 在所有 Java 提交中击败了
     * 61.78%
     * 的用户
     * 通过测试用例：
     * 39 / 39
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> cache = new HashSet<>();
        while (headA != null || headB!= null){
            if (headA != null){
                if (!cache.add(headA)){
                    return headA;
                }
                headA = headA.next;
            }
            if (headB != null){
                if (!cache.add(headB)){
                    return headB;
                }
                headB = headB.next;
            }
        }
        return null;
    }

    /**
     * 方法二：双指针
     * 思路和算法
     *
     * 使用双指针的方法，可以将空间复杂度降至 O(1)O(1)。
     *
     * 只有当链表 \textit{headA}headA 和 \textit{headB}headB 都不为空时，两个链表才可能相交。因此首先判断链表 \textit{headA}headA 和 \textit{headB}headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回 \text{null}null。
     *
     * 当链表 \textit{headA}headA 和 \textit{headB}headB 都不为空时，创建两个指针 \textit{pA}pA 和 \textit{pB}pB，初始时分别指向两个链表的头节点 \textit{headA}headA 和 \textit{headB}headB，然后将两个指针依次遍历两个链表的每个节点。具体做法如下：
     *
     * 每步操作需要同时更新指针 \textit{pA}pA 和 \textit{pB}pB。
     *
     * 如果指针 \textit{pA}pA 不为空，则将指针 \textit{pA}pA 移到下一个节点；如果指针 \textit{pB}pB 不为空，则将指针 \textit{pB}pB 移到下一个节点。
     *
     * 如果指针 \textit{pA}pA 为空，则将指针 \textit{pA}pA 移到链表 \textit{headB}headB 的头节点；如果指针 \textit{pB}pB 为空，则将指针 \textit{pB}pB 移到链表 \textit{headA}headA 的头节点。
     *
     * 当指针 \textit{pA}pA 和 \textit{pB}pB 指向同一个节点或者都为空时，返回它们指向的节点或者 \text{null}null。
     *
     * 证明
     *
     * 下面提供双指针方法的正确性证明。考虑两种情况，第一种情况是两个链表相交，第二种情况是两个链表不相交。
     *
     * 情况一：两个链表相交
     *
     * 链表 \textit{headA}headA 和 \textit{headB}headB 的长度分别是 mm 和 nn。假设链表 \textit{headA}headA 的不相交部分有 aa 个节点，链表 \textit{headB}headB 的不相交部分有 bb 个节点，两个链表相交的部分有 cc 个节点，则有 a+c=ma+c=m，b+c=nb+c=n。
     *
     * 如果 a=ba=b，则两个指针会同时到达两个链表相交的节点，此时返回相交的节点；
     *
     * 如果 a \ne ba
     * 
     * ​
     *  =b，则指针 \textit{pA}pA 会遍历完链表 \textit{headA}headA，指针 \textit{pB}pB 会遍历完链表 \textit{headB}headB，两个指针不会同时到达链表的尾节点，然后指针 \textit{pA}pA 移到链表 \textit{headB}headB 的头节点，指针 \textit{pB}pB 移到链表 \textit{headA}headA 的头节点，然后两个指针继续移动，在指针 \textit{pA}pA 移动了 a+c+ba+c+b 次、指针 \textit{pB}pB 移动了 b+c+ab+c+a 次之后，两个指针会同时到达两个链表相交的节点，该节点也是两个指针第一次同时指向的节点，此时返回相交的节点。
     *
     * 情况二：两个链表不相交
     *
     * 链表 \textit{headA}headA 和 \textit{headB}headB 的长度分别是 mm 和 nn。考虑当 m=nm=n 和 m \ne nm
     * 
     * ​
     *  =n 时，两个指针分别会如何移动：
     *
     * 如果 m=nm=n，则两个指针会同时到达两个链表的尾节点，然后同时变成空值 \text{null}null，此时返回 \text{null}null；
     *
     * 如果 m \ne nm
     * 
     * ​
     *  =n，则由于两个链表没有公共节点，两个指针也不会同时到达两个链表的尾节点，因此两个指针都会遍历完两个链表，在指针 \textit{pA}pA 移动了 m+nm+n 次、指针 \textit{pB}pB 移动了 n+mn+m 次之后，两个指针会同时变成空值 \text{null}null，此时返回 \text{null}null。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
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
