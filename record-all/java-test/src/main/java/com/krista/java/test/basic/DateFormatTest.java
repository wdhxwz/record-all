package com.krista.java.test.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * DateFormatTest 日期格式化测试
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/26 10:59
 */
public class DateFormatTest {
    public static void main(String[] args) {
        // 创建无大小限制的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            DateFormatTask task = new DateFormatTask();
            // 将任务提交到线程池
            Future<?> future = threadPool.submit(task);
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException | InterruptedException ex) {
                // 运行时如果出现异常则进入 catch 块
                System.err.println("执行时出现异常：" + ex.getMessage());
            }
        }

        threadPool.shutdown();

    }

    static class DateFormatTask implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            String str = DateFormatWrapperWithThreadLocal.format(
                    DateFormatWrapperWithThreadLocal.parse("2017-07-17 16:54:54"));
            System.out.printf("Thread(%s) -> %s\n", Thread.currentThread().getName(), str);

            return null;
        }

    }
}
