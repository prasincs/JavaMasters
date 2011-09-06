package eu.javaspecialists.course.master.datastructs.solution622d;

import java.io.*;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        TextFile tf = new TextFile(GenerateLargeFile.FILE_NAME);

        long linesRead = 0;

        for (String line : tf) {
            linesRead++;
            if (linesRead % 100000 == 0) {
                System.out.println("linesRead = " + linesRead);
                System.out.println(line);
                System.out.println(linesRead * line.length());
            }
        }
    }
}