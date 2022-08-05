package com.meng.level2.day16;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈(中等)
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 *
 * 示例 1:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * -231 <= val <= 231 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 104 次
 */
public class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 93.21%
     * 的用户
     * 内存消耗：
     * 43 MB
     * , 在所有 Java 提交中击败了
     * 75.73%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }


}

class MinStack1 {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 30.02%
     * 的用户
     * 内存消耗：
     * 43.3 MB
     * , 在所有 Java 提交中击败了
     * 42.54%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    LinkedList<Integer> nums;
    LinkedList<Integer> minNum;
    public MinStack1() {
        nums = new LinkedList<>();
        minNum = new LinkedList<>();
    }

    public void push(int x) {
       nums.push(x);
       minNum.push(minNum.size()>0?Math.min(x,minNum.peek()):x);
    }

    public void pop() {
        nums.pop();
        minNum.pop();
    }

    public int top() {
        return nums.peek();
    }

    public int getMin() {
        return minNum.peek();
    }


}