package com.krista.java.test.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * ThreadPoolApp
 *
 * @author dw_wangdonghong
 * @date 2018/10/18 17:34
 */
public class ThreadPoolApp {

    public static void main(String[] args) {
        ExecutorService singleThreadPool = ThreadPoolUtil.newFixedThreadPool("test-pool-%d", 4);

        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));

    }
}
