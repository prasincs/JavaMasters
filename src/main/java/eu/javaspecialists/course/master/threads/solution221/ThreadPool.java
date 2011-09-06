package eu.javaspecialists.course.master.threads.solution221;

import java.util.*;
import java.util.concurrent.locks.*;

// solution211 #2 - getting better ...
public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition runQueueNotEmpty = lock.newCondition();
    private final LinkedList<Runnable> runQueue = new LinkedList<Runnable>();
    private volatile boolean running = true;

    public ThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "Worker" + i);
            worker.start();
        }
    }

    public void submit(Runnable job) {
        enqueue(job);
    }

    private void enqueue(Runnable job) {
        lock.lock();
        try {
            runQueue.addLast(job);
            runQueueNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private Runnable take() throws InterruptedException {
        lock.lock();
        try {
            while (runQueue.isEmpty()) {
                runQueueNotEmpty.await();
            }
            return runQueue.removeFirst();
        } finally {
            lock.unlock();
        }
    }

    public int getRunQueueLength() {
        lock.lock();
        try {
            return runQueue.size();
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        running = false;
        group.interrupt();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            while (running && !Thread.currentThread().isInterrupted()) {
                try {
                    take().run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
