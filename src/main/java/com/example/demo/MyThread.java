package com.example.demo;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class MyThread extends Thread {
    public void run() {
        System.out.println("MyThread is running");
    }

    // Thread 线程类
    // 两种方式创建线程：
    // 1. extends Thread
    // *     class PrimeThread extends Thread {
    // *         long minPrime;
    // *         PrimeThread(long minPrime) {
    // *             this.minPrime = minPrime;
    // *         }
    // *
    // *         public void run() {
    // *             // compute primes larger than minPrime
    // *             &nbsp;.&nbsp;.&nbsp;.
    // *         }
    // *     }
    // *     PrimeThread p = new PrimeThread(143);
    // *     p.start();

    // 2. implements Runnable
    // *     class PrimeRun implements Runnable {
    // *         long minPrime;
    // *         PrimeRun(long minPrime) {
    // *             this.minPrime = minPrime;
    // *         }
    // *
    //         *         public void run() {
    // *             // compute primes larger than minPrime
    // *             &nbsp;.&nbsp;.&nbsp;.
    // *         }
    // *     }
    // *     PrimeRun p = new PrimeRun(143);
    // *     new Thread(p).start();

    // 线程类几个常用的方法

    // run()方法，不用多说，启动当前线程；

    // sleep()方法，让当前线程休息指定的时间，休息过程中不释放线程占有的锁

    // yield()方法，提示调度器当前线程愿意放弃处理器的执行权，给其他线程点儿机会，
    // 当然，调度器可以不理会这个提示; 这个方法的应用场景很少，常常用于debug，重现竞争条件，复现问题；

    // interrupt()方法，用于中断线程或者查询线程是否已经被中断

    // wait()方法，

    // join()方法，
}
