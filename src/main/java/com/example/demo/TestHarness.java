package com.example.demo;

import java.util.concurrent.CountDownLatch;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class TestHarness {
    // Latch 闭锁，用于测试比较线程数量与时间效率的关系
    // 使用"启动门"(Starting Gate)和"结束门"（Ending Gate）
    // 启动门使得所有线程同时启动，而不是创建线程后立即启动线程，这样就防止先创建的线程"领先"后创建的线程
    // 结束门能够等待最后一个线程结束，而不是顺序等待所有线程结束（注意区别，如果最后一个线程不是最后结束的，那么两个数字是不同的）

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                }  catch (InterruptedException ignored) {
                }
            });
            t.start();
        }

        long start = System.nanoTime();
        // 让所有线程启动
        startGate.countDown();
        // 等待所有线程结束
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        TestHarness testHarness = new TestHarness();

        long time1 = testHarness.timeTasks(10, () -> System.err.println("Hello, World"));
        System.out.println("10 threads: " + time1 + " nanoseconds");

        long time2 = testHarness.timeTasks(100, () -> System.err.println("Hello, World"));
        System.out.println("100 threads: " + time2 + " nanoseconds");

        long time3 = testHarness.timeTasks(1000, () -> System.err.println("Hello, World"));
        System.out.println("100 threads: " + time3 + " nanoseconds");

        // 注意，这里多线程，每个线程打印一次hello，world，其实没有反映出多线程合作的时间优势；
        // 可以选择更合适的例子，用来比较同一个任务，多线程时间和单线程时间
    }
}
