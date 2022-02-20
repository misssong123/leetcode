package com.meng.dataStructureFoundation.fourteenth;

import java.util.*;

/**
 * 155. 最小栈
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
    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 12.51%
     * 的用户
     * 内存消耗：
     * 42.8 MB
     * , 在所有 Java 提交中击败了
     * 22.39%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    private List<Integer> list;
    private PriorityQueue<Integer> queue ;
    public MinStack() {
        list = new ArrayList<>();
        queue = new PriorityQueue<>(Integer::compareTo);
    }

    public void push(int val) {
        list.add(0,val);
        queue.add(val);
    }

    public void pop() {
        int val = list.remove(0);
        queue.remove(val);
    }

    public int top() {
        return list.get(0);
    }

    public int getMin() {
        return queue.peek();
    }
}

/**
 * 方法一：辅助栈
 * 思路
 *
 * 要做出这道题目，首先要理解栈结构先进后出的性质。
 *
 * 对于栈来说，如果一个元素 a 在入栈时，栈里有其它的元素 b, c, d，那么无论这个栈在之后经历了什么操作，只要 a 在栈中，b, c, d 就一定在栈中，因为在 a 被弹出之前，b, c, d 不会被弹出。
 *
 * 因此，在操作过程中的任意一个时刻，只要栈顶的元素是 a，那么我们就可以确定栈里面现在的元素一定是 a, b, c, d。
 *
 * 那么，我们可以在每个元素 a 入栈时把当前栈的最小值 m 存储起来。在这之后无论何时，如果栈顶元素是 a，我们就可以直接返回存储的最小值 m。
 *
 *
 *
 * 算法
 *
 * 按照上面的思路，我们只需要设计一个数据结构，使得每个元素 a 与其相应的最小值 m 时刻保持一一对应。因此我们可以使用一个辅助栈，与元素栈同步插入与删除，用于存储与每个元素对应的最小值。
 *
 * 当一个元素要入栈时，我们取当前辅助栈的栈顶存储的最小值，与当前元素比较得出最小值，将这个最小值插入辅助栈中；
 *
 * 当一个元素要出栈时，我们把辅助栈的栈顶元素也一并弹出；
 *
 * 在任意一个时刻，栈内元素的最小值就存储在辅助栈的栈顶元素中。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/min-stack/solution/zui-xiao-zhan-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：
 * 4 ms
 * , 在所有 Java 提交中击败了
 * 97.86%
 * 的用户
 * 内存消耗：
 * 43.4 MB
 * , 在所有 Java 提交中击败了
 * 5.19%
 * 的用户
 * 通过测试用例：
 * 31 / 31
 */
class MinStack1 {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack1() {
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