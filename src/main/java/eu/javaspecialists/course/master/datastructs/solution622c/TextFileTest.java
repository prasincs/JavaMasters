package eu.javaspecialists.course.master.datastructs.solution622c;

import junit.framework.*;

import java.io.*;
import java.util.*;

public class TextFileTest extends TestCase {
    private static final String TEST_FILE_NAME = "testFile.txt";
    private final List<String> names = new ArrayList<String>();
    private File file;

    public void setUp() throws IOException {
        file = new File(TEST_FILE_NAME);

        names.clear();
        names.add("John");
        names.add("Anton");
        names.add("Dirk");
        names.add("Heinz");

        PrintWriter out = new PrintWriter(file);
        for (String name : names) {
            out.println(name);
        }
        out.close();
    }

    public void tearDown() {
        file.delete();
    }

    public void testRemove() throws IOException {
        int i = 0;
        TextFile tf = new TextFile(TEST_FILE_NAME);

        for (String name : tf) {
            System.out.println("::: " + name);
            assertEquals(names.get(i++), name);
        }

        // testing remove()
        for (Iterator<String> it = tf.iterator(); it.hasNext();) {
            String name = it.next();
            if ("Dirk".equals(name)) {
                it.remove();
                System.out.println("removing dirk");
            }
        }

        names.remove("Dirk");

        i = 0;
        for (String name : tf) {
            System.out.println("::: " + name);
            assertEquals(names.get(i++), name);
        }
    }

    public void testConcurrentModificationException() throws IOException {
        TextFile tf = new TextFile(TEST_FILE_NAME);

        Iterator<String> broken = tf.iterator();

        for (Iterator<String> it = tf.iterator(); it.hasNext();) {
            String name = it.next();
            if ("Dirk".equals(name)) {
                it.remove();
            }
        }
        try {
            broken.next();
            fail("Expected a ConcurrentModificationException");
        } catch (ConcurrentModificationException success) {
        }
    }

    public void testBadRemove() throws IOException {
        TextFile tf = new TextFile(TEST_FILE_NAME);
        Iterator<String> it = tf.iterator();
        try {
            it.remove();
            fail("Expected an IllegalStateException");
        } catch (IllegalStateException success) {

        }
    }

    public void testFirstRemove() throws IOException {
        int i = 0;
        TextFile tf = new TextFile(TEST_FILE_NAME);

        // testing remove() on first element
        Iterator<String> it = tf.iterator();
        it.next();
        it.remove();

        names.remove("John");

        i = 0;
        for (String name : tf) {
            System.out.println("::: " + name);
            assertEquals(names.get(i++), name);
        }
    }

    public void testLastRemove() throws IOException {
        int i = 0;
        TextFile tf = new TextFile(TEST_FILE_NAME);

        // testing remove() on first element
        Iterator<String> it = tf.iterator();
        while (it.hasNext()) {
            it.next();
        }
        it.remove();

        names.remove("Heinz");

        i = 0;
        for (String name : tf) {
            System.out.println("::: " + name);
            assertEquals(names.get(i++), name);
        }
    }

    public void testAllRemove() throws IOException {
        TextFile tf = new TextFile(TEST_FILE_NAME);

        // testing remove() on first element
        Iterator<String> it = tf.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }

        it = tf.iterator();
        assertFalse(it.hasNext());
    }
}