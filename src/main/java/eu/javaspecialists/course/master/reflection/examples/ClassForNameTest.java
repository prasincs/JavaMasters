package eu.javaspecialists.course.master.reflection.examples;

import java.lang.reflect.*;

public class ClassForNameTest {
    public static void main(String[] args) {
        for (String class_name : args) {
            try {
                Class<?> c = Class.forName(class_name);
                System.out.println(c.getSimpleName());
                for (Field field : c.getDeclaredFields())
                    System.out.println(field);
            } catch (ClassNotFoundException e) {
                System.err.println("No class " + class_name);
            }
        }
    }
}
