package com.meng.DataStructureFundamentals.ten10;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}