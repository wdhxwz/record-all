package com.krista.java.test.multithread;

import com.krista.java.test.threadpool.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatchDemo
 *
 * <p>
 * CountDownLatch:倒计时器，是多线程并发控制中非常有用的工具类，它可以控制线程等待，直到倒计时器归0再继续执行。
 * </p>
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/3 17:25
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            com.krista.extend.utils.ThreadPoolUtil.addTask(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        // 等待多久时间后不管倒计时器有没有归0主线程继续往下执行
        countDownLatch.await(400, TimeUnit.MILLISECONDS);
        countDownLatch.await();
        System.out.println(countDownLatch.getCount());
        System.out.println(System.currentTimeMillis() - start);
    }
}
