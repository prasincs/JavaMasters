package eu.javaspecialists.course.master.reflection.solution531;

import java.lang.reflect.*;
import java.util.*;

public class MyArrayList<T> extends ArrayList<T> {
    private final Class<T> clazz;

    public MyArrayList(Class<T> clazz, int initialCapacity) {
        super(initialCapacity);
        this.clazz = clazz;
    }

    public MyArrayList(Class<T> clazz) {
        this.clazz = clazz;
    }

    public MyArrayList(Class<T> clazz, Collection<? extends T> c) {
        super(c);
        this.clazz = clazz;
    }

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] values = (T[]) Array.newInstance(clazz, size());
        return super.toArray(values);
    }
}
