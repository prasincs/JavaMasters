package eu.javaspecialists.course.master.io.solution312;

import java.io.*;

public class SerializationSizeMeasurer {
    public static int sizeOf(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        int size = baos.size();
        oos.writeObject(o);
        oos.flush();
        size = baos.size() - size;
        return size;
    }
}
