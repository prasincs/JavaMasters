package eu.javaspecialists.course.master.io.solution311;

import java.io.*;
import java.util.*;

public class NonSerializableArrayList<T> extends ArrayList<T> {
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new NotSerializableException("This ArrayList cannot be serialized");
    }

    // other option is to do the following:
    // private final Object donotserialize = new Object();
}
