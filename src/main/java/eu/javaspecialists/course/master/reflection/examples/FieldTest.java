package eu.javaspecialists.course.master.reflection.examples;

import java.lang.reflect.*;

public class FieldTest {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println("Integer accessible fields");
        for (Field field : Integer.class.getFields()) {
            System.out.println("\t" + field);
        }

        System.out.println("\nInteger all fields");
        for (Field field : Integer.class.getDeclaredFields()) {
            System.out.println("\t" + field);
        }

        System.out.println("\nString private field value");
        Field field = String.class.getDeclaredField("value");
        System.out.println("\t" + field);
    }
}
