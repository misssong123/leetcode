package com.meng.dupthread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz {
    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.92% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了99.06% 的用户
     */
    private int n;
    private volatile int number = 1;
    private volatile int state = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    Condition conditionD = lock.newCondition();
    public FizzBuzz(int n) {
        this.n = n;

    }
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (number <= n){
            lock.lock();
            try{
                if (state != 1){
                    conditionB.await();
                }else {
                    printFizz.run();
                    number++;
                    change();
                    if (state == 0){
                        conditionA.signal();
                    }else if (state == 2){
                        conditionA.signal();
                        conditionC.signal();
                    }else if(state == 3){
                        conditionA.signal();
                        conditionD.signal();
                    }
                }
            }finally {
                lock.unlock();
            }
        }
    }
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (number <= n){
            lock.lock();
            try{
                if (state != 2){
                    conditionC.await();
                }else {
                    printBuzz.run();
                    number++;
                    change();
                    if (state == 1){
                        conditionA.signal();
                        conditionB.signal();
                    }else if (state == 0){
                        conditionA.signal();
                    }else if(state == 3){
                        conditionA.signal();
                        conditionD.signal();
                    }
                }
            }finally {
                lock.unlock();
            }
        }
    }
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (number <= n){
            lock.lock();
            try{
                if (state != 3){
                    conditionD.await();
                }else {
                    printFizzBuzz.run();
                    number++;
                    change();
                    if (state == 1){
                        conditionA.signal();
                        conditionB.signal();
                    }else if (state == 2){
                        conditionA.signal();
                        conditionC.signal();
                    }else if(state == 0){
                        conditionA.signal();
                    }
                }
            }finally {
                lock.unlock();
            }
        }
    }
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
            while (number <= n){
                if (state != 0){
                    conditionA.await();
                }else {
                    printNumber.accept(number++);
                    change();
                    if (state == 1){
                        conditionB.signal();
                    }else if (state == 2){
                        conditionC.signal();
                    }else if(state == 3){
                        conditionD.signal();
                    }
                }
            }
            conditionB.signal();
            conditionC.signal();
            conditionD.signal();
        }finally {
            lock.unlock();
        }
    }
    private void change(){
        if (number % 15 == 0){
            state = 3;
        }else if (number % 5 == 0){
            state = 2;
        }else if (number % 3 == 0){
            state = 1;
        }else {
            state = 0;
        }
    }

    public static void main(String[] args) {
        FizzBuzz demo = new FizzBuzz(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.number(new IntConsumerImpl());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.fizz(this::run);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.buzz(this::run);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.fizzbuzz(this::run);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();;
    }
}
/**
 * 执行用时：8 ms, 在所有 Java 提交中击败了87.42% 的用户
 * 内存消耗：38 MB, 在所有 Java 提交中击败了98.81% 的用户
class FizzBuzz {
    private int n;

    private Semaphore number = new Semaphore(1);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);


    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            number.acquire();
            if (i % 3 != 0 && i % 5 != 0) {//开始打印
                printNumber.accept(i);
                number.release();
            } else if (i % 3 == 0 && i % 5 != 0) {//fizz开始打印
                fizz.release();
            } else if (i % 3 != 0 && i % 5 == 0) {//buzz开始打印
                buzz.release();
            } else {
                fizzbuzz.release();//fizzbuzz开始打印
            }
        }
    }
}

作者：a-fei-8
        链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded/solution/chang-you-duo-xian-cheng-zhi-jiao-ti-da-eeurc/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 **/
/**
 class FizzBuzz {
 private int n;
 private ReentrantLock lock = new ReentrantLock();
 int state = 0;
 private Condition condition = lock.newCondition();

 public FizzBuzz(int n) {
 this.n = n;
 }

 // printFizz.run() outputs "fizz".
 public void fizz(Runnable printFizz) throws InterruptedException {
 for (int i = 3; i <= n; i += 3) {
 try {
 if (i % 3 == 0 && i % 5 == 0) continue;
 lock.lock();
 while (state != 3) {
 condition.await();
 }
 printFizz.run();
 state = 0;
 condition.signalAll();
 } finally {
 lock.unlock();
 }
 }
 }

 // printBuzz.run() outputs "buzz".
 public void buzz(Runnable printBuzz) throws InterruptedException {
 for (int i = 5; i <= n; i += 5) {
 try {
 if (i % 3 == 0 && i % 5 == 0) continue;
 lock.lock();
 while (state != 5) {
 condition.await();
 }
 printBuzz.run();
 state = 0;
 condition.signalAll();
 } finally {
 lock.unlock();
 }
 }
 }

 // printFizzBuzz.run() outputs "fizzbuzz".
 public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
 for (int i = 15; i <= n; i += 15) {
 try {
 lock.lock();
 while (state != 15) {
 condition.await();
 }
 printFizzBuzz.run();
 state = 0;
 condition.signalAll();
 } finally {
 lock.unlock();
 }
 }
 }

 // printNumber.accept(x) outputs "x", where x is an integer.
 public void number(IntConsumer printNumber) throws InterruptedException {
 for (int i = 1; i <= n; i++) {
 try {
 lock.lock();
 while (state != 0) {
 condition.await();
 }
 if (i % 3 != 0 && i % 5 != 0) {
 printNumber.accept(i);
 } else {
 if (i % 3 == 0 && i % 5 == 0) state = 15;
 else if (i % 3 == 0) state = 3;
 else if (i % 5 == 0) state = 5;
 condition.signalAll();
 }
 } finally {
 lock.unlock();
 }
 }
 }
 }

 作者：a-fei-8
 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded/solution/chang-you-duo-xian-cheng-zhi-jiao-ti-da-eeurc/
 来源：力扣（LeetCode）
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 **/