package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class ThreadDeadLock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<?> header;
            Future<?> footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将发生死锁, 因为子任务在等待子任务的结果
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            return "test";
        }
    }

    private class LoadFileTask implements Runnable {
        public LoadFileTask(String s) {
        }

        @Override
        public void run() {
        }
    }
}
