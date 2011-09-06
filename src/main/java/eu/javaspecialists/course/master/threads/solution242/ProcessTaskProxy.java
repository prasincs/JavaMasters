package eu.javaspecialists.course.master.threads.solution242;

import java.util.*;

public class ProcessTaskProxy {
    public void printRCEKey() {
        HashMap map = (HashMap) ThreadLocalContextHolder.get();
        System.out.println("Thread Name : " +
                Thread.currentThread().getName() + "\t TL Value : " +
                map.get(Thread.currentThread().getName()));
        ThreadLocalContextHolder.cleanupThread();
    }
}
