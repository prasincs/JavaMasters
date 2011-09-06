package eu.javaspecialists.course.master.io.solution313;

import junit.framework.*;

import java.io.*;
import java.util.*;

public class CorrectnessTest extends TestCase {
    private final Random rand = new Random(0);

    public void testWritingRandomValue() throws ClassNotFoundException, IOException {
        testWriteAndRead(new ComplexClass(rand.nextInt(), rand.nextLong(),
                Integer.toString(rand.nextInt()), rand.nextBoolean(),
                rand.nextDouble(), rand.nextFloat()));
    }

    public void testNullValues() throws ClassNotFoundException, IOException {
        testWriteAndRead(new ComplexClass(rand.nextInt(), rand.nextLong(), null,
                rand.nextBoolean(), null, rand.nextFloat()));
    }

    public void testNaNValues() throws ClassNotFoundException, IOException {
        testWriteAndRead(new ComplexClass(rand.nextInt(), rand.nextLong(), "hello",
                rand.nextBoolean(), Double.NaN, rand.nextFloat()));
    }

    private static void testWriteAndRead(ComplexClass cc1) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(cc1);
        out.close();
        ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(bout
                .toByteArray()));
        ComplexClass cc2 = (ComplexClass) oin.readObject();
        assertEquals(cc1, cc2);
    }
}