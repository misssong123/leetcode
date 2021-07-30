package com.meng.dupthread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1116. 打印零与奇偶数
 *
 * 假设有这么一个类：
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 *
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 *     线程 A 将调用 zero()，它只输出 0 。
 *     线程 B 将调用 even()，它只输出偶数。
 *     线程 C 将调用 odd()，它只输出奇数。
 *
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 *
 * 示例 2：
 *
 * 输入：n = 5
 * 输出："0102030405"
 */
public class ZeroEvenOdd {
    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了6.62% 的用户
     */
    private int n;
    private volatile  int number = 1;
    private volatile int state = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    public ZeroEvenOdd(int n) {
        this.n = n;
    }
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
             while (number <= n){
                 if (state != 0) {
                     conditionA.await();
                 }
                printNumber.accept(0);
                if (number % 2 == 0){
                    conditionC.signal();
                    state = 2;
                }else {
                    conditionB.signal();
                    state = 1;
                }
                conditionA.await();
            }
            conditionB.signal();
            conditionC.signal();
        }finally {
            lock.unlock();
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
        while (number <= n){
                if (state != 2){
                    conditionC.await();
                }else {
                    printNumber.accept(number++);
                    conditionA.signal();
                    state = 0;
                }
            }
        }finally {
            lock.unlock();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (number <= n ){
            lock.lock();
            try{
                if (state != 1){
                    conditionB.await();
                }else {
                    printNumber.accept(number++);
                    conditionA.signal();
                    state = 0;
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ZeroEvenOdd demo = new ZeroEvenOdd(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.zero(new IntConsumerImpl());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.odd(new IntConsumerImpl());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.even(new IntConsumerImpl());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
/**
 * 执行用时：9 ms, 在所有 Java 提交中击败了81.78% 的用户
 * 内存消耗：37.3 MB, 在所有 Java 提交中击败了93.89% 的用户
 *   private int n;
 *
 *     private volatile int start = 1;
 *
 *     private volatile int state;
 *     private Lock lock = new ReentrantLock();
 *     private Condition zero = lock.newCondition();
 *     private Condition even = lock.newCondition();
 *     private Condition odd = lock.newCondition();
 *
 *     public ZeroEvenOdd(int n) {
 *         this.n = n;
 *     }
 *
 *     // printNumber.accept(x) outputs "x", where x is an integer.
 *     public void zero(IntConsumer printNumber) throws InterruptedException {
 *         lock.lock();
 *         try {
 *             while (start <= n) {
 *                 if (state != 0) {
 *                     zero.await();
 *                 }
 *                 printNumber.accept(0);
 *                 if (start % 2 == 0) {
 *                     state = 2;
 *                     even.signal();
 *                 } else {
 *                     state = 1;
 *                     odd.signal();
 *                 }
 *                 zero.await();
 *             }
 *             odd.signal();
 *             even.signal();
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 *
 *     //偶数
 *     public void even(IntConsumer printNumber) throws InterruptedException {
 *         lock.lock();
 *         try {
 *             while (start <= n) {
 *                 if (state != 2) {
 *                     even.await();
 *                 } else {
 *                     printNumber.accept(start++);
 *                     state = 0;
 *                     zero.signal();
 *                 }
 *             }
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 *
 *     //基数
 *     public void odd(IntConsumer printNumber) throws InterruptedException {
 *         lock.lock();
 *         try {
 *             while (start <= n) {
 *                 if (state != 1) {
 *                     odd.await();
 *                 } else {
 *                     printNumber.accept(start++);
 *                     state = 0;
 *                     zero.signal();
 *                 }
 *             }
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 *
 * 作者：a-fei-8
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd/solution/chang-you-duo-xian-cheng-zhi-da-yin-ling-qy3o/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 **/