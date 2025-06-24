package com.meng.oneday.leetcode.editor.cn;

import com.meng.oneday.PolyNode;

class AddPoly1634 {

    public PolyNode addPoly1634(PolyNode poly1, PolyNode poly2) {
        PolyNode head  = new PolyNode();
        PolyNode cur = head;
        while (poly1 != null && poly2 != null) {
            PolyNode node = null;
            if(poly1.power == poly2.power){
                //计算系数
                int coefficient = poly1.coefficient + poly2.coefficient;
                if (coefficient != 0){
                    node = new PolyNode(coefficient,poly1.power);
                }
                poly1 = poly1.next;
                poly2 = poly2.next;
            }else if (poly1.power > poly2.power) {
                node = new PolyNode(poly1.coefficient,poly1.power);
                poly1 = poly1.next;
            }else {
                node = new PolyNode(poly2.coefficient,poly2.power);
                poly2 = poly2.next;
            }
            if (node != null){
                cur.next = node;
                cur = cur.next;
            }
        }
        while (poly1 != null){
            cur.next = poly1;
            cur = cur.next;
            poly1 = poly1.next;
        }
        while (poly2 != null){
            cur.next = poly2;
            cur = cur.next;
            poly2 = poly2.next;
        }
        return head.next;
    }
}

