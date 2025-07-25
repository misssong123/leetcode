package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;


class SortedListToBST109 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了38.39% 的Java用户
     * 	内存消耗:44 MB,击败了71.00% 的Java用户
     * @param head
     * @return
     */
    public TreeNode sortedListToBST109(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> vals = new ArrayList<>();
        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }
        return buildTree(vals, 0, vals.size() - 1);
    }

    private TreeNode buildTree(List<Integer> vals, int l, int r) {
        if(l > r){
            return null;
        }
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(vals.get(mid));
        root.left = buildTree(vals, l, mid - 1);
        root.right = buildTree(vals, mid + 1, r);
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了38.39% 的Java用户
     * 	内存消耗:43.9 MB,击败了73.37% 的Java用户
     * @param head
     * @return
     */
    public TreeNode sortedListToBSTOfficial1(ListNode head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    ListNode globalHead;

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了38.39% 的Java用户
     * 	内存消耗:44 MB,击败了60.58% 的Java用户
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
