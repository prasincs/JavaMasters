package eu.javaspecialists.course.master.datastructs.solution622b;

import java.io.*;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        System.out.println("Windows test file:");
        System.out.println("------------------");
        TextFile tfw = new TextFile(
                "src/eu/javaspecialists/course/master/datastructs/solution622b/"
                        + "TestFileWindows");
        for (String line : tfw) {
            System.out.println("> " + line);
        }

        System.out.println("Unix test file:");
        System.out.println("---------------");
        TextFile tfu = new TextFile(
                "src/eu/javaspecialists/course/master/datastructs/solution622b/"
                        + "TestFileUnix");
        for (String line : tfu) {
            System.out.println("> " + line);
        }
    }
}