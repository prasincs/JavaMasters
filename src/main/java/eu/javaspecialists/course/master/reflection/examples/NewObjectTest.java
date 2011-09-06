package eu.javaspecialists.course.master.reflection.examples;

import java.util.*;

public class NewObjectTest {
    public static void main(String[] args) throws IllegalAccessException,
            InstantiationException {
        Collection names = ArrayList.class.newInstance();
        names.add("Heinz");
        names.add("Anesti");
        System.out.println(names);
    }
}
