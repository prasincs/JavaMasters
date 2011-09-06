package eu.javaspecialists.course.master.reflection.examples;

import java.lang.reflect.*;

public class GetClassTest {
    public static void main(String[] args) {
        System.out.println("String has these methods:");
        for (Method method : "hello".getClass().getMethods()) {
            System.out.println("\t" + method);
        }
    }
}
