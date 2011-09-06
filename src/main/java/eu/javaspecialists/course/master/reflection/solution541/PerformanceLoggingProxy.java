package eu.javaspecialists.course.master.reflection.solution541;

import java.lang.reflect.*;

public class PerformanceLoggingProxy {
    public static <T> T wrap(Class<T> intf, final T obj) {
        return intf.cast(Proxy.newProxyInstance(
                intf.getClassLoader(),
                new Class<?>[]{intf},
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        long time = System.nanoTime();
                        try {
                            return method.invoke(obj, args);
                        } finally {
                            time = System.nanoTime() - time;
                            System.out.println(time + "\t" + method);
                        }
                    }
                })
        );
    }
}
