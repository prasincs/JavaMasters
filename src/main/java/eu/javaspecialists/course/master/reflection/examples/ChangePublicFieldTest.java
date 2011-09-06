package eu.javaspecialists.course.master.reflection.examples;

import java.lang.reflect.*;

public class ChangePublicFieldTest {
    public static void main(String[] args) throws NoSuchFieldException,
            IllegalAccessException {
        BasicIntClass bic = new BasicIntClass();
        Field count = BasicIntClass.class.getField("count");
        System.out.println(count.get(bic));
        count.set(bic, 42);
        System.out.println(bic.count);
    }
}
