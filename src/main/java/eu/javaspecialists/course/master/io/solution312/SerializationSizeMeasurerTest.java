package eu.javaspecialists.course.master.io.solution312;

import junit.framework.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class SerializationSizeMeasurerTest extends TestCase {
    public void testHashMap() throws IOException {
        assertEquals(78, SerializationSizeMeasurer
                .sizeOf(new HashMap<String, String>()));
        assertEquals(163, SerializationSizeMeasurer.sizeOf(new Vector<String>()));
        assertEquals(5816, SerializationSizeMeasurer.sizeOf(new JFrame()));
    }
}
