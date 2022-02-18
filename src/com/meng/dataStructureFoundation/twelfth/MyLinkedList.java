package com.meng.dataStructureFoundation.twelfth;
/**
 * 707. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 *
 * 示例：
 *
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *
 *
 * 提示：
 *
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 */
public class MyLinkedList {
    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 95.86%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 20.05%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    int[] nums = null;
    int len;
    public MyLinkedList() {
        nums = new int[1000];
        len = 0;
    }

    public int get(int index) {
        if (index >= len){
            return -1;
        }
        return nums[index];
    }

    public void addAtHead(int val) {
        if (len > 0){
            move(0,true);
        }
        nums[0] = val;
        len++;
    }

    public void addAtTail(int val) {
        nums[len++] = val;
    }

    public void addAtIndex(int index, int val) {
        if (index>len){
            return;
        }
        if (index == len){
            nums[len++] = val;
            return;
        }
        if (len < 0){
            addAtHead(val);
        }else {
            move(index,true);
            nums[index] = val;
            len++;
        }
    }

    private void move(int index,boolean flag) {
        if (flag){//后移
            for(int i = len ; i > index ; i--){
                nums[i] = nums[i-1];
            }
        }else {//前移
            for(int i = index ; i < len-1 ; i++){
                nums[i] = nums[i+1];
            }
        }

    }

    public void deleteAtIndex(int index) {
        if (index>=len){
            return;
        }
        move(index,false);
        len--;
    }
}

/**
 * 执行用时：
 * 9 ms
 * , 在所有 Java 提交中击败了
 * 81.24%
 * 的用户
 * 内存消耗：
 * 41.7 MB
 * , 在所有 Java 提交中击败了
 * 11.77%
 * 的用户
 * 通过测试用例：
 * 64 / 64
 */
class MyLinkedList1 {
    int size;
    ListNode head;
    public MyLinkedList1() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        ListNode curr = head;
        for(int i = 0; i < index + 1; ++i) curr = curr.next;
        return curr.val;
    }
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;

        ++size;
        ListNode pred = head;
        for(int i = 0; i < index; ++i) pred = pred.next;

        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        size--;
        ListNode pred = head;
        for(int i = 0; i < index; ++i) pred = pred.next;

        // delete pred.next
        pred.next = pred.next.next;
    }
}

/**
 * 执行用时：
 * 6 ms
 * , 在所有 Java 提交中击败了
 * 99.94%
 * 的用户
 * 内存消耗：
 * 41.4 MB
 * , 在所有 Java 提交中击败了
 * 19.71%
 * 的用户
 * 通过测试用例：
 * 64 / 64
 */
class MyLinkedList2 {
    int size;
    ListNode head, tail;
    public MyLinkedList2() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        ListNode curr = head;
        if (index + 1 < size - index)
            for(int i = 0; i < index + 1; ++i) curr = curr.next;
        else {
            curr = tail;
            for(int i = 0; i < size - index; ++i) curr = curr.prev;
        }

        return curr.val;
    }
    public void addAtHead(int val) {
        ListNode pred = head, succ = head.next;

        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    public void addAtTail(int val) {
        ListNode succ = tail, pred = tail.prev;

        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for(int i = 0; i < index; ++i) pred = pred.next;
            succ = pred.next;
        }
        else {
            succ = tail;
            for (int i = 0; i < size - index; ++i) succ = succ.prev;
            pred = succ.prev;
        }
        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for(int i = 0; i < index; ++i) pred = pred.next;
            succ = pred.next.next;
        }
        else {
            succ = tail;
            for (int i = 0; i < size - index - 1; ++i) succ = succ.prev;
            pred = succ.prev.prev;
        }
        --size;
        pred.next = succ;
        succ.prev = pred;
    }
}