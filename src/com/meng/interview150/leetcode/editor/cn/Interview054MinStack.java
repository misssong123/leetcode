package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
//使用辅助栈

/**
 * 解答成功:
 * 	执行耗时:6 ms,击败了11.16% 的Java用户
 * 	内存消耗:44.1 MB,击败了39.02% 的Java用户
 */
class Interview054MinStack {
    Deque<Integer> stack;
    Deque<Integer> minStack;
    public Interview054MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        minStack.push(Integer.MAX_VALUE);
    }
    
    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(),val));
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
 * 	执行耗时:7 ms,击败了7.86% 的Java用户
 * 	内存消耗:43.8 MB,击败了60.97% 的Java用户
 */
 class MinStack {

    // 数据栈
    private Stack<Integer> data;
    // 辅助栈
    private Stack<Integer> helper;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    // 思路 2：辅助栈和数据栈不同步
    // 关键 1：辅助栈的元素空的时候，必须放入新进来的数
    // 关键 2：新来的数小于或者等于辅助栈栈顶元素的时候，才放入（特别注意这里等于要考虑进去）
    // 关键 3：出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈，即"出栈保持同步"就可以了

    public void push(int x) {
        // 辅助栈在必要的时候才增加
        data.add(x);
        // 关键 1 和 关键 2
        if (helper.isEmpty() || helper.peek() >= x) {
            helper.add(x);
        }
    }

    public void pop() {
        // 关键 3：data 一定得 pop()
        if (!data.isEmpty()) {
            // 注意：声明成 int 类型，这里完成了自动拆箱，从 Integer 转成了 int，因此下面的比较可以使用 "==" 运算符
            // 参考资料：https://www.cnblogs.com/GuoYaxiang/p/6931264.html
            // 如果把 top 变量声明成 Integer 类型，下面的比较就得使用 equals 方法
            int top = data.pop();
            if(top == helper.peek()){
                helper.pop();
            }
        }
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {
        if(!helper.isEmpty()){
            return helper.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

}
