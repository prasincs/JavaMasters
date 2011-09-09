package eu.javaspecialists.course.master.optimizations.exercise811;

import eu.javaspecialists.course.master.util.*;

import java.io.*;

public class ValidatorTestHarness {
    public static final int REPEATS = 1;
    public static final int DATA_POINTS = 10;

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        Validator[] validators = {
                new ValidatorImpl(),
                new NewValidatorImpl(),
                // put your own Validator here
        };
        // CorrectnessTest.check(validators);
        for (Validator val : validators) {
            System.out.println(val.getClass().getSimpleName());
            System.out.println(val.getClass().getSimpleName().replaceAll(".", "="));
            System.out.println("Dataset1 Count\tDataset1 Time\tDataset2 Count"
                    + "\tDataset2 Time\tDataset3 Count\tDataset3 Time");
            for (int i = 0; i < DATA_POINTS; i++) {
                test(val);
            }
        }
    }

    private static void test(Validator val) throws IOException,
            ClassNotFoundException {
        test(val, "dataset1.dat");
        System.out.print("\t");
        System.out.flush();
        test(val, "dataset2.dat");
        System.out.print("\t");
        System.out.flush();
        test(val, "dataset3.dat");
        System.out.println();
    }

    private static void test(Validator val, String dataset) throws IOException,
            ClassNotFoundException {
        Benchmark mbm = new Benchmark();
        mbm.start();

        int truecount = 0;
        for (int i = 0; i < REPEATS; i++) {
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(dataset)));
            truecount = 0;
            String s;
            while ((s = (String) in.readObject()) != null) {
                if (val.checkInteger(s))
                    truecount++;
            }
            in.close();
        }
        mbm.stop();

        System.out.printf("%d\t%s", truecount, mbm);
    }
}