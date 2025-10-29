package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.Node;

import java.util.ArrayList;
import java.util.List;

class CopyRandomListLCR154 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了5.87% 的Java用户
     * 	内存消耗:43.7 MB,击败了16.14% 的Java用户
     * @param head
     * @return
     */
    public Node copyRandomListLCR154(Node head) {
        if (head == null){
            return null;
        }
        List<Node> origin = new ArrayList<>();
        List<Node> copy = new ArrayList<>();
        Node cur = head;
        while (cur != null){
            origin.add(cur);
            copy.add(new Node(cur.val));
            cur = cur.next;
        }
        //构建next和Random指针
        for(int i = 0 ; i < origin.size() ; i++){
            if(i < origin.size() - 1){
                copy.get(i).next = copy.get(i + 1);
            }
            if (origin.get(i).random != null){
                copy.get(i).random = copy.get(origin.indexOf(origin.get(i).random));
            }
        }
        return copy.get(0);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了72.86% 的Java用户
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 复制每个节点，把新节点直接插到原节点的后面
        for (Node cur = head; cur != null; cur = cur.next.next) {
            cur.next = new Node(cur.val, cur.next);
        }

        // 遍历交错链表中的原链表节点
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                // 要复制的 random 是 cur.random 的下一个节点
                cur.next.random = cur.random.next;
            }
        }

        // 把交错链表分离成两个链表
        Node newHead = head.next;
        Node cur = head;
        for (; cur.next.next != null; cur = cur.next) {
            Node copy = cur.next;
            cur.next = copy.next; // 恢复原节点的 next
            copy.next = copy.next.next; // 设置新节点的 next
        }
        cur.next = null; // 恢复原节点的 next
        return newHead;
    }

}

