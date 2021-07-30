package com.meng.dupthread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1115. 交替打印FooBar
 *
 * 我们提供一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 *
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 *
 * 示例 2:
 *
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 */
public class FooBar {
    /**
     * 执行用时：21 ms, 在所有 Java 提交中击败了93.93% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了77.73% 的用户
     */
    private int n;
    private volatile  int number = 1;
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    public FooBar(int n) {
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (number != 1) {
                    conditionA.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                number++;
                conditionB.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
            while (number != 2){
                conditionB.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            number--;
            conditionA.signal();
            }finally {
                lock.unlock();
            }
        }
    }
}
/**
 * 执行用时：20 ms, 在所有 Java 提交中击败了99.39% 的用户
 * 内存消耗：38.8 MB, 在所有 Java 提交中击败了14.11% 的用户
 * class FooBar {
 *             private int n;
 *
 *             private Semaphore fooSema = new Semaphore(1);
 *             private Semaphore barSema = new Semaphore(0);
 *
 *             public FooBar(int n) {
 *                 this.n = n;
 *             }
 *
 *             public void foo(Runnable printFoo) throws InterruptedException {
 *
 *                 for (int i = 0; i < n; i++) {
 *                     fooSema.acquire();//值为1的时候，能拿到，执行下面的操作
 *                     printFoo.run();
 *                     barSema.release();//释放许可给barSema这个信号量 barSema 的值+1
 *                 }
 *             }
 *
 *             public void bar(Runnable printBar) throws InterruptedException {
 *
 *                 for (int i = 0; i < n; i++) {
 *                     barSema.acquire();//值为1的时候，能拿到，执行下面的操作
 *                     printBar.run();
 *                     fooSema.release();//释放许可给fooSema这个信号量 fooSema 的值+1
 *                 }
 *             }
 *         }
 *
 * 作者：a-fei-8
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately/solution/chang-you-duo-xian-cheng-zhi-1115-by-a-f-mf5u/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
