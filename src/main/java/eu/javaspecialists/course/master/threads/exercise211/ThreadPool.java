package eu.javaspecialists.course.master.threads.exercise211;

import sun.jvm.hotspot.runtime.Threads;

import java.security.PrivateKey;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {
    // Create a thread group field
    // Create a Lock field (ReentrantLock)
    // Create a Condition field
    // Create a LinkedList field containing Runnable
    private ThreadGroup group = new ThreadGroup("ThreadPool");
    private ReentrantLock lock = new ReentrantLock();
    private Condition workHasArrived = lock.newCondition();

    private LinkedList<Runnable> workQueue = new LinkedList<Runnable>();
    private volatile boolean running = true;

    public ThreadPool(int poolSize) {
        // create several Worker threads in the thread group
        for (int i = 0; i < poolSize; i++){
            Worker worker = new Worker(group, "worker-"+i);
            worker.start();
        }
    }

    private Runnable take() throws InterruptedException {
        // lock the lock.
        // if the LinkedList is empty, we await on the condition
        //
        // remove the first job from the LinkedList and return it
        if (Thread.interrupted())
            throw new InterruptedException();
        lock.lock();
        Runnable runnable = null;
        while (workQueue.isEmpty()){
            workHasArrived.await();
        }

        try {
            runnable = workQueue.removeFirst();
        } finally {
            lock.unlock();
        }
        return runnable;
        //throw new UnsupportedOperationException("not implemented");
    }

    public void submit(Runnable job) throws InterruptedException{
         if (Thread.interrupted())
            throw new InterruptedException();
        // lock the lock.
        // Add the job to the LinkedList and signal the condition
        lock.lock();
        //System.out.println("Job submitted");
        try {
            workQueue.add(job);
            workHasArrived.signal();
        } finally {
            lock.unlock();
        }


    }

    public int getRunQueueLength() {
        // return the length of the LinkedList
        // remember to also lock the lock!
        lock.lock();
        int length = 0;
        try{
            length = workQueue.size();
        }finally {
            lock.unlock();
        }
        return length;
        //throw new UnsupportedOperationException("not implemented");
    }

    public void shutdown() throws InterruptedException {
        // this should stop the ThreadGroup
        if (Thread.interrupted())
            throw new InterruptedException();
        running = false;
       group.interrupt();
       //group.stop();

    }


    private class Worker extends Thread {
        private volatile boolean running = true;
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {

            // we run in an infinite loop:
            // remove the next job from the linked list using take()
            // we then call the run() method on the job
            System.out.println("Running "+ running);
            while (running) {
                try {
                    //System.out.println("running worker");
                    Runnable job = take();
                    job.run();        // Alien code
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    //break;
                    //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

                }
            }

        }


    }
}