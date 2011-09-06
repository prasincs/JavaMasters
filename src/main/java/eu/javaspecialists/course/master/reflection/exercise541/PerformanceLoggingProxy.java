package eu.javaspecialists.course.master.reflection.exercise541;

public class PerformanceLoggingProxy {
    public static <T> T wrap(Class<T> intf, final T obj) {
        // make a new dynamic proxy instance with an invocation handler that
        // does:
        // long time = System.nanoTime();
        // try {
        // return method.invoke(obj, args);
        // } finally {
        // time = System.nanoTime() - time;
        // System.out.println(time + "\t" + method);
        // }
        throw new UnsupportedOperationException("todo");
    }
}
