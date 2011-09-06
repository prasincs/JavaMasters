package eu.javaspecialists.course.master.io.solution313d;

import eu.javaspecialists.course.master.io.exercise313.*;
import eu.javaspecialists.course.master.util.*;

import java.io.*;
import java.util.*;

/**
 * Run this with the following JVM switches:
 * <p/>
 * -server -Xmx256m
 */
public class SerializeTest {
    public static void main(String... args) throws Exception {
        ComplexClass[] objects = new ComplexClass[100000];
        Random rand = new Random(0);
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new ComplexClass(rand.nextInt(), rand.nextLong(), Integer
                    .toString(rand.nextInt()), rand.nextBoolean(), rand.nextDouble(),
                    rand.nextFloat());
        }

        for (int i = 0; i < 30; i++) {
            test(objects);
        }
    }

    private static void test(ComplexClass[] objects) throws IOException {
        Benchmark mbm = new Benchmark();
        mbm.start();
        for (int j = 0; j < 10; j++) {
            DataOutputStream dout = new DataOutputStream(new NullOutputStream());
            for (ComplexClass object : objects) {
                object.writeRaw(dout);
            }
            dout.close();
        }
        mbm.stop();
        System.out.println(mbm);
    }
}