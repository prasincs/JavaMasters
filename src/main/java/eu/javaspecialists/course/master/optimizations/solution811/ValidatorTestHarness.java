package eu.javaspecialists.course.master.optimizations.solution811;

import eu.javaspecialists.course.master.optimizations.exercise811.*;
import eu.javaspecialists.course.master.util.*;

import java.io.*;

// run with -server and -Xmx500m
public class ValidatorTestHarness {
    public static final int REPEATS = 5;
    public static final int DATA_POINTS = 10;

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        Validator[] validators = {
                new ValidatorImpl()
        };
        CorrectnessTest.check(validators);
        String[][] data = {readData("dataset1.dat"),
                readData("dataset2.dat"),
                readData("dataset3.dat")};
        for (Validator val : validators) {
            System.out.println(val.getClass().getSimpleName());
            System.out.println(val.getClass().getSimpleName().replaceAll(".", "="));
            System.out.println("Dataset1 Count\tDataset1 Time\tDataset2 Count"
                    + "\tDataset2 Time\tDataset3 Count\tDataset3 Time");
            for (int i = 0; i < DATA_POINTS; i++) {
                test(val, data);
            }
        }
    }

    private static void test(Validator val, String[][] data) throws IOException,
            ClassNotFoundException {
        test(val, data[0]);
        System.out.print("\t");
        System.out.flush();
        test(val, data[1]);
        System.out.print("\t");
        System.out.flush();
        test(val, data[2]);
        System.out.println();
    }

    private static void test(Validator val, String[] dataset) throws IOException,
            ClassNotFoundException {
        Benchmark mbm = new Benchmark();
        mbm.start();

        int truecount = 0;
        for (int i = 0; i < REPEATS; i++) {
            truecount = 0;
            for (String s : dataset) {
                if (val.checkInteger(s))
                    truecount++;
            }
        }

        mbm.stop();
        System.out.printf("%d\t%s", truecount, mbm);
    }

    private static String[] readData(String dataset) throws IOException,
            ClassNotFoundException {
        System.out.println("Reading in " + dataset);
        String[] data = new String[GenerateData.ROWS_IN_DATASET];
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(dataset)));
        String s;
        int i = 0;
        while ((s = (String) in.readObject()) != null) {
            data[i++] = s;
            if (i % 100000 == 0) {
                System.out.println("row " + i);
            }
        }
        in.close();
        return data;
    }
}