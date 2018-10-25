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
        ExecutorService singleThreadPool = ThreadPoolUtil.newFixedThreadPool("test-pool-%d", 4,4196);

        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) singleThreadPool;
        System.out.println("ActiveCount:" + threadPoolExecutor.getActiveCount());
        System.out.println("CompletedTaskCount:" + threadPoolExecutor.getCompletedTaskCount());
        System.out.println("CorePoolSize:" + threadPoolExecutor.getCorePoolSize());
        System.out.println("LargestPoolSize:" + threadPoolExecutor.getLargestPoolSize());
        System.out.println("MaximumPoolSize:" + threadPoolExecutor.getMaximumPoolSize());
        System.out.println("PoolSize:" + threadPoolExecutor.getPoolSize());
        System.out.println("Queue:" + threadPoolExecutor.getQueue());
        System.out.println("TaskCount:" + threadPoolExecutor.getTaskCount());

        singleThreadPool.shutdown();
    }
}
