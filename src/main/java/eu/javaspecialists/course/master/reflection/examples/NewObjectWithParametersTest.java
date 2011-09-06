package eu.javaspecialists.course.master.reflection.examples;

import java.lang.reflect.*;

public class NewObjectWithParametersTest {
    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor<String> newString = String.class.getConstructor(String.class);
        String name = newString.newInstance("Heinz");
        System.out.println("name = " + name);
    }
}
