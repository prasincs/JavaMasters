package eu.javaspecialists.course.master.reflection.exercise531;

import java.lang.reflect.Array;
import java.util.*;

public class MyArrayList<T> extends ArrayList<T> {
    private final Class<T> clazz;

    public MyArrayList(Class<T> clazz) {
        this.clazz = clazz;
        //throw new UnsupportedOperationException("TODO");
    }

    public T[] toArray() {
        T[] values = (T[])Array.newInstance(clazz, size());
        return super.toArray(values);
        //throw new UnsupportedOperationException("TODO");
    }
}
