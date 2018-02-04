package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class SynContainer {
    // 快照策略的并发列表容器，ArrayList的并发容器版本
    // 每次修改容器的时候都会复制底层的数组，这是相当大的开销；
    // 因此，仅当遍历操作远远多于修改操作时才应该使用CopyOnWriteArrayList
    List<Integer> myList = new CopyOnWriteArrayList<>();

    // HashMap的并发容器版本
    // 内容有点多，看了一个小时的源码文档...
    // 核心的内容大概是：ConcurrentHashMap利用一种粒度更细的加锁机制(分段锁)来实现更大程度的共享；
    // 这种加锁机制下，
    // 1. 任意数量的读取线程可以并发的访问Map
    // 2. 读取操作的线程和写入操作的线程可以并发访问Map
    // 3. 一定数量的写入线程可以并发修改Map
    // 从而，并发环境下支持更高的吞吐量，而单线程只损失非常小的性能

    // ConcurrentHashMap返回的迭代器具有"弱一致性"，而不是"及时失败"
    // 弱一致性的迭代器可以容忍并发修改，支持遍历已有的元素，但不保证迭代器构造后的修改能反映到容器
    Map<Integer, Integer> myMap = new ConcurrentHashMap<>();

    // SortedMap的并发容器版本
    Map<Integer, Integer> myMap2 = new ConcurrentSkipListMap<>();

    // 广泛用于"生产者-消费者"模型的阻塞队列
    // 如果生产者速率低于消费者速率，消费者就一直等待；
    // 如果生产者速率高于消费者速率，队列已满，生产者将不能继续生产；
    // 在构建高可靠应用程序时，有界队列就可以让程序在超负荷过载的时候变得更加健壮
    Queue<Integer> myQueue = new ArrayBlockingQueue<>(10);

    // 生产者-消费者模型的设计，还可以参考Executor任务执行框架
    // Executor是执行提交的Runnable任务的对象；Executor接口给出了一种解耦任务提交和任务执行的方式;
    // Executor通常用于避免显式的创建线程, 例如 new Thread(new(RunnableTask())).start();
    // 可以写成
    // Executor executor = <em>anExecutor</em>;
    // executor.execute(new RunnableTask1());
    // executor.execute(new RunnableTask2());
    // ...
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
    Executor executor = new ThreadPoolExecutor(10, 100, 1000L, TimeUnit.SECONDS, workQueue);


    // 重入锁
    class X {
        private final ReentrantLock lock = new ReentrantLock();
        public void m() {
            lock.lock();  // block until condition holds
            try {
                // ... method body
            } finally {
                lock.unlock();
            }
        }
    }
    // TODO: 闭锁Latch， FutureTask
}
