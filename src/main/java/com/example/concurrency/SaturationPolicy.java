package com.example.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class SaturationPolicy {

    private static int N_THREADS = 8;
    private static int CAPACITY = 1024;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(N_THREADS, N_THREADS, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(CAPACITY));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // Caller-Runs 策略
        // 将任务退给调用者，由调用者（主线程）执行任务，从而执行任务过程中主线程不能再接收新的任务；
        // 主线程阻塞 => TCP层队列满，丢包 => 服务降级
    }
}
