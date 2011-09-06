package eu.javaspecialists.course.master.threads.solution211;

import java.util.*;
import java.util.concurrent.locks.*;

// solution211 #1 - not perfect yet ...
public class ThreadPool {
    // Create a thread group field
    private final ThreadGroup group = new ThreadGroup("threadpool");
    // Create a Lock field (ReentrantLock)
    private final Lock lock = new ReentrantLock();
    // Create a Condition field
    private final Condition emptyRunQueue = lock.newCondition();
    // Create a LinkedList field containing Runnable
    private final LinkedList<Runnable> jobs = new LinkedList<Runnable>();

    public ThreadPool(int poolSize) {
        // create several Worker threads in the thread group
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "worker-" + i);
            worker.start();
        }
    }

    public void submit(Runnable job) {
        // lock the lock.
        lock.lock();
        try {
            // Add the job to the LinkedList and signal the condition
            jobs.add(job);
            emptyRunQueue.signal();
        } finally {
            lock.unlock();
        }
    }

    private Runnable take() throws InterruptedException {
        // lock the lock.
        lock.lock();
        try {
            // if the LinkedList is empty, we await on the condition
            while (jobs.isEmpty()) {
                emptyRunQueue.await();
            }
            // remove the first job from the LinkedList and return it
            return jobs.removeFirst();
        } finally {
            lock.unlock();
        }
    }

    public int getRunQueueLength() {
        // remember to also lock the lock!
        lock.lock();
        try {
            // return the length of the LinkedList
            return jobs.size();
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        // this should stop the ThreadGroup
        group.stop();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            // we run in an infinite loop:
            while (true) {
                try {
                    // remove the next job from the linked list using take()
                    Runnable job = take();
                    // we then call the run() method on the job
                    job.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
