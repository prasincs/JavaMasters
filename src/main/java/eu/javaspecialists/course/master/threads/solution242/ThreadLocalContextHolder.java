package eu.javaspecialists.course.master.threads.solution242;

import java.util.*;

public class ThreadLocalContextHolder {
    private static final ThreadLocal THREAD_WITH_TCONTEXT =
            new ThreadLocal();

    private ThreadLocalContextHolder() {
    }

    public static void put(Map key) {
        if (THREAD_WITH_TCONTEXT.get() == null) {
            THREAD_WITH_TCONTEXT.set(key);
        }
        THREAD_WITH_TCONTEXT.get();
    }

    public static Object get() {
        return THREAD_WITH_TCONTEXT.get();
    }

    public static void cleanupThread() {
        THREAD_WITH_TCONTEXT.remove();
    }
}
