package com.meng.dupthread;

import java.util.function.IntConsumer;

public class IntConsumerImpl implements IntConsumer {
    @Override
    public void accept(int value) {
        System.out.println(Thread.currentThread().getName()+":"+value);
    }
}
