package eu.javaspecialists.course.master.datastructs.solution622d;

import java.io.*;

public class GenerateLargeFile {
    public static final String FILE_NAME = "data.txt";

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new BufferedOutputStream(new FileOutputStream(FILE_NAME))));
        for (int i = 0; i < 1000 * 1000 * 1000 / 4; i++) {
            if (i % 100 * 1000 == 99999) {
                System.out.println("writing " + i);
            }
            out.printf("%08d Some Interesting Data%n", i);
        }
        out.close();
    }
}
