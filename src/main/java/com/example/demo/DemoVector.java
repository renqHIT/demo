package com.example.demo;

import java.util.Iterator;
import java.util.Vector;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class DemoVector {

    public static void main(String[] args) {

        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }
        // 多线程环境下可能抛出ArrayIndexOutOfBoundsException, 因为vector可能在当前线程遍历的同时被其他线程删除元素；
        for (int i = 0; i < vector.size(); i++) {
            doSomething(vector.get(i));
        }
        // 使用迭代器访问，多线程环境会抛出ConcurrentModificationException, 一种fail-fast行为, 及早发现vector在遍历中size被修改；
        for (Integer v : vector) {
            doSomething(v);
        }
        // 单线程情况下，当对象直接从vector中删除，而没有通过调用Iterator.remove实现时，也会引起ConcurrentModificationException
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) > 3) {
                System.out.println("unsafe remove: " + vector.get(i));
                vector.remove(vector.get(i));
            }
        }
        // 正确的remove姿势
        for (Iterator<Integer> iterator = vector.iterator(); iterator.hasNext(); ) {
            Integer current = iterator.next();
            if (current > 6) {
                System.out.println("safe remove: " + current);
                iterator.remove();
            }
        }
        // 要想避免越界异常，可以对vector加锁，独占vector
        synchronized (vector) {
            for (Integer v : vector) {
                doSomething(v);
            }
        }

        Thread thread = new Thread(() -> System.out.println("My thread is running"));
        thread.start();
    }

    private static void doSomething(Integer integer) {
        System.out.println("do something with " + integer);
    }

}
