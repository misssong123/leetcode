package com.meng;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 *     你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortList {
    /**
     *
     * @param head
     * @return
     * 执行用时：774 ms, 在所有 Java 提交中击败了5.04% 的用户
     * 内存消耗：43.2 MB, 在所有 Java 提交中击败了32.99% 的用户
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode first =  new ListNode(0);
        first.next = head;
        while (head.next !=null){
            if (head.val <= head.next.val){
                head = head.next;
                continue;
            }
            ListNode temp = first;
            while (temp.next.val<=head.next.val){
                temp = temp.next;
            }
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = temp.next;
            temp.next = cur;
        }
        return first.next;
    }
    /**
     * 官方解法1
     * 方法一：自顶向下归并排序
     *
     * 对链表自顶向下归并排序的过程如下。
     *
     *     找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动 222 步，慢指针每次移动 111 步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
     *
     *     对两个子链表分别排序。
     *
     *     将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
     *
     * 上述过程可以通过递归实现。递归的终止条件是链表的节点个数小于或等于 111，即当链表为空或者链表只包含 111 个节点时，不需要对链表进行拆分和排序。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：7 ms, 在所有 Java 提交中击败了40.32% 的用户
     * 内存消耗：46.8 MB, 在所有 Java 提交中击败了11.71% 的用户
     */
    public ListNode sortList1(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
    /**
     * 官方解法2
     * 方法二：自底向上归并排序
     *
     * 使用自底向上的方法实现归并排序，则可以达到 O(1)O(1)O(1) 的空间复杂度。
     *
     * 首先求得链表的长度 length\textit{length}length，然后将链表拆分成子链表进行合并。
     *
     * 具体做法如下。
     *
     *     用 subLength\textit{subLength}subLength 表示每次需要排序的子链表的长度，初始时 subLength=1\textit{subLength}=1subLength=1。
     *
     *     每次将链表拆分成若干个长度为 subLength\textit{subLength}subLength 的子链表（最后一个子链表的长度可以小于 subLength\textit{subLength}subLength），按照每两个子链表一组进行合并，合并后即可得到若干个长度为 subLength×2\textit{subLength} \times 2subLength×2 的有序子链表（最后一个子链表的长度可以小于 subLength×2\textit{subLength} \times 2subLength×2）。合并两个子链表仍然使用「21. 合并两个有序链表」的做法。
     *
     *     将 subLength\textit{subLength}subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 length\textit{length}length，整个链表排序完毕。
     *
     * 如何保证每次合并之后得到的子链表都是有序的呢？可以通过数学归纳法证明。
     *
     *     初始时 subLength=1\textit{subLength}=1subLength=1，每个长度为 111 的子链表都是有序的。
     *
     *     如果每个长度为 subLength\textit{subLength}subLength 的子链表已经有序，合并两个长度为 subLength\textit{subLength}subLength 的有序子链表，得到长度为 subLength×2\textit{subLength} \times 2subLength×2 的子链表，一定也是有序的。
     *
     *     当最后一个子链表的长度小于 subLength\textit{subLength}subLength 时，该子链表也是有序的，合并两个有序子链表之后得到的子链表一定也是有序的。
     *
     * 因此可以保证最后得到的链表是有序的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：10 ms, 在所有 Java 提交中击败了22.57% 的用户
     * 内存消耗：46.5 MB, 在所有 Java 提交中击败了22.55% 的用户
     */
    public ListNode sortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge1(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge1(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
    /**
     * 其他解法
     * 解答一：归并排序（递归法）
     *
     *     题目要求时间空间复杂度分别为O(nlogn)O(nlogn)O(nlogn)和O(1)O(1)O(1)，根据时间复杂度我们自然想到二分法，从而联想到归并排序；
     *
     *     对数组做归并排序的空间复杂度为 O(n)O(n)O(n)，分别由新开辟数组O(n)O(n)O(n)和递归函数调用O(logn)O(logn)O(logn)组成，而根据链表特性：
     *         数组额外空间：链表可以通过修改引用来更改节点顺序，无需像数组一样开辟额外空间；
     *         递归额外空间：递归调用函数将带来O(logn)O(logn)O(logn)的空间复杂度，因此若希望达到O(1)O(1)O(1)空间复杂度，则不能使用递归。
     *
     *     通过递归实现链表归并排序，有以下两个环节：
     *         分割 cut 环节： 找到当前链表中点，并从中点将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）；
     *             我们使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点。
     *             找到中点 slow 后，执行 slow.next = None 将链表切断。
     *             递归分割时，输入当前链表左端点 head 和中心节点 slow 的下一个节点 tmp(因为链表是从 slow 切断的)。
     *             cut 递归终止条件： 当head.next == None时，说明只有一个节点了，直接返回此节点。
     *         合并 merge 环节： 将两个排序链表合并，转化为一个排序链表。
     *             双指针法合并，建立辅助ListNode h 作为头部。
     *             设置两指针 left, right 分别指向两链表头部，比较两指针处节点值大小，由小到大加入合并链表头部，指针交替前进，直至添加完两个链表。
     *             返回辅助ListNode h 作为头部的下个节点 h.next。
     *             时间复杂度 O(l + r)，l, r 分别代表两个链表长度。
     *         当题目输入的 head == None 时，直接返回None。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：6 ms, 在所有 Java 提交中击败了53.08% 的用户
     * 内存消耗：46.8 MB, 在所有 Java 提交中击败了13.61% 的用户
     */
    public ListNode sortList3(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList3(head);
        ListNode right = sortList3(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }
}
