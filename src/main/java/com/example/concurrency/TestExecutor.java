package com.example.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class TestExecutor {
    // Executor提供了一些工厂方法
    Executor executor1 = Executors.newFixedThreadPool(8);
    Executor executor2 = Executors.newCachedThreadPool();

    // Executor生命周期管理，shutdown方法用于拒绝新来的任务，shutdownNow方法用来停止现在进行中的任务
    // ExecutorService
    void shutdownAndAwaitTermination(ExecutorService pool) {
        // 禁止提交新的任务
        pool.shutdown();
        try {
            // 等待一段指定的时间
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 取消正在执行中的任务
                pool.shutdownNow();
                // 等待一段时间，让任务响应被取消信号
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // 如果当前线程也被取消，再次取消进行中的线程
            pool.shutdownNow();
            // 保留中断信号flag
            Thread.currentThread().interrupt();
        }
    }
    // 常用方法：submit, invokeAll, awaitTermination
    // 输入元素内容是Callable<T>的容器，返回元素内容是Future<T>的列表
    // <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
    // long timeout, TimeUnit unit) throws InterruptedException;
}
