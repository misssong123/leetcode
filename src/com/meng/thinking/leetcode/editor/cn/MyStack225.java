package com.meng.thinking.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class MyStack225 {
    LinkedList<Integer> list;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了22.09% 的Java用户
     */
    public MyStack225() {
        list = new LinkedList<>();
    }
    
    public void push(int x) {
        list.push(x);
    }
    
    public int pop() {
        return list.pop();
    }
    
    public int top() {
        return list.peek();
    }
    
    public boolean empty() {
        return list.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)
class MyStack {
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
