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

    // wait()方法，让当前线程等待，直到其他线程调用notify()或者notifyAll()方法; 当前线程必须拥有这个对象的监视器（锁）
    // 线程释放监视器，直到其他线程调用notify()或者notifyAll()方法；
    // 注意，wait()方法必须在循环中调用, 否则无法重新执行...
    // synchronized (obj) {
    // while (condition does not hold)
    //     obj.wait();
    //     // Perform action appropriate to condition
    // }

    // join()方法，等待指定时间，让当前线程死掉...
    // 大多数平台类库的可阻塞方法，都定义了限时版本和不限时版本，例如：
    // Thread.join() 限时
    // Thread.wait() 不限时
    // BlockingQueue.put() 不限时，等待直到队列可以插入元素
    // BlockingQueue.offer() 限时，超时则放弃
    // CountDownLatch.await() 有两种签名，带参数的是限时版本，不带参数的是不限时版本
    // Selector.select()
}