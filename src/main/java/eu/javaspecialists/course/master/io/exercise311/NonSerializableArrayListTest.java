package eu.javaspecialists.course.master.io.exercise311;

import eu.javaspecialists.course.master.io.exercise313.*;
import junit.framework.*;

import java.io.*;

public class NonSerializableArrayListTest extends TestCase {
    public void test() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new NullOutputStream()
        );
        try {
            out.writeObject(new NonSerializableArrayList<Object>());
            fail();
        } catch (NotSerializableException success) {
        } finally {
            out.close();
        }
    }
}
