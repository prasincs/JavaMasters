package eu.javaspecialists.course.master.datastructs.exercise621;

import java.util.*;
import java.util.concurrent.*;

public class ListPerformanceTest {
    private final static boolean COPY_ON_WRITE = true;

    public static void testList(CopyOnWriteArrayList<String> list) {
        for (String s : list) {
        }
    }

    public static void testList(ArrayList<String> list) {
        synchronized (list) {
            for (String s : list) {
            }
        }
    }

    static class Worker extends Thread{
        List<String> list = null;

        Worker(List<String> list){
            this.list = list;
        }

        public void run(){
            for (int i = 0; i < 1000000; i++)
                testList((CopyOnWriteArrayList<String>)this.list);
        }
    }


    public static void main(String... args) throws InterruptedException {
        // create an initial collection with 100 values
        List<String> collection = new ArrayList<String>();
        for (int i = 0; i < 100; i++)
            collection.add(i+"");
        // use your thread pool to create 10 threads

        Worker[] threads = new Worker[10];
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10; i++){
            threads[i] = new Worker(new CopyOnWriteArrayList<String>(collection));
            threads[i].start();
        }
        //ExecutorService exec = Executors.newFixedThreadPool(10);


        // test either the copy on write list or the array list, iterating
        // 1000000 times in a thread safe manner

        // Once all of the threads are finished, measure the time it took
        time = System.currentTimeMillis() - time;
        System.out.println(time);
        // Shut down the pool
    }
}
