package eu.javaspecialists.course.master.memory.solution441;

import java.util.*;

public class IntegerObjectPool {
    private final Map<String, Integer> pool = new HashMap<String, Integer>();

    public Integer getValue(String s) {
        synchronized (pool) {
            Integer val = pool.get(s);
            if (val == null) {
                pool.put(s, val = new Integer(s));
            }
            return val;
        }
    }
}