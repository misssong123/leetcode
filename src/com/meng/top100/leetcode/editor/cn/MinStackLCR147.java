package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 解答成功:
 * 	执行耗时:4 ms,击败了96.89% 的Java用户
 * 	内存消耗:44.2 MB,击败了38.09% 的Java用户
 */
class MinStackLCR147 {
    Deque<Integer> stack;
    Deque<Integer> minStack;
    public MinStackLCR147() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()){
            minStack.push(x);
        }else {
            minStack.push(minStack.peek());
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * 解答成功:
 * 	执行耗时:4 ms,击败了96.89% 的Java用户
 * 	内存消耗:44 MB,击败了59.16% 的Java用户
 */
class MinStack {
    // 注意不要使用 Stack 类，因为它继承自 Vector，是同步的，会导致一些性能问题
    private final Deque<int[]> st = new ArrayDeque<>();

    public MinStack() {
        // 添加栈底哨兵 Integer.MAX_VALUE
        // 这里的 0 写成任意数都可以，反正用不到
        st.push(new int[]{0, Integer.MAX_VALUE});
    }

    public void push(int val) {
        st.push(new int[]{val, Math.min(getMin(), val)});
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek()[0];
    }

    public int getMin() {
        return st.peek()[1];
    }
}

