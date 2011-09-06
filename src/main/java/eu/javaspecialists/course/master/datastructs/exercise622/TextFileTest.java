package eu.javaspecialists.course.master.datastructs.exercise622;

import java.io.*;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        TextFile tf = new TextFile(
                "src/eu/javaspecialists/course/master/datastructs/exercise622/"
                        + "TextFile.java");
        for (String line : tf) {
            System.out.println("> " + line);
        }
    }
}
