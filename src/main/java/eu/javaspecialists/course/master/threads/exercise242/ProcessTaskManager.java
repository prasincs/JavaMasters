package eu.javaspecialists.course.master.threads.exercise242;

import java.util.*;

public class ProcessTaskManager implements Runnable {
    private long rce_key = 0L;
    final ProcessTaskProxy ptp = new ProcessTaskProxy();

    public void run() {
            HashMap map = new HashMap();
            map.put(Thread.currentThread().getName(), "V-" + Thread.currentThread().getName());
            ThreadLocalContextHolder.put(map);
            ptp.printRCEKey();
    }
}
