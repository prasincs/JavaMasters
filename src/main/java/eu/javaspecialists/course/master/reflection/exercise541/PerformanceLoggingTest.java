package eu.javaspecialists.course.master.reflection.exercise541;

import java.util.*;

public class PerformanceLoggingTest {
    public static void main(String... args) {
        Map<String, String> map = PerformanceLoggingProxy.wrap(Map.class,
                new HashMap());
        map.put("hello", "world");
        map.clear();
        if (map.isEmpty()) {
            System.out.println(map);
        }
    }
}
