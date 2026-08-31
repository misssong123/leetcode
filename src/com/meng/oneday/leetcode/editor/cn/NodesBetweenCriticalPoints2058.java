package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class NodesBetweenCriticalPoints2058 {
    public int[] nodesBetweenCriticalPoints2058(ListNode head) {
        //记录临界点
        List<Integer> indexs = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        int index = 0;
        while (head != null) {
            if(list.size() >=2){
                int size = list.size();
                if ((list.get(size -1)[0] > list.get(size -2)[0] && list.get(size -1)[0] > head.val)
                        ||(list.get(size -1)[0] < list.get(size -2)[0] && list.get(size -1)[0] < head.val)
                        ) {
                    indexs.add(list.get(size-1)[1]);
                }
            }
            list.add(new int[]{head.val,index});
            index++;
            head = head.next;
        }
        if (indexs.size() < 2){
            return new int[]{-1,-1};
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1 ; i < indexs.size();i++){
            min = Math.min(min,indexs.get(i) - indexs.get(i-1));
        }
        return new int[]{min,indexs.get(indexs.size()-1) - indexs.get(0)};
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:104 MB,击败了52.81% 的Java用户
     * @param head
     * @return
     */
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = 0;
        int pre = Integer.MIN_VALUE / 2;
        int minDis = Integer.MAX_VALUE;
        ListNode a = head;
        ListNode b = head.next;
        ListNode c = head.next.next;

        for (int i = 1; c != null; i++) {
            if (a.val < b.val && b.val > c.val || a.val > b.val && b.val < c.val) {
                if (first == 0) {
                    first = i;
                }
                minDis = Math.min(minDis, i - pre);
                pre = i;
            }
            a = b;
            b = c;
            c = c.next;
        }

        if (first >= pre) { // 临界点少于两个
            return new int[]{-1, -1};
        }
        return new int[]{minDis, pre - first};
    }

}
