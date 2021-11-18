package com.meng.algorithm;

import java.util.List;

/**
 * 237. 删除链表中的节点
 * 难度
 * 简单
 *
 * 1018
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 接收动态
 * 反馈
 * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
 *
 * 题目数据保证需要删除的节点 不是末尾节点 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
 * 示例 2：
 *
 *
 * 输入：head = [4,5,1,9], node = 1
 * 输出：[4,5,9]
 * 解释：指定链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9
 * 示例 3：
 *
 * 输入：head = [1,2,3,4], node = 3
 * 输出：[1,2,4]
 * 示例 4：
 *
 * 输入：head = [0,1], node = 0
 * 输出：[1]
 * 示例 5：
 *
 * 输入：head = [-3,5,-99], node = -3
 * 输出：[5,-99]
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [2, 1000]
 * -1000 <= Node.val <= 1000
 * 链表中每个节点的值都是唯一的
 * 需要删除的节点 node 是 链表中的一个有效节点 ，且 不是末尾节点
 */
public class DeleteNode {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.8 MB
     * , 在所有 Java 提交中击败了
     * 65.62%
     * 的用户
     * 通过测试用例：
     * 41 / 41
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    /**
     * 方法一：和下一个节点交换
     *
     * 删除链表中的节点的常见的方法是定位到待删除节点的上一个节点，修改上一个节点的
     * next
     * next 指针，使其指向待删除节点的下一个节点，即可完成删除操作。
     *
     * 这道题中，传入的参数
     * node
     * node 为要被删除的节点，无法定位到该节点的上一个节点。注意到要被删除的节点不是链表的末尾节点，因此
     * node
     * .
     * next
     * node.next 不为空，可以通过对
     * node
     * node 和
     * node
     * .
     * next
     * node.next 进行操作实现删除节点。
     *
     * 在给定节点
     * node
     * node 的情况下，可以通过修改
     * node
     * node 的
     * next
     * next 指针的指向，删除
     * node
     * node 的下一个节点。但是题目要求删除
     * node
     * node，为了达到删除
     * node
     * node 的效果，只要在删除节点之前，将
     * node
     * node 的节点值修改为
     * node
     * .
     * next
     * node.next 的节点值即可。
     *
     * 例如，给定链表
     * 4
     * →
     * 5
     * →
     * 1
     * →
     * 9
     * 4→5→1→9，要被删除的节点是
     * 5
     * 5，即链表中的第
     * 2
     * 2 个节点。可以通过如下两步操作实现删除节点的操作。
     *
     * 将第
     * 2
     * 2 个节点的值修改为第
     * 3
     * 3 个节点的值，即将节点
     * 5
     * 5 的值修改为
     * 1
     * 1，此时链表如下：
     * 4
     * →
     * 1
     * →
     * 1
     * →
     * 9
     * 4→1→1→9
     *
     * 删除第
     * 3
     * 3 个节点，此时链表如下：
     * 4
     * →
     * 1
     * →
     * 9
     * 4→1→9
     *
     * 达到删除节点
     * 5
     * 5 的效果。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list/solution/shan-chu-lian-biao-zhong-de-jie-dian-by-x656s/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.8 MB
     * , 在所有 Java 提交中击败了
     * 65.62%
     * 的用户
     * 通过测试用例：
     * 41 / 41
     */
    public void deleteNode1(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}